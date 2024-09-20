package com.client.app.uitests.module;

import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sogeti.dia.common.config.BaseTest;
import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.utils.LoggerUtil;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RestAssuredTest_1 extends BaseTest {
	@Test
	public void postAwardPendingVehicleDetails()
	{
		 // Specify the base URL to the RESTful web service
		 RestAssured.baseURI = Config.API_BASE_URL;
		 String bearerToken = Config.API_PASSWORD;
		 String endpoint = "/AwardPending/GetList";

		JSONObject requestParams = new JSONObject();
		requestParams.put("SubjectId", "03a41d55-c922-4237-9fd1-0569c5580d21");
		requestParams.put("currentPage", 1);
		requestParams.put("pageSize", 25);
		requestParams.put("sortColumn", "");
		requestParams.put("sortAscending", true);
		requestParams.put("favStockNumber", 0);
		requestParams.put("favvin", "");

		 // Get the RequestSpecification of the request that you want to sent
		 // to the server. The server is specified by the BaseURI that we have
		 // specified in the above step.
		 RequestSpecification httpRequest = RestAssured.given();

		 // Make a request to the server by specifying the method Type and the method URL.
		 // This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + bearerToken)
				.queryParam("tenant", "US")
				.body(requestParams.toJSONString())
				.post(endpoint);


		// Assert that correct status code is returned.
		Assert.assertEquals(response.getStatusCode(), 200);

		// Now let us print the body of the message to see what response we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("API Response:\n" + responseBody);

		// Assert that itemcount value from the response body is matching with expected value
		JsonPath apiResponse = response.jsonPath();
		int itemCountActual = apiResponse.get("itemCount");
		Assert.assertEquals(itemCountActual, 1);
		String vinActual = apiResponse.get("lstAwardPendingVehicles[0].vin");
		Assert.assertEquals(vinActual, "WA1JCCFS7JR969898");
		String descriptionActual = apiResponse.getString("lstAwardPendingVehicles[0].description");
		Assert.assertEquals(descriptionActual, "2018 AUDI Q3 PREMIUM PLUS");

	 }

    @Test
	public void postAwardPendingVehicleHeaders() {
		RestAssured.baseURI = Config.API_BASE_URL;
		String bearerToken = Config.API_PASSWORD;
		String endpoint = "/AwardPending/GetList";

		JSONObject requestParams = new JSONObject();
		requestParams.put("SubjectId", "03a41d55-c922-4237-9fd1-0569c5580d21");
		requestParams.put("currentPage", 1);
		requestParams.put("pageSize", 25);
		requestParams.put("sortColumn", "");
		requestParams.put("sortAscending", true);
		requestParams.put("favStockNumber", 0);
		requestParams.put("favvin", "");

		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + bearerToken)
				.queryParam("tenant", "US")
				.body(requestParams.toJSONString())
				.post(endpoint);

		// Reader header of a give name. In this line we will get
		// Header named Content-Type
		String contentType = response.header("Content-Type");
		LoggerUtil.logMessageNoScreenShot("Content-Type value: " + contentType);

		// Reader header of a give name. In this line we will get
		// Header named Server
		String serverType = response.header("Server");
		LoggerUtil.logMessageNoScreenShot("Server value: " + serverType);

		// Reader header of a give name. In this line we will get
		// Header named Content-Encoding
		String acceptLanguage = response.header("Content-Encoding");
		LoggerUtil.logMessageNoScreenShot("Content-Encoding: " + acceptLanguage);


	}

	public List<HashMap<String, Object>> getDBResult() throws SQLException {
		ResultSet rs = null;
		if (rs == null) throw new AssertionError();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();

		List<HashMap<String, Object>> dbResult = new ArrayList<>();
		while (rs.next()) {
			HashMap<String, Object> row = new HashMap<>(columns);
			for (int i = 1; i <= columns; i++)
				row.put(rsmd.getColumnName(i), rs.getObject(i));

			dbResult.add(row);
		}

		return dbResult;
	}

}