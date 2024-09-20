package com.sogeti.dia.common.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;
import io.qameta.allure.Step;

/**
 * Class for loging
 * @author Savita Tambe
 *
 */
public abstract class LoggerUtil { 
	 static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("devpinoyLogger"); 
	 
	 /**********************************************************************************************
	  * Logs a message to the console, the allure reports and the testng reports.
	  * 
	  * @param message {@link String} - Message to be logged
	  * @author Savita Tambe created March 27, 2018
	  * @version 1.0 March 27, 2018
	  ***********************************************************************************************/	
	@Step("{0}")
    public static void logMessage(String message) {
		logConsoleMessage(message);
        log.info(message);        
		Reporter.log(message, false);		
		testNGScreenshotCapture();
    
		if ((TestRun.isDesktop() || TestRun.isMobile()) && (Config.SCREENSHOT_CAPTURE.equalsIgnoreCase("EveryStep"))) {	   
	        AllureManagerUtil.attachScreenshot();    
		}
    }
	
	 /**********************************************************************************************
	  * Logs a message to the console, the allure reports and the testng reports without screensot
	  * 
	  * @param message {@link String} - Message to be logged
	  * @author Savita Tambe created March 27, 2018
	  * @version 1.0 March 27, 2018
	  ***********************************************************************************************/	
	@Step("{0}")
    public static void logMessageNoScreenShot(String message) {
		logConsoleMessage(message);
		log.info(message);        
		Reporter.log(message, false);			
   }
	
	/**********************************************************************************************
     * Logs a message to the console only
     * 
     * @param message {@link String} - Message to be logged
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/		
	public static void logConsoleMessage(String message) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date()) + " " + message);
    }
	
	/**********************************************************************************************
     * Logs a message in Log4J and Console file
     * 
     * @param message {@link String} - Message to be logged
     * @author Savita Tambe created May 11, 2018
     * @version 1.0 May 11, 2018
     ***********************************************************************************************/		
	public static void logErrorMessage(String message) {
		 logConsoleMessage(message);
		 log.error(message);
    }
	
	/**********************************************************************************************
     * Disables log4j console output. Helpful if you don't want a lot of console clutter.
     * 
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/		
	public static void disableLog4JConsoleOutput() {
		LoggerUtil.logConsoleMessage("Disabling log4j console output.");
		org.apache.log4j.Logger.getLogger("org.BIU.utils.logging.ExperimentLogger").setLevel(Level.OFF);
    	org.apache.log4j.Logger.getRootLogger().setLevel(Level.OFF);
	}
	
	/**********************************************************************************************
     * Enables log4j console output.
     * 
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/	
	public static void enableLog4JConsoleOutput() {
		LoggerUtil.logConsoleMessage("Enabling log4j console output.");
		org.apache.log4j.Logger.getLogger("org.BIU.utils.logging.ExperimentLogger").setLevel(Level.ALL);
    	org.apache.log4j.Logger.getRootLogger().setLevel(Level.ALL);
	}
	
	/**********************************************************************************************
     * Screenshot capture for testNG
     * 
     * @author Savita Tambe created May 25, 2018
     * @version 1.0 May 25, 2018
     ***********************************************************************************************/	
	public static void testNGScreenshotCapture() {
		if (TestRun.isDesktop() || TestRun.isMobile()) {
			File scrFile = null; 
			Calendar calendar = Calendar.getInstance();
		    SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		   
			try {
					if (TestRun.isMobile())
						scrFile = ((TakesScreenshot) DriverManagerUtil.getAppiumDriver()).getScreenshotAs(OutputType.FILE);
					else if (TestRun.isDesktop())
						scrFile = ((TakesScreenshot) DriverManagerUtil.getWebDriver()).getScreenshotAs(OutputType.FILE);
				
					String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
		            File destFile = new File((String) reportDirectory + "/screenshots/" + "_" + formater.format(calendar.getTime()) + ".png");
		            FileUtils.copyFile(scrFile, destFile);
		            Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
	        } catch (IOException e) {
	        	LoggerUtil.logErrorMessage("Unable to take the screenshot: " + e);
	        }
		}
	}	
}
