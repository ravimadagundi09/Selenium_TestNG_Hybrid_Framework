package Automation.stepDefinations;

import java.io.IOException;

import org.testng.Assert;

import Automation.pageobjects.CartPage;
import Automation.pageobjects.CheckOutPage;
import Automation.pageobjects.ConfirmationPage;
import Automation.pageobjects.LandingPage;
import Automation.pageobjects.ProductCatalogue;
import Automation.testComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	LandingPage landingPage;
	ProductCatalogue productCatalogue;
	ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_Landed_On_Ecommerce_Page() throws IOException {
		landingPage=launchApplication();
	}
	
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username,String password) {
	productCatalogue=landingPage.loginApplication(username, password);
	}
	
	
	@When("^I add product (.+) to cart$")
	public void add_Product_to_cart(String productName) {
		productCatalogue.addProductToCart(productName);
	}
	
	//And Checkout <productName> and submit the order
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException {
		CartPage cartPage=productCatalogue.goToCartPage();
        
        Boolean match=cartPage.VerifyProduct(productName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage=cartPage.goToCheckout();
        
         checkOutPage.selectCountry("india");
         confirmationPage=checkOutPage.submitOrder();
	}
	
	//Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String msg) {
		 String confirmMessage=confirmationPage.getConfirmationMessage();
         Assert.assertTrue(confirmMessage.equalsIgnoreCase(msg));
         driver.close();
	}
	
	//Then "Incorrect email or password." error message is displayed
	@Then("{string} error message is displayed")
	public void error_message_displayed_confirmationPage(String msg) {
		
		Assert.assertEquals(msg,landingPage.getErrorMessage());
        driver.close();
	}
	
	

}
