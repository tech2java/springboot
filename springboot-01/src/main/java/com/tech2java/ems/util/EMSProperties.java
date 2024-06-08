package com.tech2java.ems.util;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Component("emsProperties")
@Data
@ConfigurationProperties(prefix = "ems")
@PropertySource("classpath:message.properties")
@Validated
public class EMSProperties {

    @Size(min = 5,max = 10,message = "Address should be between 5 chars and 10 characters")
    private String address;

    private Map<String,String> contact;

    private List<String> branches;



}
