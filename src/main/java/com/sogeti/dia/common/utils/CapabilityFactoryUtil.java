package com.sogeti.dia.common.utils;

import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

/**
 * Class to set desired capabilities
 * @author Savita Tambe
 *
 */
public class CapabilityFactoryUtil {
	 /**********************************************************************************************
     * To set the Desired Capabilities for local execution
     *
     * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/	
    public static DesiredCapabilities localDesktop() {
    	DesiredCapabilities capabilities = new DesiredCapabilities();	
       

     	if (TestRun.getBrowserName().equalsIgnoreCase("Chrome")) {    	  							   	    	     	 		
     		capabilities.setBrowserName("chrome");
			capabilities.setVersion(TestRun.getBrowserVersion());
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);    	    	
	     	capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);	
	     	
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);							 
			
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--test-type");
			options.addArguments("--disable-extensions");
			options.addArguments("start-maximized");
			options.addArguments("--use-fake-ui-for-media-stream=1");			             
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);	        		
     	}
     
     	else if (TestRun.getBrowserName().equalsIgnoreCase("Firefox")) {
     		capabilities.setBrowserName("firefox");
     		capabilities.setVersion(TestRun.getBrowserVersion());
     		     		
     		FirefoxOptions options = new FirefoxOptions();
     		options.setBinary(Config.FIREFOX_BINARY);
     		capabilities.merge(options);
     	}
     	
    	else if (TestRun.getBrowserName().equalsIgnoreCase("MicrosoftEdge")) {
    		 capabilities.setVersion(TestRun.getBrowserVersion());
    		 EdgeOptions options = new EdgeOptions();    		 
    		 capabilities.merge(options);
     	}
     	
    	else if (TestRun.getBrowserName().equalsIgnoreCase("IE")) {   
     		capabilities.setBrowserName("internet explorer");
     		capabilities.setVersion(TestRun.getBrowserVersion());
     		
     		InternetExplorerOptions options = new InternetExplorerOptions();
     		options.setCapability("unexpectedAlertBehaviour", "accept");
     		options.setCapability("nativeEvents", false);
     		options.setCapability("requireWindowFocus", true);
     		options.setCapability("javascriptEnabled", true);
     		options.setCapability("ensureCleanSession", true);
     		options.setCapability("disable-popup-blocking", true);
     		options.enablePersistentHovering();
     		options.ignoreZoomSettings();
     		options.introduceFlakinessByIgnoringSecurityDomains();
     		capabilities.merge(options);
       	}
        	
     	return capabilities;    	     
    }
	
    /**********************************************************************************************
     * To set the Desired Capabilities for LambdaTest execution
     * 
     * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
     * @author Savita Tambe created Jul 1, 2020
     * @version 1.0 July 1, 2020
     ***********************************************************************************************/	
     public static DesiredCapabilities lambdaTestDesktop() {
    	DesiredCapabilities capabilities = new DesiredCapabilities();

    	capabilities.setCapability("build", TestRun.getBuild());
		capabilities.setCapability("name", TestRun.getScriptName());
	//	capabilities.setCapability("platform", TestRun.getPlatformName());
		capabilities.setCapability("browserName", TestRun.getBrowserName());
		capabilities.setCapability("browserVersion", TestRun.getBrowserVersion());
		//capabilities.setCapability("tunnel", true);
		//capabilities.setCapability("network", true);
		//capabilities.setCapability("video", true);
		//capabilities.setCapability("visual", true);
		//capabilities.setCapability("console", true);
       
		return capabilities;
    }
     
