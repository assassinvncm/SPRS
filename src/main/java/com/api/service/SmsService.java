package com.api.service;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.api.entity.SmsPojo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class SmsService {

	private final String ACCOUNT_SID = "ACaef9117a42d80b9837a3d4bc0acf8fe0";

	private final String AUTH_TOKEN = "053af5443301bf8db48885245631534f";

	private final String FROM_NUMBER = "+14128967877";

	public void send(SmsPojo sms) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
				.create();
		System.out.println("Here is my id:" + message.getSid());// Unique resource ID created to manage this transaction

	}

}