package com.andersen.chronology.trips.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.unsplash")
public class PhotoProperties {

    private String url;
    private String accessKey;
}
