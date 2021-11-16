package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.PushNotificationRequest;
import com.api.dto.SubscriptionRequest;
import com.api.service.NotificationService;


@RestController
@RequestMapping("/sprs/api/notification-manage")
public class NotificationController {
	
	@Autowired
    private NotificationService notificationService;

	@PostMapping("/test")
    public void test(@RequestParam("id") Long id) {
        //notificationService.subscribeToTopic(subscriptionRequestModel);
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
}
