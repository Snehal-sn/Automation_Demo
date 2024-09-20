package com.client.app.uitests.module;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.client.app.common.utils.CalculatorUtil;
import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;
import com.sogeti.dia.common.utils.ExcelUtil;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


class WindowsApp_1 extends BaseTest {
	protected CalculatorUtil calculatorUtil;	
	protected static HashMap<Integer, HashMap<String, String>> testData;	
	protected static String className;	
	
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() {		 
		CapabilityFactoryUtil.initiateDriver();
		
		calculatorUtil = new CalculatorUtil();
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
	public void verifyCalculatorTest(HashMap<String, String> testData) {	
		String result  = calculatorUtil.combination();
		Assert.assertEquals(result, "Display is 8");
	}
}

//Note:
//Search for settings on widnows -> For Developer Settings -> Developer mode 
//The Windows Driver supports testing of Universal Windows Platform (UWP) and Classic Windows (Win32) applications.
//https://github.com/microsoft/WinAppDriver/tree/v1.0