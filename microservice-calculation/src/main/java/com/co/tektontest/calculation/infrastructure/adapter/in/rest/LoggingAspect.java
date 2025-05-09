package com.co.tektontest.calculation.infrastructure.adapter.in.rest;

import com.co.tektontest.calculation.domain.port.out.ExternalServicePort;
import com.co.tektontest.calculation.infrastructure.adapter.dto.HistoryCallDataCommand;
import com.co.tektontest.calculation.infrastructure.shared.mapper.SendDataCommandMappter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class for response interception at endpoint .
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    private final SendDataCommandMappter sendDataCommandMappter;

    private final ExternalServicePort externalServicePort;


    public LoggingAspect(SendDataCommandMappter sendDataCommandMappter, ExternalServicePort externalServicePort) {
        this.sendDataCommandMappter = sendDataCommandMappter;
        this.externalServicePort = externalServicePort;
    }

    @Around("execution(* com.co.tektontest.calculation.infrastructure.adapter.in.rest.CalculatePercentageController..*(..))")
    public Object interceptarEndpoint(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Se intercepta controlador!");

        Object resultExecution = null;

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String methodParameters = getMethodParameters(joinPoint.getArgs());

        resultExecution = joinPoint.proceed();

        String pathEndpoint = getPathEndpoint(servletRequestAttributes);
        String responseSever = getResponseStatusEndpoint(servletRequestAttributes);

        HistoryCallDataCommand historyCallDataCommand =
                this.sendDataCommandMappter.dataToHistoryCallDataCommand(pathEndpoint, methodParameters, responseSever);

        this.externalServicePort.sendExternalCallHistory(historyCallDataCommand);

        return resultExecution;
    }

    private String getMethodParameters(Object[] argumentos) {
        return Arrays.stream(argumentos)
                .map(arg -> arg != null ? arg.toString() : "null")
                .collect(Collectors.joining(", "));
    }

    private String getPathEndpoint(ServletRequestAttributes servletRequestAttributes) {
        if (!Objects.isNull(servletRequestAttributes)) {
            return servletRequestAttributes.getRequest().getRequestURI();
        }
        return "NA";
    }

    private String getResponseStatusEndpoint(ServletRequestAttributes servletRequestAttributes) {
        String result = "";

        if (!Objects.isNull(servletRequestAttributes)) {
            try {
                HttpServletResponse httpServletResponse = servletRequestAttributes.getResponse();
                result = getResponseStatusName(httpServletResponse.getStatus());
            } catch (NullPointerException e) {
                return "NA";
            }
        }
        return result;
    }

    private String getResponseStatusName(Integer statusCode) {
        String statusName = HttpStatus.resolve(statusCode).name();
        return String.valueOf(statusCode)
                .concat(" - ")
                .concat(statusName);
    }
}
