package com.common.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.exception.AppException;

public class DateUtils {

	// https://easycodeforall.com/java-date-utility-functions.html

	/**
	 * get current sql.date
	 * 
	 * @return current date
	 */
	public static Timestamp getCurrentSqlDate() {
		Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		return date;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Timestamp convertJavaDateToSqlDate(java.util.Date date) {
		if (date == null)
			return null;
		return new java.sql.Timestamp(date.getTime());
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date convertSqlDateToJavaDate(java.sql.Timestamp date) {
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
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	public static java.util.Date getDateFromStringInYYYYMMDD(String dateStr) {
		java.util.Date javaDate = null;
		if (dateStr == null) {
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
		if (dateStr == null) {
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

	public static String getDateYYYYMMDD(java.sql.Date sqlDate) {

		String rs = null;

		return rs;

	}

	public static String getDatehhmm(java.sql.Date sqlDate) {

		String rs = null;

		return rs;

	}
	
	public static java.sql.Date concatToSqlDate(String date , String time) {

		String rs = date.concat(" ").concat(time);

		return null;

	}

}
