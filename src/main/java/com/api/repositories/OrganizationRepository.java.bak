package com.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long>{
	
	Optional<Organization> findById(Long id);
}
