package pl.com.web.shop.domain.common;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@Configuration
@Import({
        GeneralAdvice.class,
        RoutingAdvice.class,
        SpringProblemProperties.class
})
public class ProblemAdviceAutoConfiguration {
}
