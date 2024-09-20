package com.client.app.common.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.validator.Msg;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.client.app.pageobjects.CareSourcePage;
import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.DateTimeUtil;
import com.sogeti.dia.common.utils.DriverManagerUtil;
import com.sogeti.dia.common.utils.LoggerUtil;
import com.sogeti.dia.common.utils.WebInteractUtil;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


public class CareSourceUtil {
		protected CareSourcePage careSourcePage;
		SoftAssert softAssert = new SoftAssert();

		public CareSourceUtil() {
			careSourcePage = new CareSourcePage();
		}
		
		
		public void LaunchApp(Map<String, String> testData) {
//			WebInteractUtil.launchWebApp(Config.APP_URL_CareSource);
			WebInteractUtil.launchWebApp(testData.get("URL"));
		}

		
		
		public void NavigateToFindADoctor() {
			if (WebInteractUtil.isPresent(careSourcePage.Iam,Config.MEDIUM_PAUSE)) {
				Select IamObj = new Select(careSourcePage.Iam);
				IamObj.selectByValue("a-member");
				Select WanttoObj = new Select(careSourcePage.IwantTo);
				WanttoObj.selectByValue("find-a-doctor-or-other-care-provider");
				WebInteractUtil.click(careSourcePage.GuideMeBtn);
				
			} else {
				LoggerUtil.logMessage("Find A Doctor link is NOT available");
			}			
			if (WebInteractUtil.waitForInvisibilityOfElement(careSourcePage.Progress, 120)) {
				LoggerUtil.logMessage("Navigated to Find A Doctor Page successfully.");
			}
			else 
				LoggerUtil.logMessage("Could not navigate to Find A Doctor Page");
		}
		
		
		public void NavigateToFindADoctor1() {
			if (WebInteractUtil.isPresent(careSourcePage.FindDocLnk,Config.MEDIUM_PAUSE)) {
				WebInteractUtil.click(careSourcePage.FindDocLnk);
			} else {
				LoggerUtil.logMessage("Find A Doctor link is NOT available");
			}			
			if (WebInteractUtil.waitForInvisibilityOfElement(careSourcePage.Progress, 120)) {
				LoggerUtil.logMessage("Navigated to Find A Doctor Page successfully.");
			}
			else 
				LoggerUtil.logMessage("Could not navigate to Find A Doctor Page");
		}
		
		public void SearchADoctor(Map<String, String> testData) {
//			WebInteractUtil.sendKeys(careSourcePage.AddressTxb, testData.get("Address"),Keys.ENTER);
//			WebInteractUtil.sendKeyboardEvents(careSourcePage.AddressTxb, Keys.ENTER, Config.MEDIUM_PAUSE);
			WebInteractUtil.sendKeys(careSourcePage.PhysicianNameTxb, testData.get("DocName"),Keys.ENTER);
		}


		/*public void VerifyDoctorInformation(Map<String, String> testData) {
			int Flag=0;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (WebInteractUtil.waitForInvisibilityOfElementByLocator(By.xpath("//*[@class='spinner']"), Config.MEDIUM_PAUSE)) {
				LoggerUtil.logMessage("All Doctors list is displayed succesfully");
					List<WebElement> DocList = DriverManagerUtil.getWebDriver().findElementsByXPath(careSourcePage.DocListName);
					LoggerUtil.logMessage(DocList.size()+"- Total list of Doctors");
					for (int i =0;i<DocList.size();i++) {
						String Name = DriverManagerUtil.getWebDriver().findElement(By.xpath(careSourcePage.DocListName+"["+(i+1)+"]")).getText();
						String Address = DriverManagerUtil.getWebDriver().findElement(By.xpath(careSourcePage.DocListAdd+"["+(i+1)+"]")).getText();
						String speciality = DriverManagerUtil.getWebDriver().findElement(By.xpath(careSourcePage.DocListSpec+"["+(i+1)+"]")).getText();
						String language = DriverManagerUtil.getWebDriver().findElement(By.xpath(careSourcePage.DocListLang+"["+(i+1)+"]")).getText();
						LoggerUtil.logMessage((i+1)+".Doctor's Name is:"+Name);
						LoggerUtil.logMessage((i+1)+".Doctor's Address is:"+Address);
						LoggerUtil.logMessage((i+1)+".Doctor's Specility is:"+speciality);
						LoggerUtil.logMessage((i+1)+".Doctor's Language is:"+language);
						if (Name.equals(testData.get("DocName")) && Address.equals(testData.get("DocAddress")) && speciality.equals(testData.get("DocSpeciality")) && language.equals(testData.get("DocLanguage"))) {
							LoggerUtil.logMessage("Expected doctor's Name:"+Name+"| Address:"+Address+"| Speciality:"+speciality+" is available in the list");
							Flag=1;
							break;
						}
						else {
							Flag=0;
						}

//						Assert.assertEquals(testData.get("DocName"), Name, "Actual Doctor's Name did not match with the Expected Result");
//						Assert.assertEquals();
//						SoftAssert softAssert = new SoftAssert();
						softAssert.assertEquals(testData.get("DocName"), Name, "Actual Doctor's Name did not match with the Expected Result");
						softAssert.assertEquals(testData.get("DocAddress"), Address, "Failure");

						softAssert.assertAll();
					}
					if (Flag==0) {
						LoggerUtil.logMessage("Expected doctor's Name :"+testData.get("DocName")+"| Address:"+testData.get("DocAddress")+"| Speciality:"+testData.get("DocSpeciality")+" is NOT available in the list");					
					}


										
			}
		}*/

	public void verifyDoctorInformation(Map<String, String> testData) {
		try {
			Thread.sleep(5000);
			WebInteractUtil.waitForElementToBeClickable(careSourcePage.DocName, Config.MEDIUM_PAUSE);
			LoggerUtil.logMessage("All Doctors list is displayed successfully");

			List<WebElement> docList = DriverManagerUtil.getWebDriver().findElementsByXPath(careSourcePage.DocListName);
			LoggerUtil.logMessage(docList.size() + " doctors found.");

			for (int i = 0; i < docList.size(); i++) {
				String docName = DriverManagerUtil.getWebDriver().findElement(By.xpath(careSourcePage.DocListName + "[" + i+1 + "]")).getText();
				String docAddress = DriverManagerUtil.getWebDriver().findElement(By.xpath(careSourcePage.DocListAdd + "[" + i+1 + "]")).getText();
				String docSpecialty = DriverManagerUtil.getWebDriver().findElement(By.xpath(careSourcePage.DocListSpec + "[" + i+1 + "]")).getText();
				String docLanguage = DriverManagerUtil.getWebDriver().findElement(By.xpath(careSourcePage.DocListLang + "[" + i+1 + "]")).getText();

				Assert.assertEquals(docName, testData.get("DocName"), "Doctor's Name " + docName + " does not match with expected value '" + testData.get("DocName") + "'.");
				softAssert.assertEquals(docAddress, testData.get("DocAddress"), "Doctor's Address " + docAddress + " does not match with expected value '" + testData.get("DocAddress") + "'.");
				softAssert.assertEquals(docSpecialty, testData.get("DocSpeciality"), "Doctor's Specialty " + docSpecialty + " does not match with expected value '" + testData.get("DocSpeciality") + "'.");
				softAssert.assertEquals(docLanguage, testData.get("DocLanguage"), "Doctor's Language " + docLanguage + " does not match with expected value '" + testData.get("DocLanguage") + "'.");
			}

			softAssert.assertAll();

		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Doctors list did not load correctly");
			e.printStackTrace();
		}

	}
		
	}
		