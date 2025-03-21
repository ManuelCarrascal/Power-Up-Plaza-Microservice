package com.pragma.powerup.infrastructure.feign;

import feign.Client;
import feign.Logger;
import feign.RequestInterceptor;
import feign.httpclient.ApacheHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private final FeignClientInterceptor feignClientInterceptor;

    public FeignConfig(FeignClientInterceptor feignClientInterceptor) {
        this.feignClientInterceptor = feignClientInterceptor;
    }

    @Bean
    public Client feignClient() {
        return new ApacheHttpClient();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return feignClientInterceptor;
    }
}