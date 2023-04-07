package pl.com.web.shop.domain.config;

import com.fasterxml.jackson.databind.Module;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.cloud.openfeign.support.SortJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.web.shop.domain.ShopDomainServiceApplicationBootstrap;

@Configuration
@EntityScan(basePackageClasses = ShopDomainServiceApplicationBootstrap.class)
public class Config {

    @Bean
    public Module pageJacksonModule() {
        return new PageJacksonModule();
    }

    @Bean
    public Module sortJacksonModule() {
        return new SortJacksonModule();
    }
}
