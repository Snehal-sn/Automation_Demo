package com.client.app.common.utils;

import com.client.app.pageobjects.AprisoPage;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.utils.LoggerUtil;
import com.sogeti.dia.common.utils.WebInteractUtil;
import net.bytebuddy.asm.Advice;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;




import java.util.Map;

import static com.mongodb.client.model.Filters.eq;
import static com.sogeti.dia.common.utils.WebInteractUtil.click;
import static com.sogeti.dia.common.utils.WebInteractUtil.waitForElementToBeVisible;
import static groovy.xml.Entity.lt;
import static org.openqa.selenium.Keys.ENTER;


public class AprisoUtil extends BaseTest {
    public AprisoPage aprisoPage;
    public   String aprisoUrl;
    public String usernamedata;
    public String passwordData;
    public String OrderType;
     public String OrderStatus;
    public String Facility;
    public String Priority;
    public String Process;
    public String ProcessRev;
    public String ProductNo;
    public String Description;
    public String Quantity;
    public String UOM ;

    public String Screens;

    public Map<String, String> orderData;
    public AprisoUtil() {
        aprisoPage = new AprisoPage();
    }

    public boolean launchWebApplication(Map<String, String> testData, String url) throws InterruptedException {


        System.out.println("Connection");

        String connectionString = "mongodb://localhost:27017/";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();


        try (MongoClient mongoClient = MongoClients.create(settings))
        {
            try {

                System.out.println("mongoDB");
                Thread.sleep(10000);
                MongoDatabase database = mongoClient.getDatabase("Apriso_Order");

                //System.out.println("Yusssss");


                MongoCollection<Document> collection = database.getCollection("Url");
                //System.out.println("found database");

                MongoCollection<Document> collection1 = database.getCollection("credential");
                //System.out.println("found database");

//                Document ddoc2 = new Document("url", "admin2");
//                collection.insertOne(ddoc2);
//                System.out.println("Inserted");
                Document doc1 = collection.find().first();

                //System.out.println(doc1.toJson());
                JSONObject object = new JSONObject(doc1.toJson());
                //JSONObject location = object.getJSONObject("url");
                String lng = object.getString("url");
                aprisoUrl = lng;

                Document doc2 = collection1.find().first();

                //System.out.println(doc2.toJson());
                JSONObject object1 = new JSONObject(doc2.toJson());
                String lng1 = object1.getString("username");
                String lng2 = object1.getString("password");
                usernamedata = lng1;
                passwordData = lng2;
                //System.out.println(lng1 +""+ lng2);
                Thread.sleep(10000);
//
//                MongoCollection<Document> collection2 = database.getCollection("details");
//                Document doc3 = collection2.find().first();
//                JSONObject object2 = new JSONObject(doc3.toJson());
//
//                String lng3 = object2.getString("Order Type");
//                String lng4= object2.getString("Order Status");
//                String lng5 = object2.getString("Facility");
//                String lng6= object2.getString("Priority");
//                String lng7 = object2.getString("Process");
//                String lng8 = object2.getString("Process Rev");
//                String lng9 = object2.getString("Product No");
//                String lng10 = object2.getString("Description");
//                String lng11 = object2.getString("Quantity");
//                String lng12 = object2.getString("UOM");
//
//                OrderType = lng3;
//                OrderStatus = lng4;
//                Facility = lng5;
//                Priority = lng6;
//                Process = lng7;
//                ProcessRev = lng8;
//                ProductNo =lng9;
//                Description = lng10;
//                Quantity = lng11;
//                UOM      = lng12;
//
//                orderData.put("OrderType", lng3);
//                orderData.put("OrderStatus", lng4);
//                System.out.println(orderData.toString());
//                System.out.println(lng11 +"and"+ lng12);



                MongoCursor<Document> cursor =  collection.find().cursor();//Mongo Cursor interface implementing the iterator protocol
                System.out.println("found cursor");
                cursor.forEachRemaining(System.out::println);

                Bson filter = Filters.exists("username");

                collection.find(filter).forEach(doc -> System.out.println(doc.toJson()));

                Bson projectionFields = Projections.fields(
                        //Projections.include("username", "password"),
                        Projections.include("username"),
                        Projections.excludeId());
                System.out.println("found me");

                Document doc = collection.find(eq("username", "admin"))
                        .projection(projectionFields)
                        .sort(Sorts.descending("username"))
                        .first();
                System.out.println("found admin");



                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException | InterruptedException e) {
                e.printStackTrace();

            }
        }
        try {

            WebInteractUtil.launchWebApp(aprisoUrl);
            LoggerUtil.logMessage("URL opened successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to launch URL\n" + e.getMessage());
        }

        Thread.sleep(10000);
        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Name,10000);
            WebInteractUtil.sendKeys(aprisoPage.Name, usernamedata);
            LoggerUtil.logMessage("Username Enter Successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to enter username\n" + e.getMessage());
        }
        //Thread.sleep(5000);

        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Password,10000);
            WebInteractUtil.sendKeys(aprisoPage.Password, passwordData);
            LoggerUtil.logMessage("Password entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to enter password\n" + e.getMessage());
        }

       // Thread.sleep(5000);
        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.LogIn,10000);
            WebInteractUtil.clickByJS(aprisoPage.LogIn);
            LoggerUtil.logMessageNoScreenShot("Clicked on Submit button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Submit button\n" + e.getMessage());
        }
        Thread.sleep(10000);


        return false;
    }

    public void errorHandling() throws InterruptedException {
        try{
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.SearchBox,10000);
            WebInteractUtil.sendKeys(aprisoPage.SearchBox, "Order Cockpit");
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.OrderCockpit,10000);
            WebInteractUtil.clickByJS(aprisoPage.OrderCockpit);
            LoggerUtil.logMessageNoScreenShot("Clicked on OrderCockpit button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on OrderCockpit button\n" + e.getMessage());
        }

        WebInteractUtil.switchToFrameByIndex(1,10);
        System. out. println("Switch to the frame");
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.CreateNew,10000);
        click(aprisoPage.CreateNew);
        //  WebInteractUtil.clickByJS(aprisoPage.CreateNew);
        LoggerUtil.logMessageNoScreenShot("Clicked on CreateNew button");
        // Thread.sleep(10000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Facility,10000);

        try {
            //  WebInteractUtil.clickByJS(aprisoPage.Facility);
            WebInteractUtil.sendKeys(aprisoPage.Facility, "LE HAVRE");
            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("Clicked on Facility button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on OrderCockpit button\n" + e.getMessage());
            //  Thread.sleep(10000);
        }
        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.OrderType,10000);
            //  WebInteractUtil.clickByJS(aprisoPage.OrderType);
            WebInteractUtil.sendKeys(aprisoPage.OrderType, "CA Production Order");
            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("OrderType is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered OrderType \n" + e.getMessage());
            //   Thread.sleep(10000);
        }
        try {
            //    WebInteractUtil.clickByJS(aprisoPage.Process);
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Process,10000);
            WebInteractUtil.sendKeys(aprisoPage.Process, "SNA_DEMO_BDL0010-30-0-CM01-G1");
            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("OrderType is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered Process \n" + e.getMessage());
            //Thread.sleep(10000);
        }
        try {
            //   WebInteractUtil.clickByJS(aprisoPage.ProcessRevision);
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.ProcessRevision,10000);

            WebInteractUtil.sendKeys(aprisoPage.ProcessRevision, "REV.001.001.001");
            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("ProcessRevision is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered ProcessRevision \n" + e.getMessage());
            //  Thread.sleep(10000);
        }

        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Productbutton,10000);

            WebInteractUtil.clickByJS(aprisoPage.Productbutton);
            //  WebInteractUtil.waitForElementToBeVisible(aprisoPage.productFilter,10000);
            WebInteractUtil.sendKeys(aprisoPage.productFilter, "BDL0010-30-0-CM01");
            aprisoPage.productFilter.sendKeys(ENTER);
            WebInteractUtil.clickByJS(aprisoPage.SelectValue);
            WebInteractUtil.clickByJS(aprisoPage.OkButton);
            Thread.sleep(10000);

            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("ProductNo is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered ProductNo \n" + e.getMessage());
            Thread.sleep(10000);
        }

        try {

            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Quantity,10000);
            WebInteractUtil.sendKeys(aprisoPage.Quantity, "3");
            LoggerUtil.logMessageNoScreenShot("Quantity is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered Quantity \n" + e.getMessage());
        }
        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.SaveButton,10000);
            WebInteractUtil.clickByJS(aprisoPage.SaveButton);
            LoggerUtil.logMessageNoScreenShot("Clicked on Save Button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Save Button \n" + e.getMessage());
        }


        WebInteractUtil.clickByJS(aprisoPage.serialbutton);
        WebInteractUtil.clickByJS(aprisoPage.AddSerial);
        WebInteractUtil.sendKeys(aprisoPage.AddSerialBox, "App1");
        WebInteractUtil.clickByJS(aprisoPage.ClickOk);
        Thread.sleep(5000);
        LoggerUtil.logErrorMessage("1st Series Created");


        WebInteractUtil.clickByJS(aprisoPage.AddSerial);
        WebInteractUtil.sendKeys(aprisoPage.AddSerialBox, "App2");
        WebInteractUtil.clickByJS(aprisoPage.ClickOk);
        LoggerUtil.logMessage("2nd Series Created");



        WebInteractUtil.switchToFrameByIndex(2,10);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.General,50000);
        WebInteractUtil.clickByJS(aprisoPage.General);
        Thread.sleep(4000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.ClickOnExplode,10000);
        WebInteractUtil.clickByJS(aprisoPage.ClickOnExplode);
        Thread.sleep(8000);
        LoggerUtil.logMessage("ERROR_APR_GEN_REQUIRED_SERIAL");



    }



    public void OrderCockpit(Map<String, String> orderData, String Screens) throws InterruptedException {

//            String connectionString = "mongodb://localhost:27017/";
//            System.out.println("connected");
//            ServerApi serverApi = ServerApi.builder()
//                    .version(ServerApiVersion.V1)
//                    .build();
//
//            MongoClientSettings settings = MongoClientSettings.builder()
//                    .applyConnectionString(new ConnectionString(connectionString))
//                    .serverApi(serverApi)
//                    .build();
//
//
//            try (MongoClient mongoClient = MongoClients.create(settings))
//            {
//
//                Thread.sleep(10000);
//                MongoDatabase database = mongoClient.getDatabase("Apriso_Order");
//
//                MongoCollection<Document> collection3 = database.getCollection("Screens");
//                Document doc4 = collection3.find().first();
//                JSONObject object3 = new JSONObject(doc4.toJson());
//
//                String lng15 = object3.getString("Screens");
//                 Screens = lng15;
//
//
//                MongoCollection<Document> collection2 = database.getCollection("details");
//                Document doc3 = collection2.find().first();
//                JSONObject object2 = new JSONObject(doc3.toJson());
//
//                String lng3 = object2.getString("Order Type");
//                String lng4= object2.getString("Order Status");
//                String lng5 = object2.getString("Facility");
//                String lng6= object2.getString("Priority");
//                String lng7 = object2.getString("Process");
//                String lng8 = object2.getString("Process Rev");
//                String lng9 = object2.getString("Product No");
//                String lng10 = object2.getString("Description");
//                String lng11 = object2.getString("Quantity");
//                String lng12 = object2.getString("UOM");
//
//                Map<String, String> orderData, String Screens = lng3;
//                OrderStatus = lng4;
//                Facility = lng5;
//                Priority = lng6;
//                Process = lng7;
//                ProcessRev = lng8;
//                ProductNo =lng9;
//                Description = lng10;
//                Quantity = lng11;
//                UOM      = lng12;
//
//
//                orderData.put("Screens", lng15);
//                orderData.put("OrderType", lng3);
//                orderData.put("OrderStatus", lng4);
//                //  System.out.println(orderData.toString());
//                System.out.println(lng11 +"and"+ lng12);

            try{
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.SearchBox,10000);
            WebInteractUtil.sendKeys(aprisoPage.SearchBox, "Order Cockpit");
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.OrderCockpit,10000);
            WebInteractUtil.clickByJS(aprisoPage.OrderCockpit);
            LoggerUtil.logMessageNoScreenShot("Clicked on OrderCockpit button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on OrderCockpit button\n" + e.getMessage());
        }

        WebInteractUtil.switchToFrameByIndex(1,10);
        System. out. println("Switch to the frame");
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.CreateNew,10000);
        click(aprisoPage.CreateNew);
      //  WebInteractUtil.clickByJS(aprisoPage.CreateNew);
        LoggerUtil.logMessageNoScreenShot("Clicked on CreateNew button");
       // Thread.sleep(10000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Facility,10000);

        try {
          //  WebInteractUtil.clickByJS(aprisoPage.Facility);
            WebInteractUtil.sendKeys(aprisoPage.Facility, "LE HAVRE");
            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("Clicked on Facility button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on OrderCockpit button\n" + e.getMessage());
          //  Thread.sleep(10000);
        }
        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.OrderType,10000);
            //  WebInteractUtil.clickByJS(aprisoPage.OrderType);
            WebInteractUtil.sendKeys(aprisoPage.OrderType, "CA Production Order");
            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("OrderType is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered OrderType \n" + e.getMessage());
         //   Thread.sleep(10000);
        }
        try {
        //    WebInteractUtil.clickByJS(aprisoPage.Process);
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Process,10000);
            WebInteractUtil.sendKeys(aprisoPage.Process, "SNA_DEMO_BDL0010-30-0-CM01-G1");
            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("OrderType is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered Process \n" + e.getMessage());
            //Thread.sleep(10000);
        }
        try {
         //   WebInteractUtil.clickByJS(aprisoPage.ProcessRevision);
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.ProcessRevision,10000);

            WebInteractUtil.sendKeys(aprisoPage.ProcessRevision, "REV.001.001.001");
            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("ProcessRevision is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered ProcessRevision \n" + e.getMessage());
          //  Thread.sleep(10000);
        }

        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Productbutton,10000);

            WebInteractUtil.clickByJS(aprisoPage.Productbutton);
          //  WebInteractUtil.waitForElementToBeVisible(aprisoPage.productFilter,10000);
            WebInteractUtil.sendKeys(aprisoPage.productFilter, "BDL0010-30-0-CM01");
            aprisoPage.productFilter.sendKeys(ENTER);
            WebInteractUtil.clickByJS(aprisoPage.SelectValue);
            WebInteractUtil.clickByJS(aprisoPage.OkButton);
            Thread.sleep(10000);

            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("ProductNo is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered ProductNo \n" + e.getMessage());
            Thread.sleep(10000);
        }

        try {

            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Quantity,10000);
            WebInteractUtil.sendKeys(aprisoPage.Quantity, "3");
            LoggerUtil.logMessageNoScreenShot("Quantity is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered Quantity \n" + e.getMessage());
        }
        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.SaveButton,10000);
            WebInteractUtil.clickByJS(aprisoPage.SaveButton);
            LoggerUtil.logMessageNoScreenShot("Clicked on Save Button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Save Button \n" + e.getMessage());
        }


            WebInteractUtil.clickByJS(aprisoPage.serialbutton);
            WebInteractUtil.clickByJS(aprisoPage.AddSerial);
            WebInteractUtil.sendKeys(aprisoPage.AddSerialBox, "App1");
            WebInteractUtil.clickByJS(aprisoPage.ClickOk);
            Thread.sleep(5000);

            WebInteractUtil.clickByJS(aprisoPage.AddSerial);
            WebInteractUtil.sendKeys(aprisoPage.AddSerialBox, "App2");
            WebInteractUtil.clickByJS(aprisoPage.ClickOk);
            Thread.sleep(7000);

            WebInteractUtil.clickByJS(aprisoPage.AddSerial);
            WebInteractUtil.sendKeys(aprisoPage.AddSerialBox, "App3");
            WebInteractUtil.clickByJS(aprisoPage.ClickOk);
            Thread.sleep(5000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.General,10000);
        WebInteractUtil.clickByJS(aprisoPage.General);
            //Thread.sleep(4000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.ClickOnExplode,10000);
        WebInteractUtil.clickByJS(aprisoPage.ClickOnExplode);
            //Thread.sleep(4000);

    //******************* Operation1 ************************************************
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Operation1,10000);
        WebInteractUtil.clickByJS(aprisoPage.Operation1);
        LoggerUtil.logMessageNoScreenShot("Clicked on Save Operation1");
       // Thread.sleep(10000);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.VerifyGeneral,10000);
        WebInteractUtil.clickByJS(aprisoPage.VerifyGeneral);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.VerifyGeneral, Config.MEDIUM_PAUSE), " General menu is NOT available.");
        LoggerUtil.logMessage("General menu is available.");
        Thread.sleep(4000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Tools,10000);
        WebInteractUtil.clickByJS(aprisoPage.Tools);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Tools, Config.MEDIUM_PAUSE), "Tools menu is NOT available.");
        LoggerUtil.logMessage("Tools menu is available.");
        //Thread.sleep(2000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Components,10000);
        WebInteractUtil.clickByJS(aprisoPage.Components);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Components, Config.MEDIUM_PAUSE), "Components menu is NOT available");
        LoggerUtil.logMessage("Components menu is available.");
       // Thread.sleep(2000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Characteristics,10000);
        WebInteractUtil.clickByJS(aprisoPage.Characteristics);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Characteristics, Config.MEDIUM_PAUSE), "Characteristics menu is NOT available");
        Thread.sleep(2000);
        LoggerUtil.logMessage("Characteristics menu is available.");

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Checklists,10000);
        WebInteractUtil.clickByJS(aprisoPage.Checklists);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Checklists, Config.MEDIUM_PAUSE), "Checklists menu is NOT available.");
        Thread.sleep(5000);
        LoggerUtil.logMessage("Checklists menu is available.");

        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        WebInteractUtil.doubleClick(aprisoPage.Arrow);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.WorkInstructions,13000);
        WebInteractUtil.clickByJS(aprisoPage.WorkInstructions);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.WorkInstructions, Config.MEDIUM_PAUSE), "WorkInstructions menu is NOT available");
        Thread.sleep(5000);
        LoggerUtil.logMessage("WorkInstructions menu is available.");
        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        Thread.sleep(2000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.NonConformances,13000);
        WebInteractUtil.clickByJS(aprisoPage.NonConformances);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.NonConformances, Config.MEDIUM_PAUSE), "NonConformances menu is NOT available.");
        Thread.sleep(4000);
        LoggerUtil.logMessage("NonConformances menu is available.");

        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        Thread.sleep(2000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Issues,13000);
        WebInteractUtil.clickByJS(aprisoPage.Issues);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Issues, Config.MEDIUM_PAUSE), "Issues menu is NOT available.");
        Thread.sleep(4000);
        LoggerUtil.logMessage("Issues menu is available.");

        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        Thread.sleep(2000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Documents,13000);
        WebInteractUtil.clickByJS(aprisoPage.Documents);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Documents, Config.MEDIUM_PAUSE), "Documents menu is NOT available.");
        Thread.sleep(4000);
        LoggerUtil.logMessage("Documents menu is available.");
        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        Thread.sleep(2000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Attachments,13000);
        WebInteractUtil.clickByJS(aprisoPage.Attachments);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Attachments, Config.MEDIUM_PAUSE), "Attachments menu is NOT available.");
        Thread.sleep(2000);
        LoggerUtil.logMessage("Attachments menu is available.");
        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Comments,13000);
        WebInteractUtil.clickByJS(aprisoPage.Comments);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Comments, Config.MEDIUM_PAUSE), "Comments menu is NOT available.");
         Thread.sleep(10000);
        LoggerUtil.logMessage("Comments menu is available.");

        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow); Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.VerifyGeneral,13000);
        WebInteractUtil.clickByJS(aprisoPage.VerifyGeneral);
       Thread.sleep(4000);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Release,13000);
        WebInteractUtil.clickByJS(aprisoPage.Release);
        LoggerUtil.logMessage("Operation 1 is Released.");
        Thread.sleep(12000);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.ClickOnOrder,13000);
        WebInteractUtil.doubleClick(aprisoPage.ClickOnOrder);
        LoggerUtil.logMessage("ClickOnOrder menu is available.");
        //Thread.sleep(5000);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.ReleaseOperations,13000);
        WebInteractUtil.clickByJS(aprisoPage.ReleaseOperations);
        Thread.sleep(10000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.StartOrder,13000);
        WebInteractUtil.clickByJS(aprisoPage.StartOrder);
        LoggerUtil.logMessage("StartOrder menu is available.");
        Thread.sleep(4000);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.CompleteOrder,13000);
        try {
            WebInteractUtil.clickByJS(aprisoPage.CompleteOrder);
            LoggerUtil.logMessageNoScreenShot("Clicked on CompleteOrder button");
            LoggerUtil.logMessageNoScreenShot("Order is created successfully using given data");

        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on CompleteOrder button\n" + e.getMessage());
        }
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.VerifyOrder, Config.MEDIUM_PAUSE), "Order is completed successfully.");

        Thread.sleep(7000);
    }


    public void orderCreation() throws InterruptedException {




        try{
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.SearchBox,10000);
            WebInteractUtil.sendKeys(aprisoPage.SearchBox, "Order Cockpit");
            System. out. println("Send key Order cockpit");
            aprisoPage.SearchBox.sendKeys(ENTER);
            System. out. println("Click on enter");


            WebInteractUtil.waitForElementToBeVisible(aprisoPage.OrderCockpit,10000);
            WebInteractUtil.clickByJS(aprisoPage.OrderCockpit);
            WebInteractUtil.click(aprisoPage.OrderCockpit);

            LoggerUtil.logMessageNoScreenShot("Clicked on OrderCockpit button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on OrderCockpit button\n" + e.getMessage());
        }
        WebInteractUtil.switchToFrameByIndex(1,10);
        System. out. println("Switch to the frame");
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.CreateNew,10000);
        click(aprisoPage.CreateNew);
        //  WebInteractUtil.clickByJS(aprisoPage.CreateNew);
        LoggerUtil.logMessageNoScreenShot("Clicked on CreateNew button");
        // Thread.sleep(10000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Facility,10000);


        try {
            //  WebInteractUtil.clickByJS(aprisoPage.Facility);
            WebInteractUtil.sendKeys(aprisoPage.Facility, "LE HAVRE");
            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("Clicked on Facility button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on OrderCockpit button\n" + e.getMessage());
            //  Thread.sleep(10000);
        }
        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.OrderType,10000);
            //  WebInteractUtil.clickByJS(aprisoPage.OrderType);
            WebInteractUtil.sendKeys(aprisoPage.OrderType, "CA Production Order");
            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("OrderType is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered OrderType \n" + e.getMessage());
            //   Thread.sleep(10000);
        }
        try {
            //    WebInteractUtil.clickByJS(aprisoPage.Process);
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Process,10000);
            WebInteractUtil.sendKeys(aprisoPage.Process, "SNA_DEMO_BDL0010-30-0-CM01-G1");
            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("OrderType is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered Process \n" + e.getMessage());
            //Thread.sleep(10000);
        }
        try {
            //   WebInteractUtil.clickByJS(aprisoPage.ProcessRevision);
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.ProcessRevision,10000);

            WebInteractUtil.sendKeys(aprisoPage.ProcessRevision, "REV.001.001.001");
            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("ProcessRevision is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered ProcessRevision \n" + e.getMessage());
            //  Thread.sleep(10000);
        }

        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Productbutton,10000);

            WebInteractUtil.clickByJS(aprisoPage.Productbutton);
            //  WebInteractUtil.waitForElementToBeVisible(aprisoPage.productFilter,10000);
            WebInteractUtil.sendKeys(aprisoPage.productFilter, "BDL0010-30-0-CM01");
            aprisoPage.productFilter.sendKeys(ENTER);
            WebInteractUtil.clickByJS(aprisoPage.SelectValue);
            WebInteractUtil.clickByJS(aprisoPage.OkButton);
            Thread.sleep(10000);

            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
            LoggerUtil.logMessageNoScreenShot("ProductNo is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered ProductNo \n" + e.getMessage());
            Thread.sleep(10000);
        }

        try {

            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Quantity,10000);
            WebInteractUtil.sendKeys(aprisoPage.Quantity, "3");
            LoggerUtil.logMessageNoScreenShot("Quantity is entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to entered Quantity \n" + e.getMessage());
        }
        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.SaveButton,10000);
            WebInteractUtil.clickByJS(aprisoPage.SaveButton);
            LoggerUtil.logMessageNoScreenShot("Clicked on Save Button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Save Button \n" + e.getMessage());
        }


        WebInteractUtil.clickByJS(aprisoPage.serialbutton);
        WebInteractUtil.clickByJS(aprisoPage.AddSerial);
        WebInteractUtil.sendKeys(aprisoPage.AddSerialBox, "App1");
        WebInteractUtil.clickByJS(aprisoPage.ClickOk);
        Thread.sleep(5000);

        WebInteractUtil.clickByJS(aprisoPage.AddSerial);
        WebInteractUtil.sendKeys(aprisoPage.AddSerialBox, "App2");
        WebInteractUtil.clickByJS(aprisoPage.ClickOk);
        Thread.sleep(7000);

        WebInteractUtil.clickByJS(aprisoPage.AddSerial);
        WebInteractUtil.sendKeys(aprisoPage.AddSerialBox, "App3");
        WebInteractUtil.clickByJS(aprisoPage.ClickOk);
        Thread.sleep(5000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.General,10000);
        WebInteractUtil.clickByJS(aprisoPage.General);
        //Thread.sleep(4000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.ClickOnExplode,10000);
        WebInteractUtil.clickByJS(aprisoPage.ClickOnExplode);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Operation1,10000);
        WebInteractUtil.clickByJS(aprisoPage.Operation1);
        LoggerUtil.logMessageNoScreenShot("Clicked on Save Operation1");
        // Thread.sleep(10000);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.VerifyGeneral,10000);
        WebInteractUtil.clickByJS(aprisoPage.VerifyGeneral);
        //Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.VerifyGeneral, Config.MEDIUM_PAUSE), " General menu is NOT available.");
        LoggerUtil.logMessage("General menu is available.");
        Thread.sleep(4000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Tools,10000);
        WebInteractUtil.clickByJS(aprisoPage.Tools);
       // Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Tools, Config.MEDIUM_PAUSE), "Tools menu is NOT available.");
        LoggerUtil.logMessage("Tools menu is available.");
        //Thread.sleep(2000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Components,10000);
        WebInteractUtil.clickByJS(aprisoPage.Components);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Components, Config.MEDIUM_PAUSE), "Components menu is NOT available");
        LoggerUtil.logMessage("Components menu is available.");
        // Thread.sleep(2000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Characteristics,10000);
        WebInteractUtil.clickByJS(aprisoPage.Characteristics);
        //Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Characteristics, Config.MEDIUM_PAUSE), "Characteristics menu is NOT available");
        Thread.sleep(2000);
        LoggerUtil.logMessage("Characteristics menu is available.");

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Checklists,10000);
        WebInteractUtil.clickByJS(aprisoPage.Checklists);
       // Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Checklists, Config.MEDIUM_PAUSE), "Checklists menu is NOT available.");
        Thread.sleep(5000);
        LoggerUtil.logMessage("Checklists menu is available.");

        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        WebInteractUtil.doubleClick(aprisoPage.Arrow);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.WorkInstructions,13000);
        WebInteractUtil.clickByJS(aprisoPage.WorkInstructions);
        //Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.WorkInstructions, Config.MEDIUM_PAUSE), "WorkInstructions menu is NOT available");
        Thread.sleep(5000);
        LoggerUtil.logMessage("WorkInstructions menu is available.");
        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        Thread.sleep(2000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.NonConformances,13000);
        WebInteractUtil.clickByJS(aprisoPage.NonConformances);
        //Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.NonConformances, Config.MEDIUM_PAUSE), "NonConformances menu is NOT available.");
        Thread.sleep(4000);
        LoggerUtil.logMessage("NonConformances menu is available.");

        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        Thread.sleep(2000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Issues,13000);
        WebInteractUtil.clickByJS(aprisoPage.Issues);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Issues, Config.MEDIUM_PAUSE), "Issues menu is NOT available.");
        Thread.sleep(4000);
        LoggerUtil.logMessage("Issues menu is available.");

        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        Thread.sleep(2000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Documents,13000);
        WebInteractUtil.clickByJS(aprisoPage.Documents);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Documents, Config.MEDIUM_PAUSE), "Documents menu is NOT available.");
        Thread.sleep(4000);
        LoggerUtil.logMessage("Documents menu is available.");
        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        Thread.sleep(2000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Attachments,13000);
        WebInteractUtil.clickByJS(aprisoPage.Attachments);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Attachments, Config.MEDIUM_PAUSE), "Attachments menu is NOT available.");
        Thread.sleep(2000);
        LoggerUtil.logMessage("Attachments menu is available.");
        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.Arrow);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Comments,13000);
        WebInteractUtil.clickByJS(aprisoPage.Comments);
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.Comments, Config.MEDIUM_PAUSE), "Comments menu is NOT available.");
        Thread.sleep(10000);
        LoggerUtil.logMessage("Comments menu is available.");

        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow); Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);
        Thread.sleep(2000);
        WebInteractUtil.doubleClick(aprisoPage.BackArrow);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.VerifyGeneral,13000);
        WebInteractUtil.clickByJS(aprisoPage.VerifyGeneral);
        Thread.sleep(4000);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Release,13000);
        WebInteractUtil.clickByJS(aprisoPage.Release);
        LoggerUtil.logMessage("Operation 1 is Released.");
        Thread.sleep(12000);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.ClickOnOrder,13000);
        WebInteractUtil.doubleClick(aprisoPage.ClickOnOrder);
        LoggerUtil.logMessage("ClickOnOrder menu is available.");
        //Thread.sleep(5000);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.ReleaseOperations,13000);
        WebInteractUtil.clickByJS(aprisoPage.ReleaseOperations);
        Thread.sleep(10000);
        WebInteractUtil.waitForElementToBeVisible(aprisoPage.StartOrder,13000);
        WebInteractUtil.clickByJS(aprisoPage.StartOrder);
        LoggerUtil.logMessage("StartOrder menu is available.");
        Thread.sleep(4000);

        WebInteractUtil.waitForElementToBeVisible(aprisoPage.CompleteOrder,13000);
        try {
            WebInteractUtil.clickByJS(aprisoPage.CompleteOrder);
            LoggerUtil.logMessageNoScreenShot("Clicked on CompleteOrder button");
            LoggerUtil.logMessageNoScreenShot("Order is created successfully using given data");

        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on CompleteOrder button\n" + e.getMessage());
        }
        Assert.assertTrue(WebInteractUtil.isPresent(aprisoPage.VerifyOrder, Config.MEDIUM_PAUSE), "Order is completed successfully.");

        Thread.sleep(7000);
    }

    }




