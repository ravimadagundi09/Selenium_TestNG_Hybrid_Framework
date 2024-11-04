package Automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		
		//to initialize driver to construct findelement for FindBy Page factory
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	//Action Medthods
	
	public String getConfirmationMessage() {
		
		//to give demo for enscapulation
//				CheckOutPage ch=new CheckOutPage(driver);
//				ch.submit.click();
			
		return confirmationMessage.getText();
		 
	}

}
