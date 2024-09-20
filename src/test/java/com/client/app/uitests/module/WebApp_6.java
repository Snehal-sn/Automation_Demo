package com.client.app.uitests.module;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.client.app.common.utils.AprisoUtil;
import com.sogeti.dia.common.utils.ExcelUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;

public class WebApp_6 extends BaseTest {

    public AprisoUtil aprisoUtil;
    public HashMap<Integer, HashMap<String, String>> testData;
    public String className;
    public String aprisoUrl;


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        CapabilityFactoryUtil.initiateDriver();

        // orangeHRMUtil = new OrangeHRMUtil();
        aprisoUtil = new AprisoUtil();
    }

    @DataProvider(name = "getData")
    public Iterator<Object[]> getTestData() {
        className = this.getClass().getSimpleName();
        testData = ExcelUtil.getTestData(className);

        ArrayList<Object[]> dataProvider = new ArrayList<Object[]>();
        for (Integer currentKey : testData.keySet()) {
            dataProvider.add(new Object[]{testData.get(currentKey)});
        }

        return dataProvider.iterator();
    }

    //    @Test(dataProvider = "getData")
////    @Severity(SeverityLevel.BLOCKER)
//    public void verifyAprisoLoginPage(HashMap<String, String> testData) throws InterruptedException {
//        boolean status = aprisoUtil.loginToApriso(testData);
//        Assert.assertTrue(status, "Login failed.");
//    }
    @Test(dataProvider = "getData")
    public void VerifyTheOrderUsingInvalidOrderData(HashMap<String, String> testData) throws InterruptedException {
        aprisoUtil.launchWebApplication(testData,aprisoUrl);
        aprisoUtil.errorHandling();


    }

}
