package com.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long>{
	List<Request> findByOrganization_id(Long id);
}
