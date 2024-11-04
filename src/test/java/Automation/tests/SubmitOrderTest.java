package Automation.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.pageobjects.CartPage;
import Automation.pageobjects.CheckOutPage;
import Automation.pageobjects.ConfirmationPage;
import Automation.pageobjects.LandingPage;
import Automation.pageobjects.OrderPage;
import Automation.pageobjects.ProductCatalogue;
import Automation.testComponents.BaseTest;
import Automation.testComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{

	String productName="ZARA COAT 3";
	
@Test(dataProvider="getData",groups="purchase",retryAnalyzer=Retry.class)
public void submitOrder(HashMap <String,String> input) throws InterruptedException, IOException {
		
		
		//LandingPage landingPage=launchApplication();
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("productname"));
		
		CartPage cartPage=productCatalogue.goToCartPage();
         
        Boolean match=cartPage.VerifyProduct(input.get("productname"));
        Assert.assertTrue(match);
        CheckOutPage checkOutPage=cartPage.goToCheckout();
        
         checkOutPage.selectCountry("india");
         ConfirmationPage confirmationPage=checkOutPage.submitOrder();
          
          String confirmMessage=confirmationPage.getConfirmationMessage();
         Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        
	}

@Test(dependsOnMethods= {"submitOrder"},retryAnalyzer=Retry.class)
public void OrderHistoryTest() {
	ProductCatalogue productCatalogue=landingPage.loginApplication("ravishopping09@gmail.com", "Smile@123");
	 OrderPage orderPage=productCatalogue.goToOrderPage();
	 Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
}

   
   @DataProvider
   public  Object[][] getData() throws IOException {
	   

	   List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Automation//data//PurchaseOrder.json");
	   
	   return new Object[][] {{data.get(0)},{data.get(1)}};
	   
//	   HashMap<String,String> map=new HashMap<String,String>();
//	   map.put("email", "ravishopping09@gmail.com");
	   
//	   map.put("password", "Smile@123");
//	   map.put("productname", "ZARA COAT 3");
//	   
//	   HashMap<String,String> map1=new HashMap<String,String>();
//	   map1.put("email", "shetty@gmail.com");
//	   map1.put("password", "Iamking@000");
//	   map1.put("productname", "ADIDAS ORIGINAL");
   }
   
// @DataProvider
// public  Object[][] getData() {
//	   
//	   return new Object[][] {{"ravishopping09@gmail.com","Smile@123","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
// }







}
