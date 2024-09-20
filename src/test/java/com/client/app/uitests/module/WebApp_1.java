package com.client.app.uitests.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.client.app.common.utils.CareSourceUtil;
import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;
import com.sogeti.dia.common.utils.ExcelUtil;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class WebApp_1 extends BaseTest {
	protected CareSourceUtil careSourceUtil;	
	protected static HashMap<Integer, HashMap<String, String>> testData;	
	protected static String className;	
	
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() {		 
		CapabilityFactoryUtil.initiateDriver();		
		careSourceUtil = new CareSourceUtil();
	}
	
	@DataProvider(name = "getData")
	public Iterator<Object[]> getTestData() {
		className = this.getClass().getSimpleName();
		testData = ExcelUtil.getTestData(className);

		ArrayList<Object[]> dataProvider = new ArrayList<Object[]>();
		for (Integer currentKey : testData.keySet()) {
			dataProvider.add(new Object[] { testData.get(currentKey) });
			System.out.println("Data Provider: " + dataProvider);
		}

		return dataProvider.iterator();
	}
		
	@Test(dataProvider = "getData")
	@Severity(SeverityLevel.BLOCKER)
	public void verifyCareSource(HashMap<String, String> testData) {	
		
		careSourceUtil.LaunchApp(testData);
		careSourceUtil.NavigateToFindADoctor();
//		Assert.assertTrue(status, "Login failed.");
		
		careSourceUtil.SearchADoctor(testData);
//		Assert.assertTrue(status, "Appointment could not be booked.");	
		
		careSourceUtil.verifyDoctorInformation(testData);
		
	}
}