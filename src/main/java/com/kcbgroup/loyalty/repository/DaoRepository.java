package com.kcbgroup.loyalty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.Dao;

@Repository
public interface DaoRepository extends JpaRepository<Dao, Long> {
	
	Optional<Dao> findByBranchCode(String branchCode);

}
