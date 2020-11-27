package com.RestAssured.Testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TC_003_GetAllHeaders_Response {
	
	@Test
	public void Registor() {
		
		//set the RestAssured base url
		RestAssured.baseURI = "https://reqres.in";
		
		//Request object
		RequestSpecification HttpRequestObj = RestAssured.given();		
		
		//Response object
		Response HttpResponseObject = HttpRequestObj.request(Method.GET,"/api/user?page=2");
		
		//Print the response object to console window
		String responsebody = HttpResponseObject.getBody().asString();
		
		Headers responseAllHeaders = HttpResponseObject.getHeaders();
		
		for(Header header:responseAllHeaders) {
			System.out.println(header.getName() + " :  " + header.getValue());	
		}
		
		//Basic Authentication
		/*
		 * PreemptiveBasicAuthScheme AuthScheme=new PreemptiveBasicAuthScheme();
		 * AuthScheme.setUserName("UserName"); AuthScheme.setPassword("PWD");
		 * 
		 */
		
	}

}
