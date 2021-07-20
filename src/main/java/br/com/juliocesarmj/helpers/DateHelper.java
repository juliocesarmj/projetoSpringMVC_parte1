package br.com.juliocesarmj.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {
	
	public static Date toDate(String data) {
		
		int ano = Integer.parseInt(data.substring(0, 4));
		int mes = Integer.parseInt(data.substring(5,7));
		int dia = Integer.parseInt(data.substring(8,10));
		
		Calendar cal = new GregorianCalendar(ano, mes -1, dia);
		
		return cal.getTime();
	}
	
	public static String toString(Date data) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(data);
	}
	
	public static String toStringPtBr(Date data) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}
}
