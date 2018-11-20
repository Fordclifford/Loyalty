package com.kcbgroup.loyalty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.TransactionCode;

@Repository
public interface TransactionCodeRepository extends JpaRepository<TransactionCode, Long> {

}
