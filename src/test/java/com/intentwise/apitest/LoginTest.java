package com.intentwise.apitest;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
 
public class LoginTest {
	
	private String token = "";
	private String userID = "";
	
	private static final String BASE_URL = "https://apitest.intentwise.com";
	
	@Test
	public void loginSuccess()
	{   
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = BASE_URL;
 
		RequestSpecification httpRequest = RestAssured.given();
 
		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "girish@intentwise.com"); // Cast
		requestParams.put("password", "Password@123");
		
		
		httpRequest.header("Content-Type", "application/json");
		 
		// Add the Json to the body of the request
		httpRequest.body(requestParams.toString());
		 
		// Post the request and check the response
		Response response = httpRequest.post("/user/login");
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		token = response.getBody().asString().substring(10, 46);
		userID = response.getBody().asString().substring(91, 96);
		System.out.println("here is the token: "+token+ "here is userID " + userID);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
	}
 
	@Test
	public void loginFailure()
	{   
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = BASE_URL;
 
		RequestSpecification httpRequest = RestAssured.given();
 
		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "girish@intentwise.com"); // Cast
		requestParams.put("password", "Password123");
		
		httpRequest.header("Content-Type", "application/json");
		 
		// Add the Json to the body of the request
		httpRequest.body(requestParams.toString());
		 
		// Post the request and check the response
		Response response = httpRequest.post("/user/login");
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 401);
	}
	@Test
	public void getUser()
	{   
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = BASE_URL;
 
		RequestSpecification httpRequest = RestAssured.given();
 
		JSONObject requestParams = new JSONObject();
		requestParams.put("X-Auth-Token" , token); // THIS IS WHERE SOMETHING IS PROBABLY GOING WRONG
		
		
		httpRequest.header("Content-Type", "application/json");
		 
		// Add the Json to the body of the request
		httpRequest.body(requestParams.toString());
		 
		// Post the request and check the response
		Response response = httpRequest.post("/organization/10060/user/"+userID);
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 301);
	}
	
	@Test
	public void getAllOrganization()
	{   
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = BASE_URL;
 
		RequestSpecification httpRequest = RestAssured.given();
 
		JSONObject requestParams = new JSONObject();
		requestParams.put("X-Auth-Token" , token); // THIS IS WHERE SOMETHING IS PROBABLY GOING WRONG
		
		
		httpRequest.header("Content-Type", "application/json");
		 
		// Add the Json to the body of the request
		httpRequest.body(requestParams.toString());
		 
		// Post the request and check the response
		Response response = httpRequest.post("/organization/");
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 301);
	}

}