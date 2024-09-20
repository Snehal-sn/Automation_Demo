package com.client.app.common.utils;

import java.util.Map;

import com.client.app.pageobjects.EmployeeManagementPage;
import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.LoggerUtil;
import com.sogeti.dia.common.utils.MobileInteractUtil;
import com.sogeti.dia.common.utils.WebInteractUtil;

public class EmployeeManagementUtil {
		protected EmployeeManagementPage employeeManagementPage;

		public EmployeeManagementUtil() {
			employeeManagementPage = new EmployeeManagementPage();
		}
		
		public boolean loginEmpManagement(Map<String, String> testData) {
			boolean status = false;
			
			MobileInteractUtil.sendKeys(employeeManagementPage.userNameTxb, testData.get("UserName"));
			MobileInteractUtil.sendKeys(employeeManagementPage.passwordTxb, testData.get("Password"));
			
			if (TestRun.isAndroid())
				MobileInteractUtil.closeKeyboard();
			
			MobileInteractUtil.click(employeeManagementPage.loginBtn);
			if (WebInteractUtil.isPresent(employeeManagementPage.signOutBtn, Config.MEDIUM_PAUSE)) {
				LoggerUtil.logMessage("User login successfull.");
				status = true;
			}
			else 
				LoggerUtil.logMessage("User login unsuccessfull.");
			
			return status;
		}
		
		public boolean clickAddEmployeeBtn() {
			boolean status = false;
					
			status = MobileInteractUtil.click(employeeManagementPage.addEmployeeBtn);
			if (status) {
				LoggerUtil.logMessage("Clicked on Add Employee button.");
			}
			else 
				LoggerUtil.logMessage("Could not click on Add Employee button.");
						
			return status;
		}
		
		public void setEmployeeDetails(Map<String, String> testData) {						
			if (TestRun.isAndroid()) {
				MobileInteractUtil.sendKeys(employeeManagementPage.firstNameTxb, testData.get("FirstName"));
				MobileInteractUtil.sendKeys(employeeManagementPage.lastNameTxb, testData.get("LastName"));
				MobileInteractUtil.sendKeys(employeeManagementPage.ageTxb, testData.get("Age"));
				MobileInteractUtil.sendKeys(employeeManagementPage.addressTxb, testData.get("Address"));
				MobileInteractUtil.sendKeys(employeeManagementPage.designationTxb, testData.get("Designation"));					
			}
			else if (TestRun.isIos()) {
				MobileInteractUtil.sendKeys(employeeManagementPage.inputFieldsTxb.get(0), testData.get("FirstName"));
				MobileInteractUtil.sendKeys(employeeManagementPage.inputFieldsTxb.get(1), testData.get("LastName"));
				MobileInteractUtil.sendKeys(employeeManagementPage.inputFieldsTxb.get(2), testData.get("Age"));
				MobileInteractUtil.sendKeys(employeeManagementPage.inputFieldsTxb.get(3), testData.get("Address"));
				MobileInteractUtil.sendKeys(employeeManagementPage.inputFieldsTxb.get(4), testData.get("Designation"));	
			}					
		}		
		
		public boolean clickSaveEmployeeBtn() {
			boolean status = false;
					
			status = MobileInteractUtil.click(employeeManagementPage.saveBtn);
			if (status) {
				LoggerUtil.logMessage("Clicked on Save Employee button.");
			}
			else 
				LoggerUtil.logMessage("Could not click on Save Employee button.");
						
			return status;
		}	
		
		public boolean verifyEmployeeName() {
			boolean status = false;
					
			status = MobileInteractUtil.isPresent(employeeManagementPage.empNameTxa, Config.MEDIUM_PAUSE);
			if (status) {
				LoggerUtil.logMessage("Employee added successfully.");
			}
			else 
				LoggerUtil.logMessage("Employee not added successfully.");
						
			return status;
		}
		
		public boolean clickSignOut() {
			boolean status = false;
					
			status = MobileInteractUtil.click(employeeManagementPage.signOutBtn);
			if (status) {
				LoggerUtil.logMessage("Sign out successfully.");
			}
			else 
				LoggerUtil.logMessage("Sign out is unsuccessfull.");
						
			return status;
		}
}
