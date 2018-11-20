package com.kcbgroup.loyalty.repository;

import java.util.Optional;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.RedemptionSummary;

@Repository
public interface RedemptionSummaryRepository extends DataTablesRepository<RedemptionSummary, Long> {
	
	Optional<RedemptionSummary> findByCustomerNo(String customerNo);

}
