package com.co.tektontest.callhistory.domain.port.in;

import com.co.tektontest.callhistory.domain.model.CallHistoryCommand;
import com.co.tektontest.callhistory.domain.model.CallHistoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

/**
 * Call History port interface.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
public interface CallHistoryPort {

    /**
     * Method to obtain the entire paged call history.
     * @param pageable Paginable object where you define the number of elements to be displayed, the order and the number of pages.
     * @return the list of paged calls is returned
     */
    Mono<Page<CallHistoryResponse>> getAllHistoryData(Pageable pageable);


    /**
     * Method to obtain the entire paged call history.
     * @param historyCommand Object to store a call.
     */
    void saveCallHistory(CallHistoryCommand historyCommand);
}
