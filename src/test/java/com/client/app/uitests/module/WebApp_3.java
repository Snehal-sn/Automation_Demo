package com.client.app.uitests.module;

import com.client.app.common.utils.SwagLabsUtil;
import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;
import com.sogeti.dia.common.utils.ExcelUtil;
import com.sogeti.dia.common.utils.WebInteractUtil;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class WebApp_3 extends BaseTest {
	protected SwagLabsUtil swagLabsUtil;
	protected static HashMap<Integer, HashMap<String, String>> testData;	
	protected static String className;	
	
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() {		 
		CapabilityFactoryUtil.initiateDriver();

		swagLabsUtil = new SwagLabsUtil();
	}
	
	@DataProvider(name = "getData")
	public Iterator<Object[]> getTestData() {
		className = this.getClass().getSimpleName();
		testData = ExcelUtil.getTestData(className);

		ArrayList<Object[]> dataProvider = new ArrayList<Object[]>();
		for (Integer currentKey : testData.keySet()) {
			dataProvider.add(new Object[] { testData.get(currentKey) });
		}

		return dataProvider.iterator();
	}
		
	@Test(dataProvider = "getData") 
	@Severity(SeverityLevel.BLOCKER)
	public void verifySwagLabsLoginPage(HashMap<String, String> testData) throws InterruptedException {
		boolean status = swagLabsUtil.loginToSwagLabs(testData);
		Assert.assertTrue(status, "Login failed.");

		status = swagLabsUtil.addProductToCart(testData.get("Product Name"));
		Assert.assertTrue(status, "Add to Cart feature failed");

		swagLabsUtil.checkout(testData);
		Assert.assertTrue(status, "Checkout feature failed.");

		swagLabsUtil.verifyPurchaseDetails(testData);

	}
}