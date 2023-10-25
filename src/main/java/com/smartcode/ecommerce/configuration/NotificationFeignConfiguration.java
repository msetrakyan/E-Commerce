package com.smartcode.ecommerce.configuration;


import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
@EnableFeignClients
public class NotificationFeignConfiguration {

    @Value("${notification-username}")
    private String username;

    @Value("${notification-password}")
    private String password;

    @Bean
    public RequestInterceptor basicAuthRequestInterceptorToNotificator() {
        return requestTemplate -> {
            String authHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
            requestTemplate.header("Authorization", authHeader);
        };
    }

}
