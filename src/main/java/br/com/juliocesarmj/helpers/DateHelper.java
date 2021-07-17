package br.com.juliocesarmj.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {
	
	public static Date toDate(String date) {
		
		//date is yyyy-MM-dd
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5,7));
		int day = Integer.parseInt(date.substring(8,10));
		
		Calendar cal = new GregorianCalendar(year, month - 1, day);
		return cal.getTime();
	}
	
	public static String toString(Date date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
}
