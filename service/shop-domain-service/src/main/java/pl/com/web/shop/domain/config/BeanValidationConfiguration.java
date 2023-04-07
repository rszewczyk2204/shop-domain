package pl.com.web.shop.domain.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.spi.nodenameprovider.JavaBeanProperty;
import org.hibernate.validator.spi.nodenameprovider.Property;
import org.hibernate.validator.spi.nodenameprovider.PropertyNodeNameProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class BeanValidationConfiguration {
    private final ObjectMapper objectMapper;

    public LocalValidatorFactoryBean validator(@NotNull AutowireCapableBeanFactory beanFactory) {
        var factory = new CustomLocalValidatorFactoryBean(objectMapper);
        factory.setMessageInterpolator(new ResourceBundleMessageInterpolator());
        factory.setConstraintValidatorFactory(new SpringConstraintValidatorFactory(beanFactory));
        return factory;
    }

    @RequiredArgsConstructor
    public static class CustomLocalValidatorFactoryBean extends LocalValidatorFactoryBean {
        private final ObjectMapper objectMapper;
        private ExecutableValidator executableValidator;

        @Override
        protected void postProcessConfiguration(javax.validation.Configuration<?> configuration) {
            var hibernateValidatorConfiguration = (HibernateValidatorConfiguration) configuration;
            hibernateValidatorConfiguration.propertyNodeNameProvider(new JacksonPropertyNodeNameProvider(objectMapper));
        }

        @Override
        public ExecutableValidator forExecutables() {
            if (Objects.isNull(executableValidator)) {
                this.executableValidator = new UnproxyingExecutableValidator(super.forExecutables());
            }
            return executableValidator;
        }
    }

    @RequiredArgsConstructor
    public static class UnproxyingExecutableValidator implements ExecutableValidator {
        private final ExecutableValidator executableValidator;

        @Override
        public <T> Set<ConstraintViolation<T>> validateParameters(T object, Method method, Object[] parameterValues, Class<?>... groups) {
            unproxyParameters(parameterValues);
            return executableValidator.validateParameters(object, method, parameterValues, groups);
        }

        @Override
        public <T> Set<ConstraintViolation<T>> validateReturnValue(T t, Method method, Object o, Class<?>... classes) {
            return executableValidator.validateReturnValue(t, method, o, classes);
        }

        @Override
        public <T> Set<ConstraintViolation<T>> validateConstructorParameters(Constructor<? extends T> constructor, Object[] objects, Class<?>... classes) {
            return executableValidator.validateConstructorParameters(constructor, objects, classes);
        }

        @Override
        public <T> Set<ConstraintViolation<T>> validateConstructorReturnValue(Constructor<? extends T> constructor, T t, Class<?>... classes) {
            return executableValidator.validateConstructorReturnValue(constructor, t, classes);
        }

        private void unproxyParameters(@NotNull Object[] parameterValues) {
            for (int i = 0; i < parameterValues.length; ++i) {
                var parameterValue = parameterValues[i];
                if (parameterValue instanceof HibernateProxy) {
                    parameterValues[i] = Hibernate.unproxy(parameterValue);
                }
            }
        }
    }

    @RequiredArgsConstructor
    private static class JacksonPropertyNodeNameProvider implements PropertyNodeNameProvider {
        private final ObjectMapper objectMapper;

        @Override
        public String getName(Property property) {
            if (property instanceof JavaBeanProperty) {
                return getJavaBeanPropertyName((JavaBeanProperty) property);
            }

            return getDefaultName(property);
        }

        private String getJavaBeanPropertyName(@NotNull JavaBeanProperty property) {
            var type = objectMapper.constructType(property.getDeclaringClass());
            var desc = objectMapper.getSerializationConfig().introspect(type);

            return desc.findProperties().stream()
                    .filter(prop -> prop.getInternalName().equals(property.getName()))
                    .map(BeanPropertyDefinition::getName)
                    .findFirst()
                    .orElseGet(property::getName);
        }

        private String getDefaultName(@NotNull Property property) {
            return property.getName();
        }
    }

    @ConditionalOnWebApplication
    @Configuration
    public class ValidationMessageConfiguration implements WebMvcConfigurer {
        @Autowired
        private AutowireCapableBeanFactory beanFactory;

        @Override
        public Validator getValidator() {
            return validator(beanFactory);
        }
    }
}
