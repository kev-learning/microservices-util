package com.microservices.core.util.api.tracing;

import io.micrometer.common.KeyValue;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationFilter;
import org.springframework.boot.info.BuildProperties;

import java.util.Optional;

public class BuildInfoObservationFilter implements ObservationFilter {


    private BuildProperties buildProperties;

    public BuildInfoObservationFilter(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Override
    public Observation.Context map(final Observation.Context context) {
        String version = Optional.ofNullable(buildProperties.getVersion()).orElse("");
        KeyValue buildVersion = KeyValue.of("build.version", version);

        return context.addLowCardinalityKeyValue(buildVersion);
    }
}
