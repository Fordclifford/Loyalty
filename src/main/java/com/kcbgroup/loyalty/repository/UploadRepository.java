package com.kcbgroup.loyalty.repository;

import java.util.Optional;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.Upload;

@Repository
public interface UploadRepository extends DataTablesRepository<Upload, Long> {
	
	Optional<Upload> findByName(String name);

}
