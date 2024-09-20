package com.sogeti.dia.common.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.lambdatest.tunnel.Tunnel;
import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

/**
 * Class to setup Driver instances
 * @author Savita Tambe
 *
 */
public class DriverManagerUtil {
	private static ThreadLocal<RemoteWebDriver> webDriver = new ThreadLocal<RemoteWebDriver>();
	private static ThreadLocal<AndroidDriver<MobileElement>> androidDriver = new ThreadLocal<AndroidDriver<MobileElement>>();
	private static ThreadLocal<AppiumDriver<MobileElement>> appiumDriver = new ThreadLocal<AppiumDriver<MobileElement>>();
	private static ThreadLocal<IOSDriver<MobileElement>> iOSDriver = new ThreadLocal<IOSDriver<MobileElement>>();
	private static ThreadLocal<WindowsDriver<WindowsElement>> windowsDriver = new ThreadLocal<WindowsDriver<WindowsElement>>();
	private static AppiumDriverLocalService appiumDriverLocalService;
	private static EdgeDriverService edgeDriverService;
	private static Tunnel lambdaTestTunnel;
	
	/**********************************************************************************************
     * Sets the Remote WebDriver instance for the running session.
     * 
     * @param driver {@link RemoteWebDriver} - The instance of the driver
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static void setWebDriver(RemoteWebDriver driver) {
        webDriver.set(driver);
    }
    
    /**********************************************************************************************
     * Gets the Remote WebDriver instance for the running session.
     *      
     * @return driver {@link RemoteWebDriver} - The instance of the driver
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static RemoteWebDriver getWebDriver() {
        return webDriver.get();
    }
    
    /**********************************************************************************************
     * Sets the Appium Driver instance for the running session.
     * 
     * @param driver {@link AppiumDriver} - The instance of Appium driver
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static void setAppiumDriver(AppiumDriver<MobileElement> driver) {
        appiumDriver.set(driver);
    }
    
    /**********************************************************************************************
     * Gets the Appium Driver instance for the running session.
     * 
     * @return driver {@link AppiumDriver} - The instance of Appium driver
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static AppiumDriver<MobileElement> getAppiumDriver() {
        return appiumDriver.get();
    }
    
    /**********************************************************************************************
     * Sets the Android Driver instance for the running session.
     * 
     * @param driver {@link AndroidDriver} - The instance of Android driver
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static void setAndroidDriver(AndroidDriver<MobileElement> driver) {
        androidDriver.set(driver);
    }
    
    /**********************************************************************************************
     * Gets the Android Driver instance for the running session.
     * 
     * @return driver {@link AndroidDriver} - The instance of Android driver
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static AndroidDriver<MobileElement> getAndroidDriver() {
        return androidDriver.get();
    }
    
    /**********************************************************************************************
     * Sets the IOS Driver instance for the running session.
     * 
     * @param driver {@link IOSDriver} - The instance of IOS driver
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static void setIOSDriver(IOSDriver<MobileElement> driver) {
        iOSDriver.set(driver);
    }
    
    /**********************************************************************************************
     * Gets the IOS Driver instance for the running session.
     * 
     * @return driver {@link IOSDriver} - The instance of IOS driver
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static IOSDriver<MobileElement> getIOSDriver() {
        return iOSDriver.get();
    }
        
	/**********************************************************************************************
    * Sets the WindowsDriver instance for the running session.
    * 
    * @param driver {@link WindowsDriver} - The instance of the driver
    * @author Savita Tambe created March 27, 2018
    * @version 1.0 March 27, 2018
    ***********************************************************************************************/
   public static void setWindowsDriver(WindowsDriver<WindowsElement> driver) {
	   windowsDriver.set(driver);
   }
   
   /**********************************************************************************************
    * Gets the WindowsDriver instance for the running session.
    *      
    * @return driver {@link WindowsDriver} - The instance of the driver
    * @author Savita Tambe created March 27, 2018
    * @version 1.0 March 27, 2018
    ***********************************************************************************************/
   public static RemoteWebDriver getWindowsDriver() {
       return windowsDriver.get();
   }

