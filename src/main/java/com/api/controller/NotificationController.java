package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.NotificationDto;
import com.api.dto.PushNotificationRequest;
import com.api.dto.SPRSResponse;
import com.api.dto.SubscriptionRequest;
import com.api.dto.UserDto;
import com.api.entity.User;
import com.api.service.NotificationService;
import com.api.service.UserService;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api/notification-manage")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private UserService userService;

	@PostMapping("/test")
	public void test(@RequestParam("id") Long id) {
		// notificationService.subscribeToTopic(subscriptionRequestModel);
		notificationService.sendPnsToDeviceInCity(id);

	}

	@PostMapping("/subscribe")
	public void subscribeToTopic(@RequestBody SubscriptionRequest subscriptionRequestModel) {
		notificationService.subscribeToTopic(subscriptionRequestModel);
	}

	@PostMapping("/unsubscribe")
	public void unsubscribeFromTopic(SubscriptionRequest subscriptionRequestModel) {
		notificationService.unsubscribeFromTopic(subscriptionRequestModel);
	}

	@PostMapping("/token")
	public String sendPnsToDevice(@RequestBody PushNotificationRequest pushNotificationRequestModel) {
		return notificationService.sendPnsToDevice(pushNotificationRequestModel);
	}

	@PostMapping("/topic")
	public String sendPnsToTopic(@RequestBody PushNotificationRequest pushNotificationRequestModel) {
		return notificationService.sendPnsToTopic(pushNotificationRequestModel);
	}

	@PostMapping("/send-all")
	public ResponseEntity<?> sendPnsToDevices(@RequestBody PushNotificationRequest pushNotificationRequestModel) {
		return ResponseEntity.ok(notificationService.sendPnsToDevices(pushNotificationRequestModel));
	}

	@GetMapping("/send-all")
	public ResponseEntity<?> getNotification(@RequestHeader("Authorization") String requestTokenHeader) {

		List<NotificationDto> lstNotification = notificationService.getNotificationByUser(null,0,0);
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Get List Item Successfull", "", lstNotification, null));
	}

	@GetMapping("/get-all")
	public ResponseEntity<?> getNotifications(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {

		User user = userService.getUserbyTokenAuth(requestTokenHeader);

		List<NotificationDto> lstNotification = notificationService.getNotificationByUser(user.getId(), pageIndex,
				pageSize);
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Get List Item Successfull", "", lstNotification, null));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateNotification(@RequestHeader("Authorization") String requestTokenHeader,
			@PathVariable("id") Long notiId, @RequestParam("status") String status) {

		User user = userService.getUserbyTokenAuth(requestTokenHeader);
		;

		notificationService.updateStatusNotification(notiId, status);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get List Item Successfull", "", "", null));
	}
}
