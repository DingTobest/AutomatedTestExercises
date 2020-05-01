package testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
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
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.AfterGroups;;

public class AnnotationsTest1 {

	@BeforeSuite(alwaysRun=true)
	public void beforeSuite() {
		System.out.println(AnnotationsTest1.class + "==> " + "beforeSuite");
	}

	@AfterSuite(alwaysRun=true)
	public void afterSuite() {
		System.out.println(AnnotationsTest1.class + "==> " + "afterSuite");
	}

	@BeforeTest(alwaysRun=true)
	public void beforeTest() {
		System.out.println(AnnotationsTest1.class + "==> " + "beforeTest");
	}

	@AfterTest(alwaysRun=true)
	public void afterTest() {
		System.out.println(AnnotationsTest1.class + "==> " + "afterTest");
	}
	
	@BeforeGroups(groups="smoketest")
	public void testBeforeGroups() {
		System.out.println(AnnotationsTest1.class + "==> " + "@BeforeGroups");
	}
	
	@AfterGroups(groups="smoketest")
	public void testAfterGroups() {
		System.out.println(AnnotationsTest1.class + "==> " + "@AfterGroups");
	}

	@BeforeClass(alwaysRun=true)
	public void beforeClass() {
		System.out.println(AnnotationsTest1.class + "==> " + "beforeClass");
	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		System.out.println(AnnotationsTest1.class + "==> " + "afterClass");
	}

	@BeforeMethod(alwaysRun=true)
	public void beforeMethod() {
		System.out.println(AnnotationsTest1.class + "==> " + "beforeMethod");
	}

	@AfterMethod(alwaysRun=true)
	public void afterMethod() {
		System.out.println(AnnotationsTest1.class + "==> " + "afterMethod");
	}

	@Test(groups="smoketest")
	public void testcase1() {
		System.out.println(AnnotationsTest1.class + "==> " + "testcase1");
	}

	@Test
	public void testcase2() {
		System.out.println(AnnotationsTest1.class + "==> " + "testcase2");
	}
	
	@DataProvider
	public Object[][] dp() {
		System.out.println(AnnotationsTest1.class + "==> " + "@DataProvider");
		return new Object[][] { new Object[] { "a", "b" }, new Object[] { "c", "d" }, };
	}
	
	@Test(dataProvider = "dp", groups="smoketest")
	public void testcase3(String str1, String str2) {
		System.out.println(AnnotationsTest1.class + "==> " + "testcase3 : " + str1 + " : " + str2);
	}
}
