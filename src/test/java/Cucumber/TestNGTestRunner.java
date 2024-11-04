package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//monochrome makes the cucumber o/p in readeable formate
@CucumberOptions(features="src/test/java/Cucumber",glue="Automation.stepDefinations",
monochrome=true,tags="@Regression",plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
//cucumberoptions dont have power to read TestNG assertions so we need to use AbstractTestNGCucumberTests
	
}

//used TestNG assertions so we are using TestNG Runner