package com.co.tektontest.calculation.infrastructure.adapter.in.rest;

import com.co.tektontest.calculation.application.dto.NumbersDataCommand;
import com.co.tektontest.calculation.domain.model.CalculationResult;
import com.co.tektontest.calculation.domain.port.in.CalculatePercentagePort;
import com.co.tektontest.calculation.infrastructure.shared.exception.GlobalExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
@ContextConfiguration(classes = CalculatePercentageController.class)
public class CalculatePercentageControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatePercentagePort calculatePercentagePort;

    @Autowired
    private ObjectMapper objectMapper;


    private CalculationResult calculationResult;

    private static final String BASE_URL = "/api/v1/calculate";

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

        var calculatePercentageController = new CalculatePercentageController(calculatePercentagePort);

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        var converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);

        mockMvc = MockMvcBuilders.standaloneSetup(calculatePercentageController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setMessageConverters(converter)
                .build();

        calculationResult = new CalculationResult(2.3, 2.5, 3.0, 10.0);
    }

    @Test
    void testRequestcalculatePercentage() throws Exception {

        when(this.calculatePercentagePort.calculate(any(NumbersDataCommand.class))).thenReturn(Optional.of(this.calculationResult));

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders
                        .post(BASE_URL.concat("/"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("firstData", "2.3")
                        .param("secondData", "2.5"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.result").exists())
                .andReturn();

    }

}
