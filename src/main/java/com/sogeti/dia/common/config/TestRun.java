package com.sogeti.dia.common.config;

/**
 * Class to declare constants
 * @author Savita Tambe
 *
 */
public class TestRun {	
	//Common for Android & iOS
	private static ThreadLocal<String> interfaceType = new ThreadLocal<String>();		
	private static ThreadLocal<String> executionPlatform = new ThreadLocal<String>();	
	private static ThreadLocal<String> appType = new ThreadLocal<String>();
	private static ThreadLocal<String> appLanguage = new ThreadLocal<String>();
	private static ThreadLocal<String> appEnvironment = new ThreadLocal<String>();
	private static ThreadLocal<String> launchExistingApp = new ThreadLocal<String>();
    private static ThreadLocal<String> isRealMobile = new ThreadLocal<String>();
	private static ThreadLocal<String> platformName = new ThreadLocal<String>();
	private static ThreadLocal<String> platformVersion = new ThreadLocal<String>();
	private static ThreadLocal<String> automationName = new ThreadLocal<String>();
	private static ThreadLocal<String> udid = new ThreadLocal<String>();
	private static ThreadLocal<String> deviceName = new ThreadLocal<String>();
	private static ThreadLocal<String> browserName = new ThreadLocal<String>();	
	private static ThreadLocal<String> browserVersion = new ThreadLocal<String>();	

	//iOS
	private static ThreadLocal<String> iOSDeviceType = new ThreadLocal<String>();
	private static ThreadLocal<String> wdaPort = new ThreadLocal<String>();
	private static ThreadLocal<String> webkitDebugProxyPort = new ThreadLocal<String>();
	
	//iOS - Simulator
	private static ThreadLocal<String> language = new ThreadLocal<String>();
	private static ThreadLocal<String> locale = new ThreadLocal<String>();
	
	//BrowserStack
	private static ThreadLocal<String> project = new ThreadLocal<String>();
	private static ThreadLocal<String> build = new ThreadLocal<String>();
	private static ThreadLocal<String> scriptName = new ThreadLocal<String>();
	
	//SauceLabs
	private static ThreadLocal<String> name = new ThreadLocal<String>();
	private static ThreadLocal<String> appiumVersion = new ThreadLocal<String>();
	private static ThreadLocal<String> deviceOrientation = new ThreadLocal<String>();

	//TestObjcet
	private static ThreadLocal<String> apiKey = new ThreadLocal<String>();
	private static ThreadLocal<String> suiteName = new ThreadLocal<String>();
	private static ThreadLocal<String> testName = new ThreadLocal<String>();
	
	//Perfecto
	private static ThreadLocal<String> manufacturer = new ThreadLocal<String>();
	private static ThreadLocal<String> model = new ThreadLocal<String>();
					
	//Kobiton
	private static ThreadLocal<String> sessionName = new ThreadLocal<String>();
	private static ThreadLocal<String> sessionDesc = new ThreadLocal<String>();
		
	//BitBar
	private static ThreadLocal<String> testRun = new ThreadLocal<String>();

	//Native & Hybrid Application details
	private static ThreadLocal<String> androidAPK = new ThreadLocal<String>();
	private static ThreadLocal<String> appPackage = new ThreadLocal<String>();
	private static ThreadLocal<String> appActivity = new ThreadLocal<String>();
	private static ThreadLocal<String> iOSIPA = new ThreadLocal<String>();
	private static ThreadLocal<String> iOSAPP = new ThreadLocal<String>();	
	private static ThreadLocal<String> bundleId = new ThreadLocal<String>();

	//Parallel execution
	private static ThreadLocal<String> ipaddress = new ThreadLocal<String>();
	
	//Windows
	private static ThreadLocal<String> appPath = new ThreadLocal<String>();
				
	 
	public static void setInterfaceType(String interfaceType) {
        TestRun.interfaceType.set(interfaceType);
    }
    
    public static String getInterfaceType() {
        return TestRun.interfaceType.get();
    }
    
    public static Boolean isMobile() {
    	return getInterfaceType().equalsIgnoreCase("Mobile");
    }
    
    public static Boolean isDesktop() {
    	return getInterfaceType().equalsIgnoreCase("Desktop");
    }
    
    public static Boolean isWindows() {
    	return getInterfaceType().equalsIgnoreCase("Windows");
    }
    
    public static Boolean isApi() {
    	return getInterfaceType().equalsIgnoreCase("Api");
    }
    	
    public static void setExecutionPlatform(String executionPlatform) {
        TestRun.executionPlatform.set(executionPlatform);
    }
    
    public static String getExecutionPlatform() {
    	return executionPlatform.get();
    }
    
    public static Boolean isLocal() {
    	return getExecutionPlatform().equalsIgnoreCase("Local");
    }
    
