package testcase;

import org.testng.annotations.Test;

public class FlowCaseTest {
  @Test(priority = 4)
  public void testcase1_4() {
	  System.out.println("执行顺序4");
  }
  
  @Test(priority = 3)
  public void testcase2_3() {
	  System.out.println("执行顺序3");
  }
  
  @Test(priority = 2)
  public void testcase3_2() {
	  System.out.println("执行顺序2");
  }
  
  @Test(priority = 1)
  public void testcase4_1() {
	  System.out.println("执行顺序1");
  }
  
//  @Test
//  public void testcase1_4() {
//	  System.out.println("执行顺序4");
//  }
//  
//  @Test
//  public void testcase2_3() {
//	  System.out.println("执行顺序3");
//  }
//  
//  @Test
//  public void testcase3_2() {
//	  System.out.println("执行顺序2");
//  }
//  
//  @Test
//  public void testcase4_1() {
//	  System.out.println("执行顺序1");
//  }
}
