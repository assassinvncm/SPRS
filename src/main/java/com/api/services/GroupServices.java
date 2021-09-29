package com.api.services;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.model.Group;

public interface GroupServices extends JpaRepository<Group, Long>{
	Group findByName(String name);
	
	Optional<Group> findById(Long id);
}
