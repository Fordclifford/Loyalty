package com.kcbgroup.loyalty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.ReasonCode;

@Repository
public interface ReasonCodeRepository extends JpaRepository<ReasonCode, Long> {

}
