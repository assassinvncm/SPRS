package com.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Long>{
	
	@Query(value = "SELECT d.* FROM Device d where d.user_id = :uid",nativeQuery = true)
	Optional<Device> findDeviceByUserId(@Param("uid") Long uId);
	
//	@Query(value = "SELECT d.* FROM Device d inner join Address a on d.id = a.user_id inner join  where d.user_id = :uid",nativeQuery = true)
//	Optional<Device> findDeviceByCityId(@Param("uid") Long city_id);
}
