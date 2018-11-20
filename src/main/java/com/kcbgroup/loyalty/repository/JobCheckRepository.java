package com.kcbgroup.loyalty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.JobCheck;

@Repository
public interface JobCheckRepository extends JpaRepository<JobCheck, Long> {

}
