package com.kcbgroup.loyalty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
	
	Optional<Merchant> findBymId(Integer mId);

}
