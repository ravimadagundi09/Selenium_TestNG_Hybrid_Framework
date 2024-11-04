package Automation.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.pageobjects.CartPage;
import Automation.pageobjects.CheckOutPage;
import Automation.pageobjects.ConfirmationPage;
import Automation.pageobjects.LandingPage;
import Automation.pageobjects.ProductCatalogue;
import Automation.testComponents.BaseTest;
import Automation.testComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest{

@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
public void loginErrorValidation() throws InterruptedException, IOException {
		
		landingPage.loginApplication("ravishopping09@gmail.com", "Smil@123");
		//Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
		Assert.assertEquals("Incorrect email  password.",landingPage.getErrorMessage());
	 
	}

@Test(retryAnalyzer=Retry.class)
public void ProductErrorValidation() throws InterruptedException, IOException {
		
		String productName="ZARA COAT 3";
		//LandingPage landingPage=launchApplication();
		ProductCatalogue productCatalogue=landingPage.loginApplication("ravimadagundi@gmail.com", "Qwert@123");
		productCatalogue.addProductToCart(productName);
		
		CartPage cartPage=productCatalogue.goToCartPage();
         
        Boolean match=cartPage.VerifyProduct("ZARA COAT 33");
        Assert.assertFalse(match);
 
	}

}
