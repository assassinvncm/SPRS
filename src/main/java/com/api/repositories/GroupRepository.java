package com.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long>{
	Group findByName(String name);
	
	Optional<Group> findById(Long id);
}
