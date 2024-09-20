package com.client.app.uitests.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.client.app.common.utils.EmployeeManagementUtil;
import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;
import com.sogeti.dia.common.utils.ExcelUtil;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class MobileNativeApp_1 extends BaseTest{
	protected EmployeeManagementUtil employeeManagementUtil;	
	protected static HashMap<Integer, HashMap<String, String>> testData;	
	protected static String className;	
	
	
	@BeforeMethod(alwaysRun=true)
	public void setup() {		
	   CapabilityFactoryUtil.initiateDriver();
	   		 			  			
	   employeeManagementUtil = new EmployeeManagementUtil();
	}
		
	@DataProvider(name = "getData")
	public Iterator<Object[]> getTestData() {
		className = this.getClass().getSimpleName();
		testData = ExcelUtil.getTestData(className);

		ArrayList<Object[]> dataProvider = new ArrayList<>();
		for (Integer currentKey : testData.keySet()) {
			dataProvider.add(new Object[] { testData.get(currentKey) });
		}

		return dataProvider.iterator();
	}
		
	@Test(dataProvider = "getData") 
	@Severity(SeverityLevel.BLOCKER)
	public void verifyEmpManagementTest(HashMap<String, String> testData) {
		boolean status = employeeManagementUtil.loginEmpManagement(testData);
		Assert.assertTrue(status, "Login failed.");
		
		status = employeeManagementUtil.clickAddEmployeeBtn();
		Assert.assertTrue(status, "Could not click on Add Employee button.");
		
		employeeManagementUtil.setEmployeeDetails(testData);
		
		status = employeeManagementUtil.clickSaveEmployeeBtn();
		Assert.assertTrue(status, "Could not click on Save Employee button.");
	
		status = employeeManagementUtil.verifyEmployeeName();
		Assert.assertTrue(status, "Employee is not saved.");
	}
}
