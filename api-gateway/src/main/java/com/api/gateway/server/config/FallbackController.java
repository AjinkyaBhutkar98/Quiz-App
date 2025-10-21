package com.api.gateway.server.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class FallbackController {

    @GetMapping("/categoryFallback")
    public Mono<String> categoryFallback(){

        return Mono.just("category service is down!! please try again later... from circuit breaker");

    }
}
