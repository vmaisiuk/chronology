package com.andersen.chronology.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.andersen.chronology.jwt")
public class JwtProperties {

    private String secretKey;
    private long jwtExpiration;
}
