package com.client.app.common.utils;

import com.client.app.pageobjects.AprisoPage;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.sogeti.dia.common.utils.LoggerUtil;
import com.sogeti.dia.common.utils.WebInteractUtil;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.HashMap;

import static com.mongodb.client.model.Filters.eq;

public class Login_Via_Database {

    public AprisoPage aprisoPage;

    public boolean launchWebFromDatabase(HashMap<Integer, HashMap<String, String>> testData) throws InterruptedException {
        try {
            //  WebInteractUtil.launchWebApp(testData.get("URL"));
            LoggerUtil.logMessage("URL opened successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to launch URL\n" + e.getMessage());
        }

        //Thread.sleep(10000);
        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Name, 10000);
            //  WebInteractUtil.sendKeys(aprisoPage.Name, testData.get("UserName"));
            LoggerUtil.logMessageNoScreenShot("Username OrderType");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to enter username\n" + e.getMessage());
        }
        //Thread.sleep(5000);

        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.Password, 10000);
            //  WebInteractUtil.sendKeys(aprisoPage.Password, testData.get("Password"));
            LoggerUtil.logMessageNoScreenShot("Password entered successfully");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to enter password\n" + e.getMessage());
        }

        // Thread.sleep(5000);
        try {
            WebInteractUtil.waitForElementToBeVisible(aprisoPage.LogIn, 10000);
            WebInteractUtil.clickByJS(aprisoPage.LogIn);
            LoggerUtil.logMessageNoScreenShot("Clicked on Submit button");
        } catch (Exception e) {
            LoggerUtil.logErrorMessage("Failed to click on Submit button\n" + e.getMessage());
        }
        // Thread.sleep(10000);


        return false;
    }

    public void Connect() {

        System.out.println("Connection");
        //loginViaDatabase.Connect();

        //String connectionString = "mongodb+srv://vivekkatkar24:<password>@cluster0.73j9t4j.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        String connectionString = "mongodb://localhost:27017/";
        //mongodb://localhost:27017/
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings))
        {
            try {
                // Send a ping to confirm a successful connection
                System.out.println("mongoDB");
                Thread.sleep(10000);
                MongoDatabase database = mongoClient.getDatabase("Apriso_Order");
                // database.runCommand(new Document("ping", 1));
                // database.runCommand(new Document(System.out.println("")));
                System.out.println("Yusssss");

                //for (String name : database.listCollectionNames()) {
                //    System.out.println(name);
                //}
                //Thread.sleep(20000);
                MongoCollection<Document> collection = database.getCollection("Url");
                System.out.println("found database");

                Document ddoc2 = new Document("url", "admin2");
                collection.insertOne(ddoc2);
                System.out.println("Inserted");
                Document doc1 = collection.find().first();
                System.out.println(doc1.toJson());
/*
                try {
                    // Inserts a sample document describing a movie into the collection
                    InsertOneResult result = collection.insertOne(new Document()
                            .append("_id", new ObjectId())
                            .append("title", "Ski Bloopers")
                            .append("genres", Arrays.asList("Documentary", "Comedy")));
                    // Prints the ID of the inserted document
                    System.out.println("Success! Inserted document id: " + result.getInsertedId());

                    // Prints a message if any exceptions occur during the operation
                } catch (MongoException me) {
                    System.err.println("Unable to insert due to an error: " + me);
                }

*/
//                FindIterable<Document> doc1 = collection.find();
//                // Prints a message if there are no result documents, or prints the result document as JSON
//                if (doc1 == null) {
//                    System.out.println("No results found.");
//                } else {
//                    System.out.println(doc1.cursor());
//                }
//
//                FindIterable<Document> iterDoc = collection.find();
//                System.out.println(iterDoc.iterator().toString());
//                Iterator it = iterDoc.iterator();
//                while (it.hasNext()) {
//                    System.out.println(it.next());
//                }



                Thread.sleep(10000);
                MongoCursor<Document> cursor =  collection.find().cursor();//Mongo Cursor interface implementing the iterator protocol
                System.out.println("found cursor");
                cursor.forEachRemaining(System.out::println);


//                DBCursor cursor = coll.find();
//                while (cursor.hasNext()) {
//                    DBObject obj = cursor.next();
//                    //do your thing
//                }

                //DBCursor cur = db.getCollection("people").find();
                //DBObject dbo = cur.next();
                //Set<String> s = dbo.keySet();
                Bson filter = Filters.exists("username");

                collection.find(filter).forEach(doc -> System.out.println(doc.toJson()));


//Creates instructions to project two document fields
                Bson projectionFields = Projections.fields(
                        //Projections.include("username", "password"),
                        Projections.include("username"),
                        Projections.excludeId());
                System.out.println("found me");
//                // Retrieves the first matching document, applying a projection and a descending sort to the results
                Document doc = collection.find(eq("username", "admin"))
                        .projection(projectionFields)
                        .sort(Sorts.descending("username"))
                        .first();
                System.out.println("found admin");
                // Prints a message if there are no result documents, or prints the result document as JSON
                // if (doc == null) {
                //     System.out.println("No results found.");
//                } else {
                //      System.out.println(doc.toJson());
                //}}



                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException | InterruptedException e) {
                e.printStackTrace();

            }
        }


    }
}