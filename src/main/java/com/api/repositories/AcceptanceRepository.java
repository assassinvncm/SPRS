package com.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.Acceptance;

public interface AcceptanceRepository extends JpaRepository<Acceptance, Long>{
	Optional<Acceptance> findById(Long id);
}
