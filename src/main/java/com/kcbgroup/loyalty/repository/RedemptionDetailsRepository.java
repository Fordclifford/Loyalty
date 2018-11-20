package com.kcbgroup.loyalty.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.RedemptionDetails;

@Repository
public interface RedemptionDetailsRepository extends JpaRepository<RedemptionDetails, Long> {
	
	List<RedemptionDetails> findByCustomerNo(String customerNo);
	
	List<RedemptionDetails> findByCustomerNoAndPointsFlag(String customerNo, String pointsFlag);
	
	@Query(value="SELECT SUM(POINTS) from RedemptionDetails where CUSTOMERNO=?", nativeQuery=true)
    Optional<BigDecimal> sumPoints(String customerNo);

}
