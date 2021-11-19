package com.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
	
	@Query("SELECT from User u INNER JOIN")
	List<Notification> getNotifications(@RequestParam("uId") Long uId);
}
