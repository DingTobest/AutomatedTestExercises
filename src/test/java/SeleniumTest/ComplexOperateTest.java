package SeleniumTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
	
	@Test
	public void test_richEditorTest() {
		String testURL = rootPath + "RichEditorTest.html";
		driver.get(testURL);
		
		WebElement editor = driver.findElement(By.xpath("//div[@class='w-e-text-container']/div[@class='w-e-text']"));
		
		// 直接赋值，成功
		editor.sendKeys("123456789");
		
		// js操作，成功
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].innerHTML += '<p>智云未来</p>'", editor);
		
		// 另外可以通过找到紧邻控件，通过tab导航，剪切板输入的形式进行输入
	}
	
	@Test
	public void test_comparePic() {
		driver.get(sogouURL);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		File copyFile = new File("D:\\workspace_java\\AutomatedTestExercises\\pic\\comparetest1.png");
		if (copyFile.exists()) {
			copyFile.delete();
		}
		
		File compareFile = new File("D:\\workspace_java\\AutomatedTestExercises\\pic\\comparetest2.png");
		if (compareFile.exists()) {
			compareFile.delete();
		}
		
		try{
			Files.copy(scrFile.toPath(), copyFile.toPath());
			Files.copy(scrFile.toPath(), compareFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedImage bufferCopy = ImageIO.read(copyFile);
			DataBuffer dataCopyFile = bufferCopy.getData().getDataBuffer();
			int sizeCopyFile = dataCopyFile.getSize();
			
			BufferedImage bufferCoompare = ImageIO.read(compareFile);
			DataBuffer dataCoompareFile = bufferCoompare.getData().getDataBuffer();
			int sizeCoompareFile = dataCoompareFile.getSize();
			
			Boolean matchFlag = true;
			if (sizeCopyFile == sizeCoompareFile) {
				for (int i = 0; i < sizeCopyFile; i++) {
					if (dataCopyFile.getElem(i) != dataCoompareFile.getElem(i)) {
						matchFlag = false;
						break;
					}
				}
			} else {
				matchFlag = false;
			}
			
			Assert.assertTrue(matchFlag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 当前执行的页面元素高亮显示
	// 保存上个高亮的页面元素，当高亮执行下个元素时，移除上个元素的高亮状态。
	WebElement last_element = null;
	public void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		if (last_element != null) {
			js.executeScript("arguments[0].removeAttribute('style');", last_element);
		}

		// 使用Javascript语句将传入参数的页面元素对象的背景颜色和边框颜色分别设定黄色和红色
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				"background: yellow; border: 2px solid red;");
		last_element = element;
	}
	
	@Test
	public void test_highlightElement() {
		driver.get(sogouURL);
		WebElement searchInputBox = driver.findElement(By.id("query"));
		WebElement submitButton = driver.findElement(By.id("stb"));
		
		highlightElement(searchInputBox);
		searchInputBox.sendKeys("智云未来");
		
		try {
			Thread.sleep(1000 * 3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		highlightElement(submitButton);
		try {
			Thread.sleep(1000 * 3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		submitButton.click();
	}
	
	@Test
	public void test_html5VideoTest() {
		String testURL = rootPath + "Html5VideoTest.html";
		driver.get(testURL);

		WebElement videoPlayer = driver.findElement(By.tagName("video"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String videoSrc = (String) js.executeScript("return arguments[0].currentSrc;", videoPlayer);

		System.out.println(videoSrc);

		Double videoDuration = (Double) js.executeScript("return arguments[0].duration;", videoPlayer);

		System.out.println(videoDuration.intValue());

		try {
			Thread.sleep(1000 * 3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		js.executeScript("return arguments[0].play();", videoPlayer);

		try {
			Thread.sleep(1000 * 3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		js.executeScript("return arguments[0].pause();", videoPlayer);

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		System.out.println(scrFile.toPath());

		File copyFile = new File("D:\\workspace_java\\AutomatedTestExercises\\pic\\vidotest.png");
		if (copyFile.exists()) {
			copyFile.delete();
		}

		try {
			Files.copy(scrFile.toPath(), copyFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_canvas() {
		String testURL = rootPath + "HtmlCanvasTest.html";
		driver.get(testURL);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String script = "var canvas=document.getElementById('myCanvas');" + 
				"var ctx=canvas.getContext('2d');" + 
				"ctx.fillStyle='#FF0000';" + 
				"ctx.fillRect(0,0,80,100);";
		js.executeScript(script);
		
		try {
			Thread.sleep(1000 * 3);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		System.out.println(scrFile.toPath());

		File copyFile = new File("D:\\workspace_java\\AutomatedTestExercises\\pic\\canvastest.png");
		if (copyFile.exists()) {
			copyFile.delete();
		}

		try {
			Files.copy(scrFile.toPath(), copyFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_localStorage() {
		String testURL = rootPath + "Html5WebstorageLocal.html";
		driver.get(testURL);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String lastname = (String)js.executeScript("return localStorage.lastname;");
		
		Assert.assertEquals("Gate", lastname);
		
		js.executeScript("localStorage.clear();");
	}
	
	@Test
	public void test_sessionStorage() {
		String testURL = rootPath + "Html5WebstorageSession.html";
		driver.get(testURL);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String lastname = (String)js.executeScript("return sessionStorage.lastname;");
		
		Assert.assertEquals("Gate", lastname);
		
		js.executeScript("sessionStorage.clear();");
	}
}
