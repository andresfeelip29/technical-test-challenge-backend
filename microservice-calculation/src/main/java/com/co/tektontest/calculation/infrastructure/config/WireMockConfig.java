package com.co.tektontest.calculation.infrastructure.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class configuration type for external service emulation.
 *
 * @author andres on 2025/05/08.
 * @version 1.0.0
 */
@Configuration
public class WireMockConfig {
    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer() {
        return new WireMockServer(WireMockConfiguration.options().port(9003));
    }
}
