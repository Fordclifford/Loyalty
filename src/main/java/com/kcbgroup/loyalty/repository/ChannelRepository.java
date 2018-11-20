package com.kcbgroup.loyalty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

}
