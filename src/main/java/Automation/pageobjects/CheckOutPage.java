package Automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Automation.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		
		//to initialize driver to construct findelement for FindBy Page factory
		PageFactory.initElements(driver, this);
	}
	
	
	//PageFactory is also a subset of Page Object
	
	@FindBy(css="[placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	private WebElement submit;
	
	By results=By.cssSelector(".ta-results");
	
	//action methods
	
	public void selectCountry(String countryname) {
		 Actions a=new Actions(driver);
         a.sendKeys(country,countryname).click().build().perform();
         waitForElementToAppear(results);
        selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}
	
	

}
