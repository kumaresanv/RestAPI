package com.RestAssured.Utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
	
	public ExtentHtmlReporter htmlReporters;
	public ExtentReports extent;
	public ExtentTest  test;
	
	
	public void onStart(ITestContext testContext) {
		System.out.println(" kumar - onStart");
		
		htmlReporters = new ExtentHtmlReporter(System.getProperty("user.dir")+"/repotrs/RestAssuredAPI.html");
		
		htmlReporters.config().setDocumentTitle("Report title");
		htmlReporters.config().setReportName("RestAssured Testing Reports");
		htmlReporters.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporters);
		extent.setSystemInfo("User","kumaresan");
		extent.setSystemInfo("environment","QA");
		extent.setSystemInfo("Host Name","Localhost");
		
		System.out.println("Onstart() ITtestcontext - GetName :- " + testContext.getName());
		System.out.println("Onstart() ITtestcontext - testContext.getClass().getName() :- " + testContext.getClass().getName());
		
		System.out.println("Onstart() ITtestcontext - ToString :- " + testContext.toString());
		
		
	}

	public void onTestSuccess(ITestResult tr) {
		String test = tr.getTestName();
		System.out.println("Test Passed GetTestName :" + test);
		ExtentTest etest = extent.createTest(tr.getName());
		etest.log(Status.PASS,"Test case Passed is "+ tr.getName());	
	}
	
	public void onTestFailure(ITestResult tr) {
		String test = tr.getTestName();
		System.out.println("Test Failure GetTestName :" + test);
		ExtentTest etest = extent.createTest(tr.getName());
		etest.log(Status.FAIL,"Test case Failed is "+ tr.getName());
		etest.log(Status.FAIL,"Test case Failed is "+ tr.getThrowable());
	}	
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println(" kumar - onTestStart");
		
		System.out.println(result.getName()+" test case started");
		
	}

}
