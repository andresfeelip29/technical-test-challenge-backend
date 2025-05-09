package com.co.tektontest.callhistory.infrastructure.adapter.out.persitence;

import com.co.tektontest.callhistory.domain.port.out.CallHistoryRespositoryPort;
import com.co.tektontest.callhistory.infrastructure.entity.CallHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


/**
 * Call History persintecen adapter.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Component
public class CallHistoryPersitenceAdapter implements CallHistoryRespositoryPort {

    private final CallHistoryPostgresRepository repository;

    public CallHistoryPersitenceAdapter(CallHistoryPostgresRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<CallHistoryEntity> getAllHistoryData(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveCallHistory(CallHistoryEntity callHistoryEntity) {
        this.repository.save(callHistoryEntity);
    }
}
