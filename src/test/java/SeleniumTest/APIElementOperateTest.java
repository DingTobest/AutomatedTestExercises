package SeleniumTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APIElementOperateTest extends BaseTest {
	public String rootPath = "D:\\workspace_java\\AutomatedTestExercises\\src\\test\\resources\\SeleniumAPITest\\";

	@Test
	public void test_clearAPI() {
		String testPath = rootPath + "ClearTest.html";
		driver.get(testPath);

		WebElement textBox = driver.findElement(By.id("testText"));
		textBox.clear();
	}

	@Test
	public void test_sendKeysAPI() {
		String testPath = rootPath + "ClearTest.html";
		driver.get(testPath);

		WebElement textBox = driver.findElement(By.id("testText"));
		textBox.clear();
		textBox.sendKeys("dingzhitest");
	}

	@Test
	public void test_clickAPI() {
		String testPath = rootPath + "ClickTest.html";
		driver.get(testPath);

		WebElement button = driver.findElement(By.id("button"));
		button.click();
	}

	@Test
	public void test_doubleClickAPI() {
		String testPath = rootPath + "ClickTest.html";
		driver.get(testPath);

		WebElement dbInput = driver.findElement(By.id("dbText"));
		Actions builder = new Actions(driver);
		builder.doubleClick(dbInput).build().perform();
	}

	@Test
	public void test_dropboxAPI() {
		String testPath = rootPath + "SelectTest.html";
		driver.get(testPath);

		Select dropList = new Select(driver.findElement(By.name("fruit")));
		Assert.assertFalse(dropList.isMultiple());
		Assert.assertEquals("桃子", dropList.getFirstSelectedOption().getText());

		dropList.selectByIndex(3);
		Assert.assertEquals("猕猴桃", dropList.getFirstSelectedOption().getText());

		dropList.selectByValue("shanzha");
		Assert.assertEquals("山楂", dropList.getFirstSelectedOption().getText());

		dropList.selectByVisibleText("荔枝");
		Assert.assertEquals("荔枝", dropList.getFirstSelectedOption().getText());
	}

	@Test
	public void test_multpleDropboxAPI() {
		String testPath = rootPath + "MultipleSelectTest.html";
		driver.get(testPath);

		Select dropList = new Select(driver.findElement(By.name("fruit")));
		List<String> expect_options = Arrays.asList((new String[] { "桃子", "西瓜", "橘子", "猕猴桃", "山楂", "荔枝" }));

		List<String> actual_option = new ArrayList<String>();

		for (WebElement option : dropList.getOptions()) {
			actual_option.add(option.getText());

			dropList.selectByVisibleText(option.getText());

			try {
				Thread.sleep(1000 * 1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dropList.deselectByVisibleText(option.getText());
		}

		Assert.assertEquals(expect_options.toArray(), actual_option.toArray());
	
		dropList.selectByIndex(3);
		dropList.selectByValue("shanzha");
		dropList.selectByVisibleText("荔枝");
		
		dropList.deselectByIndex(3);
		
		dropList.deselectAll();
		
		
	}

	@Test
	public void test_radioAPI() {
		String testPath = rootPath + "RadioTest.html";
		driver.get(testPath);

		WebElement radioOption = driver.findElement(By.xpath("//input[@value='berry']"));
		
		if (!radioOption.isSelected()) {
			radioOption.click();
		}
		
		Assert.assertTrue(radioOption.isSelected());
		
		List<WebElement> fruits = driver.findElements(By.xpath("//form/child::input[@type='radio']"));
		
		System.out.println("-----test_radioAPI-----");
		for (WebElement fruit: fruits) {
			System.out.println(fruit.getAttribute("value"));
			if (fruit.getAttribute("value").equals("watermelon")) {
				if (!fruit.isSelected()) {
					fruit.click();
					
					Assert.assertTrue(fruit.isSelected());
				}
			}
		}
	}

	@Test
	public void test_checkboxAPI() {
		String testPath = rootPath + "CheckBoxTest.html";
		driver.get(testPath);

		WebElement orangeCheckBox = driver.findElement(By.xpath("//input[@value='orange']"));
		
		if (!orangeCheckBox.isSelected()) {
			orangeCheckBox.click();
		}
		
		Assert.assertTrue(orangeCheckBox.isSelected());
		
		try {
			Thread.sleep(1000 * 1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (orangeCheckBox.isSelected()) {
			orangeCheckBox.click();
		}
		
		Assert.assertFalse(orangeCheckBox.isSelected());
		
		List<WebElement> checkboxs = driver.findElements(By.name("fruit"));
		for (WebElement checkbox:checkboxs) {
			checkbox.click();
		}
	}
}
