package com.co.tektontest.callhistory.infrastructure.adapter.out.persitence;


import com.co.tektontest.callhistory.infrastructure.entity.CallHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Call History spring persintecen postgre.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */

@Repository
public interface CallHistoryPostgresRepository extends JpaRepository<CallHistoryEntity, Long> {
}