    public static Boolean isBrowserStack() {
    	return getExecutionPlatform().equalsIgnoreCase("BrowserStack");
    }
    
    public static Boolean isSauceLabs() {
    	return getExecutionPlatform().equalsIgnoreCase("SauceLabs");
    }
    
    public static Boolean isTestObject() {
    	return getExecutionPlatform().equalsIgnoreCase("TestObject");
    }
    
    public static Boolean isPerfecto() {
    	return getExecutionPlatform().equalsIgnoreCase("Perfecto");
    }
    
    public static Boolean ispCloudy() {
    	return getExecutionPlatform().equalsIgnoreCase("pCloudy");
    }
        
    public static Boolean isKobiton() {
    	return getExecutionPlatform().equalsIgnoreCase("Kobiton");
    }
      
    public static Boolean isBitBar() {
    	return getExecutionPlatform().equalsIgnoreCase("BitBar");
    }
    
    public static Boolean isSeeTest() {
    	return getExecutionPlatform().equalsIgnoreCase("SeeTest");
    }
    
    public static Boolean isMicrofocusMC() {
    	return getExecutionPlatform().equalsIgnoreCase("MicrofocusMC");
    } 
    
    public static Boolean isLambdaTest() {
    	return getExecutionPlatform().equalsIgnoreCase("LambdaTest");
    }    
        
    public static void setAppType(String appType) {
        TestRun.appType.set(appType);
    }
    
    public static String getAppType() {
    	return appType.get();
    }
    
    public static Boolean isNativeHybrid() {
    	boolean status = false;
    	if (getAppType().equalsIgnoreCase("Native") || getAppType().equalsIgnoreCase("Hybrid")) 
    		status = true;
    	 
    	return status;
    }
        
    public static Boolean isWeb() {
    	return getAppType().equalsIgnoreCase("Web");
    } 
    
    public static void setAppLanguage(String appLanguage) {
        TestRun.appLanguage.set(appLanguage);
    }
    
    public static String getAppLanguage() {
        return TestRun.appLanguage.get();
    } 
    
    public static void setAppEnvironment(String appEnvironment) {
        TestRun.appEnvironment.set(appEnvironment);
    }
    
    public static String getAppEnvironment() {
    	return appEnvironment.get();
    }

   public static void setLaunchExistingApp(String launchExistingApp) {
        TestRun.launchExistingApp.set(launchExistingApp);
    }
	
    public static String getLaunchExistingApp() {
        return TestRun.launchExistingApp.get();
    }
    
    public static Boolean isLaunchExistingApp() {
        return getLaunchExistingApp().equalsIgnoreCase("Yes");
    }
    public static void setPlatformName(String platformName) {
        TestRun.platformName.set(platformName);
    }
    
    public static String getPlatformName() {
        return TestRun.platformName.get();
    }
    public static void setisRealMobile(String isRealMobile) {
        TestRun.isRealMobile.set(isRealMobile);
    }

    public static String getisRealMobile() {
        return TestRun.isRealMobile.get();
    }

    public static Boolean isRealMobile() {
        return getisRealMobile().equalsIgnoreCase("Yes");
    }

    public static Boolean isIos() {
    	return getPlatformName().equalsIgnoreCase("IOS");
    }
    
    public static Boolean isAndroid() {
    	return getPlatformName().equalsIgnoreCase("ANDROID");
    }  
    
    public static void setPlatformVersion(String platformVersion) {
        TestRun.platformVersion.set(platformVersion);
    }
    
    public static String getPlatformVersion() {
        return TestRun.platformVersion.get();
    } 
    
    public static void setAutomationName(String automationName) {
        TestRun.automationName.set(automationName);
    }
    
    public static String getAutomationName() {
        return TestRun.automationName.get();
    }          
    
    public static void setUdid(String udid) {
        TestRun.udid.set(udid);
    }
   
    public static String getUdid() {
        return TestRun.udid.get();
    }
    
    public static String getDeviceName() {
        return TestRun.deviceName.get();
    }
    
    public static void setDeviceName(String deviceName) {
        TestRun.deviceName.set(deviceName);
    }
    
    public static void setBrowserName(String browserName) {
        TestRun.browserName.set(browserName);
    }
    
    public static String getBrowserName() {
        return TestRun.browserName.get();
    }    
    
    public static void setBrowserVersion(String browserVersion) {
        TestRun.browserVersion.set(browserVersion);
    }
    
    public static String getBrowserVersion() {
        return TestRun.browserVersion.get();
    }  
    
    public static void setIOSDeviceType(String iOSDeviceType) {
        TestRun.iOSDeviceType.set(iOSDeviceType);
    }
    
    public static String getIOSDeviceType() {
    	return TestRun.iOSDeviceType.get();
    }
    
    public static void setWDAPort(String wdaPort) {
        TestRun.wdaPort.set(wdaPort);
    }
    
