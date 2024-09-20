package com.client.app.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.DriverManagerUtil;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class OrangeHRMPage {
	//Login Page
	@FindBy(name = "username")
	public WebElement userNameTxb;
	
	@FindBy(name = "password")
	public WebElement passwordTxb;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement loginBtn;
	
	//Home
	//@FindBy(id = "welcome")

	@FindBy(xpath = "//span[contains(@class,'oxd-userdropdown')]")
	public WebElement welcomeLnk;
	
	//@FindBy(id = "menu_admin_viewAdminModule")
	@FindBy(xpath = "//span[contains(@class,'oxd-text')][text()='Admin']")
	public WebElement adminLnk;
	
	@FindBy(xpath = "//a[text()='Logout']")
	public WebElement logoutLnk;
		
	//Work shifts
	//@FindBy(id = "menu_admin_Job")
	@FindBy(xpath = "//li/span[contains(text(),'Job')]")
	public WebElement jobLnk;
	
//	@FindBy(id = "menu_admin_workShift")
	@FindBy(xpath = "//li/span[contains(text(),'Job')]/following-sibling::ul/li/a[text()='Work Shifts']")
	public WebElement workShiftsLnk;
	
	@FindBy(xpath = "//h6[text()='Work Shifts']")
	public WebElement workShiftsLabel;

	@FindBy(xpath = "//div[@class='oxd-table-header'][@role='rowgroup']")
	public WebElement workShiftsTbl;
	
//	@FindBy(id = "btnAdd")
	@FindBy(xpath = "//button[contains(@class,'oxd-button')]")
	public WebElement addBtn;

	@FindBy(xpath = "//h6[text()='Add Work Shift']")
	public WebElement addWorkShiftLabel;
		
//	@FindBy(id = "workShift_name")
	@FindBy(xpath = "//label[text()='Shift Name']/parent::div/following-sibling::div/input")
	public WebElement shiftNameTxb;

	
	//@FindBy(id = "workShift_workHours_from")
	@FindBy (xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/div/div/input")
	public WebElement workFromTxb;
	
//	@FindBy(id = "workShift_workHours_to")
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/div/div/input")
	public WebElement workToTxb;
	
	//@FindBy(id = "workShift_availableEmp")
//	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[1]/input")
//	public WebElement availableEmployeesLst;

	@FindBy(xpath = "//label[text()='Assigned Employees']/parent::div/following-sibling::div//input")
	public WebElement assignedEmployeesTxb;
	
	//@FindBy(id = "btnAssignEmployee")
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[2]")
	public WebElement assignEmp;

	//@FindBy(id = "btnSave")
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/button[2]")
	public WebElement saveBtn;
	
	//@FindBy(id = "btnDelete")
	@FindBy(xpath = "//div[contains(@class,'orangehrm-horizontal-padding')]//button")
	public WebElement deleteBtn;
	
	//@FindBy(id = "dialogDeleteBtn")
	@FindBy(xpath = "//div[contains(@class,'orangehrm-dialog-popup')][@role='document']//button/i[contains(@class,'trash')]")
	public WebElement dialogDeleteBtn;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[3]/div/div[2]/p")
	public WebElement duration;

	public OrangeHRMPage() {  
		if (TestRun.isMobile())
			PageFactory.initElements(new AppiumFieldDecorator(DriverManagerUtil.getAppiumDriver()), this);
		else if (TestRun.isDesktop())
			PageFactory.initElements(DriverManagerUtil.getWebDriver(), this);
	}
}