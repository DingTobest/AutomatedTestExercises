package testcase;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class AnnotationsTest2 {
	@Test(dataProvider = "dp", groups="smoketest")
	public void testcase1(String str1, String str2) {
		System.out.println(AnnotationsTest2.class + "==> " + "testcase1 : " + str1 + " : " + str2);
	}

	@BeforeMethod(alwaysRun=true)
	public void beforeMethod() {
		System.out.println(AnnotationsTest2.class + "==> " + "beforeMethod");
	}

	@AfterMethod(alwaysRun=true)
	public void afterMethod() {
		System.out.println(AnnotationsTest2.class + "==> " + "afterMethod");
	}

	@DataProvider
	public Object[][] dp() {
		System.out.println(AnnotationsTest2.class + "==> " + "@DataProvider");
		return new Object[][] { new Object[] { "a", "b" }, new Object[] { "c", "d" }, };
	}

	@BeforeClass(alwaysRun=true)
	public void beforeClass() {
		System.out.println(AnnotationsTest2.class + "==> " + "beforeClass");
	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		System.out.println(AnnotationsTest2.class + "==> " + "afterClass");
	}

}
