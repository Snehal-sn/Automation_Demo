package com.client.app.common.utils;

import com.client.app.pageobjects.SwagLabsPage;
import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.DriverManagerUtil;
import com.sogeti.dia.common.utils.LoggerUtil;
import com.sogeti.dia.common.utils.RandomDataUtil;
import com.sogeti.dia.common.utils.WebInteractUtil;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.text.DecimalFormat;
import java.util.Map;


public class SwagLabsUtil {
    protected SwagLabsPage swagLabsPage;
    protected RandomDataUtil dataUtil;
    protected SoftAssert softAssert;

    public SwagLabsUtil() {
        swagLabsPage = new SwagLabsPage();
        softAssert = new SoftAssert();
        dataUtil = new RandomDataUtil();
    }

    public boolean loginToSwagLabs(Map<String, String> testData) throws InterruptedException {
        boolean status = false;

        try {
            WebInteractUtil.launchWebApp(testData.get("URL"));
            LoggerUtil.logMessage("URL opened successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to launch URL\n" + e.getMessage());
        }

        try {
            WebInteractUtil.sendKeys(swagLabsPage.userNameTxb, testData.get("UserName"));
            LoggerUtil.logMessageNoScreenShot("Username entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to enter username\n" + e.getMessage());
        }

        try {
            WebInteractUtil.sendKeys(swagLabsPage.passwordTxb, testData.get("Password"));
            LoggerUtil.logMessageNoScreenShot("Password entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to enter password\n" + e.getMessage());
        }

        try {
            WebInteractUtil.clickByJS(swagLabsPage.loginBtn);
            LoggerUtil.logMessageNoScreenShot("Clicked on Login button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Login button\n" + e.getMessage());
        }

        Assert.assertTrue(WebInteractUtil.isPresent(swagLabsPage.productsLbl, Config.MEDIUM_PAUSE), "User Login Failed.");
        status = true;
        LoggerUtil.logMessage("User logged in successfully.");

        return true;
    }

    public boolean addProductToCart(String productName) throws InterruptedException {
        Boolean status = false;

//		WebInteractUtil.click(swagLabsPage.productlink);
        String productXPath = "//div[@class='inventory_item_name'][text()='" + productName + "']";
        try {
            if (TestRun.isMobile())
                DriverManagerUtil.getAppiumDriver().findElementByXPath(productXPath).click();
            else if (TestRun.isDesktop())
                DriverManagerUtil.getWebDriver().findElementByXPath(productXPath).click();

            LoggerUtil.logMessageNoScreenShot("Clicked on Product name " + productName);
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Product name\n" + e.getMessage());
        }

        if (TestRun.isMobile()) {
            Assert.assertTrue(WebInteractUtil.isPresent(DriverManagerUtil.getAppiumDriver().findElementByXPath("//div[contains(@class,'inventory_details_name')][text()='" + productName + "']"), Config.MEDIUM_PAUSE), "Product page failed to open");
            LoggerUtil.logMessage("Product page opened successfully");
        } else if (TestRun.isDesktop()) {
            Assert.assertTrue(WebInteractUtil.isPresent(DriverManagerUtil.getWebDriver().findElementByXPath("//div[contains(@class,'inventory_details_name')][text()='" + productName + "']"), Config.MEDIUM_PAUSE), "Product page failed to open");
            LoggerUtil.logMessage("Product page opened successfully");
        }

        try {
            WebInteractUtil.click(swagLabsPage.addtocart);
            LoggerUtil.logMessageNoScreenShot("Clicked on Add to Cart");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Add to Cart\n" + e.getMessage());
        }

        if (WebInteractUtil.isPresent(swagLabsPage.cartQty, Config.MEDIUM_PAUSE)) {
            if (Integer.parseInt(WebInteractUtil.getText(swagLabsPage.cartQty)) > 0) {
                status = true;
                LoggerUtil.logMessageNoScreenShot("Product added to cart successfully");
            }
        } else
            LoggerUtil.logErrorMessage("Product not added to cart");

        return status;
    }


    public String getcustomerdetails(Map<String, String> testData) {
        String Fname = testData.get("First Name");

        String Lname = testData.get("Last Name");

        String Postalcode = testData.get("Zip/Postal Code");

        return Fname + Lname + Postalcode;
    }


    public void checkout(Map<String, String> testData) throws InterruptedException {

        try {
            WebInteractUtil.click(swagLabsPage.cart);
            LoggerUtil.logMessageNoScreenShot("Clicked on Cart icon");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Cart icon\n" + e.getMessage());
        }

        try {
            WebInteractUtil.click(swagLabsPage.checkout);
            LoggerUtil.logMessageNoScreenShot("Clicked on Checkout button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Checkout button\n" + e.getMessage());
        }

        try {
            WebInteractUtil.sendKeys(swagLabsPage.Fnametxb, testData.get("First Name"));
            LoggerUtil.logMessage("First name entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to enter First name\n" + e.getMessage());
        }

        try {
            WebInteractUtil.sendKeys(swagLabsPage.Lnametxb, testData.get("Last Name"));
            LoggerUtil.logMessageNoScreenShot("Last name entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to enter Last name\n" + e.getMessage());
        }

        try {
            WebInteractUtil.sendKeys(swagLabsPage.PostalCodetxb, testData.get("Zip/Postal Code"));
            LoggerUtil.logMessageNoScreenShot("Zip/Postal Code entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to enter Zip/Postal Code\n" + e.getMessage());
        }

        WebInteractUtil.click(swagLabsPage.continueBtn);

        Assert.assertTrue(WebInteractUtil.isPresent(swagLabsPage.checkoutOverviewLbl, Config.MEDIUM_PAUSE), "Checkout Overview Page failed to load");
        LoggerUtil.logMessage("Checkout Overview page loaded successfully");

    }

    public float calculateTotalPrice(String price, String taxPercentage) {
        float totalPrice = 0;
        totalPrice = Float.parseFloat(price) + (Float.parseFloat(price) * dataUtil.getPercentage(taxPercentage));
        DecimalFormat f = new DecimalFormat("##.00");
        return Float.parseFloat(f.format(totalPrice));
    }

    public void verifyPurchaseDetails(Map<String, String> testData) {

        softAssert.assertEquals(WebInteractUtil.getText(swagLabsPage.checkoutItemName), testData.get("Product Name"),
                "Product Name does not match expected value. ");

        softAssert.assertEquals(WebInteractUtil.getText(swagLabsPage.checkoutItemPrice), "$" + testData.get("Product Price"),
                "Product Price does not match expected value. ");

        String expectedTotal = "$" + calculateTotalPrice(testData.get("Product Price"), testData.get("Tax"));
        String actualTotal = WebInteractUtil.getText(swagLabsPage.checkoutSummaryTotal).replace("Total:","").trim();
        softAssert.assertEquals(actualTotal, expectedTotal,
                "Summary Total does not match the expected value. ");

        softAssert.assertAll();
       // WebInteractUtil.waitForElementToBeVisible();
      //  WebInteractUtil.e
        WebInteractUtil.click(swagLabsPage.finish);
        Assert.assertTrue(WebInteractUtil.isPresent(swagLabsPage.checkoutComplete, Config.MEDIUM_PAUSE), "Failed to complete Checkout");
        LoggerUtil.logMessage("Checkout completed successfully");

    }

}
