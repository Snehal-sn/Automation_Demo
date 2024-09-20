package com.client.app.uitests.module;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.selenium.fluent.Target;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.applitools.eyes.selenium.Eyes;
import com.client.app.pageobjects.ApplitoolsPage;
import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;
import com.sogeti.dia.common.utils.DriverManagerUtil;
import com.sogeti.dia.common.utils.LoggerUtil;
import com.sogeti.dia.common.utils.WebInteractUtil;

import io.qameta.allure.Description;


public class ApplitoolsVisual_1 extends BaseTest {
	protected ApplitoolsPage applitoolsPage;
	private static final String appName = ApplitoolsVisual_1.class.getSimpleName();
	private static BatchInfo batch;
	private Eyes eyes;

	int counter = 3;

	
	@BeforeMethod(alwaysRun=true)
	public void setUp() {
		CapabilityFactoryUtil.initiateDriver();
		applitoolsPage = new ApplitoolsPage();

		batch = new BatchInfo(appName);

		//Initialize the eyes SDK and set your private API key.
		eyes = new Eyes();

		//Set capabilities
		eyes.setForceFullPageScreenshot(false);
		eyes.setStitchMode(StitchMode.CSS);
		eyes.setMatchLevel(MatchLevel.STRICT);
//		eyes.setIsDisabled(true);

		//Set the API key from the env variable.
		eyes.setApiKey(Config.APPLITOOLS_API_KEY);

		//Start the test by setting AUT's name, window or the page name that's being tested, viewport width and height
		if (TestRun.isMobile())
			eyes.open(DriverManagerUtil.getAppiumDriver(), "Applitools", "HomePage");
		else if (TestRun.isDesktop())
			eyes.open(DriverManagerUtil.getWebDriver(), "Applitools", "HomePage");

	}
	
//	@Description("Verify Orange HRM scenario")
	@Test
	public void applitoolsEyesTest() {

		try {						 
				WebInteractUtil.launchWebApp(Config.APPLITOOLS_APP_URL);
				
				//Visual checkpoint #1.
				eyes.checkWindow("Home Page");

				for (int stepNumber = 0; stepNumber < counter; stepNumber++) {
					WebInteractUtil.click(applitoolsPage.diff1Lnk);

					//Visual checkpoint #2
					eyes.checkWindow("Difference Page-" +stepNumber);
//					eyes.check("Difference Page", Target.region(applitoolsPage.diff1Lnk).matchLevel(MatchLevel.CONTENT));
				}
				WebInteractUtil.click(applitoolsPage.btnClickMe);
				//Visual checkpoint #3
				eyes.checkWindow("After click");
				
				//Navigate to the application
			    LoggerUtil.logMessage("Applitools sample script.");

		} catch (Exception e) {
//			LoggerUtil.logConsoleMessage(e.toString());
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void closeEyes() {
		DriverManagerUtil.stopWebDriver();
		checkApplitoolsResults(eyes);
	}

	public static void checkApplitoolsResults(Eyes eyes) {
		Boolean throwTestCompleteException = false;
		TestResults results = eyes.close(throwTestCompleteException);
		printResults(results);
	}

	static void printResults(TestResults result) {
		LoggerUtil.logConsoleMessage("Visual Testing results: " + result);
		String url = result.getUrl();
		if (result.isNew())
			LoggerUtil.logConsoleMessage("New Baseline Created: URL=" + url);
		else if (result.isPassed())
			LoggerUtil.logConsoleMessage("All steps passed:     URL=" + url);
		else
			LoggerUtil.logConsoleMessage("Test Failed:          URL=" + url);
	}
}