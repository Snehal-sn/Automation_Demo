package com.sogeti.dia.common.config;

/**
 * Class to declare constants
 * @author Savita Tambe
 *
 */
public class Config {
	//Desktop Grid details
	public static final String GRID_HUB_IP = "localhost";
	public static final String GRID_HUB_PORT = "4443";
	public static final String GRID_URL = "http://" + GRID_HUB_IP + ":" + GRID_HUB_PORT + "/wd/hub";
	
	//Browser Stack
	public static final String BROWSER_STACK_USER_NAME = "";
	public static final String BROWSER_STACK_ACCESS_KEY = "";
	public static final String BROWSER_STACK_URL = "https://" + BROWSER_STACK_USER_NAME + ":" + BROWSER_STACK_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	//SauceLabs details
	public static final String SAUCELABS_USER_NAME = "";
	public static final String SAUCELABS_ACCESS_KEY = "";
	public static final String SAUCELABS_APPIUM_URL = "http://" + SAUCELABS_USER_NAME + ":" + SAUCELABS_ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";	
	public static final String SAUCELABS_SELENIUM_URL = "http://" + SAUCELABS_USER_NAME + ":" + SAUCELABS_ACCESS_KEY + "@ondemand.saucelabs.com/wd/hub";	
		
	//TestObject details - US data center
	public static final String TEST_OBJECT_URL = "http://us1.appium.testobject.com/wd/hub";	
	
	//Perfecto details
	public static final String PERFECTO_USER_NAME = ""; 
	public static final String PERFECTO_SECURITY_TOKEN = ""; 
	public static final String PERFECTO_HOST = "partners.perfectomobile.com";
	public static final String PERFECTO_URL = "https://" + PERFECTO_HOST + "/nexperience/perfectomobile/wd/hub";		

	//pCloudy
	public static final String PCLOUDY_USER_NAME = "";
	public static final String PCLOUDY_ACCESS_KEY = "";
	public static final String PCLOUDY_URL = "https://device.pcloudy.com/appiumcloud/wd/hub";	
	
	//Kobiton
	public static final String KOBITON_SERVER_URL = "";	
		
	//BitBar details
	public static final String TESTDROID_API_KEY = "";			
	public static final String BITBAR_URL = "http://appium.bitbar.com/wd/hub";
				
	//SeeTest
	public static final String SEETEST_ACCESS_KEY = "";
	public static final String SEETEST_URL = "https://cloud.seetest.io/wd/hub";		
					
	//HPE Mobile Center
	public static final String HPE_MC_URL = "";	
	public static final String HPE_MC_USERNAME = "";
	public static final String HPE_MC_PASSWORD = "";
	
	//LambdaTest
	public static final String LAMBDATEST_USER_NAME = "";
	public static final String LAMBDATEST_ACCESS_KEY = "";
	//Lambda mobileapp/browser
	public static final String LAMBDATEST_URL_DESKTOP = "https://" + LAMBDATEST_USER_NAME + ":" + LAMBDATEST_ACCESS_KEY + "@mobile-hub.lambdatest.com/wd/hub";
	//Lambda desktop browser
	public static final String LAMBDATEST_URL_MOBILE = "https://" + LAMBDATEST_USER_NAME + ":" + LAMBDATEST_ACCESS_KEY + "@hub.lambdatest.com/wd/hub";

	//Applitools API key
	public static final String APPLITOOLS_API_KEY = "";
		
	//Voice Automation
	public static final String VOICE_RSS_API_KEY = "";
	public static final String VOICERSS_BASEURL = "https://api.voicerss.org/?";
	
	//Allure Screenshot capture
	public static final String SCREENSHOT_CAPTURE = "EveryStep"; //EveryStep or LastStep
	
	//RemoteWebdriver Config
	public static final int MAX_WAIT_TIME = 180;
	public static final int POLLING_TIME = 500;		
	public static final int XSMALL_PAUSE = 3;
	public static final int SMALL_PAUSE = 10;
	public static final int MEDIUM_PAUSE = 30;
	public static final int LARGE_PAUSE = 60;
	public static final int XLARGE_PAUSE = 120;
	
