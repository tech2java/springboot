package com.tech2java.ems.util;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources(
        {
         @PropertySource(value = "classpath:test1.properties" ,ignoreResourceNotFound = true),
         @PropertySource("classpath:test.properties")
        }
)
public class AppConfig {
}
