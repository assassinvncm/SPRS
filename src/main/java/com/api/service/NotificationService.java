package com.api.service;

import java.util.List;

import com.api.dto.NotificationDto;
import com.api.dto.PushNotificationRequest;
import com.api.dto.SubscriptionRequest;
import com.api.entity.Notification;
import com.google.firebase.messaging.BatchResponse;

public interface NotificationService {
	
	/**
	 * send push notification for device in a city
	 * @param city_id
	 */
	void sendPnsToDeviceInCity(Long city_id);

	void subscribeToTopic(SubscriptionRequest subscriptionRequest);
	
	void unsubscribeFromTopic(SubscriptionRequest subscriptionRequest);

	String sendPnsToDevice(PushNotificationRequest pushNotificationRequest);

	BatchResponse sendPnsToDevices(PushNotificationRequest pushNotificationRequest);

	String sendPnsToTopic(PushNotificationRequest pushNotificationRequestModel);
	
	/**
	 * get list notification by user id
	 * @return list<Notification>
	 */
	List<NotificationDto> getNotificationByUser(Long uId);
	
	/**
	 * save list notification
	 */
	void saveNotification(List<Notification> notifications);
}
