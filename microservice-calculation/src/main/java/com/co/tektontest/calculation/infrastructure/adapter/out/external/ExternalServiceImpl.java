package com.co.tektontest.calculation.infrastructure.adapter.out.external;

import com.co.tektontest.calculation.domain.port.out.ExternalServicePort;
import com.co.tektontest.calculation.infrastructure.adapter.dto.HistoryCallDataCommand;
import com.co.tektontest.calculation.infrastructure.shared.enums.ExceptionMessage;
import com.co.tektontest.calculation.infrastructure.shared.exception.ErrorRequestExternalServiceException;
import com.co.tektontest.calculation.infrastructure.shared.exception.PercentegaExternalRquestNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


/**
 * Implementation of the port for the consultation of the external service of the percentage.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Slf4j
@Component
public class ExternalServiceImpl implements ExternalServicePort {

    private final WebClient.Builder webClient;

    @Value("${external-request.microservice-data-support}")
    private String urlMicroserviceDataSupport;

    @Value("${external-request.microservice-callhistoy}")
    private String urlMicroserviceCallHistory;

    public ExternalServiceImpl(WebClient.Builder webClient) {
        this.webClient = webClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getExternalPercentage() {

        log.info("Se realiza solicitud de consulta para obtener el porcentaje en el servicio externo!");

       return this.webClient.build().get()
                .uri(urlMicroserviceDataSupport.concat("/support"))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Double.class)
                .onErrorResume(WebClientRequestException.class, e -> !e.getMessage().isEmpty() ? Mono.error(new ErrorRequestExternalServiceException(
                        ExceptionMessage.ERROR_REQUEST_EXTERNAL_REQUEST.getMessage())) : Mono.empty())
                .onErrorResume(WebClientResponseException.class, e -> e.getStatusCode().is4xxClientError() ? Mono.error(new ErrorRequestExternalServiceException(
                        ExceptionMessage.ERROR_REQUEST_EXTERNAL_REQUEST.getMessage())) : Mono.empty())
                .switchIfEmpty(Mono.error(new PercentegaExternalRquestNotFoundException(
                        ExceptionMessage.PERCENTAGE_NOT_FOUND_EXTERNAL_REQUEST.getMessage())))
                .block();

    }

    @Override
    @Async
    public void sendExternalCallHistory(HistoryCallDataCommand callDataCommand)  {

        log.info("Se realiza solicitud de registro en servicio externo para historial de llamadas!");

        this.webClient.build().post()
                .uri(urlMicroserviceCallHistory.concat("/history"))
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(callDataCommand)
                .retrieve()
                .toBodilessEntity()
                .subscribeOn(Schedulers.boundedElastic())
                .toFuture();
        log.info("Termina peticion asincrona!");
    }
}
