package com.RestAssured.Testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TC_002_Post_name {
	
	@Test
	public void Registor() {
		
		//set the RestAssured base url
		RestAssured.baseURI = "https://reqres.in";
		
		//Request object
		RequestSpecification HttpRequestObj = RestAssured.given();
		
		
		//Set requestBody object
		//Request payload sending along with post request
		JSONObject requestParams=new JSONObject();
		requestParams.put("name", "morpheus");
		requestParams.put("job", "leader");	
		HttpRequestObj.header("content-Type","application/json");		
		HttpRequestObj.body(requestParams.toJSONString());		
		
		//Response object
		Response HttpResponseObject = HttpRequestObj.request(Method.POST,"/api/users");
		
		//Print the response object to console window
		String responsebody = HttpResponseObject.getBody().asString();
		System.out.println("Response body is : "+responsebody);	
		System.out.println("json path :Name  "+HttpResponseObject.jsonPath().get("name"));	
		System.out.println("json path :ID "+HttpResponseObject.jsonPath().get("id"));	
		System.out.println("json path :created date "+HttpResponseObject.jsonPath().get("createdAt"));
		
		Assert.assertEquals(requestParams.get("name"), "morpheus");
		//Status code validation
		int Statuscode = HttpResponseObject.getStatusCode();
		Assert.assertEquals(Statuscode, 201);
		Assert.assertEquals(HttpResponseObject.jsonPath().get("name"), requestParams.get("name"));
		
		
	}

}
