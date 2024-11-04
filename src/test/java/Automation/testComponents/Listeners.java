package Automation.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import Automation.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	WebDriver driver;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	//Thread safe and runs in concurrent mode so parallel run will get execute correctly
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		
		ITestListener.super.onTestStart(result);
		
		//create an entry when test start for each method
		test=extent.createTest(result.getMethod().getMethodName());
		
		//unique thread id(errorValidationTest) maps to->test
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		ITestListener.super.onTestSuccess(result);
		extentTest.get().log(Status.PASS,"Test Passed");//extentTest.get()=gets the unique Test (test.log())
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//gives the error details this is required
		extentTest.get().fail(result.getThrowable());
		
		//get driver
		//we cannot use getMethods coz fileds are associated with class level
		//getTestClass>goes to tenstng class
		//getRealClass>goes to actual test class
		
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		//Screenshot ,Attach to report
		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		extentTest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
		
		//ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		
		//to wrap the report and generate report
		extent.flush();
	}
	

}
