package com.api.repositories;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.entity.ReliefPoint;
import com.api.entity.User;
import com.api.repositories.custom.UserRepositoryCustom;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
	User findByUsername(String username);

	Optional<User> findByPhone(String phone);

//	User saveUser(User user);

	@Modifying
	@Transactional
	@Query("update User u set u.password = :newPass where u.id = :uId")
	void updateUser(@Param("uId") Long id, @Param("newPass") String newPass);
	
	@Query("SELECT u FROM User u WHERE u.username LIKE %:name%")
	List<User> searchByNameLike(@Param("name") String name);

	@Query("select u from User u where u.organization.id = :orgId and u.id != :uId and (:name IS NULL OR :name = '' OR u.username LIKE %:name%)")
	Page<User> getOwnOrganizeUser(@Param("orgId") Long orgId, @Param("uId") Long uId, @Param("name") String name, Pageable pageable);//
	
	@Transactional
	@Procedure(procedureName  = "prc_grant_group")
	String grantGroup(@Param("user_id") long user_id, @Param("group_id") long group_id);
	
	@Transactional
	@Procedure(procedureName  = "prc_ungrant_group")
	String ungrantGroup(@Param("user_id") long user_id, @Param("group_id") long group_id);
	
	@Transactional
	@Procedure(procedureName  = "prc_grant_permission")
	String grantPermission(@Param("group_id") long group_id, @Param("permission_id") long permission_id);
	
	@Transactional
	@Procedure(procedureName  = "prc_ungrant_permission")
	String ungrantPermission(@Param("group_id") long group_id, @Param("permission_id") long permission_id);
	
	@Query("SELECT u FROM User u INNER JOIN u.user_sos s WHERE s.id = :SOS_id")
	Optional<User> getUserBySosId(@Param("SOS_id") long id);
	
}
