package com.client.app.pageobjects;

import java.util.List;
import org.openqa.selenium.support.PageFactory;

import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.DriverManagerUtil;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;



public class EmployeeManagementPage {
	//Login screen
	@AndroidFindBy(id = "com.capgemini.employeeinfo:id/et_username_field")
	@iOSFindBy(id = "UserNameTextField")
	public MobileElement userNameTxb;

	@AndroidFindBy(id = "com.capgemini.employeeinfo:id/et_password_field")
	@iOSFindBy(id = "PasswordTextField")
	public MobileElement passwordTxb;
	
	@AndroidFindBy(id = "com.capgemini.employeeinfo:id/btn_login")
	@iOSFindBy(id = "LoginButton")
	public MobileElement loginBtn;
	
	//Employee List
	@AndroidFindBy(id = "tv_sign_out")
	@iOSFindBy(id = "Sign out")
	public MobileElement signOutBtn;

	@AndroidFindBy(id = "iv_add_user_icon")
	@iOSFindBy(id = "Add")
	public MobileElement addEmployeeBtn;
	
	@AndroidFindBy(id = "tv_employee_name")
	@iOSFindBy(id = "nameLabel")
	public MobileElement empNameTxa;
	
	//Add employee
	@AndroidFindBy(id = "tv_create_emp_cancel")
	@iOSFindBy(id = "Cancel")
	public MobileElement cancelBtn;

	@AndroidFindBy(id = "tv_save_detail")
	@iOSFindBy(id = "Save")
	public MobileElement saveBtn;
		
	@AndroidFindBy(id = "et_first_name")
	public MobileElement firstNameTxb;
	
	@AndroidFindBy(id = "et_last_name")
	public MobileElement lastNameTxb;
	
	@AndroidFindBy(id = "et_age")
	public MobileElement ageTxb;
	
	@AndroidFindBy(id = "et_address")
	public MobileElement addressTxb;
	
	@AndroidFindBy(id = "et_designation")
	public MobileElement designationTxb;
	
	@iOSFindBy(className = "XCUIElementTypeTextField")
	public List<MobileElement> inputFieldsTxb;	
	
	
	public EmployeeManagementPage() {  
		if (TestRun.isAndroid())
			PageFactory.initElements(new AppiumFieldDecorator(DriverManagerUtil.getAndroidDriver()), this);
		else if (TestRun.isIos())
			PageFactory.initElements(new AppiumFieldDecorator(DriverManagerUtil.getIOSDriver()), this);
	}
}