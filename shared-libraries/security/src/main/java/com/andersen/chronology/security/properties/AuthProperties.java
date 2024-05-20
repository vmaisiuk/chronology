package com.andersen.chronology.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.andersen.chronology.auth")
public class AuthProperties {

    private String accountDetailsPath;
}
