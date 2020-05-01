package testcase;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
	@Test(dataProvider = "testData")
	public void testDataProvider(String indexCode) {
		System.out.println("testDataProvider");
		System.out.println(indexCode);
	}

	@DataProvider
	public Object[][] testData() {
		System.out.println("@DataProvider");
		return new Object[][] { new Object[] { "qzzs.sc" }, new Object[] { "000905.XSHG" }, };
	}
}
