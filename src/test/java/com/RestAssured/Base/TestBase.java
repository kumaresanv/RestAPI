package com.RestAssured.Base;


import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.apache.log4j.Logger;

import com.RestAssured.Utilities.logRestAssured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response httpResponse;
	//public static logRestAssured ldogRestAssured;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		logger = Logger.getLogger("RestAssuredAPI");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.INFO);		
	}
	
}