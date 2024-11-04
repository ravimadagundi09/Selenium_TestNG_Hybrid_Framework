package Automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Automation.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		
		//to initialize driver to construct findelement for FindBy Page factory
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	//PageFactory
	
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> cartProduct;
	
	@FindBy(css="li[class='totalRow'] button[type='button']")
	WebElement checkout;
	
	By checkout_bttn=By.cssSelector("#toast-container");
	
	//action methods
	
	public Boolean VerifyProduct(String productName) {
		
        Boolean match= cartProduct.stream().anyMatch(Product-> Product.getText().equalsIgnoreCase(productName));
        return match;
		
	}
	
	public CheckOutPage goToCheckout() throws InterruptedException {
		JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        Thread.sleep(1000);
        waitForElementToBeClickable(checkout);
		checkout.click();
	   CheckOutPage checkOutPage=new CheckOutPage(driver);
	   return checkOutPage;
	}
	

}
