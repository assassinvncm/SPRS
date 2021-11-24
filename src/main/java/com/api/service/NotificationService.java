package com.api.service;

import java.util.List;

import com.api.dto.NotificationDto;
import com.api.dto.PushNotificationRequest;
import com.api.dto.SubscriptionRequest;
import com.api.entity.Notification;
import com.api.entity.ReliefPoint;
import com.google.firebase.messaging.BatchResponse;

public interface NotificationService {

	/**
	 * send push notification for device in a city
	 * 
	 * @param city_id
	 */
	void sendPnsToDeviceInCity(Long city_id);

	void subscribeToTopic(SubscriptionRequest subscriptionRequest);

	void unsubscribeFromTopic(SubscriptionRequest subscriptionRequest);

	String sendPnsToDevice(PushNotificationRequest pushNotificationRequest);

	BatchResponse sendPnsToDevices(PushNotificationRequest pushNotificationRequest);

	String sendPnsToTopic(PushNotificationRequest pushNotificationRequestModel);
	
	/**
	 * get quantity of notification that un check
	 * @param user_id
	 * @return
	 */
	int getQuantityUncheckNotification(Long user_id);
	
	/**
	 * 
	 * @param user_id
	 */
	void updateStatusCheckAll(Long user_id);

	/**
	 * get list notification by user id
	 * 
	 * @return list<Notification>
	 */
	List<NotificationDto> getNotificationByUser(Long uId, int pageIndex, int pageSize);

	/**
	 * save list notification
	 */
	void saveNotification(Notification notifications);

	/**
	 * update status notification
	 * 
	 * @param notiId
	 * @param status
	 */
	void updateStatusNotification(Long notiId, String status);

	void PushNotificationJob(Object object, PushNotificationRequest pushNotificationRequest);

	void sendPnsToDeviceWhenCreateReliefPoint(ReliefPoint rp, String message);
}