   /**********************************************************************************************
	 * Sets the Driver instance for the running session.
	 * 
	 * @param capabilities {@link DesiredCapabilities} - The desired capabilities
	 * @author Savita Tambe created March 27, 2018
	 * @version 1.0 March 27, 2018
	 ***********************************************************************************************/	
	public static void initiateDriver(DesiredCapabilities capabilities) { 		
		String url = null;
		
		switch(TestRun.getExecutionPlatform().toUpperCase())
		{
		   case "LOCAL" :
		   {
			   if (TestRun.isDesktop())	{		
				   if (TestRun.getBrowserName().equalsIgnoreCase("MicrosoftEdge"))
					   url = edgeDriverService.getUrl().toString();
				   else
					   url = Config.GRID_URL;
			   }
			   else if (TestRun.isMobile() || TestRun.isWindows())			
				   url = appiumDriverLocalService.getUrl().toString(); 
				
			   break;
		   }
		   
		   case "LAMBDATEST" :
		   {
				if (TestRun.isDesktop())
					url = Config.LAMBDATEST_URL_DESKTOP;
		   		else
						url = Config.LAMBDATEST_URL_MOBILE;
			   break;
		   }
		   
		   case "BROWSERSTACK" :
		   {
				url = Config.BROWSER_STACK_URL;
				break;
		   }
			
		   case "TESTOBJECT" :
		   {
				url = Config.TEST_OBJECT_URL;
				break;
		   }
		   
		   case "SAUCELABS" :
		   {			   
			   if (TestRun.isDesktop())			
				   url = Config.SAUCELABS_SELENIUM_URL;
			   
			   else if (TestRun.isMobile())	
				   url = Config.SAUCELABS_APPIUM_URL;
			   
			   break;
		   }				
		   
		   case "PERFECTO" :
		   {			  
			   if (TestRun.isDesktop())			
				   url = Config.PERFECTO_URL + "/fast";

			   else if (TestRun.isMobile())	
				   url = Config.PERFECTO_URL;
		
			   break;
		   }
		   			   
		   case "KOBITON" :
		   {
				url = Config.KOBITON_SERVER_URL;
				break;
		   }
			
		   case "PCLOUDY" :
		   {
				url = Config.PCLOUDY_URL;
				break;
		   }
		   
		   case "BITBAR" :
		   {
				url = Config.BITBAR_URL;		
				break;
		   }
		   
		   case "SEETEST" :
		   {
				url = Config.SEETEST_URL;
				break;
		   }		   
		   
		   case "HPEMC" :
		   {
				url = Config.HPE_MC_URL;
				break;
		   }
		}
		
		//Initiate the driver		  
        if (TestRun.isDesktop()) {
        	webDriver(url, capabilities);
        }
        
        else if (TestRun.isMobile() && TestRun.isAndroid()) {
        	androidDriver(url, capabilities);	               
        } 
        
        else if (TestRun.isMobile() && TestRun.isIos()) {
        	iOSDriver(url, capabilities);
        }
        
        else if (TestRun.isWindows()) {
        	windowsDriver(url, capabilities);
        }
	}
	
