package com.sogeti.dia.common.config;

import java.lang.reflect.Method;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.sogeti.dia.common.utils.GalenReporterUtil;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.sogeti.dia.common.utils.AllureManagerUtil;
import com.sogeti.dia.common.utils.DriverManagerUtil;

import static com.mongodb.client.model.Filters.eq;


/**
 * Class for suite setup
 * @author Savita Tambe
 *
 */
public class BaseTest {
	@BeforeSuite(alwaysRun=true)
    public void suiteSetup(ITestContext context) {     			
		if (context.getCurrentXmlTest().getParameter("interfaceType").equalsIgnoreCase("Desktop") && 
    		context.getCurrentXmlTest().getParameter("executionPlatform").equalsIgnoreCase("Local")) {
			DriverManagerUtil.startEdgeDriverService();    	
    		DriverManagerUtil.startSeleniumGrid();    	
    	}
		else if (context.getCurrentXmlTest().getParameter("interfaceType").equalsIgnoreCase("LambdaTest")) 
			DriverManagerUtil.startLambdaTestTunnel();
	}
	
	@BeforeTest(alwaysRun=true)
	public void testSetUp(ITestContext context) {
		TestRun.setInterfaceType(context.getCurrentXmlTest().getParameter("interfaceType"));
		TestRun.setExecutionPlatform(context.getCurrentXmlTest().getParameter("executionPlatform"));	
		TestRun.setAppType(context.getCurrentXmlTest().getParameter("appType"));  
		TestRun.setAppLanguage(context.getCurrentXmlTest().getParameter("appLanguage"));
		TestRun.setAppEnvironment(context.getCurrentXmlTest().getParameter("appEnvironment"));
	
		//Launching existing app
		TestRun.setLaunchExistingApp(context.getCurrentXmlTest().getParameter("launchExistingApp"));
			
		//Common in Android & iOS
		TestRun.setPlatformName(context.getCurrentXmlTest().getParameter("platformName"));		
		TestRun.setPlatformVersion(context.getCurrentXmlTest().getParameter("platformVersion"));
		TestRun.setAutomationName(context.getCurrentXmlTest().getParameter("automationName"));
		TestRun.setUdid(context.getCurrentXmlTest().getParameter("udid"));
		TestRun.setDeviceName(context.getCurrentXmlTest().getParameter("deviceName"));		
		
		//Web Apps
		TestRun.setBrowserName(context.getCurrentXmlTest().getParameter("browserName"));
		TestRun.setBrowserVersion(context.getCurrentXmlTest().getParameter("browserVersion"));			
			
		//iOS
		TestRun.setIOSDeviceType(context.getCurrentXmlTest().getParameter("iOSDeviceType"));
		TestRun.setWDAPort(context.getCurrentXmlTest().getParameter("wdaPort"));
		TestRun.setWebkitDebugProxyPort(context.getCurrentXmlTest().getParameter("webkitDebugProxyPort"));
				
		//iOS Simulator
		TestRun.setLanguage(context.getCurrentXmlTest().getParameter("language"));
		TestRun.setLocale(context.getCurrentXmlTest().getParameter("locale"));
		
		//BrowserStack
		TestRun.setProject(context.getCurrentXmlTest().getParameter("project"));
		TestRun.setBuild(context.getCurrentXmlTest().getParameter("build"));
				
		//Sauce Labs
		TestRun.setName(context.getCurrentXmlTest().getParameter("name"));
		TestRun.setAppiumVersion( context.getCurrentXmlTest().getParameter("appiumVersion"));
		TestRun.setDeviceOrientation(context.getCurrentXmlTest().getParameter("deviceOrientation"));
			
		//TestObject
		TestRun.setAPIKey(context.getCurrentXmlTest().getParameter("apiKey"));
		TestRun.setSuiteName(context.getCurrentXmlTest().getParameter("suiteName"));
		TestRun.setTestName(context.getCurrentXmlTest().getParameter("testName"));	
					
		//Perfecto
		TestRun.setManufacturer(context.getCurrentXmlTest().getParameter("manufacturer"));
		TestRun.setModel(context.getCurrentXmlTest().getParameter("model"));
		
		//Kobiton
		TestRun.setSessionName(context.getCurrentXmlTest().getParameter("sessionName"));
		TestRun.setSessionDesc(context.getCurrentXmlTest().getParameter("sessionDesc"));
				
		//BitBar
		TestRun.setTestRun(context.getCurrentXmlTest().getParameter("testRun"));
				
		//Native & Hybrid Application details
		TestRun.setAndroidAPK(context.getCurrentXmlTest().getParameter("androidAPK"));
		TestRun.setAppPackage(context.getCurrentXmlTest().getParameter("appPackage"));		
		TestRun.setAppActivity(context.getCurrentXmlTest().getParameter("appActivity"));	
		TestRun.setIOSIPA( context.getCurrentXmlTest().getParameter("iOSIPA"));		
		TestRun.setIOSAPP(context.getCurrentXmlTest().getParameter("iOSAPP"));		
		TestRun.setBundleId(context.getCurrentXmlTest().getParameter("bundleId"));
		
		//Parallel Execution
		TestRun.setIPAddress(context.getCurrentXmlTest().getParameter("ipAddress"));	
		
		//Windows app path
		TestRun.setAppPath(context.getCurrentXmlTest().getParameter("appPath"));	
		
		//Localization
		//LocalizationUtil.setLocalizationDetails(AppLanguage);
		//LocalizationUtil.setLocalizationDetailsEng();		
			
		//Start Appium Server
		DriverManagerUtil.startAppiumServer();



	}
	
