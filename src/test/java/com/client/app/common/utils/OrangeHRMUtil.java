package com.client.app.common.utils;

import com.client.app.pageobjects.OrangeHRMPage;
import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.DateTimeUtil;
import com.sogeti.dia.common.utils.DriverManagerUtil;
import com.sogeti.dia.common.utils.LoggerUtil;
import com.sogeti.dia.common.utils.WebInteractUtil;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Map;


public class OrangeHRMUtil {
    protected OrangeHRMPage orangeHRMPage;

    public OrangeHRMUtil() {
        orangeHRMPage = new OrangeHRMPage();
    }

    public boolean loginToOrangeHRM(Map<String, String> testData) {
        boolean status = false;

        try {
            WebInteractUtil.launchWebApp(testData.get("URL"));
            LoggerUtil.logMessage("URL opened successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to launch URL\n" + e.getMessage());
        }

        try {
            WebInteractUtil.sendKeys(orangeHRMPage.userNameTxb, testData.get("UserName"));
            LoggerUtil.logMessageNoScreenShot("Username entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to enter username\n" + e.getMessage());
        }

        try {
            WebInteractUtil.sendKeys(orangeHRMPage.passwordTxb, testData.get("Password"));
            LoggerUtil.logMessageNoScreenShot("Password entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to enter password\n" + e.getMessage());
        }

        try {
            WebInteractUtil.clickByJS(orangeHRMPage.loginBtn);
            LoggerUtil.logMessageNoScreenShot("Clicked on Submit button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Submit button\n" + e.getMessage());
        }

        Assert.assertTrue(WebInteractUtil.isPresent(orangeHRMPage.welcomeLnk, Config.MEDIUM_PAUSE), "User Login Failed.");
        status = true;
        LoggerUtil.logMessage("User logged in successfully");

        return status;
    }

    public boolean navigateToWorkShiftsModule() {
        boolean status = false;

        try {
            WebInteractUtil.click(orangeHRMPage.adminLnk);
            LoggerUtil.logMessageNoScreenShot("Clicked on Admin menu link");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Admin menu link\n" + e.getMessage());
        }

        try {
            WebInteractUtil.click(orangeHRMPage.jobLnk);
            LoggerUtil.logMessageNoScreenShot("Clicked on Job menu");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Job menu\n" + e.getMessage());
        }

        try {
            WebInteractUtil.clickByJS(orangeHRMPage.workShiftsLnk);
            LoggerUtil.logMessageNoScreenShot("Clicked on Work Shifts menu item");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Work Shifts menu item\n" + e.getMessage());
        }

        if (WebInteractUtil.isPresent(orangeHRMPage.workShiftsLabel, Config.MEDIUM_PAUSE))
            status = true;

        Assert.assertTrue(WebInteractUtil.isPresent(orangeHRMPage.workShiftsLabel, Config.MEDIUM_PAUSE), "Failed to navigate to 'Works Shifts' module");
        LoggerUtil.logMessage("Navigated to Work Shifts module successfully.");

        return status;
    }

    public String getUniqueShiftName(Map<String, String> testData) {
        return testData.get("ShiftName") + DateTimeUtil.getCurrentTimestamp();
    }

