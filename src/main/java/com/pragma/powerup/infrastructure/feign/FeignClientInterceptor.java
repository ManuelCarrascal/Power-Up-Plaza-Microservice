package com.pragma.powerup.infrastructure.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class FeignClientInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && !authHeader.isEmpty()) {
                requestTemplate.header("Authorization", authHeader);
            }
        }
    }
}