    /**********************************************************************************************
     * To set the Desired Capabilities for Browserstack execution
     * 
     * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/	
     public static DesiredCapabilities browserStackDesktop() {
    	DesiredCapabilities capabilities = new DesiredCapabilities();

    	capabilities.setCapability("project", TestRun.getProject());   	    		
		capabilities.setCapability("build", TestRun.getBuild());   
		capabilities.setCapability("name", TestRun.getScriptName());
    	capabilities.setCapability("os", TestRun.getPlatformName());
		capabilities.setCapability("os_version", TestRun.getPlatformVersion());
		capabilities.setCapability("browser", TestRun.getBrowserName());
		capabilities.setCapability("browser_version", TestRun.getBrowserVersion());
		capabilities.setCapability("browserstack.local", "false");
		capabilities.setCapability("browserstack.selenium_version", "3.5.2");
		
		return capabilities;
     }
     
     /**********************************************************************************************
      * To set the Desired Capabilities for local execution
      * 
      * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
      * @author Savita Tambe created March 27, 2018
      * @version 1.0 March 27, 2018
      ***********************************************************************************************/	
     public static DesiredCapabilities sauceLabsDesktop() {         
    	 DesiredCapabilities capabilities = new DesiredCapabilities();
     	
    	 if (TestRun.getBrowserName().equalsIgnoreCase("Chrome"))   
    		capabilities.setCapability("browserName", "Chrome");
		
		 else if (TestRun.getBrowserName().equalsIgnoreCase("Firefox"))  
    		capabilities.setCapability("browserName", "Firefox");
			
		 else if (TestRun.getBrowserName().equalsIgnoreCase("IE"))
			capabilities.setCapability("browserName", "Internet Explorer");
    			
		capabilities.setCapability("name", TestRun.getName());
		capabilities.setCapability("platform", TestRun.getPlatformName());
       	capabilities.setCapability("version", TestRun.getBrowserVersion());
		
       	return capabilities;
     }     
     
