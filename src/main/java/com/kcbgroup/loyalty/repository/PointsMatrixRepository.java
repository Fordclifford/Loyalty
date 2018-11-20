package com.kcbgroup.loyalty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcbgroup.loyalty.model.PointsMatrix;

@Repository
public interface PointsMatrixRepository extends JpaRepository<PointsMatrix, Long> {

}
