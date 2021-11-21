package com.api.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	@Query(value = "SELECT nr from Notification nr INNER JOIN nr.receivers u where u.id = :uId")
	List<Notification> getNotifications(@RequestParam("uId") Long uId, Pageable pageable);
}
