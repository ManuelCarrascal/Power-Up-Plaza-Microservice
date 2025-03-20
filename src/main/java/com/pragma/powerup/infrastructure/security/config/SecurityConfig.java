package com.pragma.powerup.infrastructure.security.config;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Bean
    public static RequestInterceptor requestInterceptor() {
        return requestTemplate -> {

        };
    }


}
