package com.kcbgroup.loyalty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.TransSummary;

@Repository
public interface TransSummaryRepository extends JpaRepository<TransSummary, Long> {

}
