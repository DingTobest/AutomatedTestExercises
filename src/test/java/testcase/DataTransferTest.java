package testcase;

import org.testng.annotations.Test;

import util.DataTransferPool;


public class DataTransferTest {
  @Test
  public void testcase1() {
	  System.out.println("存储数据到传递数据池中");
	  DataTransferPool.putKeyValue("testData", "GetDataTransferPoolData");
  }
  
  @Test
  public void testcase2() {
	  try {
		String poolData = DataTransferPool.getParamMapValueByKey("testData").toString();
		System.out.println("获取数据池数据: " + poolData);
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
}
