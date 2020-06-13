package SeleniumTest;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APIBrowserTest extends BaseTest{
  @Test
  public void test_operateBrowser() {
	  Point point = new Point(150, 150);
	  Dimension dimension = new Dimension(500, 500);
	  
	  driver.manage().window().setPosition(point);
	  
	  driver.manage().window().setSize(dimension);
	  
	  System.out.println("获取当前浏览器位置：");
	  System.out.println(driver.manage().window().getPosition());
	  System.out.println("获取当前浏览器大小：");
	  System.out.println(driver.manage().window().getSize());
	  
	  driver.manage().window().maximize();
	  
	  driver.get(sogouURL);
  }
  
  @Test
  public void test_getTitle() {
	  driver.get(sogouURL);
	  System.out.println(driver.getTitle());
  }
  
  @Test
  public void test_getPageSource() {
	  driver.get(sogouURL);
	  System.out.println(driver.getPageSource());
  }
  
  @Test
  public void test_getCurrentPageUrl() {
	  driver.get(sogouURL);
	  System.out.println(driver.getCurrentUrl());
  }
}
