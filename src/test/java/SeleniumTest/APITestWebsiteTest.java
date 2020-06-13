package SeleniumTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class APITestWebsiteTest extends BaseTest{
	@Test
	public void test_api_get() {
		driver.get(sogouURL);
	}

	@Test
	public void test_api_navigate() {
		driver.navigate().to(baiduURL);
	}

	@Test
	public void test_api_back() {
		driver.navigate().to(sogouURL);
		driver.navigate().to(baiduURL);
		driver.navigate().back();
	}

	@Test
	public void test_api_forward() {
		driver.navigate().to(sogouURL);
		driver.navigate().to(baiduURL);
		driver.navigate().back();
		driver.navigate().forward();
	}

	@Test
	public void test_api_refresh() {
		driver.navigate().to(sogouURL);
		driver.navigate().refresh();
	}

}
