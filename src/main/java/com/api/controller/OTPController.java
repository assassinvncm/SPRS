package com.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SPRSResponse;
import com.api.entity.SmsPojo;
import com.api.entity.User;
import com.api.service.OtpService;
import com.api.service.SmsService;
import com.api.service.UserService;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api")
public class OTPController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OtpService otpService;
	
	@Autowired
	SmsService smsService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/generateOtp", method = RequestMethod.POST)
	public ResponseEntity<?> generateOtp(@Validated @RequestBody SmsPojo pojo) {
		logger.info("Start generate OTP and send to: "+pojo.getTo());
//
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String username = auth.getName();
		
		String username = userService.getUsernameByPhone(pojo.getTo());
		int otp = otpService.generateOTP(username);
		logger.info("OTP : " + otp +" of user: "+username);
		pojo.setMessage(Constants.OTP_MESSAGE+otp);
		smsService.send(pojo);

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Send OTP Success!", "", null, null));
	}

	@RequestMapping(value = "/validateOtp", method = RequestMethod.POST)
	public ResponseEntity<?> validateOtp(@Validated @RequestBody SmsPojo pojo) {

		final String SUCCESS = "Entered Otp is valid";

		final String FAIL = "Entered Otp is NOT valid. Please Retry!";
		int otpnum = pojo.getOtp();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String username = auth.getName();
		String username = userService.getUsernameByPhone(pojo.getTo());
		logger.info("Otp Number : " + otpnum +" of user: "+username);

		//Validate the Otp 
		if (otpnum >= 0) {
			int serverOtp = otpService.getOtp(username);

			if (serverOtp > 0) {
				if (otpnum == serverOtp) {
					otpService.clearOTP(username);
					return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, SUCCESS, "", null, null));
				} else {
					return ResponseEntity.ok(new SPRSResponse(Constants.FAILED, FAIL, "", null, null));
				}
			} else {
				return ResponseEntity.ok(new SPRSResponse(Constants.FAILED, FAIL, "", null, null));
			}
		} else {
			return ResponseEntity.ok(new SPRSResponse(Constants.FAILED, FAIL, "", null, null));
		}
	}
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ResponseEntity<?> forgotPassword(@Validated @RequestBody SmsPojo pojo) {

		final String SUCCESS = "Reset password success!";

		final String FAIL = "Reset password fail!";
		int otpnum = pojo.getOtp();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String username = auth.getName();
		String username = userService.getUsernameByPhone(pojo.getTo());
		logger.info("Otp Number : " + otpnum +" of user: "+username);

		//Validate the Otp 
		if (otpnum >= 0) {
			int serverOtp = otpService.getOtp(username);

			if (serverOtp > 0) {
				if (otpnum == serverOtp) {
					otpService.clearOTP(username);
					String new_pass = userService.generatePassword(8);
					User u = userService.findByUsername(username);
					u.setPassword(new_pass);
					userService.save(u);
					pojo.setMessage(Constants.RESET_PASSWORD_MESSAGE+new_pass);
					smsService.send(pojo);
					return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, SUCCESS, "", null, null));
				} else {
					return ResponseEntity.ok(new SPRSResponse(Constants.FAILED, FAIL, "", null, null));
				}
			} else {
				return ResponseEntity.ok(new SPRSResponse(Constants.FAILED, FAIL, "", null, null));
			}
		} else {
			return ResponseEntity.ok(new SPRSResponse(Constants.FAILED, FAIL, "", null, null));
		}
	}
}
