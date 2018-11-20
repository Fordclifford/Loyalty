package com.kcbgroup.loyalty.repository;

import java.util.Optional;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.Kcb;

@Repository
public interface KcbRepository extends DataTablesRepository<Kcb, Long> {
	
	Optional<Kcb> findByTransRef(String transRef);
	
	@Procedure(name="incoming_kcb_cursor")
    void callIncomingKcbCursor();

}
