package com.kcbgroup.loyalty.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.Infinia;

@Repository
public interface InfiniaRepository extends DataTablesRepository<Infinia, Long> {}
