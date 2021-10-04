package com.ultils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ultilities {
	public static String getCurrentDateStr(String formatDate) {
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat(formatDate);
		return f.format(d);
	}
	
	public static Date getCurrentDate(String formatDate) {
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat(formatDate);
		try {
			return f.parse(f.format(d));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isExistedIn(String origin, String[] match) {
		boolean check = false;
		for (String string : match) {
			if(string.equals(origin)) {
				check = true;
			}
		}
		return check;
	}
}
