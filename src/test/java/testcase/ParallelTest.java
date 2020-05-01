package testcase;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParallelTest {
	@Test
	public void testcase1() {
		System.out.println("当前运行的线程编号: " + Thread.currentThread().getId() + ",当前执行的案例：[testcase1]");
	}

	@Test
	public void testcase2() {
		System.out.println("当前运行的线程编号: " + Thread.currentThread().getId() + ",当前执行的案例：[testcase2]");
	}

	@Test
	public void testcase3() {
		System.out.println("当前运行的线程编号: " + Thread.currentThread().getId() + ",当前执行的案例：[testcase3]");
	}

	@Test
	public void testcase4() {
		System.out.println("当前运行的线程编号: " + Thread.currentThread().getId() + ",当前执行的案例：[testcase4]");
	}

	@Test
	public void testcase5() {
		System.out.println("当前运行的线程编号: " + Thread.currentThread().getId() + ",当前执行的案例：[testcase5]");
	}

	@Test
	public void testcase6() {
		System.out.println("当前运行的线程编号: " + Thread.currentThread().getId() + ",当前执行的案例：[testcase6]");
	}

	@Test
	public void testcase7() {
		System.out.println("当前运行的线程编号: " + Thread.currentThread().getId() + ",当前执行的案例：[testcase7]");
	}

	@Test
	public void testcase8() {
		System.out.println("当前运行的线程编号: " + Thread.currentThread().getId() + ",当前执行的案例：[testcase8]");
	}

	@Test
	public void testcase9() {
		System.out.println("当前运行的线程编号: " + Thread.currentThread().getId() + ",当前执行的案例：[testcase9]");
	}

	@DataProvider(parallel = true)
	public Object[][] testData() {
		return new Object[][] { { "1" }, { "2" }, { "3" },{ "4" },{ "5" },{ "6" },{ "7" },{ "8" },{ "9" },};
	}

	@Test(dataProvider = "testData")
	public void testcaseDataProvider(String index) {
		System.out.println("当前运行的线程编号: " + Thread.currentThread().getId() + ",当前执行的数据内容：[" + index + "]");
	}
}