     /**********************************************************************************************
      * To set the Desired Capabilities for Perfecto execution
      *
      * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
      * @author Savita Tambe created March 27, 2018
      * @version 1.0 March 27, 2018
      ***********************************************************************************************/	
	 public static DesiredCapabilities perfectoDesktop() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		 
		capabilities.setCapability("securityToken", Config.PERFECTO_SECURITY_TOKEN);
		capabilities.setCapability("platformName", TestRun.getPlatformName());
		capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());
		capabilities.setCapability("browserName", TestRun.getBrowserName());
		capabilities.setCapability("browserVersion", TestRun.getBrowserVersion());
		capabilities.setCapability("resolution", "1280x1024");
		capabilities.setCapability("location", "US East");
		
		return capabilities;
	 }		
	 
	/**********************************************************************************************
	 * To set the Desired Capabilities for Seetest execution
	 * 
	 * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
	 * @author Savita Tambe created March 27, 2018
	 * @version 1.0 March 27, 2018
	 ***********************************************************************************************/	
	public static DesiredCapabilities seeTestDesktop() {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("accessKey", Config.SEETEST_ACCESS_KEY);
   		capabilities.setCapability("platformName", TestRun.getPlatformName());
   		
   		if (TestRun.getBrowserName().equalsIgnoreCase("Chrome"))   
   			capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
   		else if (TestRun.getBrowserName().equalsIgnoreCase("Firefox"))   
   			capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
   		else if (TestRun.getBrowserName().equalsIgnoreCase("IE"))   
   			capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.IE);
   		else if (TestRun.getBrowserName().equalsIgnoreCase("Safari"))
   			capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.SAFARI);
   		else if (TestRun.getBrowserName().equalsIgnoreCase("Edge"))   
   			capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.EDGE);
   		
   		capabilities.setCapability(CapabilityType.BROWSER_VERSION,TestRun.getBrowserVersion());
   		capabilities.setCapability("testName", TestRun.getName());
		
		return capabilities;	  
	}
	
	/**********************************************************************************************
	 * To set the Desired Capabilities for local execution
	 * 
	 * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
	 * @author Savita Tambe created March 27, 2018
	 * @version 1.0 March 27, 2018
	 ***********************************************************************************************/

	public static DesiredCapabilities localMobile() {
		DesiredCapabilities capabilities = new DesiredCapabilities();

    	if (TestRun.isNativeHybrid() && TestRun.isAndroid()) {
		   if (!TestRun.isLaunchExistingApp())
    			capabilities.setCapability("app", Config.ANDROID_APP_PATH + TestRun.getAndroidAPK());
    		
			capabilities.setCapability("appPackage", TestRun.getAppPackage());
			capabilities.setCapability("appActivity", TestRun.getAppActivity());
			//capabilities.setCapability("noReset", false);
			//capabilities.setCapability("fullReset", true);		
    	}
    	
    	else if (TestRun.isNativeHybrid() && TestRun.isIos()) {	    	    		
	    	//capabilities.setCapability("noReset", true);				
			capabilities.setCapability("showXcodeLog", true);
			
			if(TestRun.getIOSDeviceType().equalsIgnoreCase("Real") && !TestRun.isLaunchExistingApp())
				capabilities.setCapability("app", Config.IOS_REAL_DEVICE_APP_PATH + TestRun.getIOSIPA());		    			   				    								
			
			else if (TestRun.getIOSDeviceType().equalsIgnoreCase("Simulator")) {
				if (!TestRun.isLaunchExistingApp())
					capabilities.setCapability("app", Config.IOS_SIMULATOR_APP_PATH + TestRun.getIOSAPP());
				capabilities.setCapability("language", TestRun.getLanguage());
				capabilities.setCapability("locale", TestRun.getLocale());
			}
			
			capabilities.setCapability("bundleId", TestRun.getBundleId()); 
 		}
 		
 		else if (TestRun.isWeb()) {    			
 			if (TestRun.isIos()) {	
    			capabilities.setCapability("startIWDP", "true");
				capabilities.setCapability("wdaLocalPort", TestRun.getWDAPort());		    			
				capabilities.setCapability("webkitDebugProxyPort", TestRun.getWebkitDebugProxyPort());
    			capabilities.setCapability("safariAllowPopups", true);
 			}
 			capabilities.setCapability("browserName", TestRun.getBrowserName());
 			//capabilities.setCapability("autoWebview", "true");
 		}
	    	
    	if (TestRun.isAndroid()) {
	    	capabilities.setCapability("disableAndroidWatchers", true);
	 		capabilities.setCapability("autoGrantPermission", true);
	 		capabilities.setCapability("skipUnlock", true);
	 		capabilities.setCapability("ignoreUnimportantViews", true);
	 		capabilities.setCapability("skipDeviceInitialization", true);
	 		capabilities.setCapability("skipServerInstallation", true);	 	
    	}
    	
		if (TestRun.isIos()) {
			capabilities.setCapability("useNewWDA", false);			
			capabilities.setCapability("usePrebuiltWDA", true);
			capabilities.setCapability("realDeviceScreenshotter", "idevicescreenshot");
		}
		
		//Common for Android & iOS
	    capabilities.setCapability("automationName", TestRun.getAutomationName());
	    capabilities.setCapability("platformName", TestRun.getPlatformName());		
	    capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());
 		capabilities.setCapability("udid", TestRun.getUdid());
 		capabilities.setCapability("deviceName", TestRun.getDeviceName());		
		capabilities.setCapability("mjpegScreenshotUrl", "http://localhost:8080/stream.mjpeg");
	    return capabilities;

    }
    
    /**********************************************************************************************
     * To set the Desired Capabilities for Browserstack execution
     * 
     * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/	
	public static DesiredCapabilities browserStackMobile() {
		DesiredCapabilities capabilities = new DesiredCapabilities();	
    	
    	if (TestRun.isAndroid()) {	    		
	    	if (TestRun.isNativeHybrid()) {
				capabilities.setCapability("app", TestRun.getAndroidAPK());
				capabilities.setCapability("appPackage", TestRun.getAppPackage());
				capabilities.setCapability("appActivity", TestRun.getAppActivity());
	    	}
	    		 			
	    	//Speed Up Test Execution
    		capabilities.setCapability("browserstack.appium_version", "1.17.0");
    		capabilities.setCapability("noReset", true);
    		capabilities.setCapability("disableAndroidWatchers", true);
    		capabilities.setCapability("autoGrantPermission", true);
    		capabilities.setCapability("skipUnlock", true);    	
    		capabilities.setCapability("ignoreUnimportantViews", true);    		
    		capabilities.setCapability("skipDeviceInitialization", true);
    		capabilities.setCapability("skipServerInstallation", true);    	    	    			 
	    }
		 
    	else if (TestRun.isIos()) {
    		if (TestRun.isNativeHybrid()) {
	    		capabilities.setCapability("app", TestRun.getIOSIPA());	    			   
				capabilities.setCapability("bundleId", TestRun.getBundleId()); 	    				        			   
    		}
    		    		 	
			//Speed Up Test Execution			
			capabilities.setCapability("browserstack.appium_version", "1.15.0");
			capabilities.setCapability("noReset", true);
			capabilities.setCapability("usePrebuiltWDA", true);
			capabilities.setCapability("realDeviceScreenshotter", "idevicescreenshot");
			//capabilities.setCapability("useJSONSource", true); - Should work for large apps, for small apps execution takes 10 Sec extra
		}
		
    	if (TestRun.isWeb()) {
			capabilities.setCapability("browserName", TestRun.getBrowserName());
			//Local Testing
			//capabilities.setCapability("browserstack.local", "true");   
			//capabilities.setCapability("browserstack.localIdentifier", "Test123");
		}
		       	
		capabilities.setCapability("project", TestRun.getProject());   	    		
		capabilities.setCapability("build", TestRun.getBuild());   
		capabilities.setCapability("name", TestRun.getScriptName());
		capabilities.setCapability("os", TestRun.getPlatformName());
		capabilities.setCapability("os_version", TestRun.getPlatformVersion());	    		 
		capabilities.setCapability("device", TestRun.getDeviceName());
		capabilities.setCapability("real_mobile", "true");
		capabilities.setCapability("browserstack.debug", "true");
		capabilities.setCapability("browserstack.networkLogs", "true");   	    				
		capabilities.setCapability("automationName", TestRun.getAutomationName());
		
		return capabilities;
    }    
    
    /**********************************************************************************************
 	  * To set the Desired Capabilities for Testobject execution
 	  *
 	  * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
 	  * @author Savita Tambe created March 27, 2018
 	  * @version 1.0 March 27, 2018
 	  ***********************************************************************************************/	
     public static DesiredCapabilities testObjectMobile() {    	 
    	 DesiredCapabilities capabilities = new DesiredCapabilities();	
    	 
    	 if (TestRun.isWeb())			    	
     		capabilities.setCapability("browserName", TestRun.getBrowserName());
 		
 		capabilities.setCapability("testobject_api_key", TestRun.getAPIKey());  	    		
 		capabilities.setCapability("testobject_suite_name", TestRun.getSuiteName());
 		capabilities.setCapability("testobject_test_name", TestRun.getTestName());
 		capabilities.setCapability("platformName", TestRun.getPlatformName());
 		capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());
 		capabilities.setCapability("deviceName", TestRun.getDeviceName());	
 		//capabilities.setCapability("testobject_device", TestRun.getDeviceName());	
		
 		return capabilities;
     }
     
     /**********************************************************************************************
   	 * To set the Desired Capabilities for Saucelabs execution
   	 * 
   	 * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
   	 * @author Savita Tambe created March 27, 2018
   	 * @version 1.0 March 27, 2018
   	 ***********************************************************************************************/	
    public static DesiredCapabilities sauceLabsMobile() {
    	DesiredCapabilities capabilities = new DesiredCapabilities();	
       	
    	if (TestRun.isNativeHybrid() && TestRun.isAndroid())
    		capabilities.setCapability("app", TestRun.getAndroidAPK());        						   
    	else if (TestRun.isNativeHybrid() && TestRun.isIos())
	    	capabilities.setCapability("app", TestRun.getIOSAPP());
		else if (TestRun.isWeb())
			capabilities.setCapability("browserName", TestRun.getBrowserName());		
		
		capabilities.setCapability("name", TestRun.getName());
		capabilities.setCapability("appiumVersion", TestRun.getAppiumVersion());
		capabilities.setCapability("platformName", TestRun.getPlatformName()); 
		capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());
		capabilities.setCapability("deviceName", TestRun.getDeviceName());
		capabilities.setCapability("deviceOrientation", TestRun.getDeviceOrientation());			    	
		capabilities.setCapability("automationName", TestRun.getAutomationName());
		
		return capabilities;		
    }
      
    /**********************************************************************************************
     * To set the Desired Capabilities for Perfecto execution
     *
     * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
     * @author Savita Tambe created March 27, 2018
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/	
    public static DesiredCapabilities perfectoMobile() {
    	DesiredCapabilities capabilities = new DesiredCapabilities();	
   	  
   	  	if (TestRun.isNativeHybrid() && TestRun.isAndroid()) {    			        	    		
   	  		capabilities.setCapability("app", TestRun.getAndroidAPK());
	   		capabilities.setCapability("appPackage", TestRun.getAppPackage());		
	   		capabilities.setCapability("automationName", "Appium"); 
	   	}
			
	   	else if (TestRun.isNativeHybrid() && TestRun.isIos()) {	    
	   		capabilities.setCapability("app", TestRun.getIOSIPA());	    			   
	   		capabilities.setCapability("bundleId", TestRun.getBundleId()); 	    				        			   
	   	}
				
		else if (TestRun.isWeb())
			capabilities.setCapability("browserName", TestRun.getBrowserName());
		
		//Common for Android & iOS
		capabilities.setCapability("securityToken", Config.PERFECTO_SECURITY_TOKEN);
		capabilities.setCapability("platformName", TestRun.getPlatformName());
		//capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());				
		capabilities.setCapability("manufacturer", TestRun.getManufacturer());    		
		//capabilities.setCapability("model", TestRun.getModel());	
		//capabilities.setCapability("deviceName", TestRun.getDeviceName());
		capabilities.setCapability("autoInstrument", true);
		
		return capabilities;	
    }
    
    /**********************************************************************************************
  	  * To set the Desired Capabilities for Kobiton execution
  	  * 
  	  * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
  	  * @author Savita Tambe created March 27, 2018
  	  * @version 1.0 March 27, 2018
  	***********************************************************************************************/	
    public static DesiredCapabilities kobitonMobile() {
    	 DesiredCapabilities capabilities = new DesiredCapabilities();	
    	 
    	 if (TestRun.isNativeHybrid() && TestRun.isAndroid()) {   			        	    		
    		 capabilities.setCapability("app", TestRun.getAndroidAPK());
    		 capabilities.setCapability("appPackage", TestRun.getAppPackage());
    		 capabilities.setCapability("appActivity", TestRun.getAppActivity());
    	 }
	
    	else if (TestRun.isNativeHybrid() && TestRun.isIos()) {
			capabilities.setCapability("app", TestRun.getIOSIPA());
			capabilities.setCapability("bundleId", TestRun.getBundleId()); 	    				        			   
    	 }   			
			
  		else if (TestRun.isWeb())
  			capabilities.setCapability("browserName", TestRun.getBrowserName());
  		
  		capabilities.setCapability("sessionName", TestRun.getSessionName());
  		capabilities.setCapability("sessionDescription", TestRun.getSessionDesc());
  		capabilities.setCapability("platformName", TestRun.getPlatformName());
  		capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());
  		capabilities.setCapability("deviceOrientation",  TestRun.getDeviceOrientation());
  		capabilities.setCapability("deviceGroup", "KOBITON");
  		capabilities.setCapability("deviceName", TestRun.getDeviceName());
		
  		return capabilities;			    	
     }
      
     /**********************************************************************************************
      * To set the Desired Capabilities for Pcloudy execution
      * 
      * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
      * @author Savita Tambe created March 27, 2018
      * @version 1.0 March 27, 2018
      ***********************************************************************************************/	
     public static DesiredCapabilities pcloudyMobile() {	
    	 DesiredCapabilities capabilities = new DesiredCapabilities();	

     	 if (TestRun.isNativeHybrid() && TestRun.isAndroid()) {
     		  capabilities.setCapability("pCloudy_ApplicationName", TestRun.getAndroidAPK());
     		  capabilities.setCapability("appPackage", TestRun.getAppPackage());
     		  capabilities.setCapability("appActivity", TestRun.getAppActivity());  
   		 }
     	  
     	 else if (TestRun.isNativeHybrid() && TestRun.isIos()) {	
     		  capabilities.setCapability("pCloudy_ApplicationName", TestRun.getIOSIPA());	
     		  capabilities.setCapability("bundleId", TestRun.getBundleId());
     		  capabilities.setCapability("usePrebuiltWDA", false);
     		  capabilities.setCapability("acceptAlerts", true);
		 }
		
		 if (TestRun.isWeb())
			  capabilities.setBrowserName(TestRun.getBrowserName());					 
		  
		 if (TestRun.isIos())  		    			
			 capabilities.setCapability("automationName", TestRun.getAutomationName());	
		  
		 capabilities.setCapability("pCloudy_Username", Config.PCLOUDY_USER_NAME);
		 capabilities.setCapability("pCloudy_ApiKey", Config.PCLOUDY_ACCESS_KEY);  			    		  		    		
		 capabilities.setCapability("pCloudy_DeviceManafacturer", TestRun.getManufacturer());
		 //capabilities.setCapability("pCloudy_DeviceFullName", TestRun.getDeviceName());	
		 //capabilities.setCapability("pCloudy_DeviceVersion", TestRun.getPlatformVersion());	
		 capabilities.setCapability("pCloudy_DurationInMinutes", 10);
		 capabilities.setCapability("newCommandTimeout", 600);
		 capabilities.setCapability("launchTimeout", 90000);
		 
		 return capabilities;	
     }
       
     /**********************************************************************************************
      * To set the Desired Capabilities for Bitbar execution
      * 
      * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
      * @author Savita Tambe created March 27, 2018
      * @version 1.0 March 27, 2018
      ***********************************************************************************************/	
     public static DesiredCapabilities bitbarMobile() {    
    	  DesiredCapabilities capabilities = new DesiredCapabilities();	
    	  
    	  if (TestRun.isNativeHybrid())
    		  capabilities.setCapability("testdroid_target", TestRun.getPlatformName());    		    	  
    	  
    	  else if (TestRun.isWeb()) {
    		  capabilities.setCapability("testdroid_target", TestRun.getBrowserName());
    		  capabilities.setCapability("browserName", TestRun.getBrowserName());
    	  }
    	  
    	  if (TestRun.isNativeHybrid() && TestRun.isAndroid())    			        	    		
			  capabilities.setCapability("bitbar_app", TestRun.getAndroidAPK());    				    
		  else if (TestRun.isNativeHybrid() && TestRun.isIos()) {	    
			  capabilities.setCapability("bitbar_app", TestRun.getIOSIPA());
			  capabilities.setCapability("bundleId", TestRun.getBundleId());
		  }		
    	  
    	  if (TestRun.isIos()) {  
    		  capabilities.setCapability("automationName", TestRun.getAutomationName());	
    	  }
			
    	  capabilities.setCapability("testdroid_apiKey", Config.TESTDROID_API_KEY);  
    	  capabilities.setCapability("testdroid_project", TestRun.getProject()); 
    	  capabilities.setCapability("testdroid_testrun", TestRun.getTestRun());			
    	  capabilities.setCapability("platformName", TestRun.getPlatformName());
    	  capabilities.setCapability("testdroid_device", TestRun.getDeviceName());    	    						
    	  capabilities.setCapability("deviceName", TestRun.getDeviceName());		
    	  capabilities.setCapability("testdroid_findDevice", "true");
    	  capabilities.setCapability("testdroid_testTimeout", Config.XLARGE_PAUSE);
    	  capabilities.setCapability("newCommandTimeout", Config.XLARGE_PAUSE);

    	  return capabilities;
     }

     /**********************************************************************************************
      * To set the Desired Capabilities for Seetest execution
      * 
      * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
      * @author Savita Tambe created March 27, 2018
      * @version 1.0 March 27, 2018
      ***********************************************************************************************/	
     public static DesiredCapabilities seeTestMobile() {	      
    	 DesiredCapabilities capabilities = new DesiredCapabilities();	
        	 
    	 if (TestRun.isAndroid())
    		 capabilities.setCapability("deviceQuery", "@os='android' and @category='PHONE'"); // + TestRun.getPlatformVersion() + "'");
    	 else if (TestRun.isIos())
    		 capabilities.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
	
		 if (TestRun.isNativeHybrid() && TestRun.isAndroid()) {
    		capabilities.setCapability(MobileCapabilityType.APP, TestRun.getAndroidAPK());
			capabilities.setCapability("appPackage", TestRun.getAppPackage());
			capabilities.setCapability("appActivity", TestRun.getAppActivity());
		 }
    	   
    	 else if (TestRun.isNativeHybrid() && TestRun.isIos()) {	
			capabilities.setCapability(MobileCapabilityType.APP, TestRun.getIOSIPA());
			capabilities.setCapability("bundleId", TestRun.getBundleId()); 					
    	 }
        	
		 if (TestRun.isWeb() && TestRun.isAndroid())
        	capabilities.setBrowserName(MobileBrowserType.CHROMIUM);
		 else if (TestRun.isWeb() && TestRun.isIos())
        	capabilities.setBrowserName(MobileBrowserType.SAFARI);    			        	 
  
		 capabilities.setCapability("testName", TestRun.getTestName());
		 capabilities.setCapability("accessKey", Config.SEETEST_ACCESS_KEY);
	
		 return capabilities;  
     }
    
     /**********************************************************************************************
      * To set the Desired Capabilities for Microfocus MC execution
      * 
      * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
      * @author Savita Tambe created March 27, 2018
      * @version 1.0 March 27, 2018
      ***********************************************************************************************/	
     public static DesiredCapabilities microfocusMC() {	
    	  DesiredCapabilities capabilities = new DesiredCapabilities();	

    	  if (TestRun.isNativeHybrid() && TestRun.isAndroid()) {	    		
    		  capabilities.setCapability("appPackage", TestRun.getAppPackage());
    		  capabilities.setCapability("appActivity", TestRun.getAppActivity());
    		  //capabilities.setCapability("noReset", false);
    		  //capabilities.setCapability("fullReset", true);	
    	  }
   	  
    	  else if (TestRun.isNativeHybrid() && TestRun.isIos()) {	    	    		
    		  //capabilities.setCapability("showXcodeLog", true);	
    		  //capabilities.setCapability("usePrebuiltWDA", true);
    		  //capabilities.setCapability("noReset", true);
    		  //capabilities.setCapability("useNewWDA", false);
    		  capabilities.setCapability("bundleId", TestRun.getBundleId()); 
    	  }				
		
    	  if (TestRun.isWeb())			
    		capabilities.setCapability("browserName", TestRun.getBrowserName());

		capabilities.setCapability("userName", Config.HPE_MC_USERNAME);  
		capabilities.setCapability("password", Config.HPE_MC_PASSWORD);						
		capabilities.setCapability("platformName", TestRun.getPlatformName());
		capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());
		capabilities.setCapability("deviceName", TestRun.getDeviceName());		
		
		return capabilities;    	       
     }
         
     /**********************************************************************************************
      * To set the Desired Capabilities for LambdaTest execution
      * 
      * @return capabilities {@link DesiredCapabilities} - DesiredCapabilities object
      * @author Savita Tambe created Jul 1, 2020
      * @version 1.0 July 1, 2020
      ***********************************************************************************************/

     public static DesiredCapabilities lambdaTestMobile() {
     	DesiredCapabilities capabilities = new DesiredCapabilities();
		 if (TestRun.isNativeHybrid() && TestRun.isAndroid()) {
			 capabilities.setCapability("app", TestRun.getAndroidAPK());
			 //capabilities.setCapability("appPackage", TestRun.getAppPackage());
			 //capabilities.setCapability("appActivity", TestRun.getAppActivity(
		 }
		 else if (TestRun.isNativeHybrid() && TestRun.isIos()) {
				 // capabilities.setCapability("bundleId", TestRun.getBundleId());
				 capabilities.setCapability("app", TestRun.getIOSIPA());
			 }
			 if (TestRun.isWeb()) {
				 capabilities.setCapability("browserName", TestRun.getBrowserName());
			 }
			 capabilities.setCapability("name", TestRun.getScriptName());
			 capabilities.setCapability("platformName", TestRun.getPlatformName());
			 capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());
			 capabilities.setCapability("isRealMobile", TestRun.getisRealMobile());
		     capabilities.setCapability("deviceName", TestRun.getDeviceName());
			 //	capabilities.setCapability("browserName", TestRun.getBrowserName());
			 //capabilities.setCapability("tunnel", true);
			 //capabilities.setCapability("network", true);
			 //capabilities.setCapability("video", true);
			 //capabilities.setCapability("visual", true);
			 //capabilities.setCapability("console", true);

 		return capabilities;
     }
      
     /**********************************************************************************************
	 * To set the Desired Capabilities
	 * 
	 * @author Savita Tambe created March 27, 2018
	 * @version 1.0 March 27, 2018
	 ***********************************************************************************************/	
     public static void initiateDriver() {
    	DesiredCapabilities capabilities = new DesiredCapabilities();	
	
    	if (TestRun.isDesktop()) {    	
			switch(TestRun.getExecutionPlatform().toUpperCase())
	    	{
	    		case "LOCAL" :
	    		{    	    		   
	    		   	capabilities = localDesktop();
    	     	   	break;
	    		}
	    		
	    		case "LAMBDATEST" :
	    		{    	    		   
	    		   	capabilities = lambdaTestDesktop();
    	     	   	break;
	    		}
	
	    	   	case "BROWSERSTACK" :
	    	   	{
	    	   		capabilities = browserStackDesktop();
	    			break;
	    		}
	    	   	
	    	    case "SAUCELABS" :
	    	    {   
	    	    	capabilities = sauceLabsDesktop(); 			    		
		    		break;
	    	    }
	    	    
	    	    case "PERFECTO" :
	    	   	{
	    	   		capabilities = perfectoDesktop();
	    			break;
	    	   	}
 		
	    	   	case "SEETEST" :
	    	   	{
	    	   		capabilities = seeTestDesktop();
	    			break;
	    	   	} 
	    	   	
	    	   	default:
	    		   	capabilities = localDesktop();
	    	} 		    	    	    
		}
    	
    	else if (TestRun.isMobile()) {
	    	switch(TestRun.getExecutionPlatform().toUpperCase())
	    	{
	    		case "LOCAL" :
		    	{
		    		capabilities = localMobile();
			    	break;
		    	}
	    		case "LAMBDATEST" :
	    		{    	    		   
	    		   	capabilities = lambdaTestMobile();
    	     	   	break;
	    		}
		    	   			    			    		    
	    		case "BROWSERSTACK" :
	    		{   		    	    	    		    		    					    		
	    			capabilities = browserStackMobile();
		    		break;
		    	} 	
	    		    	
	    		case "SAUCELABS" :
	    		{ 		 
	    			capabilities = sauceLabsMobile();			    		
		    		break;
		    	}	
	    	
	    		case "TESTOBJECT" :
	    		{			    				    
	    			capabilities = testObjectMobile();
	    			 break;
		    	}
	    		    		    	
	    		case "PERFECTO":
	   		 	{
	   		 		capabilities = perfectoMobile();
					break;
		    	} 	
		    	
	    		case "KOBITON":
	    		{	 			    				    	
	    			capabilities = kobitonMobile();
	    			break;
	    		} 
	    			
	   		 	case "PCLOUDY":
	   		 	{		   		 				    				
	   		 		capabilities = pcloudyMobile();
	   		 		break;
	   		 	}
		        	   			    			   		 
	   			case "BITBAR":
	    		{							        	    					
	    			capabilities = bitbarMobile();
					break;
		    	}
	    	
	   		 	case "SEETEST":
	   		 	{			    		 
	   		 		capabilities = seeTestMobile();
		    		 break;
		    	}
		    	
	   		 	case "MICROFOCUSMC":
	   		 	{
	   		 		capabilities = microfocusMC();		
		    		break;
		    	}
	   		 	
	   		 	default :
		    		capabilities = localMobile();
	    	}		    	
	    }
    		
    	else if (TestRun.isWindows()) {
            capabilities.setCapability("platformName", "Windows");
            capabilities.setCapability("deviceName", "WindowsPC");	    			
			capabilities.setCapability("app", TestRun.getAppPath());
		}
		
		DriverManagerUtil.initiateDriver(capabilities);
    }  
}