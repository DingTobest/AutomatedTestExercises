package SeleniumTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindElementTest {
	public WebDriver driver;
	public String htmlFilePath = "D:\\workspace_java\\AutomatedTestExercises\\src\\test\\resources\\SeleniumFindElementTest\\";

	@Test
	public void test_findElementByID() {
		System.out.println("test_findElementByID");
		String url = htmlFilePath + "FindElementByIDTest.html";
		driver.get(url);
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement submit = driver.findElement(By.id("submit"));
	}

	@Test
	public void test_findElementByName() {
		System.out.println("test_findElementByName");
		String url = htmlFilePath + "FindElementByNameTest.html";
		driver.get(url);
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement submit = driver.findElement(By.name("submit"));
	}

	@Test
	public void test_findElementByClass() {
		System.out.println("test_findElementByClass");
		String url = htmlFilePath + "FindElementByClassTest.html";
		driver.get(url);
		WebElement input = driver.findElement(By.className("spread"));
	}

	@Test
	public void test_findElementByTag() {
		System.out.println("test_findElementByTag");
		String url = htmlFilePath + "FindElementByLinkTextTest.html";
		driver.get(url);
		WebElement link = driver.findElement(By.tagName("a"));
		System.out.println(link.getText());
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int length = links.size();
		for (int i = 0; i < length; i++) {
			WebElement clink = links.get(i);
			System.out.println(clink.getText());
		}
	}

	@Test
	public void test_findElementByLinkText() {
		System.out.println("test_findElementByLinkText");
		String url = htmlFilePath + "FindElementByLinkTextTest.html";
		driver.get(url);
		WebElement search_url = driver.findElement(By.linkText("sougou搜索"));
	}

	@Test
	public void test_findElementBypartialLinkText() {
		System.out.println("test_findElementBypartialLinkText");
		String url = htmlFilePath + "FindElementByLinkTextTest.html";
		driver.get(url);
		WebElement search_url = driver.findElement(By.partialLinkText("sougou"));
	}

	@Test
	public void test_findElementByXpath() {
		System.out.println("test_findElementByXpath");
		String url = htmlFilePath + "FindElementByXPathTest.html";
		driver.get(url);
		// 1. 绝对路径匹配
		WebElement input1 = driver.findElement(By.xpath("/html/body/div/input[@value='查询']"));
		
		// 2. 相对路径匹配
		WebElement input2 = driver.findElement(By.xpath("//input[@value='查询']"));
		
		// 3. 索引号匹配
		WebElement input3 = driver.findElement(By.xpath("//input[2]"));
		
		// 4. 属性值匹配
		WebElement img1 = driver.findElement(By.xpath("//img[@alt='div1-img1']"));
		WebElement img2 = driver.findElement(By.xpath("//img[@href='http://www.sougou.com/']"));
		WebElement input4 = driver.findElement(By.xpath("//div[@id='div2']/input[@name='dev2input']"));
		WebElement a1 = driver.findElement(By.xpath("//div[@id='div1']/a[@href='http://www.sogou.com']"));
		WebElement btn1 = driver.findElement(By.xpath("//input[@type='button']"));
		
		// 5. 模糊的属性值匹配
		WebElement img3 = driver.findElement(By.xpath("//img[starts-with(@alt,'div1')]"));
		WebElement img4 = driver.findElement(By.xpath("//img[contains(@alt,'g1')]"));
		
		// 6. 基于轴定位(Axis)
		// 6.1 parent关键字，查找元素上一级元素
		WebElement parentDiv1 = driver.findElement(By.xpath("//img[@alt='div1-img1']/parent::div"));
		
		// 6.2 child关键字，查找元素下一级元素
		WebElement childImg1 = driver.findElement(By.xpath("//div[@id='div1']/child::img"));
		
		// 6.3 ancestor关键字，查找元素所有的上级（祖先）节点
		WebElement ancestorDiv1 = driver.findElement(By.xpath("//img[@alt='div1-img1']/ancestor::div"));
		
		// 6.4 descendant关键字，查找元素所有的下级（后裔）节点
		WebElement descendantImg1 = driver.findElement(By.xpath("//div[@id='div1']/descendant::img"));
		
		// 6.5 following关键字，选择在当前节点之后显示的所有节点
		WebElement followingImg1 = driver.findElement(By.xpath("//div[@id='div1']/following::img"));
		
		// 6.6 following-sibling关键字，选择当前节点后面的所有平级标签
		WebElement followingSiblingInput = driver.findElement(By.xpath("//a[@href='http://www.sogou.com']/following-sibling::input"));
		
		// 6.7 preceding,选择当前节点前面的所有节点
		WebElement precedingDiv1 = driver.findElement(By.xpath("//img[@alt='div2-img2']/preceding::div"));
		
		System.out.println("----preceding关键字组合测试--------");
		List<WebElement> precedingDivs = driver.findElements(By.xpath("//img[@alt='div2-img2']/preceding::div"));
		int precedingInputsLength = precedingDivs.size();
		for (int i = 0; i < precedingInputsLength; i++) {
			WebElement div = precedingDivs.get(i);
			System.out.println(div.getAttribute("id"));
		}
		System.out.println("----preceding关键字测试结束----");
		
		// 6.8 preceding-sibling关键字，选择当前节点后面的所有平级标签
		WebElement precedingSiblingInput = driver.findElement(By.xpath("//a[@href='http://www.baidu.com']/preceding-sibling::input"));
		
		// 7. 使用页面元素的文件来定位
		WebElement texta1 = driver.findElement(By.xpath("//a[text()='baidu搜索']"));
		WebElement texta2 = driver.findElement(By.xpath("//a[contains(text(),'baidu')]"));
		WebElement diva1 = driver.findElement(By.xpath("//a[contains(text(),'baidu')]/preceding::div"));
	}

	@Test
	public void test_findElementByCssSelector() {
		// css定位比xpath快，并且稳定
		System.out.println("test_findElementByCssSelector");
		String url = htmlFilePath + "FindElementByCssSelectorTest.html";
		driver.get(url);
		
		// 1. 绝对路径匹配
		WebElement input1 = driver.findElement(By.cssSelector("html >body >div >input[type='button']"));
		
		// 2. 相对路径
		WebElement input2 = driver.findElement(By.cssSelector("input[type='button']"));
		
		// 3. 根据class进行选择
		WebElement input3 = driver.findElement(By.cssSelector("input.spread"));
		
		// 4. 通过ID属性值进行查找
		WebElement input4 = driver.findElement(By.cssSelector("input#div1input"));
		
		// 5. 通过其他属性值进行查找
		WebElement img1 = driver.findElement(By.cssSelector("img[alt='div1-img1']"));
		WebElement img2 = driver.findElement(By.cssSelector("img[alt='div1-img1'][href='http://www.baidu.com/']"));
		
		// 6. 通过属性值进行模糊匹配
		WebElement img3 = driver.findElement(By.cssSelector("img[href^='http://www.bai']"));
		WebElement img4 = driver.findElement(By.cssSelector("img[href$='idu.com/']"));
		WebElement img5 = driver.findElement(By.cssSelector("img[href*='baidu']"));
		
		// 7. 子元素查找
		WebElement input5 = driver.findElement(By.cssSelector("div#div1 > input#div1input"));
		
		// 8. 使用伪类定位元素
		// 8.1 first-child 关键字，查找元素下面第一个子元素
		WebElement input6 = driver.findElement(By.cssSelector("div#div1 :first-child"));
		
		// 8.2 nth-child 关键字，包含参数n，查找元素下面第n个子元素
		WebElement a1 = driver.findElement(By.cssSelector("div#div1 :nth-child(2)"));
		
		// 8.3 last-child 关键字，查找元素下面最后一个子元素
		WebElement input7 = driver.findElement(By.cssSelector("div#div1 :last-child"));
		
		// 8.4 focus 关键字
		WebElement input8 = driver.findElement(By.cssSelector("input :focus"));
		System.out.println(input8.getAttribute("type"));
		
		// 8.5 enable 关键字
		WebElement input9 = driver.findElement(By.cssSelector("input :enable"));
		
		// 8.6 checked 关键字
		WebElement input10 = driver.findElement(By.cssSelector("input :checked"));
		
		// 9. 查找同级元素
		WebElement a2 = driver.findElement(By.cssSelector("div#div1 >input + a"));
		WebElement img6 = driver.findElement(By.cssSelector("div#div1 >input + a + img"));
		WebElement img7 = driver.findElement(By.cssSelector("div#div1 >input + * + img"));
	}
	
	@Test
	public void test_findElementByjQuery() {
		System.out.println("test_findElementByjQuery");
		String url = "http://www.sogou.com/";
		driver.get(url);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		Boolean isLoaded = (Boolean)js.executeScript("return " + "jQuery()!= null");
		if(!isLoaded) {
			js.executeScript("var headID = "
					+ "document.getElementsByTagName(\"head\")[0];"
					+ "var newScript = document.createElement('script');"
					+ "newScript.type = 'text/javascript';" + "newScript.src = "
					+ "'http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js';"
					+ "headID.appendChild(newScript);");
		}
			
		List<WebElement> elements = (List<WebElement>)js.executeScript("return jQuery.find('a')");
		Assert.assertEquals(83, elements.size());
		for (int i = 0; i < elements.size(); i++) {
			System.out.println(elements.get(i).getText());
		}
	}
	
	@Test
	public void test_NormalTableTest() {
		System.out.println("test_NormalTableTest");
		String url = htmlFilePath + "NormalTableTest.html";
		driver.get(url);
		WebElement table = driver.findElement(By.id("table"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		Assert.assertEquals(5, rows.size());
		for (int i = 0; i < rows.size(); i++) {
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			for (int j = 0; j < cols.size(); j++) {
				System.out.print(cols.get(j).getText() + "\t");
			}
			System.out.print("\r\n");
		}
		
		WebElement cell1 = driver.findElement(By.xpath("//table[@id='table']/tbody/tr[2]/td[2]"));
		System.out.println(cell1.getText());
		
		WebElement cell2 = driver.findElement(By.cssSelector("table#table tbody tr:nth-child(2) td:nth-child(2)"));
		System.out.println(cell2.getText());
	}
	
	@Test
	public void test_ComplexTableTest() {
		System.out.println("test_ComplexTableTest");
		String url = htmlFilePath + "ComplexTableTest.html";
		driver.get(url);
//		WebElement checkbox1 = driver.findElement(By.xpath("//input[contains(text(),'沐浴露')]"));
		WebElement checkbox1 = driver.findElement(By.xpath("//td[text()='化妆品']/input[2]"));
		checkbox1.click();
		
		List<WebElement> checkboxs = driver.findElements(By.xpath("//input"));
		for (int i = 0; i < checkboxs.size(); i++) {
			System.out.println(checkboxs.get(i).getText());
		}
//		checkbox1.click();
		
	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "D:\\TestTool\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	@BeforeMethod
	  public void beforeMethod() {
	  }

	@AfterMethod
	public void afterMethod() {
		try {
			Thread.sleep(1000 * 2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
