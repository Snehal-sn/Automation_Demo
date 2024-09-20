package com.client.app.pageobjects;

import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.DriverManagerUtil;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SwagLabsPage {
	//Login Page
	@FindBy(xpath ="//input[@id='user-name']")
	public WebElement userNameTxb;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement passwordTxb;

	@FindBy(xpath = "//*[@id='login-button']")
	public WebElement loginBtn;

	@FindBy(xpath = "//span[text()='Products']")
	public WebElement productsLbl;

	@FindBy(xpath = "//*[@id=\"item_4_title_link\"]/div")
	public WebElement productlink;

	@FindBy(xpath = "//button[text()='Add to cart']")
	public WebElement addtocart;

	@FindBy(xpath = "//span[@class='shopping_cart_badge']")
	public WebElement cartQty;

	@FindBy(id = "shopping_cart_container")
	public WebElement cart;

	@FindBy(xpath = "//span[text()='Your Cart']")
	public WebElement yourCartLbl;

	@FindBy(id = "checkout")
	public WebElement checkout;


	@FindBy(xpath = "//*[@id=\"first-name\"]")
	public WebElement Fnametxb;


	@FindBy (xpath = "//*[@id=\"last-name\"]")
	public WebElement Lnametxb;


	@FindBy(xpath = "//*[@id=\"postal-code\"]")
	public WebElement PostalCodetxb;

	//@FindBy(id = "workShift_availableEmp")
	@FindBy(id = "continue")
	public WebElement continueBtn;

	@FindBy(xpath = "//span[text()='Checkout: Overview']")
	public WebElement checkoutOverviewLbl;

	@FindBy(xpath = "//div[@class='inventory_item_name']")
	public WebElement checkoutItemName;

	@FindBy(xpath = "//div[@class='inventory_item_price']")
	public WebElement checkoutItemPrice;

	@FindBy(xpath = "//div[@class='summary_total_label']")
	public WebElement checkoutSummaryTotal;

	//@FindBy(id = "btnAssignEmployee")
	@FindBy(id ="finish")
	public WebElement finish;

	@FindBy(xpath = "//h2[@class='complete-header']")
	public WebElement checkoutComplete;

	public SwagLabsPage() {
		if (TestRun.isMobile())
			PageFactory.initElements(new AppiumFieldDecorator(DriverManagerUtil.getAppiumDriver()), this);
		else if (TestRun.isDesktop())
			PageFactory.initElements(DriverManagerUtil.getWebDriver(), this);
	}
}