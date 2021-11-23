package com.api.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.dto.NotificationDto;
import com.api.dto.PushNotificationRequest;
import com.api.dto.SubscriptionRequest;
import com.api.entity.Device;
import com.api.entity.Notification;
import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.NotificationRepository;
import com.api.service.DeviceService;
import com.api.service.NotificationService;
import com.common.utils.DateUtils;
import com.exception.AppException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.ultils.Constants;

@Service
public class NotificationServiceImpl implements NotificationService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${app.firebase-config}")
	private String firebaseConfig;

	private FirebaseApp firebaseApp;

	@Autowired
	DeviceService deviceService;

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	MapStructMapper mapStructMapper;

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
		Message message = Message.builder().setToken(pushNotificationRequest.getTarget().get(0))
				.setNotification(new com.google.firebase.messaging.Notification(pushNotificationRequest.getTitle(),
						pushNotificationRequest.getBody()))
				.putData("content", pushNotificationRequest.getTitle())
				.putData("body", pushNotificationRequest.getBody()).build();

		String response = null;
		try {
			response = FirebaseMessaging.getInstance(firebaseApp).send(message);
		} catch (FirebaseMessagingException e) {
			log.error("Fail to send firebase notification", e);
		}

		return response;
	}

	@Override
	public BatchResponse sendPnsToDevices(PushNotificationRequest pushNotificationRequest) {
		// List<TokenDevice> tokenDevices =
		// tokenDeviceRepository.findByIdIn(pushNotificationRequestModel.getUids());
		// List<String> tokenDevices = deviceService.getDeviceTokenByCity(null);
		if (pushNotificationRequest.getTarget().isEmpty())
			return null;
		MulticastMessage multicastMessage = MulticastMessage.builder().addAllTokens(pushNotificationRequest.getTarget())
				.setNotification(new com.google.firebase.messaging.Notification(pushNotificationRequest.getTitle(),
						pushNotificationRequest.getBody()))
				.putData("content", pushNotificationRequest.getTitle())
				.putData("body", pushNotificationRequest.getBody()).build();

		BatchResponse response = null;
		try {
			response = FirebaseMessaging.getInstance(firebaseApp).sendMulticast(multicastMessage);
		} catch (FirebaseMessagingException e) {
			log.error("Fail to send firebase notification", e);
			// throw new AppException(501, "Send Message is fail!");
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
//		List<Device> lstToken = deviceService.getDeviceTokenByCity(city_id);
//		PushNotificationRequest pushNotificationRequest = new PushNotificationRequest();
//		pushNotificationRequest.setTarget(lstToken);
//		pushNotificationRequest.setTitle("");
//		pushNotificationRequest.setBody("Có một địa điểm cứu trợ mới được tạo gần đây");
//		sendPnsToDevices(pushNotificationRequest);
	}

	// @Override
	public void sendPnsToDeviceSubcribeStore(Store store, String message) {
		// TODO Auto-generated method stub

		List<Device> lstDevice = deviceService.getDeviceTokenByStoreId(store.getId());
		List<Notification> notifications = new ArrayList<Notification>();
		List<String> lstToken = new ArrayList<String>();

		// set notification
		Notification notification = new Notification();
		notification.setMessage(message);
		notification.setStore(store);
		notification.setType(Constants.NOTIFICATION_TYPE_STORE);
		notification.setStatus(Constants.NOTIFICATION_STATUS_UNCHECK);
		notification.setCreate_time(DateUtils.getCurrentSqlDate());
		List<User> user = new ArrayList<User>();
		for (Device d : lstDevice) {
			user.add(d.getUser());
			lstToken.add(d.getToken());
		}

		if (lstToken.isEmpty()) {
			return;
		}

		notification.setReceiver(user);

		PushNotificationRequest pushNotificationRequest = new PushNotificationRequest();
		pushNotificationRequest.setTarget(lstToken);
		pushNotificationRequest.setTitle(store.getName());
		pushNotificationRequest.setBody(message);
		this.saveNotification(notification);
		this.sendPnsToDevices(pushNotificationRequest);
	}

	public void sendPnsToDeviceWhenCreateReliefPoint(ReliefPoint rp, String message) {
		List<Device> lstDevice = deviceService.getDeviceTokenByCity(rp.getUsers().getId(),
				rp.getAddress().getSubDistrict().getDistrict().getCity().getId());
		List<String> lstToken = new ArrayList<String>();

		// set notification
		Notification notification = new Notification();
		notification.setMessage(message);
		notification.setReliefPoint(rp);
		notification.setType(Constants.NOTIFICATION_TYPE_RELIEFPOINT);
		notification.setStatus(Constants.NOTIFICATION_STATUS_UNCHECK);
		notification.setCreate_time(DateUtils.getCurrentSqlDate());
		List<User> user = new ArrayList<User>();
		for (Device d : lstDevice) {
			user.add(d.getUser());
			lstToken.add(d.getToken());
		}
		if (lstToken.isEmpty()) {
			return;
		}
		notification.setReceiver(user);

		PushNotificationRequest pushNotificationRequest = new PushNotificationRequest();
		pushNotificationRequest.setTarget(lstToken);
		pushNotificationRequest.setTitle(rp.getName());
		pushNotificationRequest.setBody(message);
		this.saveNotification(notification);
		this.sendPnsToDevices(pushNotificationRequest);
	}

	@Override
	public void saveNotification(Notification notificaitons) {
		notificationRepository.saveAndFlush(notificaitons);
	}

	@Override
	public List<NotificationDto> getNotificationByUser(Long uId, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by("create_time").descending();
		Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
		List<Notification> lstNotification = notificationRepository.getNotifications(uId, pageable);

		return mapStructMapper.lstNotificationToNotificationDto(lstNotification);
	}

	@Override
	public void updateStatusNotification(Long notiId, String status) {
		// TODO Auto-generated method stub
		Notification noti = notificationRepository.findById(notiId)
				.orElseThrow(() -> new AppException(403, "Notification not exst"));
		noti.setStatus(status);
		notificationRepository.save(noti);
	}

	@Override
	public void PushNotificationJob(Object object, PushNotificationRequest pushNotificationRequest) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getQuantityUncheckNotification(Long user_id) {
		// TODO Auto-generated method stub
		int quantity = notificationRepository.getQuantityNotificationsByStatus(user_id, Constants.NOTIFICATION_STATUS_UNCHECK);
		return quantity;
	}

}
