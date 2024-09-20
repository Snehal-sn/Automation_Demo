package com.client.app.uitests.module;

import com.client.app.common.utils.AprisoUtil;
import com.client.app.common.utils.DataUtils;
import com.client.app.common.utils.Login_Via_Database;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
//import com.mongodb.DBCursor;
import com.mongodb.client.result.InsertOneResult;
import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;
import com.sogeti.dia.common.utils.ExcelUtil;
import com.sogeti.dia.common.utils.LoggerUtil;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class WebApp_5 extends BaseTest {
    public AprisoUtil aprisoUtil;
    public DataUtils dataUtils;

    public String aprisoUrl;

    Login_Via_Database loginViaDatabase;
    public HashMap<Integer, HashMap<String, String>> testData;
    public String className;

    public Map<String, String> orderData;


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        CapabilityFactoryUtil.initiateDriver();

        // orangeHRMUtil = new OrangeHRMUtil();
        aprisoUtil = new AprisoUtil();
        dataUtils = new DataUtils();
         Login_Via_Database loginViaDatabase;
          DataUtils dataUtils;

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
    public void CreateAndVerifyOderTakingOrdarDataFromDataBase(HashMap<String, String> testData) throws InterruptedException {
//        System.out.println("Connection");
//
//        String connectionString = "mongodb://localhost:27017/";
//
//        ServerApi serverApi = ServerApi.builder()
//                .version(ServerApiVersion.V1)
//                .build();
//
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(new ConnectionString(connectionString))
//                .serverApi(serverApi)
//                .build();
//
//
//        try (MongoClient mongoClient = MongoClients.create(settings))
//        {
//            try {
//
//                System.out.println("mongoDB");
//                Thread.sleep(10000);
//                MongoDatabase database = mongoClient.getDatabase("Apriso_Order");
//
//                System.out.println("Yusssss");
//
//
//                MongoCollection<Document> collection = database.getCollection("Url");
//                System.out.println("found database");
//
//                Document ddoc2 = new Document("url", "admin2");
//                //collection.insertOne(ddoc2);
//                System.out.println("Inserted");
//                Document doc1 = collection.find().first();
//                //System.out.println(doc1.toJson());
//                JSONObject object = new JSONObject(doc1.toJson());
//                //JSONObject location = object.getJSONObject("url");
//                String lng = object.getString("url");
//                aprisoUrl = lng;
//                System.out.println(lng);
//                Thread.sleep(10000);
//                MongoCursor<Document> cursor =  collection.find().cursor();//Mongo Cursor interface implementing the iterator protocol
//                System.out.println("found cursor");
              //  cursor.forEachRemaining(System.out::println);

              //  Bson filter = Filters.exists("username");

              //  collection.find(filter).forEach(doc -> System.out.println(doc.toJson()));

//                Bson projectionFields = Projections.fields(
//                        //Projections.include("username", "password"),
//                        Projections.include("username"),
//                        Projections.excludeId());
//                System.out.println("found me");
//
//                Document doc = collection.find(eq("username", "admin"))
//                        .projection(projectionFields)
//                        .sort(Sorts.descending("username"))
//                        .first();
//                System.out.println("found admin");
//
//              //  System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
//            } catch (MongoException | InterruptedException e) {
//                e.printStackTrace();
//
//            }
//        }

        aprisoUtil.launchWebApplication(testData,aprisoUrl);
        aprisoUtil.orderCreation();
        LoggerUtil.logMessage("Order created successfully");


    }
}
