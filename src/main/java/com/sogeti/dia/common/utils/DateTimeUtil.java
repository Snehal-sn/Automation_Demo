package com.sogeti.dia.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Class for Date and Time methods
 * @author Savita Tambe
 *
 */
public class DateTimeUtil {
	/**********************************************************************************************
     * Gets the System data
     * 
     * @param format {@link String} - Date format
     * @return sysDate {@link String} - Date in specified format
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/	
	public static String getSystemDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		
		return dateFormat.format(date);
	}
	
	/**********************************************************************************************
     * Get the date with offset
     * 
     * @param format {@link String} - Date format
     * @param offset {@link int} - Offset
     * @return expectedDate {@link String} - Date in specified format
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/              
	public static String getDateWithOffset(String format, int offset) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);                      
  
		Date date = new Date();
		Calendar cal = Calendar.getInstance();                  
		cal.setTime(date); 

		cal.add(Calendar.DATE, offset);
		date = cal.getTime();
		String expectedDate = dateFormat.format(date);

		return expectedDate;
	}

	 /**********************************************************************************************
     * Get the time with offset
     *
     * @param format {@link String} - Date format
     * @param offset {@link int} - Minute Offset
     * @return time {@link String} - time with offset
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/            
    public static String getTimeWithOffset(String format, int offset) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, offset);
      
        SimpleDateFormat df = new SimpleDateFormat(format);
        String time = df.format(now.getTime());
      
        return time;
    }
    
	/**********************************************************************************************
     * Gets the current time stamp
     * 
     * @return timeStamp {@link String} - Timestamp
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static String getCurrentTimestamp() {
		String timeStamp = new Timestamp(System.currentTimeMillis()).toString();
		timeStamp = timeStamp.replaceAll("[ :.]", "_");
		return timeStamp;
	}
	
	/**********************************************************************************************
     * Get formatted date
     * 
     * @param originalFormat {@link String} - Original date format
     * @param requiredFormat {@link String} - Required date format
     * @param dateValue {@link String} - Date value to be formatted
     * @return formatteddDate {@link String} - Formatted date
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static String getFormattedDate(String originalFormat, String requiredFormat, String dateValue) { 
        Date date = null;
        String formatteddDate = "";
        DateFormat sdf = new SimpleDateFormat(originalFormat);
		
        try {
				date = sdf.parse(dateValue);
				formatteddDate = new SimpleDateFormat(requiredFormat).format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        return formatteddDate;
    }
    
	/**********************************************************************************************
     * Gets the total execution time
     * 
     * @param startTime {@link String} - Start date
     * @param endTime {@link String} - End date
     * @return totalTime {@link String} - Total time
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static String getTotalTime(Date startTime, Date endTime)
	{		
		if (endTime == null)	
			endTime = new Date();
		
		long diff = endTime.getTime() - startTime.getTime();				
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffSeconds = diff / 1000 % 60;
		
		return diffHours + ":" + diffMinutes + ":" + diffSeconds;
	}		
}