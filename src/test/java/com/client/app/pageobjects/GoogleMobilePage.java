package com.client.app.pageobjects;

import org.openqa.selenium.support.PageFactory;

import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.DriverManagerUtil;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class GoogleMobilePage {		
	@AndroidFindBy(id = "android:id/button1")
	public MobileElement allowBtn1;
	
	@AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	public MobileElement allowBtn2;

	@AndroidFindBy(id = "com.google.android.googlequicksearchbox:id/chatui_streaming_text")
	public MobileElement streamingTextTxb;

	
	public GoogleMobilePage() {  
		if (TestRun.isMobile())
			PageFactory.initElements(new AppiumFieldDecorator(DriverManagerUtil.getAppiumDriver()), this);
		else if (TestRun.isDesktop())
			PageFactory.initElements(new AppiumFieldDecorator(DriverManagerUtil.getWebDriver()), this);
	}
}