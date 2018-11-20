package com.kcbgroup.loyalty.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByRole(String role);
	
	Optional<User> findByUsername(String username);

}
