package com.RestAssured.Testcases;

import java.io.IOException;

import org.apache.log4j.Level;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.RestAssured.Base.TestBase;
import com.RestAssured.Utilities.XLUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_004_DataDriven extends TestBase {	
	
	@BeforeClass
	public void start() {
		logger.info("************* Before start of the class TC_004_DataDriven  ***********");
		logger.setLevel(Level.INFO);	
	}
	
	@Test(dataProvider="Testdata")
	public void PostDataDriven(String name,String Job) throws InterruptedException {
		
		//ldogRestAssured.startLog(this.getClass().getName());
		logger.info("*************Starting DataDriven***********");
		//SetBase url
		  RestAssured.baseURI = "https://reqres.in";
		
		//Request object
		  httpRequest = RestAssured.given();
		
		//Set Request body parameters
		JSONObject HttpRequestParams=new JSONObject();		
		HttpRequestParams.put("UserName", name);	
		HttpRequestParams.put("UserJob", Job);	
		httpRequest.header("content-Type","application/json");
		
		//Set the Request body paramenter to Request body
		httpRequest.body(HttpRequestParams.toJSONString());
		
		//Response object
		httpResponse = httpRequest.request(Method.POST,"/api/users");
		
		Thread.sleep(3000);
		
		
		//System.out.println("Response body is :" + ResponseBody);
		
		//Print the response object to console window
		String ResponseBody = httpResponse.getBody().asString();
		Assert.assertEquals(ResponseBody.contains(name),true);
		
		//Check status code
		int StatusCode = httpResponse.getStatusCode();
		Assert.assertEquals(StatusCode,201);
		
		//Check status code
		String StatusLine = httpResponse.getStatusLine();
		Assert.assertEquals(StatusLine,"HTTP/1.1 201 Created");	
		
	}
	
	@Test
	public void test2()
	{
		Assert.assertEquals(true, true);
	}
	
	/*
	@Test
	void checkResponseBody()
	{
		//Print the response object to console window
		String ResponseBody = httpResponse.getBody().asString();
		Assert.assertEquals(ResponseBody.contains(name),true);
	}
	*/
	
	
	@AfterClass
	public void EndOfClass() {
		logger.info("************* End of the class TC_004_DataDriven  ***********");		
	}
	
	
	
	
	
	@DataProvider(name="Testdata")
	public String [][] getdata() throws IOException{
		
		String Filepath = System.getProperty("user.dir")+"\\TestData\\UserTestdata.xlsx";
		System.out.println("Filepath :" + Filepath);
		
		int rowcount = XLUtils.getRowCount(Filepath, "Sheet1");
		int colcount = XLUtils.getCellCount(Filepath, "Sheet1", 1);
		
		String  data[][] = new String[rowcount][colcount];
		
		for(int i=1;i <=rowcount;i++) {
			for(int j=0;j < colcount;j++) {
				data[i-1][j] = XLUtils.getCellData(Filepath, "Sheet1", i, j);				
			}
		}
		
		//String [][] empData = {{"kumaresan123","TestLead"},{"Santhosh","Lead"}};
		return data;
		
	}	

}
