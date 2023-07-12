package com.geekbrains.shop.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@PropertySource(value = "classpath:secret.properties")
@Data
public class JwtSecretParams {
    private String secret;
    private Integer lifetime;
}

