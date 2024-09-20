package com.client.app.common.utils;

import com.client.app.pageobjects.AprisoPage;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;

import java.util.HashMap;

public class DataUtils {
    public AprisoPage aprisoPage;
    public String aprisoUrl;
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
    public String UOM;





    public void orderDetails() {
        String connectionString = "mongodb://localhost:27017/";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        System.out.println("connect");

//        MongoClient mongoClient = MongoClients.create(settings);
//
//        {
//
//            //  Thread.sleep(10000);
//            MongoDatabase database = mongoClient.getDatabase("Apriso_Order");
//
//            MongoCollection<Document> collection3 = database.getCollection("Screens");
//            Document doc4 = collection3.find().first();
//            JSONObject object3 = new JSONObject(doc4.toJson());
//
//            String lng15 = object3.getString("Screens");
//
//
//            MongoCollection<Document> collection2 = database.getCollection("details");
//            Document doc3 = collection2.find().first();
//            JSONObject object2 = new JSONObject(doc3.toJson());
//
//            String lng3 = object2.getString("Order Type");
//            String lng4 = object2.getString("Order Status");
//            String lng5 = object2.getString("Facility");
//            String lng6 = object2.getString("Priority");
//            String lng7 = object2.getString("Process");
//            String lng8 = object2.getString("Process Rev");
//            String lng9 = object2.getString("Product No");
//            String lng10 = object2.getString("Description");
//            String lng11 = object2.getString("Quantity");
//            String lng12 = object2.getString("UOM");
//
//            OrderType = lng3;
//            OrderStatus = lng4;
//            Facility = lng5;
//            Priority = lng6;
//            Process = lng7;
//            ProcessRev = lng8;
//            ProductNo = lng9;
//            Description = lng10;
//            Quantity = lng11;
//            UOM = lng12;
//
//
//            HashMap<String, String> orderData = new HashMap<String, String>();
//            orderData.put("Screens", lng15);
//            orderData.put("OrderType", lng3);
//            orderData.put("OrderStatus", lng4);
//            orderData.put("Facility", lng5);
//            orderData.put("Priority", lng6);
//            orderData.put("Process", lng7);
//            orderData.put("ProcessRev", lng8);
//            orderData.put("ProductNo", lng9);
//            orderData.put("Description", lng10);
//            orderData.put("Quantity", lng11);
//            orderData.put("UOM", lng12);
//
//            System.out.println(orderData.toString());
//            System.out.println(lng11 + "and" + lng12);
        }


    }

