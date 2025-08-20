package com.microservices.core.util.api.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.logging.Level;

@Slf4j
public class ServiceHealth {

    public static Mono<Health> getHealth(String url) {
        url = url.concat("/actuator/health");
        log.debug("Calling health API for : {}", url);

        return WebClient.builder().build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(status -> new Health.Builder().up().build())
                .onErrorResume(ex -> Mono.just(new Health.Builder().down(ex).build()))
                .log(log.getName(), Level.FINE);
    }
}
