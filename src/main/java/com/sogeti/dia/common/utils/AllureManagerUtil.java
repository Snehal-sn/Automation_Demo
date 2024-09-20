package com.sogeti.dia.common.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.sogeti.dia.common.config.TestRun;

import io.qameta.allure.Attachment;

/**
 * Class for Allure methods
 * @author Savita Tambe
 *
 */
public class AllureManagerUtil {
	private AllureManagerUtil() {}
	private static String emptyString = "empty string";
	
	/**********************************************************************************************
	 * Gets the screenshot of the browser window and attaches it to the test case in the allure report
	 * 
	 * @return imageContent {@link byte[]} - Screenshot
	 * @author Savita Tambe created March 27, 2018
	 * @version 1.0 March 27, 2018
	 ***********************************************************************************************/	
	  @Attachment(value = "Screenshot", type = "image/png")
	    public static byte[] attachScreenshot() {
	    	byte[] imageContent = emptyString.getBytes();
	    	      
	    	try {
	    			if (TestRun.isMobile())
	    				imageContent = ((TakesScreenshot) DriverManagerUtil.getAppiumDriver()).getScreenshotAs(OutputType.BYTES);
	    			else if (TestRun.isDesktop())
	    				imageContent = ((TakesScreenshot) DriverManagerUtil.getWebDriver()).getScreenshotAs(OutputType.BYTES);
	    				
			} catch (Exception e) {
				LoggerUtil.logConsoleMessage("Failed to capture screenshot.");
		    }
	 
	    	return imageContent;
	    }
    /**********************************************************************************************
   	 * Gets the selenium log and attaches it to the test case in the allure report
   	 *
   	 * @return imageContent {@link byte[]} - Screenshot
   	 * @param pathToLogFile {@link String} - Path of log file 
   	 * @author Savita Tambe created March 27, 2018
   	 * @version 1.0 March 27, 2018
   	 ***********************************************************************************************/	   
    @Attachment(value = "Selenium Log", type = "text/plain")
    public static byte[] attachSeleniumLog(String pathToLogFile) {
    	byte[] logContent = emptyString.getBytes();
    	try {
    		logContent = Files.readAllBytes(Paths.get(pathToLogFile));
    	} catch (Exception e) {
    		LoggerUtil.logErrorMessage("Failed to get log data: " + e);
    	}
    	return logContent;
    }
}