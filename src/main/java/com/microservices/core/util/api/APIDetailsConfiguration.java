package com.microservices.core.util.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "api.common")
public class APIDetailsConfiguration {

    private String termsOfService;
    private String license;
    private String licenseUrl;
    private String externalDocDesc;
    private String externalDocUrl;
    private Contact contact;
}
