package com.client.app.uitests.module;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.client.app.common.utils.AprisoUtil;

import com.sogeti.dia.common.utils.GalenReporterUtil;
import com.sogeti.dia.common.utils.LoggerUtil;
import io.qameta.allure.Severity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;
import com.sogeti.dia.common.utils.ExcelUtil;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class WebApp_4 extends BaseTest {
    public AprisoUtil aprisoUtil;
    public HashMap<Integer, HashMap<String, String>> testData;
    public String className;
    public GalenReporterUtil galenReport;

    @BeforeMethod(alwaysRun=true)
    public void setUp() {
       // CapabilityFactoryUtil.initiateDriver();
        galenReport = new GalenReporterUtil();
       // orangeHRMUtil = new OrangeHRMUtil();
        aprisoUtil = new AprisoUtil();
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

//    @Test(dataProvider = "getData")
//    @Severity(SeverityLevel.BLOCKER)
//    public void verifyAprisoLoginPage(HashMap<String, String> testData) throws InterruptedException {
//        boolean status = aprisoUtil.launchWebApplication(testData);
//        Assert.assertTrue(status, "Login failed.");
//    }
@Test(dataProvider = "getData")
    public void verifyTheOrderIsCreatedOrNotByUsingGivenData(HashMap<String, String> testData) throws InterruptedException {

//    GalenReporterUtil.generateGalenSuiteReport();
   // aprisoUtil.launchWebApplication(testData);

//    aprisoUtil.OrderCockpit(testData);
//    aprisoUtil.Logout(testData);
//    LoggerUtil.logMessage("Application logout");
//
//   // aprisoUtil.launchWebApplication(testData);
//    aprisoUtil.OrderPDFValidation(testData);
//    aprisoUtil.VerifyingOrderDetails(testData);
//    aprisoUtil.Logout(testData);
//    LoggerUtil.logMessage("Application logout");

  //  aprisoUtil.MongoDBConnection(testData);


}}
