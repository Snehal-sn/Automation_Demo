package com.client.app.uitests.module;

import com.client.app.common.utils.AprisoUtil;
import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;
import com.sogeti.dia.common.utils.ExcelUtil;
import com.sogeti.dia.common.utils.LoggerUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class WebApp_10 extends BaseTest {
    public AprisoUtil aprisoUtil;
    public HashMap<Integer, HashMap<String, String>> testData;
    public String className;


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
    @Test(dataProvider = "getData")
    public void DatabaseConnecton(HashMap<String, String> testData) throws InterruptedException {

      //  aprisoUtil.launchWebApplication(testData);
//        aprisoUtil.CheckSerial(testData);
//        LoggerUtil.logMessage("Serial is required.");
//        LoggerUtil.logMessage("Need to create 3 serial");


    }
}
