package pl.com.web.shop.domain.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "http-problem")
public class SpringProblemProperties implements ProblemProperties {
    private boolean stackTraceEnabled;
}
