package com.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

//	@Query(value = "SELECT d.* FROM Device d where d.user_id = :uid",nativeQuery = true)
//	Optional<Device> findDeviceByUserId(@Param("uid") Long uId);

	@Query(value = "SELECT d.token FROM sprs_device d \r\n" + "inner join sprs_address a on d.address_id = a.id\r\n"
			+ "inner join sprs_sub_district sd on a.sub_district_id = sd.id\r\n"
			+ "inner join sprs_district d2 on sd.districts_id = d2.id\r\n"
			+ "inner join sprs_city c on d2.city_id = c.id where c.id = :cid", nativeQuery = true)
	List<String> findTokenUserByCityId(@Param("cid") Long city_id);

	@Query(value = "SELECT d.token FROM sprs_v1.sprs_store s \r\n"
			+ "INNER JOIN sprs_v1.sprs_store_subcribe ss ON s.id = ss.store_id\r\n"
			+ "INNER JOIN sprs_v1.sprs_users u ON ss.user_id = u.id\r\n"
			+ "INNER JOIN sprs_v1.sprs_device d ON u.id = d.user_id\r\n"
			+ "WHERE s.id = :cid", nativeQuery = true)
	List<String> findTokenUserByStore(@Param("cid") Long store_id);
}
