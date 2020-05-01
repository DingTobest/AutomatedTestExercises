package testcase;

import org.testng.annotations.Test;

public class TestGroupTest {
  @Test(groups = { "smoketest", "fulltest" })
  public void testcase1() {
	  System.out.println("testcase1");
  }
  
  @Test(groups = { "smoketest", "fulltest" })
  public void testcase2() {
	  System.out.println("testcase2");
  }
  
  @Test(groups = { "fulltest" })
  public void testcase3() {
	  System.out.println("testcase3");
  }
  
  @Test(groups = { "fulltest" })
  public void testcase4() {
	  System.out.println("testcase4");
  }
  
  @Test(groups = { "fulltest" })
  public void testcase5() {
	  System.out.println("testcase5");
  }
  
  @Test(groups = { "fulltest" })
  public void testcase6() {
	  System.out.println("testcase6");
  }
}
