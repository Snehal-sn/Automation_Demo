package com.client.app.uitests.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.client.app.common.utils.OrangeHRMUtil;
import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;
import com.sogeti.dia.common.utils.ExcelUtil;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class WebApp_2 extends BaseTest {
	protected OrangeHRMUtil orangeHRMUtil;	
	protected static HashMap<Integer, HashMap<String, String>> testData;	
	protected static String className;	
	
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() {		 
		CapabilityFactoryUtil.initiateDriver();
		
		orangeHRMUtil = new OrangeHRMUtil();
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
	public void verifyShiftTest(HashMap<String, String> testData) {
		boolean status = orangeHRMUtil.loginToOrangeHRM(testData);
		Assert.assertTrue(status, "Login failed.");
		
		status = orangeHRMUtil.navigateToWorkShiftsModule();
		Assert.assertTrue(status, "Work shift module navigation failed.");
		
		String shiftName = orangeHRMUtil.getUniqueShiftName(testData);
		status = orangeHRMUtil.setWorkShiftDetails(testData, shiftName);
	//	Assert.assertTrue(status, "Work shift details not set properly.");
		
		status = orangeHRMUtil.deleteWorkShift(shiftName);
		Assert.assertTrue(status, "Work shift not deleted.");

		status = orangeHRMUtil.logOutOrangeHRM();
		Assert.assertTrue(status, "Logout failed");
	}
}