    public static String getWDAPort() {
        return TestRun.wdaPort.get();
    }
    
    public static void setWebkitDebugProxyPort(String webkitDebugProxyPort) {
        TestRun.webkitDebugProxyPort.set(webkitDebugProxyPort);
    }
    
    public static String getWebkitDebugProxyPort() {
        return TestRun.webkitDebugProxyPort.get();
    }
    
	public static void setLanguage(String language) {
        TestRun.language.set(language);
    }
    
    public static String getLanguage() {
        return TestRun.language.get();
    }
    
    public static void setLocale(String locale) {
        TestRun.locale.set(locale);
    }
    
    public static String getLocale() {
        return TestRun.locale.get();
    }

    public static void setProject(String project) {
        TestRun.project.set(project);
    }
    
    public static String getProject() {
        return TestRun.project.get();
    }
    
    public static void setBuild(String build) {
        TestRun.build.set(build);
    }
    
    public static String getBuild() {
        return TestRun.build.get();
    }
    
    public static void setScriptName(String scriptName) {
        TestRun.scriptName.set(scriptName);
    }
    
    public static String getScriptName() {
        return TestRun.scriptName.get();
    }
    
    public static void setName(String name) {
        TestRun.name.set(name);
    }
    
    public static String getName() {
        return TestRun.name.get();
    }
    
    public static void setAppiumVersion(String appiumVersion) {
        TestRun.appiumVersion.set(appiumVersion);
    }
    
    public static String getAppiumVersion() {
        return TestRun.appiumVersion.get();
    }
    
    public static void setDeviceOrientation(String deviceOrientation) {
        TestRun.deviceOrientation.set(deviceOrientation);
    }
    
    public static String getDeviceOrientation() {
        return TestRun.deviceOrientation.get();
    }
    
    public static void setAPIKey(String apiKey) {
        TestRun.apiKey.set(apiKey);
    }
    
    public static String getAPIKey() {
        return TestRun.apiKey.get();
    }
    
    public static void setSuiteName(String suiteName) {
        TestRun.suiteName.set(suiteName);
    }
    
    public static String getSuiteName() {
        return TestRun.suiteName.get();
    }
    
    public static void setTestName(String testName) {
        TestRun.testName.set(testName);
    }
    
    public static String getTestName() {
        return TestRun.testName.get();
    }
    
    public static void setManufacturer(String manufacturer) {
        TestRun.manufacturer.set(manufacturer);
    }
    
    public static String getManufacturer() {
        return TestRun.manufacturer.get();
    }
    
	public static void setModel(String model) {
        TestRun.model.set(model);
    }
    
    public static String getModel() {
        return TestRun.model.get();
    }
    
    public static void setSessionName(String sessionName) {
        TestRun.sessionName.set(sessionName);
    }
    
    public static String getSessionName() {
        return TestRun.sessionName.get();
    }
    
    public static void setSessionDesc(String sessionDesc) {
        TestRun.sessionDesc.set(sessionDesc);
    }
    
    public static String getSessionDesc() {
        return TestRun.sessionDesc.get();
    }
    
    public static void setTestRun(String testRun) {
        TestRun.testRun.set(testRun);
    }
    
    public static String getTestRun() {
        return TestRun.testRun.get();
    }
			
    public static void setAndroidAPK(String androidAPK) {
        TestRun.androidAPK.set(androidAPK);
    }
    
    public static String getAndroidAPK() {
        return TestRun.androidAPK.get();
    }
    
    public static void setAppPackage(String appPackage) {
        TestRun.appPackage.set(appPackage);
    }
    
    public static String getAppPackage() {
        return TestRun.appPackage.get();
    }
    
    public static void setAppActivity(String appActivity) {
        TestRun.appActivity.set(appActivity);
    }
    
    public static String getAppActivity() {
        return TestRun.appActivity.get();
    }
    
    public static void setIOSIPA(String iOSIPA) {
        TestRun.iOSIPA.set(iOSIPA);
    }
    
    public static String getIOSIPA() {
        return TestRun.iOSIPA.get();
    }
    
    public static void setIOSAPP(String iOSAPP) {
        TestRun.iOSAPP.set(iOSAPP);
    }
    
    public static String getIOSAPP() {
        return TestRun.iOSAPP.get();
    }
    
    public static void setBundleId(String bundleId) {
        TestRun.bundleId.set(bundleId);
    }
    
    public static String getBundleId() {
        return TestRun.bundleId.get();
    }
    
    public static void setIPAddress(String ipaddress) {
        TestRun.ipaddress.set(ipaddress);
    }
    
    public static String getIPAaddress() {
        return TestRun.ipaddress.get();
    }
    
    public static void setAppPath(String appPath) {
        TestRun.appPath.set(appPath);
    }
    
    public static String getAppPath() {
        return TestRun.appPath.get();
    }   
}