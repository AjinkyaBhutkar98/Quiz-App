package com.api.gateway.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){

        httpSecurity.cors(Customizer.withDefaults())
                .csrf(ServerHttpSecurity.CsrfSpec::disable).
                authorizeExchange(exchange ->exchange
                        .anyExchange()
                        .authenticated())
                .oauth2ResourceServer( config -> config.jwt(jwt -> Customizer.withDefaults()));

        return httpSecurity.build();
    }
}
