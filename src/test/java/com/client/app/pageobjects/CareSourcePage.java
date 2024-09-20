package com.client.app.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.DriverManagerUtil;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.List;


public class CareSourcePage {
	
	@FindBy(xpath="(//a[text()='Find A Doctor'])[1]")
	public WebElement FindDocLnk;
	
	@FindBy(id="i-am")
	public WebElement Iam;
	
	@FindBy(id="i-want-to")
	public WebElement IwantTo;
	
	@FindBy(xpath="//button[contains(text(),'Guide Me')]")
	public WebElement GuideMeBtn;
	
	@FindBy(xpath="//input[contains(@title,'Enter Physician Name')]")
	public WebElement PhysicianNameTxb;
	
	@FindBy(id="address")
	public WebElement AddressTxb;
			
	@FindBy(xpath="//button[@title='Submit']")
	public WebElement SubmitBtn;
			
	@FindBy(xpath="//i[@title='Click to remove message']")
	public WebElement PopupCloseBtn;

	@FindBy(xpath="//div[@id='doctors']")
	public WebElement Doctors;
			
	@FindBy(xpath="//div[@id='doctors']//descendant::li")
	public By DoctorsList;
	
	@FindBy(xpath="//strong[@class='doctor-name']")
	public WebElement DocName;
		
	@FindBy(xpath="//dt[contains(text(),'Specialties')]//following-sibling::dd")
	public By DocSpeciality;
	
	@FindBy(xpath="//dt[contains(text(),'Language(s)')]//following-sibling::dd")
	public By DocLanguage;
	
	@FindBy(id="progress")
	public WebElement Progress;


	public String DocListName="(//div[@id='doctors']//descendant::li//strong[@class='doctor-name'])[1]";
	public String DocListAdd="(//div[@id='doctors']//descendant::li//address)";
	public String DocListSpec="(//div[@id='doctors']//descendant::li//dt[contains(text(),'Specialties')]//following-sibling::dd)";
	public String DocListLang="(//div[@id='doctors']//descendant::li//dt[contains(text(),'Language(s)')]//following-sibling::dd)";
	
	
	public CareSourcePage() {  
		if (TestRun.isMobile())
			PageFactory.initElements(new AppiumFieldDecorator(DriverManagerUtil.getAppiumDriver()), this);
		else if (TestRun.isDesktop())
			PageFactory.initElements(DriverManagerUtil.getWebDriver(), this);
	}
}