package com.lhuang.blog.user.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("jwt.email")
@Data
@Component
public class JWTConfig {

    private String secret;
    private long expire;
}
