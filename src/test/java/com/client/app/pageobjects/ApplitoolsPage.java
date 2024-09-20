package com.client.app.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.DriverManagerUtil;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class ApplitoolsPage {
	//Home Page
	@FindBy(xpath = "//a[text()='?diff1']")
	public WebElement diff1Lnk;
	
	@FindBy(xpath = "//a[text()='?diff2']")
	public WebElement diff2Lnk;

	@FindBy(xpath = "//div/button[text()='Click me!']")
	public WebElement btnClickMe;

	
	public ApplitoolsPage() {  
		if (TestRun.isMobile())
			PageFactory.initElements(new AppiumFieldDecorator(DriverManagerUtil.getAppiumDriver()), this);
		else if (TestRun.isDesktop())
			PageFactory.initElements(DriverManagerUtil.getWebDriver(), this);
	}
}