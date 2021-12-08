package com.ultils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.jwt.config.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

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
	
	public static Timestamp toSqlDate(Date d) {
        java.sql.Timestamp sql = new java.sql.Timestamp(d.getTime());
        return sql;
	}
}
