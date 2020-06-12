package SeleniumTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebDriverTest {
	public WebDriver driver;
	String baseUrl = "http://www.sogou.com/";
	
  @Test
  public void TestCase_WebDriverTest() {
	  driver.get(baseUrl);
	  WebElement inputBox = driver.findElement(By.id("query"));
	  
	  Assert.assertTrue(inputBox.isDisplayed());
	  
	  inputBox.sendKeys("智云未来");
	  driver.findElement(By.id("stb")).click();
	  try {
		Thread.sleep(1000*5);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", "D:\\TestTool\\chromedriver.exe");
	  driver = new ChromeDriver();
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

}
