package com.kcbgroup.loyalty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.Directory;

@Repository
public interface DirectoryRepository extends JpaRepository<Directory, Long> {

}