	/**********************************************************************************************
     * Set Web Driver instance for the running session.
     * 
     * @param url {@link String} - The url
     * @param capabilities {@link DesiredCapabilities} - The desired capabilities
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void webDriver(String url, DesiredCapabilities capabilities) {
		 try {			 	
			 	setWebDriver(new RemoteWebDriver(new URL(url), capabilities));
	     } catch (Exception e) {
	         LoggerUtil.logConsoleMessage("Session could not be created: " + e);
	     }
	     
	     if (webDriver == null) {
	         LoggerUtil.logConsoleMessage("The driver was not properly initiated.");
	     }   
    }
	
	/**********************************************************************************************
     * Set Android Driver instance for the running session.
     * 
     * @param url {@link String} - The url
     * @param capabilities {@link DesiredCapabilities} - The desired capabilities
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void androidDriver(String url, DesiredCapabilities capabilities) {			
		try {
				 appiumDriver.set(new AndroidDriver<MobileElement>(new URL(url), capabilities));
	             setAppiumDriver(appiumDriver.get());
	             androidDriver.set((AndroidDriver<MobileElement>) appiumDriver.get());
	             setAndroidDriver(androidDriver.get());
		 } catch (Exception e) {
			 LoggerUtil.logConsoleMessage("Android Session could not be created: " + e);
         }
		
		 if (appiumDriver == null) {
			 LoggerUtil.logConsoleMessage("The driver was not properly initiated.");
		 } 		 		
    }
	
	/**********************************************************************************************
     * Set iOS Driver instance for the running session.
     * 
     * @param url {@link String} - The url
     * @param capabilities {@link DesiredCapabilities} - The desired capabilities
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void iOSDriver(String url, DesiredCapabilities capabilities) {
		try {
				appiumDriver.set(new IOSDriver<MobileElement>(new URL(url), capabilities));
				setAppiumDriver(appiumDriver.get());
				iOSDriver.set((IOSDriver<MobileElement>) appiumDriver.get());
				setIOSDriver(iOSDriver.get());
		 } catch (Exception e) {
	            LoggerUtil.logConsoleMessage("iOS Session could not be created: " + e);
         }
		
		 if (appiumDriver == null) {
			 LoggerUtil.logConsoleMessage("The driver was not properly initiated.");
		 } 
	}
	
	/**********************************************************************************************
     * Set Windows Driver instance for the running session.
     * 
     * @param url {@link String} - The url
     * @param capabilities {@link DesiredCapabilities} - The desired capabilities
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void windowsDriver(String url, DesiredCapabilities capabilities) {
		 try {				
				setWindowsDriver(new WindowsDriver<WindowsElement>(new URL(url), capabilities));
	     } catch (Exception e) {
	         LoggerUtil.logConsoleMessage("Session could not be created: " + e);
	     }
	     
	     if (windowsDriver == null) {
	         LoggerUtil.logConsoleMessage("The driver was not properly initiated.");
	     }   
    }
	
	/**********************************************************************************************
     * Starts Selenium Grid
     * 
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	@SuppressWarnings("deprecation")
	public static void startSeleniumGrid() {
		try { 
				Runtime.getRuntime().exec("cmd /c start " + Config.SELENIUM_GRID_FILE_PATH); 
	 	} 
		catch (Exception e) {
			LoggerUtil.logErrorMessage("Could not start Selenium grid: " + e);				 
		}
	}	
	
	/**********************************************************************************************
	* Starts Edge Driver Service
	* 
	* @author Savita Tambe created July 24, 2018
	* @version 1.0 July 24, 2018	
	***********************************************************************************************/
	public static void startEdgeDriverService() { 
		edgeDriverService = new EdgeDriverService.Builder()		
			  .usingDriverExecutable(new File(Config.EDGE_DRIVER_PATH))
			  .usingAnyFreePort()
			  .build();
		try {
				edgeDriverService.start();
		} catch (IOException e) {			
			e.printStackTrace();			
		}		
	}
	
