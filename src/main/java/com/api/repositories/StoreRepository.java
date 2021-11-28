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
	@Query(value = "CALL prc_store_getStoreByTypeOrStatus(:p_user_id,:p_status,:p_type,:p_current_page,:p_page_size);", nativeQuery = true)
	List<Object[]> getStoreByStatusOrType(@Param("p_user_id") Long p_user_id,@Param("p_status") int p_status,@Param("p_type") Long types,@Param("p_current_page") 
	int p_current_page,@Param("p_page_size") int p_page_size);
}
