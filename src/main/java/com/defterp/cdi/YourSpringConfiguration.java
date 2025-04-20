package com.defterp.cdi;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = YourSpringConfiguration.BEANS_PACKAGE,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = jakarta.inject.Named.class)
)
public class YourSpringConfiguration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    public static final String BEANS_PACKAGE = "com.defterp";

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {

    }

    // ...
}
