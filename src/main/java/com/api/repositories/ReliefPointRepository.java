package com.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.entity.ReliefPoint;

public interface ReliefPointRepository extends JpaRepository<ReliefPoint, Long>, ReliefPointRepositoryCustom{
	
	@Query("select rp from ReliefPoint rp inner join rp.address address inner join address.subDistrict sd inner join sd.district d inner join d.city  where d.city.id = :cId")
	List<ReliefPoint> findReliefPointByArea(@Param("cId") long id);
	
	@Query("select rp from User u inner join u.reliefPoints rp where u.id = :uId and rp.id = :rpId")
	Optional<ReliefPoint> findByIdAndUser (@Param("rpId") Long rpId, @Param("uId") Long uId);
	
//	@Query("select rp from User u inner join u.reliefPoints rp inner join rp.reliefInformations rif inner join rif.item i where u.id = :uId and (rp.status =:status or i.id = :typeId)")
//	Slice<ReliefPoint> findByTypeOrStatus(@Param("uId") Long uId,@Param("typeId") Long typeId ,@Param("status") Boolean status,Pageable pageRequest);
	
	//Optional<ReliefPoint> findReliefPoint();
 }
