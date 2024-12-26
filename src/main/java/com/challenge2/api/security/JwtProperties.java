package com.challenge2.api.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("security.jwt")
@Configuration
@Getter @Setter
public class JwtProperties {
    private String secret;
}
