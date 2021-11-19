package com.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.entity.Store;
import com.api.entity.User;

public interface StoreRepository extends JpaRepository<Store, Long>{
	@Query(value = "CALL filterStoreByTypeOrStatus(:status, :type);", nativeQuery = true)
	List<User> filterStoreByStatusType(@Param("status") Boolean status, @Param("type") String type);
}
