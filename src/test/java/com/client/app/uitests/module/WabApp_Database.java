package com.client.app.uitests.module;

import com.client.app.common.utils.AprisoUtil;
import com.client.app.common.utils.Login_Via_Database;
import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;
import com.sogeti.dia.common.utils.ExcelUtil;
import com.sogeti.dia.common.utils.LoggerUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WabApp_Database extends Login_Via_Database {
    public AprisoUtil aprisoUtil;
 public    WabApp_Database wabAppDatabase;

 public Login_Via_Database loginViaDatabase;

    public void VerifyNegativeFlow()  {
        System.out.println("Hi From VerifyNegativeFlow");
        loginViaDatabase.Connect();


    }



  //  public Login_Via_Database loginViaDatabase;
  //  public HashMap<Integer, HashMap<String, String>> testData;
//    public String className;


  //  @BeforeMethod(alwaysRun = true)
//    public void setUp() {
     //   CapabilityFactoryUtil.initiateDriver();

        // orangeHRMUtil = new OrangeHRMUtil();
    //    aprisoUtil = new AprisoUtil();
   // }

   // @DataProvider(name = "getData")
  //  public Iterator<Object[]> getTestData() {
       // className = this.getClass().getSimpleName();
     //   testData = ExcelUtil.getTestData(className);

      //  ArrayList<Object[]> dataProvider = new ArrayList<Object[]>();
       // for (Integer currentKey : testData.keySet()) {
       //     dataProvider.add(new Object[]{testData.get(currentKey)});
     //   }

       // return dataProvider.iterator();
   // }
    //@Test(dataProvider = "getData")
   // public void VerifyNegativeFlow()  {

//    loginViaDatabase.Connect();
//
//
//    }
}