	@BeforeMethod(alwaysRun=true)
	public void setup(Method method) {	
		TestRun.setScriptName(method.getName());  
	}	
	
	@AfterMethod(alwaysRun=true)
    public void methodTearDown() {     			
		if ((TestRun.isDesktop() || TestRun.isMobile()) && (Config.SCREENSHOT_CAPTURE.equalsIgnoreCase("LastStep")))
			AllureManagerUtil.attachScreenshot();
    	
		DriverManagerUtil.stopWebDriver();
    	DriverManagerUtil.stopAppiumDriver();
		DriverManagerUtil.stopWindowsDriver();
	}

	public void connection(){
		System.out.println("Connection");

		String connectionString = "mongodb://localhost:27017/";

		ServerApi serverApi = ServerApi.builder()
				.version(ServerApiVersion.V1)
				.build();

		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(new ConnectionString(connectionString))
				.serverApi(serverApi)
				.build();


		try (MongoClient mongoClient = MongoClients.create(settings)) {
			try {

				System.out.println("mongoDB");
				Thread.sleep(10000);
				MongoDatabase database = mongoClient.getDatabase("Apriso_Order");

				System.out.println("Yusssss");


				MongoCollection<Document> collection = database.getCollection("Url");
				System.out.println("found database");

				Document ddoc2 = new Document("url", "admin2");
				collection.insertOne(ddoc2);
				System.out.println("Inserted");
				Document doc1 = collection.find().first();
				//String url1 = doc1.toJson();
				System.out.println(doc1.toJson());

				Thread.sleep(10000);
				MongoCursor<Document> cursor = collection.find().cursor();//Mongo Cursor interface implementing the iterator protocol
				System.out.println("found cursor");
				cursor.forEachRemaining(System.out::println);

				Bson filter = Filters.exists("username");

				collection.find(filter).forEach(doc -> System.out.println(doc.toJson()));

				Bson projectionFields = Projections.fields(
						//Projections.include("username", "password"),
						Projections.include("username"),
						Projections.excludeId());
				System.out.println("found me");

				Document doc = collection.find(eq("username", "admin"))
						.projection(projectionFields)
						.sort(Sorts.descending("username"))
						.first();
				System.out.println("found admin");

				System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
			} catch (MongoException | InterruptedException e) {
				e.printStackTrace();

			}
		}

	}

	@AfterSuite(alwaysRun=true)
    public void suiteTeardown() {  
		GalenReporterUtil.generateGalenSuiteReport();
		DriverManagerUtil.stopSeleniumGrid();
		DriverManagerUtil.stopEdgeDriverService();
		DriverManagerUtil.stopAppiumServer();
	}
}