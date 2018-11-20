package com.kcbgroup.loyalty.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.DaoSummary;

@Repository
public interface DaoSummaryRepository extends JpaRepository<DaoSummary, Long> {
	
	List<DaoSummary> findByPosting75(Integer posting75);
	
	List<DaoSummary> findByPosting25(Integer posting25);
	
	List<DaoSummary> findByDao(String dao);

}
