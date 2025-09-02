package com.microservices.core.util.api.tracing;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationRegistryCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ObservationRegistryConfig implements ObservationRegistryCustomizer<ObservationRegistry> {

    @Autowired
    private BuildProperties buildProperties;

    @Override
    public void customize(ObservationRegistry registry) {
        registry.observationConfig().observationFilter(new BuildInfoObservationFilter(buildProperties));
    }
}
