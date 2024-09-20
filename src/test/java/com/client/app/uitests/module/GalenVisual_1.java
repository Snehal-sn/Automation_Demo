package com.client.app.uitests.module;

import java.util.Arrays;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;
import com.sogeti.dia.common.utils.DriverManagerUtil;
import com.sogeti.dia.common.utils.GalenReporterUtil;


public class GalenVisual_1 extends BaseTest{
	protected GalenReporterUtil galenReporterUtil;
	
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() {		 
		CapabilityFactoryUtil.initiateDriver();
		galenReporterUtil = new GalenReporterUtil();
	}
	
	@Test
    public void homePageLayoutTest()
    {		
		DriverManagerUtil.getWebDriver().navigate().to(Config.GALEN_APP_URL);			
		galenReporterUtil.checkLayout(Config.GSPEC_FILE_PATH + "hemepage.gspec", Arrays.asList("desktop"), "HomePage");
    }
}