package com.co.tektontest.callhistory.infrastructure.config;

import com.co.tektontest.callhistory.infrastructure.adapter.in.rest.CallHistoryHandler;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Config router functional
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Configuration
public class RouterFunctionConfig {

    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }

    @Bean
    public RouterFunction<ServerResponse> routes(CallHistoryHandler handler) {
        return RouterFunctions.route(RequestPredicates.GET("/api/v1/history"), handler::getAllHistoryData)
                .andRoute(RequestPredicates.POST("/api/v1/history"), handler::saveTransaction);
    }
}
