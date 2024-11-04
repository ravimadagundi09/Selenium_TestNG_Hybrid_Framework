package Automation.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	
	//this class is used to handle flacky test cases (when test is failed the test case will come to this class)
	
	int count=0;
	int maxTry=1;

	@Override
	public boolean retry(ITestResult result) {
		
		if(count<maxTry) {
			count++;
			return true;//this means it will run the test case again
		}
		return false;
	}

}
