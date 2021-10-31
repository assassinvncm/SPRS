package com.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	Optional<User> findByPhone(String phone);

//	User saveUser(User user);

	@Modifying
	@Query("update User u set u.password = :newPass where u.id = :uId")
	void updateUser(@Param("uId") Long id, @Param("newPass") String newPass);
}
