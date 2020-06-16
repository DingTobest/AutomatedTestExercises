package SeleniumTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APISysTest extends BaseTest {
	public String rootPath = "D:\\workspace_java\\AutomatedTestExercises\\src\\test\\resources\\SeleniumSysTest\\";
	
	@Test
	public void test_killWebbrowser() {
		// 结束chrome进程
		// WindowsUtils.killByName("chrome.exe");
	}
	
	@Test
	public void test_screenshot() {
		driver.get(sogouURL);
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		System.out.println(scrFile.toPath());
		
		File copyFile = new File("D:\\workspace_java\\AutomatedTestExercises\\pic\\test.png");
		if (copyFile.exists()) {
			copyFile.delete();
		}
		
		try{
			Files.copy(scrFile.toPath(), copyFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_jsExecute() {
		System.out.println("test_jsExecute");
		driver.get(sogouURL);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String title = (String)js.executeScript("return document.title");
		
		System.out.println(title);
		
		String searchButtonText = (String)js.executeScript("var button=document.getElementById('stb'); return button.value");
		
		System.out.println(searchButtonText);
	}
	
	@Test
	public void test_dragPageElement() {
		String testURL = rootPath + "DragElementTest.html";
		driver.get(testURL);
		
		WebElement draggable = driver.findElement(By.id("draggable"));
		
		for (int i = 0; i < 5; i++) {
			new Actions(driver).dragAndDropBy(draggable, 0, 10).build().perform();
		}
		
		for (int i = 0; i < 5; i++) {
			new Actions(driver).dragAndDropBy(draggable, 10, 0).build().perform();
		}
		
	}
	
	@Test
	public void test_keyboardClick() {
		driver.get(sogouURL);
		
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL);
		action.keyDown(Keys.SHIFT);
		action.keyDown(Keys.ALT);
		
		action.keyUp(Keys.CONTROL);
		action.keyUp(Keys.SHIFT);
		action.keyUp(Keys.ALT);
		
		action.keyDown(Keys.SHIFT).sendKeys("abcdefg").perform();
	}
	
	@Test
	public void test_rightMouseClick() {
		driver.get(sogouURL);
		
		Actions action = new Actions(driver);
		
		action.contextClick(driver.findElement(By.id("query"))).perform();
	}
	
	@Test
	public void RoverOnTest() {
		String testURL = rootPath + "RoverOnTest.html";
		driver.get(testURL);
		
		WebElement text1 = driver.findElement(By.id("testp"));
		WebElement text2 = driver.findElement(By.id("testp2"));
		
		Actions action = new Actions(driver);
		
		action.moveToElement(text1).perform();
		
		try {
			Thread.sleep(1000 * 2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		action.moveToElement(text2).perform();
	}
	
	@Test
	public void MouseUpAndMouseDownTest() {
		String testURL = rootPath + "MouseUpAndMouseDown.html";
		driver.get(testURL);
		WebElement div = driver.findElement(By.xpath("//div[@id='div1']"));
		
		Actions action = new Actions(driver);
		
		action.clickAndHold(div).perform();
		try {
			Thread.sleep(1000 * 2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		action.release(div).perform();
		try {
			Thread.sleep(1000 * 2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_implicitlyWait() {
		// 隐式等待不推荐使用，程序必须等待设置的时间结束之后，才会继续执行，推荐使用显式等待
		
		driver.get(sogouURL);
		
		// findElement会根据inpicitlyWait设置的时间，进行查找，没有找到就抛出NoSuchElementException
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			WebElement searchInputBox = driver.findElement(By.id("query"));
			
			WebElement searchButton = driver.findElement(By.id("stb"));
			
			searchInputBox.sendKeys("智云未来");
			
			searchButton.click();
			
		} catch (NoSuchElementException e) {
			Assert.fail("没有找到搜索的输入框");
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_ExplicitWait() {
		String testURL = rootPath + "ExplicitWaitTest.html";
		driver.get(testURL);
		
		// 声明WebDriverWait对象，设置触发条件最长等待时间为10秒
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		wait.until(ExpectedConditions.titleContains("水果"));
		
		System.out.println("网页标题出现了水果的关键字");
		
		WebElement select = driver.findElement(By.xpath("//option[@id='peach']"));
		
		wait.until(ExpectedConditions.elementToBeSelected(select));
		
		System.out.println("下拉列表的选项桃子目前处于可选中状态");
		
		// 判断是否可见，并且可被单击
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox']")));
		
		WebElement p = driver.findElement(By.xpath("//p"));
		// 判断标签对象中是否包含相关文字
		wait.until(ExpectedConditions.textToBePresentInElement(p, "爱吃的水果"));
	
		System.out.println("页面标签p中包含相关文本");
	}
	
	@Test
	public void test_customExplicitWait() {
		String testURL = rootPath + "ExplicitWaitTest.html";
		driver.get(testURL);
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement textInputBox = wait.until(new ExpectedCondition<WebElement>(){
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.xpath("//input[@type='text']"));
			}
		});
		
		Assert.assertEquals("今年夏天西瓜像当甜！", textInputBox.getAttribute("value"));
		
		Boolean containTextFlag = wait.until(new ExpectedCondition<Boolean>(){
			@Override
			public Boolean apply(WebDriver d) {
				return d.findElement(By.xpath("//p")).getText().contains("爱吃");
			}
		});
		
		Assert.assertTrue(containTextFlag);
		
		Boolean inputTextVisibleFlag = wait.until(new ExpectedCondition<Boolean>(){
			@Override
			public Boolean apply(WebDriver d) {
				return d.findElement(By.xpath("//input[@type='text']")).isDisplayed();
			}
		});
		
		Assert.assertTrue(inputTextVisibleFlag);
	}
	
	// Selenium中没有直接判断元素是否存在的函数
	// 通过findElement函数，如果对象不存在，则会抛出异常
	// 通过捕获异常的方式，判断元素是否存在
	private boolean isElementExist(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch(NoSuchElementException e) {
			return false;
		}
	}
	
	@Test
	public void test_elementExist() {
		driver.get(sogouURL);
		
		if (isElementExist(By.id("query"))) {
			WebElement searchInputBox = driver.findElement(By.id("query"));
			
			if (searchInputBox.isEnabled()) {
				searchInputBox.sendKeys("智云未来");
			} 
		} else {
			Assert.fail("页面元素不存在");
		}
	}
	
	@Test
	public void test_identifyPopUpWindowByTitle() {
		String testURL = rootPath + "PopUpWindowTest.html";
		driver.get(testURL);
		
		String parentWindowHandle = driver.getWindowHandle();
		
		WebElement sogouLink = driver.findElement(By.xpath("//a"));
		
		sogouLink.click();
		
		Set<String> allWindowsHandles = driver.getWindowHandles();
		
		if (!allWindowsHandles.isEmpty()) {
			for (String windowHandle:allWindowsHandles) {
				if (driver.switchTo().window(windowHandle).getTitle().contains("上网从sogou开始")) {
					driver.findElement(By.id("query")).sendKeys("智云未来");
					
					Actions action = new Actions(driver);
					action.keyDown(Keys.CONTROL).sendKeys("w").keyUp(Keys.CONTROL).sendKeys(Keys.NULL).perform();
					
					break;
				}
			}
		}
		
		driver.switchTo().window(parentWindowHandle);
		Assert.assertEquals(driver.getTitle(), "弹出框测试");
	}
	
	@Test
	public void test_identifyPopUpWindowByPageSource() {
		String testURL = rootPath + "PopUpWindowTest.html";
		driver.get(testURL);
		
		String parentWindowHandle = driver.getWindowHandle();
		
		WebElement sogouLink = driver.findElement(By.xpath("//a"));

		sogouLink.click();

		Set<String> allWindowsHandles = driver.getWindowHandles();

		if (!allWindowsHandles.isEmpty()) {
			for (String windowHandle : allWindowsHandles) {
				if (driver.switchTo().window(windowHandle).getPageSource().contains("上网从sogou开始")) {
					driver.findElement(By.id("query")).sendKeys("智云未来");
				}
			}
		}

		driver.switchTo().window(parentWindowHandle);
		Assert.assertEquals(driver.getTitle(), "弹出框测试");
	}
	
	@Test
	public void test_Alert() {
		String testURL = rootPath + "PopUpWindowTest.html";
		driver.get(testURL);
		WebElement button = driver.findElement(By.xpath("//input[@id='alert']"));
		
		button.click();
		
		try {
			Alert alert = driver.switchTo().alert();
			
			Assert.assertEquals("alert弹出框测试", alert.getText());
			
			alert.accept();
			
		} catch(NoAlertPresentException e) {
			Assert.fail("alert框未找到");
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_HandleConfirm() {
		String testURL = rootPath + "PopUpWindowTest.html";
		driver.get(testURL);
		WebElement button = driver.findElement(By.xpath("//input[@id='confirm']"));
		
		button.click();
		
		try {
			Alert alert = driver.switchTo().alert();
			
			Assert.assertEquals("confirm弹出框测试", alert.getText());
			
			// 点击确定按钮
//			alert.accept();
			// 点击取消按钮
			alert.dismiss();
			
		} catch(NoAlertPresentException e) {
			Assert.fail("alert框未找到");
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_HandlePrompt() {
		String testURL = rootPath + "PopUpWindowTest.html";
		driver.get(testURL);
		WebElement button = driver.findElement(By.xpath("//input[@id='prompt']"));
		
		button.click();
		
		try {
			Alert alert = driver.switchTo().alert();
			
			Assert.assertEquals("prompt弹出框测试", alert.getText());
			
			alert.sendKeys("智云未来");
			// 点击确定按钮
			alert.accept();
			// 点击取消按钮
//			alert.dismiss();
			
		} catch(NoAlertPresentException e) {
			Assert.fail("alert框未找到");
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_frame() {
		// 对frame进行操作，需要使用driver.switchTo().frame方法
		// 否则无法操作frame中的元素
		String testURL = rootPath + "FrameTest.html";
		driver.get(testURL);
		
		driver.switchTo().frame("leftframe");
		WebElement leftFrameText = driver.findElement(By.xpath("//p"));
		
		Assert.assertEquals("这是左侧frame页面上的文字", leftFrameText.getText());
		
		// 从中间frame进入其他frame页面，需要先回到初始化位置
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("middleframe");
		WebElement middleFrameText = driver.findElement(By.xpath("//p"));
		
		Assert.assertEquals("这是中间frame页面上的文字", middleFrameText.getText());

		// 从中间frame进入其他frame页面，需要先回到初始化位置
		driver.switchTo().defaultContent();

		driver.switchTo().frame("rightframe");
		WebElement rightFrameText = driver.findElement(By.xpath("//p"));

		Assert.assertEquals("这是右侧frame页面上的文字", rightFrameText.getText());

		// 从右侧frame进入其他frame页面，需要先回到初始化位置
		driver.switchTo().defaultContent();

		// 如果frame无法正常通过ID等信息查找，可以使用索引号查找，开始为0
		driver.switchTo().frame(1);
		middleFrameText = driver.findElement(By.xpath("//p"));

		Assert.assertEquals("这是中间frame页面上的文字", middleFrameText.getText());

	}
	
	@Test
	public void test_frameBySource() {
		// 对frame进行操作，需要使用driver.switchTo().frame方法
		// 否则无法操作frame中的元素
		String testURL = rootPath + "FrameTest.html";
		driver.get(testURL);
		
		List<WebElement> frames = driver.findElements(By.tagName("frame"));
		
		for (WebElement frame: frames) {
			driver.switchTo().frame(frame);
			
			if (driver.getPageSource().contains("中间frame")) {
				WebElement middleFrameText = driver.findElement(By.xpath("//p"));
				
				Assert.assertEquals("这是中间frame页面上的文字", middleFrameText.getText());
			} 
			
			driver.switchTo().defaultContent();
		}
	}
	
	@Test
	public void test_iframe() {
		// 对frame进行操作，需要使用driver.switchTo().frame方法
		// 否则无法操作frame中的元素
		String testURL = rootPath + "IFrameTest_mian.html";
		driver.get(testURL);
		
		driver.switchTo().frame("leftframe");
		
		WebElement iframe = driver.findElement(By.tagName("iframe"));
		
		driver.switchTo().frame(iframe);
		
		WebElement p = driver.findElement(By.xpath("//p"));
		
		Assert.assertEquals("这是iframe页面上的文字", p.getText());
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("middleframe");
	}
	
	@Test
	public void test_cookie() {
		driver.get("http://www.sogou.com");
		
		Set<Cookie> cookies = driver.manage().getCookies();
		Cookie newCookie = new Cookie("cookieName", "cookieValue");
		
		System.out.println(String.format("Domain->name->value->expiry->path"));
		
		for (Cookie cookie : cookies) {
			System.out.println(String.format("%s->%s->%s->%s->%s", 
					cookie.getDomain(), cookie.getName(),
					cookie.getValue(), cookie.getExpiry(), cookie.getPath()));
		}
		
		driver.manage().deleteCookieNamed("CookieName");
		
		driver.manage().deleteCookie(newCookie);
		
		driver.manage().deleteAllCookies();
		
		try {
			Thread.sleep(1000 * 3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_closeTab() {
		driver.get(sogouURL);
		try {
			Thread.sleep(1000 * 2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 通过发送ctrl+w关闭页签，尝试不成功
//		Actions action = new Actions(driver);
//		action.keyDown(Keys.CONTROL).sendKeys("w").keyUp(Keys.CONTROL).perform();
		
		driver.close();
	}
}
