package com.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long>{
	
	List<Request> findByOrganization_id(Long id);
	
	List<Request> findByGroup_id(Long id);
	
	@Query("select r from Request r inner join r.group where r.group.id = :groupId and r.status = :status")
	List<Request> filterRequestOfAdmin(@Param("groupId") Long id,@Param("status") String status);
	
	@Query("select r from Request r inner join r.organization where r.organization.id = :organizationId and r.status = :status")
	List<Request> filterRequestOfOrgAdmin(@Param("organizationId") Long id,@Param("status") String status);
}
