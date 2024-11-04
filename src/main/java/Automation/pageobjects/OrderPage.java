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

public class OrderPage extends AbstractComponent {
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		
		//to initialize driver to construct findelement for FindBy Page factory
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	//PageFactory
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	
	
	
	//action methods
	
	public Boolean VerifyOrderDisplay(String productName) {
		
        Boolean match= productNames.stream().anyMatch(Product-> Product.getText().equalsIgnoreCase(productName));
        return match;
		
	}
	


}