	//File Paths
	public static final String FIREFOX_BINARY = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	public static final String EDGE_DRIVER_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\grid\\msedgedriver.exe";
	public static final String SELENIUM_GRID_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\grid\\SeleniumGrid.bat";
	public static final String TEST_DATA_FILE_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\TestData.xlsx";
	public static final String LOCALIZATION_FOLDER_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\localization\\";
	public static final String GSPEC_FILE_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\specs\\";
	public static final String WSCRIPT_FILE_PATH = "C:\\Windows\\SysWOW64\\wscript.exe";
	public static final String KILL_DRIVERS_FILE_PATH = System.getProperty("user.dir")+"\\src\\main\\resources\\grid\\killDrivers.vbs";
	
	//App Config
	public static final String ANDROID_APP_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\apps\\";
	public static final String IOS_REAL_DEVICE_APP_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\apps\\";
	public static final String IOS_SIMULATOR_APP_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\apps\\";
	
	//Web App	
	public static final String APPLITOOLS_APP_URL = "https://applitools.com/helloworld";
	public static final String GALEN_APP_URL = "https://www.swtestacademy.com/";	
//	public static final String API_TEST_URL = "http://restapi.demoqa.com/utilities/weather/city";
	public static final String API_BASE_URL = "https://buyer-tenantcommunicatorproxy-qas-eus2.azurewebsites.net/api";
	public static final String API_PASSWORD = "eyJhbGciOiJSUzI1NiIsImtpZCI6IkMwRThFNjZBNkRBQzE1QzQxQzQ2MzgzMjE2QkIwM0IyQjdBREFBQzJSUzI1NiIsInR5cCI6ImF0K2p3dCIsIng1dCI6IndPam1hbTJzRmNRY1JqZ3lGcnNEc3JldHFzSSJ9.eyJuYmYiOjE2MjAzMjY3ODIsImV4cCI6MTYyMDM2OTk4MiwiaXNzIjoiaHR0cHM6Ly9xYXMtbG9naW4uaWFhaS5jb20iLCJjbGllbnRfaWQiOiJJQUFCdXllckFwcCIsInN1YiI6IjAzYTQxZDU1LWM5MjItNDIzNy05ZmQxLTA1NjljNTU4MGQyMSIsImF1dGhfdGltZSI6MTYyMDMyNjc4MSwiaWRwIjoibG9jYWwiLCJBc3BOZXQuSWRlbnRpdHkuU2VjdXJpdHlTdGFtcCI6IkFNNE5BNUFSMjRNUzVMRzJFWFZPR1VYUTNXWlFOQU9VIiwicHJlZmVycmVkX3VzZXJuYW1lIjoibGluZGVyc0B0ZXN0LmNvbSIsIm5hbWUiOiJsaW5kZXJzQHRlc3QuY29tIiwiZW1haWwiOiJsaW5kZXJzQHRlc3QuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcnN0bmFtZSI6Iktlbm5ldGgiLCJsYXN0bmFtZSI6IkxpbmRlciIsImp0aSI6IjQzRDQ3QjMzMTYzQkI2RjlFODdDRUM5RkM5RjgyNTYyIiwic2lkIjoiQUE1N0NCMDkwRDc5Q0U4RUQxRTRBOTA4OTA3RDI4NUUiLCJpYXQiOjE2MjAzMjY3ODIsInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiLCJlbWFpbCIsIm9mZmxpbmVfYWNjZXNzIl0sImFtciI6WyJwd2QiXX0.cmD3b06yhJ0uzUFbH2TfRVOl5rRLjGxGT5RG8wA-OYWpli7s-CvVKBp2F1fAYAy5xj3x6wxfa4SSikdao5eO6zwY0PBGzFso6Xi-m_3NTlbHNmrGqUMus25gVhZwY-PLFAHe1XYqAE0H_r7K8nSRWEdXYBUVtJEOrHLfUfwbdE_6Zy5PXcCKyOLipAytgeo-zWUImXJKp1phIBDeQTDZov2x4usIRc0rrOAmeZbMoaZgb3B6YrVi1WeJbZCpci7Y0iMGQm7xOJEgKLtr2G_xDNzoLF8ibuChlfMAUv_FlnCzfkNdBK_iXIaQiHnlG9dA0ZhahGDf1B4zzF1vvyFFGQ";


	//DB Config	
	public static final String DB_HOST = "";
	public static final String DB_PORT = "";
	public static final String DB_SID = "";
	public static final String DB_USER_ID = "";
	public static final String DB_PASSWORD = "";
}