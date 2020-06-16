package SeleniumTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ComplexOperateTest extends BaseTest {
	public String rootPath = "D:\\workspace_java\\AutomatedTestExercises\\src\\test\\resources\\SeleniumComplexOperateTest\\";
	
	public void JavaScriptClick(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("使用javascript进行页面元素的点击");
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", element);
			} else {
				System.out.println("页面元素无法进行操作");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("页面元素没有附在网页中");
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println("页面元素没有找到");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("无法处理的异常");
			e.printStackTrace();
		}
	}

	@Test
	public void test_jsClick() throws Exception {
		driver.get(sogouURL);

		WebElement searchInputBox = driver.findElement(By.id("query"));
		WebElement searchButton = driver.findElement(By.id("stb"));

		searchInputBox.sendKeys("智云未来");
		JavaScriptClick(searchButton);
	}
	
	@Test
	public void test_ajax() {
		driver.get(sogouURL);

		WebElement searchInputBox = driver.findElement(By.id("query"));
		WebElement searchButton = driver.findElement(By.id("stb"));
		
		searchInputBox.sendKeys("智云未来");
		searchButton.click();
		
		try {
			Thread.sleep(1000 * 1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.navigate().back();
		
		searchInputBox = driver.findElement(By.id("query"));
		searchInputBox.click();
		List<WebElement> words = driver.findElements(By.xpath("//ul[@class='suglist']/li"));
		
		System.out.println("test_ajax==>查看下拉框中的元素");
		for (WebElement word : words) {
			System.out.println(word.getText());
			if (word.getText().equals("智云未来")) {
				word.click();
				break;
			}
		}
		
		try {
			Thread.sleep(1000 * 1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.navigate().back();
		
		searchInputBox = driver.findElement(By.id("query"));
		searchInputBox.click();
		
		WebElement word = driver.findElement(By.xpath("//ul[@class='suglist']/li[1]"));
		
		word.click();
	}
	
	public void setAttribute(WebElement element, String attributeName, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, value);
	}
	
	public void removeAttribute(WebElement element, String attributeName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute(arguments[1]);", element, attributeName);
	}
	
	@Test
	public void test_elementAttrConfig() {
		String testURL = rootPath + "ElementAttrConfig.html";
		driver.get(testURL);
		
		WebElement textInputBox = driver.findElement(By.id("text"));
		
		setAttribute(textInputBox, "size", "10");
		
		removeAttribute(textInputBox, "size");
	}
	
	@Test
	public void test_datePickerTest() {
		// 如果遇到日期控件不能输入的情况下，设置属性，去掉readonly
		String testURL = rootPath + "DatepickerTest.html";
		driver.get(testURL);
		
		WebElement dateInputBox = driver.findElement(By.id("datepicker"));
		dateInputBox.sendKeys("12/31/2015");
	}
	
	@Test
	public void test_downloadFile() {
//		String testURL = "http://ftp.mozilla.org/pub/firefox/releases/0.10.1/";
//		String downloadFilePath = "D:\\workspace_java\\AutomatedTestExercises\\download\\";
//		driver.get(testURL);
		
		// pass
		// 下载文件时需要配置一些浏览器参数
	}
	
	@Test
	public void test_uploadFileTest() {
		// 直接发送sendkeys选择文件
		
		String testURL = rootPath + "uploadTest.html";
		driver.get(testURL);
		
		WebElement fileUploadBox = driver.findElement(By.id("fileToUpload"));
		fileUploadBox.sendKeys("D:\\workspace_java\\AutomatedTestExercises\\pic\\test.png");
		
		System.out.println(fileUploadBox.getAttribute("value"));
	}
	
	@Test
	public void test_uploadFileAutoItTest() {
		// 通过autoit上传，编译脚本为exe，通过命令行方式启动运行
		String testURL = rootPath + "uploadTest2.html";
		driver.get(testURL);
		
		WebElement fileUploadBox = driver.findElement(By.xpath("//input[@id='file22']"));

		Actions action = new Actions(driver);
		
		action.moveToElement(fileUploadBox).perform();
		action.click().perform();
		
		String scriptPath = "D:\\workspace_java\\AutomatedTestExercises\\src\\test\\java\\SeleniumTest\\uploadfile.exe";
		String uploadFilePath = "D:\\workspace_java\\AutomatedTestExercises\\pic\\test.png";
		try {
			Runtime.getRuntime().exec(scriptPath + " " + uploadFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(fileUploadBox.getAttribute("value"));
	}
	
	@Test
	public void test_scrollTest() {
		String testURL = "https://v.sogou.com/";
		driver.get(testURL);
		
		// 滚动条滚动到最下方
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		try {
			Thread.sleep(1000 * 2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement element = driver.findElement(By.xpath("//*[@id=\"container\"]/div[2]"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		
		try {
			Thread.sleep(1000 * 2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		js.executeScript("window.scrollBy(0, 800)");
	}
	
	public void setAndctrlVClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		
		Robot robot = null;
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public void pressTabKey() {
		Robot robot = null;

		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	public void pressEnterKey() {
		Robot robot = null;

		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	@Test
	public void test_robotTest() {
		driver.get(sogouURL);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("query")));
		
		setAndctrlVClipboardData("智云未来");
		
		pressTabKey();
		
		pressEnterKey();
		
		try {
			Thread.sleep(1000 * 3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
