package com.kcbgroup.loyalty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	
	Optional<Currency> findByAbbreviation(String abbreviation);

}
