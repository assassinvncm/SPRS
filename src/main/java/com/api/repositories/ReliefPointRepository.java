package com.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.entity.ReliefPoint;

public interface ReliefPointRepository extends JpaRepository<ReliefPoint, Long>{
	
	@Query("select rp from ReliefPoint rp inner join rp.address address inner join address.subDistrict sd inner join sd.district d inner join d.city  where d.city.id = :cId")
	List<ReliefPoint> findReliefPointByArea(@Param("cId") long id);
	
 }
