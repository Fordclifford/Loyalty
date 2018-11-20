package com.kcbgroup.loyalty.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.AuditTrail;

@Repository
public interface AuditTrailRepository extends DataTablesRepository<AuditTrail, Long> {}