    public boolean setWorkShiftDetails(Map<String, String> testData, String shiftName) {
        boolean status = false;

        try {
            WebInteractUtil.click(orangeHRMPage.addBtn);
            LoggerUtil.logMessageNoScreenShot("Clicked on Add button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Add button\n" + e.getMessage());
        }

        Assert.assertTrue(WebInteractUtil.isPresent(orangeHRMPage.addWorkShiftLabel, Config.MEDIUM_PAUSE), "'Add Work Shifts' page failed to open");
        LoggerUtil.logMessage("'Add Work Shift' page opened successfully");

        try {
            WebInteractUtil.sendKeys(orangeHRMPage.shiftNameTxb, shiftName); //testData.get("ShiftName"));
            LoggerUtil.logMessageNoScreenShot("Entered new Shift Name");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to enter Shift Name\n" + e.getMessage());
        }

        try {
            WebInteractUtil.sendKeys(orangeHRMPage.workFromTxb, testData.get("WorkHoursFrom"));
            LoggerUtil.logMessageNoScreenShot("Entered From time");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to enter From time\n" + e.getMessage());
        }

        try {
            WebInteractUtil.sendKeys(orangeHRMPage.workToTxb, testData.get("WorkHoursTo"));
            LoggerUtil.logMessageNoScreenShot("Entered To time");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to enter To time\n" + e.getMessage());
        }

        WebInteractUtil.click(orangeHRMPage.duration);

        try {
            WebInteractUtil.click(orangeHRMPage.saveBtn);
            LoggerUtil.logMessageNoScreenShot("Clicked on Save button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Save button\n" + e.getMessage());
        }

        Assert.assertTrue(WebInteractUtil.waitForElementToBeVisible(orangeHRMPage.workShiftsLabel, Config.MEDIUM_PAUSE),
                "'Work Shifts' page failed to open");

        WebInteractUtil.waitForElementToBeVisible(orangeHRMPage.workShiftsTbl, Config.MEDIUM_PAUSE);
        if (TestRun.isMobile()) {
            Assert.assertTrue(WebInteractUtil.isPresent(DriverManagerUtil.getAppiumDriver().findElement(By.xpath("//div[contains(@class,'oxd-table-cell')]/div[text()='" + shiftName + "']")), Config.MEDIUM_PAUSE),
                    "Failed to create shift '" + shiftName + "'");
            status = true;
            LoggerUtil.logMessage("Shift added successfully");
        } else if (TestRun.isDesktop()) {
            Assert.assertTrue(WebInteractUtil.isPresent(DriverManagerUtil.getWebDriver().findElement(By.xpath("//div[contains(@class,'oxd-table-cell')]/div[text()='" + shiftName + "']")), Config.MEDIUM_PAUSE),
                    "Failed to create shift '" + shiftName + "'");
            status = true;
            LoggerUtil.logMessage("Shift added successfully");
        }

        return status;
    }

    public boolean deleteWorkShift(String shiftName) {
        boolean status = false;
        int size = 0;

        try {
            if (TestRun.isMobile())
                DriverManagerUtil.getAppiumDriver().findElementByXPath("//div[text()='" + shiftName + "']/parent::div/preceding-sibling::div/div[contains(@class,'cell-checkbox')]").click();
            else if (TestRun.isDesktop())
                DriverManagerUtil.getWebDriver().findElementByXPath("//div[text()='" + shiftName + "']/parent::div/preceding-sibling::div/div[contains(@class,'cell-checkbox')]").click();

            LoggerUtil.logMessageNoScreenShot("Work shift selected for deletion.");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on checkbox\n" + e.getMessage());
        }

        try {
            WebInteractUtil.click(orangeHRMPage.deleteBtn);
            LoggerUtil.logMessageNoScreenShot("Clicked on Delete button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Delete button\n" + e.getMessage());
        }

        try {
            WebInteractUtil.click(orangeHRMPage.dialogDeleteBtn);
            LoggerUtil.logMessageNoScreenShot("Clicked Delete on pop-up message");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Delete in pop-up message\n" + e.getMessage());
        }

        WebInteractUtil.waitForElementToBeVisible(orangeHRMPage.workShiftsTbl, Config.MEDIUM_PAUSE);

        if (TestRun.isMobile())
            size = DriverManagerUtil.getAppiumDriver().findElementsByLinkText(shiftName).size();
        else if (TestRun.isDesktop())
            size = DriverManagerUtil.getWebDriver().findElementsByLinkText(shiftName).size();

        if (size == 0) {
            LoggerUtil.logMessage("Work shift deleted successfully.");
            status = true;
        } else
            LoggerUtil.logErrorMessage("Work shift not deleted successfully.");

        return status;
    }

    public boolean logOutOrangeHRM() {
        boolean status = false;

        if (WebInteractUtil.isPresent(orangeHRMPage.welcomeLnk, Config.XSMALL_PAUSE)) {
            try {
                WebInteractUtil.click(orangeHRMPage.welcomeLnk);
                WebInteractUtil.click(orangeHRMPage.logoutLnk);
                LoggerUtil.logMessageNoScreenShot("Clicked on Logout link");
            } catch (Exception e) {
                LoggerUtil.logErrorMessage("Failed to click on Logout link\n" + e.getMessage());
            }

            if (WebInteractUtil.isPresent(orangeHRMPage.loginBtn, Config.LARGE_PAUSE)) {
                LoggerUtil.logMessage("User logged out successfully.");
                status = true;
            } else
                LoggerUtil.logMessage("User logout failed.");
        } else
            status = true;

        return status;
    }
}