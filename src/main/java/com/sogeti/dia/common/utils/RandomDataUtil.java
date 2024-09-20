package com.sogeti.dia.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Class for Generic methods
 * @author Savita Tambe
 *
 */
public class RandomDataUtil {
	/**********************************************************************************************
     * To generate random string.
     * 
     * @param maximumLength {@link int} - Maximum length of string
     * @return charString {@link String} - Random generated string
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/	
    public static String getCharacterString(int maximumLength) {
        String charString = UUID.randomUUID().toString() + UUID.randomUUID().toString() 
                + UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString();
      
        return charString.replace("-", "").substring(0, maximumLength);
    }

    /**********************************************************************************************
     * To generate random date of birth
     * 
     * @param startYear {@link int} - Start year
     * @param endYear {@link int} - End year
     * @return dob {@link String} - Random generated date of birth
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/	   
    public static String getDateOfBirth(int startYear, int endYear) {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(startYear, endYear);
        gc.set(Calendar.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        String month = Integer.toString(gc.get(Calendar.MONTH));
        String yearS = Integer.toString(gc.get(Calendar.YEAR));
        String day = "";
        Integer dayPre = gc.get(Calendar.DAY_OF_MONTH);
        
        if (dayPre > 28) {
            day = "28";
        } else {
            day = Integer.toString(dayPre);
        }
        
        return month.replace("0", "1") + "-" + day + "-" + yearS;
    }

    /**********************************************************************************************
     * To generate random integer
     * 
     * @param minValue {@link int} - Minimum value
     * @param maxValue {@link int} - Maximum value
     * @return randomNum {@link int} - Random number
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/	   
    public static Integer getInteger(int minValue, int maxValue) {
        java.util.Random rand = new java.util.Random();

        return rand.nextInt((maxValue - minValue) + 1) + minValue;
    }

    /**********************************************************************************************
     * To generate random number in the provided range
     * 
     * @param start {@link int} - Start number
     * @param end {@link int} - End number
     * @return rnadomNumber {@link int} - Random number generated
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/	   
    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public float getPercentage(String percent) {
        float percentage = 0;
        if (percent.length() > 0) {
            if (percent.substring(percent.length() - 1).equals("%")) {
                percent = StringUtils.strip(percent, "%");
                percentage = Float.parseFloat(percent) / 100;
            } else
                percentage = Float.parseFloat(percent);
        }

        return percentage;
    }
}
