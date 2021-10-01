package com.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{
	Optional<Permission> findById(Long id);
}
