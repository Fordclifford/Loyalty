package com.kcbgroup.loyalty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.ProductCode;

@Repository
public interface ProductCodeRepository extends JpaRepository<ProductCode, Long> {
	
	List<ProductCode> findByCode(String code);

}
