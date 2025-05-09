package com.co.tektontest.calculation.infrastructure.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for asynchronous webclient requests.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder registerWebClient() {
        return WebClient.builder();
    }

}
