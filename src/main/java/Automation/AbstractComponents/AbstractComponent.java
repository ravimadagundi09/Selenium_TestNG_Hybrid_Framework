package Automation.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Automation.pageobjects.CartPage;
import Automation.pageobjects.OrderPage;

public class AbstractComponent {

     WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy) {
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		}
	
	public void waitForElementToDisappear(WebElement findBy) {
		
		//4 seconds  _Application issue not selenium issue for 4 seconds delay
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(findBy));
		}
	
public void waitForElementToBeClickable(WebElement findBy) {
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
		}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		  CartPage cartPage=new CartPage(driver);
		  return cartPage;
	}
	
	public OrderPage goToOrderPage() {
		orderHeader.click();
		  OrderPage orderPage=new OrderPage(driver);
		  return orderPage;
	}

}
