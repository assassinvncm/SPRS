package com.api.service;

import com.api.dto.PushNotificationRequest;
import com.api.dto.SubscriptionRequest;
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
}
