package SeleniumTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.os.WindowsUtils;
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
	}
}
