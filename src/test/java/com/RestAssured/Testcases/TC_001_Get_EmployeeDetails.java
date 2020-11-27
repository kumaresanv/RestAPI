package com.RestAssured.Testcases;

import static org.testng.Assert.assertEquals;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_001_Get_EmployeeDetails {
	
	
	@Test
	public void getEmployeeDetails(){
		
		//Specify the base URL
		RestAssured.baseURI="https://reqres.in";
		
		//Request object
		RequestSpecification httpRequestObject=RestAssured.given();
		
		//Response Object
		Response ResponseObj=httpRequestObject.request(Method.GET,"/api/user?page=2");
		
		//Print the response to console window
		String ResponseBody = ResponseObj.getBody().asString();
		System.out.println("Response body is : "+ResponseBody);
		
		//Status code validation
		int statusCode = ResponseObj.getStatusCode();
		System.out.println("statusCode : "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//Status code validation
		String statusLine = ResponseObj.getStatusLine();
		System.out.println("StatusLine : "+statusLine);
		Assert.assertEquals(statusLine.trim(), "HTTP/1.1 200 OK");		
	}

}
