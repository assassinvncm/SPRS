package com.api.service;

import com.api.dto.PushNotificationRequest;
import com.api.dto.SubscriptionRequest;
import com.google.firebase.messaging.BatchResponse;

public interface NotificationService {

	void subscribeToTopic(SubscriptionRequest subscriptionRequest);
	
	void unsubscribeFromTopic(SubscriptionRequest subscriptionRequest);

	String sendPnsToDevice(PushNotificationRequest pushNotificationRequest);

	BatchResponse sendPnsToDevices(PushNotificationRequest pushNotificationRequest);

	String sendPnsToTopic(PushNotificationRequest pushNotificationRequestModel);
}
