package com.co.tektontest.calculation.infrastructure.shared.mapper;

import com.co.tektontest.calculation.infrastructure.adapter.dto.HistoryCallDataCommand;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Mapping class for the send data to history.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Component
public class SendDataCommandMappter {


    public HistoryCallDataCommand dataToHistoryCallDataCommand(String pathEndpoint,
                                                               String methodParameters, String response) {
        return new HistoryCallDataCommand(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                , pathEndpoint, methodParameters, response);
    }
}
