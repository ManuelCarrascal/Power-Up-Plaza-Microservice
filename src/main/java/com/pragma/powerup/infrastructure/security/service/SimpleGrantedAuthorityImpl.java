package com.pragma.powerup.infrastructure.security.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
public class SimpleGrantedAuthorityImpl implements GrantedAuthority {
    private String authority;

    @JsonCreator
    public SimpleGrantedAuthorityImpl(@JsonProperty("authority") String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}