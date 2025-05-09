package com.co.tektontest.callhistory.domain.port.out;


import com.co.tektontest.callhistory.infrastructure.entity.CallHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Call History port repository.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
public interface CallHistoryRespositoryPort {

    /**
     * Method to obtain the entire paged call history.
     * @param pageable Paginable object where you define the number of elements to be displayed, the order and the number of pages.
     * @return the list of paged calls is returned
     */
    Page<CallHistoryEntity> getAllHistoryData(Pageable pageable);


    /**
     * Method to obtain the entire paged call history.
     * @param callHistoryEntity Entity for save to store a call.
     */
    void saveCallHistory(CallHistoryEntity callHistoryEntity);

}
