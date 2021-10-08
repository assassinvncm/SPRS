package com.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.ReliefPoint;

public interface ReliefPointRepository extends JpaRepository<ReliefPoint, Long>{
	
}
