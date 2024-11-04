package Automation.tests;

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

import Automation.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		LandingPage land=new LandingPage(driver);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("ravishopping09@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Smile@123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
        WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
         prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
         
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
         
         //invisibilityOf works faster then invisibilityOfElementLocated
         wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
         
         driver.findElement(By.cssSelector("[routerlink*=\"cart\"]")).click();
         
         List<WebElement> cartProduct=driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
         Boolean match= cartProduct.stream().anyMatch(Product-> Product.getText().equalsIgnoreCase(productName));
          Assert.assertTrue(match);
          
          //driver.findElement(By.xpath("//button[text()='Checkout']")).click();
          //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",e);
          //run the javascript code
          
          JavascriptExecutor js=(JavascriptExecutor) driver;
          js.executeScript("window.scrollBy(0,1000)");
          Thread.sleep(1000);
         WebElement checkout=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[class='totalRow'] button[type='button']")));
         checkout.click();
         // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']"))));
          //driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']")).click();
          
          
          //using action class to sent keys
          Actions a=new Actions(driver);
          a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").click().build().perform();
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
          driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
          driver.findElement(By.cssSelector(".action__submit")).click();
          
          String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
          
         Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
         
         driver.close();
          
          
          
	}

}
