package com.tech2java.rest_consumer.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProjectConfig {


    /*@Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("","");
    }*/


    @Bean
    public RestTemplate restTemplate(){
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        return restTemplateBuilder/*.basicAuthentication("","")*/.build();
    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder().
                /*filter(ExchangeFilterFunctions.basicAuthentication("","")).*/
                build();
    }
}
