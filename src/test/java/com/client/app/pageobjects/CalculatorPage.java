package com.client.app.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sogeti.dia.common.utils.DriverManagerUtil;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WindowsFindBy;


public class CalculatorPage {	
	@FindBy(name = "One")
	public WebElement oneBtn;
	
	@FindBy(name = "Seven")
	public WebElement sevenBtn;
	
	@FindBy(name = "Eight")
	public WebElement eightBtn;
	
	@FindBy(name = "Nine")
	public WebElement nineBtn;
	
	@FindBy(name = "Multiply by")
	public WebElement multiplyByBtn;
	
	@FindBy(name = "Plus")
	public WebElement plusBtn;
	
	@FindBy(name = "Equals")
	public WebElement eualsBtn;
	
	@FindBy(name = "Divide by")
	public WebElement divideByBtn;
	
	@WindowsFindBy(accessibility = "CalculatorResults")
	public WebElement resultTxa;
	
	
	public CalculatorPage() {  		
		PageFactory.initElements(new AppiumFieldDecorator(DriverManagerUtil.getWindowsDriver()), this);
	}
}