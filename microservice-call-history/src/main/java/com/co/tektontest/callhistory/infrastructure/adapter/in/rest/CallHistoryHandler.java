package com.co.tektontest.callhistory.infrastructure.adapter.in.rest;


import com.co.tektontest.callhistory.domain.model.CallHistoryCommand;
import com.co.tektontest.callhistory.domain.port.in.CallHistoryPort;
import com.co.tektontest.callhistory.infrastructure.shared.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Handler Call History.
 *
 * @author andres on 2025/05/08.
 * @version 1.0.0
 */
@Slf4j
@Component
public class CallHistoryHandler {

    private final CallHistoryPort callHistoryPort;

    private final Validator validator;

    public CallHistoryHandler(CallHistoryPort callHistoryPort, Validator validator) {
        this.callHistoryPort = callHistoryPort;
        this.validator = validator;
    }

    /**
     * {@code GET /} : Request calculate data
     * <p>
     * Endpoint to calculate the percentage of two numbers.
     *
     * @param request fThe request has the following paging parameters.
     * @return {@link ServerResponse Page<CallHistoryResponse>} paid list of calls made.
     */
    public Mono<ServerResponse> getAllHistoryData(ServerRequest request) {

        Optional<String> pageParam = request.queryParam("page");
        Optional<String> sizeParam = request.queryParam("size");
        Optional<String> sortParam = request.queryParam("sort");
        Optional<String> directionParam = request.queryParam("direction");


        if (pageParam.isPresent() && sizeParam.isPresent() &&
                sortParam.isPresent() && directionParam.isPresent()) {

            int page = Integer.parseInt(pageParam.get());
            int size = Integer.parseInt(sizeParam.get());

            Sort sortOrder = directionParam.get().equalsIgnoreCase("desc")
                    ? Sort.by(sortParam.get()).descending()
                    : Sort.by(sortParam.get()).ascending();

            Pageable pageable = PageRequest.of(page, size, sortOrder);

            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(this.callHistoryPort.getAllHistoryData(pageable), Page.class);
        }
        return ServerResponse.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(
                        new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Parametros incompletos")));
    }

    /**
     * {@code POST /} : Request calculate data
     * <p>
     * Endpoint to calculate the percentage of two numbers.
     *
     * @param request fThe request has the following data domain.
     * @return {@link ServerResponse } paid list of calls made.
     */
    public Mono<ServerResponse> saveTransaction(ServerRequest request) {

        Mono<CallHistoryCommand> commandMono = request.bodyToMono(CallHistoryCommand.class);

        return commandMono.flatMap(command -> {
            Errors errors = new BeanPropertyBindingResult(command, CallHistoryCommand.class.getName());
            this.validator.validate(command, errors);
            if (errors.hasErrors()) {
                return this.fieldValidate(errors);
            }
            this.callHistoryPort.saveCallHistory(command);
            return ServerResponse.noContent().build();
        });
    }

    private Mono<ServerResponse> fieldValidate(Errors errors) {
        Map<String, Object> result = new HashMap<>();
        return Flux.fromIterable(errors.getFieldErrors())
                .map(fieldError -> "Campo ".concat(fieldError.getField()).concat(" ").concat(Objects.requireNonNull(fieldError.getDefaultMessage())))
                .collectList()
                .flatMap(list -> {
                    result.put("status", HttpStatus.PRECONDITION_FAILED.value());
                    result.put("messages", list);
                    return ServerResponse.status(HttpStatus.PRECONDITION_FAILED)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(result));
                });
    }

}
