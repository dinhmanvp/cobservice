package cob.com.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateUtility {
	
	public static Boolean Dateformat(String dateToValidate, String dateFromat) {
		try {
			if (StringUtils.isEmpty(dateToValidate))
				return false;

			SimpleDateFormat simpleDate = new SimpleDateFormat(dateFromat);
			simpleDate.setLenient(false);
			simpleDate.parse(dateToValidate);

			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static Date AddMinute(Date input, int minute) {
		Calendar c = Calendar.getInstance();
		c.setTime(input); 
		c.add(Calendar.MINUTE, minute);
		return c.getTime();
	}
}
