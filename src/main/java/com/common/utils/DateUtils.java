package com.common.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.exception.AppException;

public class DateUtils {

	// https://easycodeforall.com/java-date-utility-functions.html

	/**
	 * get current sql.date
	 * 
	 * @return current date
	 */
	public static Date getCurrentSqlDate() {
		Date date = new java.sql.Date(new java.util.Date().getTime());
		return date;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date convertJavaDateToSqlDate(java.util.Date date) {
		if (date == null)
			return null;
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date convertSqlDateToJavaDate(java.util.Date date) {
		if (date == null)
			return null;
		return new java.util.Date(date.getTime());
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateAsStringInYYYYMMDD(java.util.Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);

	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateInyyyy_MM_ddHHmmss(java.util.Date date) {
		if(date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	public static java.util.Date getDateFromStringInYYYYMMDD(String dateStr) {
		java.util.Date javaDate = null;
		if(javaDate == null) {
			return null;
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			javaDate = formatter.parse(dateStr);
		} catch (Exception e) {
			// TODO: handle exception
			throw new AppException(405, "Format date happen error");
		}

		return javaDate;

	}
	
	public static java.util.Date getDateFromStringInYYYYMMDDHH(String dateStr) {
		java.util.Date javaDate = null;
		if(javaDate == null) {
			return null;
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			javaDate = formatter.parse(dateStr);
		} catch (Exception e) {
			// TODO: handle exception
			throw new AppException(405, "Format date happen error");
		}

		return javaDate;

	}

}
