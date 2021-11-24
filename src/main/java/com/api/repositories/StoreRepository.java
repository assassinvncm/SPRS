package com.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.dto.StoreDto;
import com.api.entity.Store;
import com.api.repositories.custom.StoreRepositoryCustom;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{
	@Query(value = "CALL prc_store_getStoreByTypeOrStatus(:user_id,:status,:type,:current_page,:page_size);", nativeQuery = true)
	List<Object[]> getStoreByStatusOrType(@Param("user_id") Long user_id,@Param("status") int status,@Param("type") String type,@Param("current_page") 
	int current_page,@Param("page_size") int page_size);
}
