package pl.com.web.shop.domain.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.com.web.shop.domain.ShopDomainServiceApplicationBootstrap;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

@Configuration
@EntityScan(basePackageClasses = ShopDomainServiceApplicationBootstrap.class)
@EnableJpaRepositories(basePackageClasses = ShopDomainServiceApplicationBootstrap.class)
public class EntityConfig {

    @Bean
    @Primary
    public JPAQueryFactory jpaQueryFactory(@NotNull EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
