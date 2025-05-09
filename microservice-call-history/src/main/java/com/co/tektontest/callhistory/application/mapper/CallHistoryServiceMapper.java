package com.co.tektontest.callhistory.application.mapper;


import com.co.tektontest.callhistory.domain.model.CallHistoryCommand;
import com.co.tektontest.callhistory.domain.model.CallHistoryResponse;
import com.co.tektontest.callhistory.infrastructure.entity.CallHistoryEntity;
import org.springframework.stereotype.Component;

/**
 * Mapping class for converter call history entity to response model.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Component
public class CallHistoryServiceMapper {

    public CallHistoryResponse callHistoryEntityToCallHistoryResponse(CallHistoryEntity callHistoryEntity) {

        return CallHistoryResponse.builder()
                .id(callHistoryEntity.getId())
                .createAt(callHistoryEntity.getCreateAt())
                .pathEndpoint(callHistoryEntity.getPathEndpoint())
                .methodParameters(callHistoryEntity.getMethodParameters())
                .response(callHistoryEntity.getResponse())
                .build();
    }

    public CallHistoryEntity callHistoryCommandToCallHistoryEntity(CallHistoryCommand callHistoryCommand){

        return CallHistoryEntity.builder()
                .createAt(callHistoryCommand.createAt())
                .pathEndpoint(callHistoryCommand.pathEndpoint())
                .methodParameters(callHistoryCommand.methodParameters())
                .response(callHistoryCommand.response())
                .build();
    }
}