	/**********************************************************************************************
	* Starts LambdaTest Tunnel
	* 
	* @author Savita Tambe created July 1, 2020
	* @version 1.0 July 1, 2020	
	***********************************************************************************************/
	public static void startLambdaTestTunnel() { 
		lambdaTestTunnel = new Tunnel();
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user", Config.LAMBDATEST_USER_NAME);
        options.put("key", Config.LAMBDATEST_ACCESS_KEY);

        try {
				lambdaTestTunnel.start(options);
		} catch (Exception e) {		
			e.printStackTrace();
		}					
	}
		/**********************************************************************************************
        * Starts Appium server
        *
        * @author Savita Tambe created July 24, 2018
        * @version 1.0 July 24, 2018
        ***********************************************************************************************/
	public static void startAppiumServer() { 
		if (TestRun.isMobile() || TestRun.isWindows()) {
			if (TestRun.isLocal()) {		
				AppiumServiceBuilder builder = new AppiumServiceBuilder();
				builder.withIPAddress(TestRun.getIPAaddress());
				builder.usingAnyFreePort();	
				builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
				builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
				appiumDriverLocalService = AppiumDriverLocalService.buildService(builder);
				appiumDriverLocalService.start();
				if(appiumDriverLocalService == null || !appiumDriverLocalService.isRunning()) {
					throw new AppiumServerHasNotBeenStartedLocallyException("An Appium server node is not installed");
				}	
			}
		}	
	}
	
	/**********************************************************************************************
     * Stops Web Driver instance for the running session.
     * 
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void stopWebDriver() {
		if (TestRun.isDesktop()) {
			try {
				 	getWebDriver().close();
			} catch (Exception e) {}
				 
			try {
					getWebDriver().quit();
			} catch (Exception e) {}
				
			LoggerUtil.logConsoleMessage("Closing the browser.");
		}		
	}
	
	/**********************************************************************************************
     * Stops Appium Driver instance for the running session.
     * 
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void stopAppiumDriver() {    	
		if (TestRun.isMobile()) {
			try {
					if (TestRun.isNativeHybrid()) 
						getAppiumDriver().closeApp();
					try {					
							getAppiumDriver().close();
					} catch (Exception e) {}	
					
					getAppiumDriver().quit();	
			} catch (Exception e) {}
			
			LoggerUtil.logConsoleMessage("Closing the mobile app.");
		}
	}
		
	/**********************************************************************************************
     * Stops Windows Driver instance for the running session.
     * 
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void stopWindowsDriver() {
		if (TestRun.isWindows()) {
			try {
				 	getWindowsDriver().close();
			} catch (Exception e) {}
				 
			try {
					getWindowsDriver().quit();
			} catch (Exception e) {}
				
			LoggerUtil.logConsoleMessage("Closing windows application.");
		}		
	}
	
	/**********************************************************************************************
     * Stops Selenium Grid
     * 
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	@SuppressWarnings("deprecation")
	public static void stopSeleniumGrid() {
		try { 
				Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
				
				String script = Config.KILL_DRIVERS_FILE_PATH;
				String executable = Config.WSCRIPT_FILE_PATH; 
				String cmdArr [] = {executable, script};
				Runtime.getRuntime ().exec(cmdArr);
	 	} 
		catch (Exception e) {
			LoggerUtil.logErrorMessage("Could not stop Selenium grid: " + e);				 
		}
	}
	
	/**********************************************************************************************
	* Stops Edge Driver Service
	* 
	* @author Savita Tambe created July 24, 2018
	* @version 1.0 July 24, 2018
	***********************************************************************************************/
	public static void stopEdgeDriverService() {  
		try {
				if (edgeDriverService != null)	
					edgeDriverService.stop();
		} catch (Exception e) {}
	}
	
	/**********************************************************************************************
	* Stops LambdaTest Tunnel
	* 
	* @author Savita Tambe created July 24, 2018
	* @version 1.0 July 24, 2018
	***********************************************************************************************/
	public static void stopLambdaTestTunnel() {  
		try {				
				lambdaTestTunnel.stop();
		} catch (Exception e) {}
	}
	
	/**********************************************************************************************
	* Stops Appium server
	* 
	* @author Savita Tambe created July 24, 2018
	* @version 1.0 July 24, 2018
	***********************************************************************************************/
	public static void stopAppiumServer() {  
		try {
				if (appiumDriverLocalService != null)	
					appiumDriverLocalService.stop();
		} catch (Exception e) {}
	}
}