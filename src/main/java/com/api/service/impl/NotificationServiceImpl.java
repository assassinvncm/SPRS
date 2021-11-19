package com.api.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.api.dto.NotificationDto;
import com.api.dto.PushNotificationRequest;
import com.api.dto.SubscriptionRequest;
import com.api.entity.Notification;
import com.api.repositories.NotificationRepository;
import com.api.service.DeviceService;
import com.api.service.NotificationService;
import com.exception.AppException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;

@Service
public class NotificationServiceImpl implements NotificationService{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${app.firebase-config}")
	private String firebaseConfig;

	private FirebaseApp firebaseApp;

	@Autowired
	DeviceService deviceService;
	
	@Autowired
	NotificationRepository notificationRepository;

	@PostConstruct
	private void initialize() {
		try {
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(
							GoogleCredentials.fromStream(new ClassPathResource(firebaseConfig).getInputStream()))
					.build();

			if (FirebaseApp.getApps().isEmpty()) {
				this.firebaseApp = FirebaseApp.initializeApp(options);
			} else {
				this.firebaseApp = FirebaseApp.getInstance();
			}
		} catch (IOException e) {
			log.error("Create FirebaseApp Error", e);
		}
	}

	@Override
	public void subscribeToTopic(SubscriptionRequest subscriptionRequest) {

		try {
			FirebaseMessaging.getInstance(firebaseApp).subscribeToTopic(subscriptionRequest.getTokens(),
					subscriptionRequest.getTopicName());
		} catch (FirebaseMessagingException e) {
			log.error("Firebase subscribe to topic fail", e);
		}
	}

	@Override
	public void unsubscribeFromTopic(SubscriptionRequest subscriptionRequest) {
		try {
			FirebaseMessaging.getInstance(firebaseApp).unsubscribeFromTopic(subscriptionRequest.getTokens(),
					subscriptionRequest.getTopicName());
		} catch (FirebaseMessagingException e) {
			log.error("Firebase unsubscribe from topic fail", e);
		}
	}

	@Override
	public String sendPnsToDevice(PushNotificationRequest pushNotificationRequest) {
//		Message message = Message.builder().setToken(pushNotificationRequest.getTarget())
//				.setNotification(com.google.firebase.messaging.Notification.builder()
//						.setTitle(pushNotificationRequest.getTitle())
//						.setBody(pushNotificationRequest.getBody()).build())
//				.putData("content", pushNotificationRequest.getTitle())
//				.putData("body", pushNotificationRequest.getBody()).build();

		String response = null;
//		try {
//			response = FirebaseMessaging.getInstance().send(message);
//		} catch (FirebaseMessagingException e) {
//			log.error("Fail to send firebase notification", e);
//		}

		return response;
	}

	@Override
	public BatchResponse sendPnsToDevices(PushNotificationRequest pushNotificationRequest) {
		// List<TokenDevice> tokenDevices =
		// tokenDeviceRepository.findByIdIn(pushNotificationRequestModel.getUids());
		List<String> tokenDevices = deviceService.getDeviceTokenByCity(null);
		MulticastMessage multicastMessage = MulticastMessage.builder().addAllTokens(tokenDevices)
				.setNotification(com.google.firebase.messaging.Notification.builder()
						.setTitle(pushNotificationRequest.getTitle()).setBody(firebaseConfig).build())
				.putData("content", pushNotificationRequest.getTitle())
				.putData("body", pushNotificationRequest.getBody()).build();

		BatchResponse response = null;
		try {
			response = FirebaseMessaging.getInstance().sendMulticast(multicastMessage);
		} catch (FirebaseMessagingException e) {
			log.error("Fail to send firebase notification", e);
			//throw new AppException(501, "Send Message is fail!");
		}

		return response;

	}

	@Override
	public String sendPnsToTopic(PushNotificationRequest pushNotificationRequestModel) {
//		Message message = Message.builder().setTopic(pushNotificationRequestModel.getTarget())
//				.setNotification(com.google.firebase.messaging.Notification.builder()
//						.setTitle(pushNotificationRequestModel.getTitle())
//						.setBody(pushNotificationRequestModel.getBody()).build())
//				.putData("content", pushNotificationRequestModel.getTitle())
//				.putData("body", pushNotificationRequestModel.getBody()).build();

		String response = null;
//		try {
//			FirebaseMessaging.getInstance().send(message);
//		} catch (FirebaseMessagingException e) {
//			log.error("Fail to send firebase notification", e);
//		}

		return response;
	}

	@Override
	public void sendPnsToDeviceInCity(Long city_id) {
		// TODO Auto-generated method stub
		List<String> lstToken = deviceService.getDeviceTokenByCity(city_id);
		PushNotificationRequest pushNotificationRequest = new PushNotificationRequest();
		pushNotificationRequest.setTarget(lstToken);
		pushNotificationRequest.setTitle("");
		pushNotificationRequest.setBody("Có một địa điểm cứu trợ mới được tạo gần đây");
		sendPnsToDevices(pushNotificationRequest);
	}
	
	//@Override
	public void sendPnsToDeviceSubcribeStore(Long store_id, String message) {
		// TODO Auto-generated method stub
		List<String> lstToken = deviceService.getDeviceTokenByStoreId(store_id);
		PushNotificationRequest pushNotificationRequest = new PushNotificationRequest();
		pushNotificationRequest.setTarget(lstToken);
		pushNotificationRequest.setTitle("");
		pushNotificationRequest.setBody(message);
		sendPnsToDevices(pushNotificationRequest);
	}
	
	@Override
	public void saveNotification(List<Notification> notificaitons) {
		notificationRepository.saveAll(notificaitons);
	}

	@Override
	public List<NotificationDto> getNotificationByUser(Long uId) {
		// TODO Auto-generated method stub
		List<Notification> lstNotification = notificationRepository.getNotifications(uId);
		
		return null;
	}

}
