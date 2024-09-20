package com.client.app.uitests.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.client.app.pageobjects.GoogleMobilePage;
import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;
import com.sogeti.dia.common.utils.ExcelUtil;
import com.sogeti.dia.common.utils.TextToSpeechUtil;
import com.sogeti.dia.common.utils.WebInteractUtil;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class TextToSpeech_1 extends BaseTest {
	protected GoogleMobilePage googleMobilePage;	
	protected static HashMap<Integer, HashMap<String, String>> testData;	
	protected static String className;	
	
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() {		 
		CapabilityFactoryUtil.initiateDriver();
		googleMobilePage = new GoogleMobilePage();
	}
	
	@DataProvider(name = "getData")
	public Iterator<Object[]> getTestData() {
		className = this.getClass().getSimpleName();
		testData = ExcelUtil.getTestData(className);

		ArrayList<Object[]> dataProvider = new ArrayList<Object[]>();
		for (Integer currentKey : testData.keySet()) {
			dataProvider.add(new Object[] { testData.get(currentKey) });
		}

		return dataProvider.iterator();
	}
		
	@Test(dataProvider = "getData") 
	@Severity(SeverityLevel.CRITICAL)
	public void verifyGoogleAssistant(HashMap<String, String> testData) {						
		String url = TextToSpeechUtil.getVoiceUrl(testData.get("SearchKeyword"), "WAV", "en-us");		
		
		//To start chrome with url
		try {
				Runtime.getRuntime().exec(new String[]{"cmd.exe","/c","start \"\" \""+ url +"\" "});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		WebInteractUtil.waitForElementToBeVisible(googleMobilePage.streamingTextTxb, Config.MEDIUM_PAUSE);		 
		Assert.assertEquals("play music", googleMobilePage.streamingTextTxb.getText().toLowerCase());
		
		//To kill chrome
		try {
				Runtime.getRuntime().exec("Taskkill /IM chrome.exe /F");
		} catch (Exception e) {
			// TODO Auto-generated catch block			
		}
	}
}