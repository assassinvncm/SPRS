package com.api.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.model.Group;

public interface GroupServices extends JpaRepository<Group, Long>{
	Group findByName(String name);
}
