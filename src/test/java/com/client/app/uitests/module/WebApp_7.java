package com.client.app.uitests.module;

import com.client.app.common.utils.AprisoUtil;
import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;
import com.sogeti.dia.common.utils.ExcelUtil;
import com.sogeti.dia.common.utils.GalenReporterUtil;
import com.sogeti.dia.common.utils.LoggerUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



    public class WebApp_7 extends BaseTest {
        public AprisoUtil aprisoUtil;
        public HashMap<Integer, HashMap<String, String>> testData;
        public String className;
        public String aprisoUrl;


        @BeforeMethod(alwaysRun = true)
        public void setUp() {
            CapabilityFactoryUtil.initiateDriver();

            //orangeHRMUtil = new OrangeHRMUtil();
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
        @Test(dataProvider = "getData")
        public void verifyThegivenOrder(HashMap<String, String> testData) throws InterruptedException {
            GalenReporterUtil.generateGalenSuiteReport();
    aprisoUtil.launchWebApplication(testData,aprisoUrl);

   // aprisoUtil.OrderCockpit(testData);
//    aprisoUtil.Logout(testData);
    LoggerUtil.logMessage("Application logout");

  //  aprisoUtil.launchWebApplication(testData);
//    aprisoUtil.OrderPDFValidation(testData);
//    aprisoUtil.VerifyingOrderDetails(testData);
//    aprisoUtil.Logout(testData);
//    LoggerUtil.logMessage("Application logout");
//     aprisoUtil.MongoDBConnection(testData);


        }
    }


