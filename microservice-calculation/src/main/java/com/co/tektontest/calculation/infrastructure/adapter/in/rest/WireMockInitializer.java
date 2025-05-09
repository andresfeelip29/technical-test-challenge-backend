package com.co.tektontest.calculation.infrastructure.adapter.in.rest;


import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Class to emulate the request for data support.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Component
public class WireMockInitializer {

    private final WireMockServer wireMockServer;

    public WireMockInitializer(WireMockServer wireMockServer) {
        this.wireMockServer = wireMockServer;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setupStubs() {
        wireMockServer.stubFor(get(urlEqualTo("/api/v1/support"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("3.2")));
    }


}
