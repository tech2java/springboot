package com.tech2java.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@SpringBootConfiguration
//@ComponentScan
//@EnableAutoConfiguration

@EnableJpaRepositories("com.tech2java.ems.repository")
@EntityScan("com.tech2java.ems.entity")
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
public class EMSApplication {

    public static void main(String[] args) {

        SpringApplication.run(EMSApplication.class,args);

    }
}
