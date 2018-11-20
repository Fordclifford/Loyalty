package com.kcbgroup.loyalty.repository;

import java.util.Optional;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.Customer;

@Repository
public interface CustomerRepository extends DataTablesRepository<Customer, Long> {
	
	Optional<Customer> findByCustomerCode(String customerCode);
	
	Optional<Customer> findByCustMobile(String custMobile);

}
