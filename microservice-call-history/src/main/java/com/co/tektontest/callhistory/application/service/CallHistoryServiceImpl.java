package com.co.tektontest.callhistory.application.service;

import com.co.tektontest.callhistory.application.mapper.CallHistoryServiceMapper;
import com.co.tektontest.callhistory.domain.model.CallHistoryCommand;
import com.co.tektontest.callhistory.domain.model.CallHistoryResponse;
import com.co.tektontest.callhistory.domain.port.in.CallHistoryPort;
import com.co.tektontest.callhistory.domain.port.out.CallHistoryRespositoryPort;
import com.co.tektontest.callhistory.infrastructure.entity.CallHistoryEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;


/**
 * Call History port implement.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Slf4j
@Service
public class CallHistoryServiceImpl implements CallHistoryPort {

    private final CallHistoryRespositoryPort callHistoryRespositoryPort;

    private final CallHistoryServiceMapper callHistoryServiceMapper;

    public CallHistoryServiceImpl(CallHistoryRespositoryPort callHistoryRespositoryPort,
                                  CallHistoryServiceMapper callHistoryServiceMapper) {
        this.callHistoryRespositoryPort = callHistoryRespositoryPort;
        this.callHistoryServiceMapper = callHistoryServiceMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Mono<Page<CallHistoryResponse>> getAllHistoryData(Pageable pageable) {
        return Mono.defer(() -> {
                    Page<CallHistoryEntity> result = this.callHistoryRespositoryPort.getAllHistoryData(pageable);
                    List<CallHistoryResponse> list = result
                            .stream()
                            .map(this.callHistoryServiceMapper::callHistoryEntityToCallHistoryResponse)
                            .toList();
                    Page<CallHistoryResponse> pageData = new PageImpl<>(list, result.getPageable(), result.getTotalElements());
                    return Mono.just(pageData);
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveCallHistory(CallHistoryCommand historyCommand) {
        this.callHistoryRespositoryPort.saveCallHistory(
                this.callHistoryServiceMapper.callHistoryCommandToCallHistoryEntity(historyCommand)
        );
    }
}