//
//    public void VerifyingOrderDetails(Map<String, String> testData) throws InterruptedException{
////        try {
////            WebInteractUtil.clickByJS(aprisoPage.OrderCockpit);
////            LoggerUtil.logMessageNoScreenShot("Clicked on OrderCockpit button");
////        } catch (Exception e) {
////            LoggerUtil.logErrorMessage("Failed to click on OrderCockpit button\n" + e.getMessage());
////        }
////        WebInteractUtil.switchToFrameByIndex(1,10);
////        System. out. println("Switch to the frame");
////        Thread.sleep(3000);
//
//        WebInteractUtil.sendKeys(aprisoPage.FilterOrder, "1505");
//        LoggerUtil.logMessage("order 1505 is search successfully");
//        aprisoPage.FilterOrder.sendKeys(Keys.ENTER);
//        WebInteractUtil.clickByJS(aprisoPage.ClickOnOrder1);
//        Thread.sleep(70000);
//        WebInteractUtil.clickByJS(aprisoPage.Details);
//        LoggerUtil.logMessage("All details verified for order 1505");
//
//    }
//    public void CheckSerial(Map<String, String> testData) throws InterruptedException {
//        try {
//            WebInteractUtil.waitForElementToBeVisible(aprisoPage.OrderCockpit,10000);
//            WebInteractUtil.clickByJS(aprisoPage.OrderCockpit);
//            LoggerUtil.logMessageNoScreenShot("Clicked on OrderCockpit button");
//        } catch (Exception e) {
//            LoggerUtil.logErrorMessage("Failed to click on OrderCockpit button\n" + e.getMessage());
//        }
//        //  WebInteractUtil.launchWebApp("http://FRPARAPRISO01/Apriso/Portal/UIService.aspx?Alias=OrderCockpit&fs=true");
//
////           WebInteractUtil.switchToFrameByIdOrName("token",10);
////        Thread.sleep(2000);
////       // WebInteractUtil.doubleClick(aprisoPage.NewCr);
////       // WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//div[@class='Toolbox Top'] //span[contains(text(),'Create New')]"), 10);
////        WebInteractUtil.verifyText(aprisoPage.WIPOrderNo,"000024040001");
////        System.out.println("verify the order");
//
////        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='apr-fullscreen-tab']"));
////        WebInteractUtil.swichTo().frame(WebInteractUtil.findelement(By.xpath("//iframe[@class='apr-fullscreen-tab']"))))
//
//
//        WebInteractUtil.switchToFrameByIndex(1,10);
//        System. out. println("Switch to the frame");
//        WebInteractUtil.waitForElementToBeVisible(aprisoPage.CreateNew,10000);
//        click(aprisoPage.CreateNew);
//        //  WebInteractUtil.clickByJS(aprisoPage.CreateNew);
//        LoggerUtil.logMessageNoScreenShot("Clicked on CreateNew button");
//        // Thread.sleep(10000);
//        WebInteractUtil.waitForElementToBeVisible(aprisoPage.Facility,10000);
//        try {
//            //  WebInteractUtil.clickByJS(aprisoPage.Facility);
//            WebInteractUtil.sendKeys(aprisoPage.Facility, "LE HAVRE");
//            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
//            LoggerUtil.logMessageNoScreenShot("Clicked on Facility button");
//        } catch (Exception e) {
//            LoggerUtil.logErrorMessage("Failed to click on OrderCockpit button\n" + e.getMessage());
//            //  Thread.sleep(10000);
//        }
//        try {
//            WebInteractUtil.waitForElementToBeVisible(aprisoPage.OrderType,10000);
//            //  WebInteractUtil.clickByJS(aprisoPage.OrderType);
//            WebInteractUtil.sendKeys(aprisoPage.OrderType, "CA Production Order");
//            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
//            LoggerUtil.logMessageNoScreenShot("OrderType is entered successfully");
//        } catch (Exception e) {
//            LoggerUtil.logErrorMessage("Failed to entered OrderType \n" + e.getMessage());
//            //   Thread.sleep(10000);
//        }
//        try {
//            //    WebInteractUtil.clickByJS(aprisoPage.Process);
//            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Process,10000);
//            WebInteractUtil.sendKeys(aprisoPage.Process, "SNA_DEMO_BDL0010-30-0-CM01-G1");
//            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
//            LoggerUtil.logMessageNoScreenShot("OrderType is entered successfully");
//        } catch (Exception e) {
//            LoggerUtil.logErrorMessage("Failed to entered Process \n" + e.getMessage());
//            //Thread.sleep(10000);
//        }
//        try {
//            //   WebInteractUtil.clickByJS(aprisoPage.ProcessRevision);
//            WebInteractUtil.waitForElementToBeVisible(aprisoPage.ProcessRevision,10000);
//
//            WebInteractUtil.sendKeys(aprisoPage.ProcessRevision, "REV.001.001.001");
//            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
//            LoggerUtil.logMessageNoScreenShot("ProcessRevision is entered successfully");
//        } catch (Exception e) {
//            LoggerUtil.logErrorMessage("Failed to entered ProcessRevision \n" + e.getMessage());
//            //  Thread.sleep(10000);
//        }
////        try {
////         //   WebInteractUtil.clickByJS(aprisoPage.ExplosionType);
////            WebInteractUtil.sendKeys(aprisoPage.ExplosionType, );
////            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
////            LoggerUtil.logMessageNoScreenShot("ExplosionType is entered successfully");
////        } catch (Exception e) {
////            LoggerUtil.logErrorMessage("Failed to entered ExplosionType \n" + e.getMessage());
////            Thread.sleep(10000);
////        }
//        try {
//            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Productbutton,10000);
//
//            WebInteractUtil.clickByJS(aprisoPage.Productbutton);
//            //  WebInteractUtil.waitForElementToBeVisible(aprisoPage.productFilter,10000);
//            WebInteractUtil.sendKeys(aprisoPage.productFilter, "BDL0010-30-0-CM01");
//            aprisoPage.productFilter.sendKeys(Keys.ENTER);
//            WebInteractUtil.clickByJS(aprisoPage.SelectValue);
//            WebInteractUtil.clickByJS(aprisoPage.OkButton);
//            Thread.sleep(10000);
//
//            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
//            LoggerUtil.logMessageNoScreenShot("ProductNo is entered successfully");
//        } catch (Exception e) {
//            LoggerUtil.logErrorMessage("Failed to entered ProductNo \n" + e.getMessage());
//            Thread.sleep(10000);
//        }
//
//        try {
//            //    WebInteractUtil.clickByJS(aprisoPage.Quantity);
//            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Quantity,10000);
//            WebInteractUtil.sendKeys(aprisoPage.Quantity, "3");
//            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
//            LoggerUtil.logMessageNoScreenShot("Quantity is entered successfully");
//        } catch (Exception e) {
//            LoggerUtil.logErrorMessage("Failed to entered Quantity \n" + e.getMessage());
//            //   Thread.sleep(10000);
//        }
//        try {
//            WebInteractUtil.waitForElementToBeVisible(aprisoPage.SaveButton,10000);
//            WebInteractUtil.clickByJS(aprisoPage.SaveButton);
//            //   WebInteractUtil.waitForElementToBeClickableByLocator(By.xpath("//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']"), 10);
//            LoggerUtil.logMessageNoScreenShot("Clicked on Save Button");
//        } catch (Exception e) {
//            LoggerUtil.logErrorMessage("Failed to click on Save Button \n" + e.getMessage());
//        }
//        // Thread.sleep(10000);
//
//
//        WebInteractUtil.clickByJS(aprisoPage.serialbutton);
//        WebInteractUtil.clickByJS(aprisoPage.AddSerial);
//        WebInteractUtil.sendKeys(aprisoPage.AddSerialBox, "App1");
//        WebInteractUtil.clickByJS(aprisoPage.ClickOk);
//        Thread.sleep(5000);
//
//        WebInteractUtil.clickByJS(aprisoPage.AddSerial);
//        WebInteractUtil.sendKeys(aprisoPage.AddSerialBox, "App2");
//        WebInteractUtil.clickByJS(aprisoPage.ClickOk);
//        Thread.sleep(5000);
//
//
//        WebInteractUtil.waitForElementToBeVisible(aprisoPage.General,10000);
//        WebInteractUtil.clickByJS(aprisoPage.General);
//        Thread.sleep(4000);
//        WebInteractUtil.waitForElementToBeVisible(aprisoPage.ClickOnExplode,10000);
//        WebInteractUtil.clickByJS(aprisoPage.ClickOnExplode);
//        Thread.sleep(6000);
//
//    }
////    public void MongoDBConnection(Map<String, String> testData) {
////        String connectionString = "mongodb+srv://vivekkatkar24:<password>@cluster0.73j9t4j.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
////
////        ServerApi serverApi = ServerApi.builder()
////                .version(ServerApiVersion.V1)
////                .build();
////
////        MongoClientSettings settings = MongoClientSettings.builder()
////                .applyConnectionString(new ConnectionString(connectionString))
////                .serverApi(serverApi)
////                .build();
////
////        // Create a new client and connect to the server
////        try (MongoClient mongoClient = MongoClients.create(settings)) {
////            try {
////                // Send a ping to confirm a successful connection
////                System.out.println("mongoDB");
////                Thread.sleep(3000);
////                MongoDatabase database = mongoClient.getDatabase("sample_mflix");
////               // database.runCommand(new Document("ping", 1));
////               // database.runCommand(new Document(System.out.println("")));
////                for (String name : database.listCollectionNames()) {
////                    System.out.println(name);
////                }
////
////                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
////            } catch (MongoException | InterruptedException e) {
////                e.printStackTrace();
////
////            }
////        }










