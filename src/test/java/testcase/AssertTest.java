package testcase;

import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AssertTest {
	@Test
	public void testAssertEquals() {
		System.out.println("Assert.assertEquals");
		Assert.assertEquals(12, 12);
		Assert.assertEquals("ABc", "ABC", "AssertTest测试结果不相等");
		// 由于Assert执行时，如果判断错误，则终止执行，案例，后续的内容无法运行
		System.out.println("此条语句无法执行");
	}
	
	@Test
	public void testAssertEqualsNoOrder() {
		System.out.println("Assert.assertEqualsNoOrder");
		String[] st1 = {"case1", "case2", "case3"};
        String[] st2 = {"case3", "case2", "case1"};
        // 虽然两个字符串顺序不同，但通过assertEqualsNoOrder进行判断可以成功
        Assert.assertEqualsNoOrder(st1, st2, "两个字符串数组不相同");
	}

	@Test
	public void testAssertTrue() {
		System.out.println("Assert.assertTrue");
		String str1 = "abc";
		String str2 = "abc";
		Assert.assertTrue(str1 == str2, "两个字符串不相等");
	}

	@Test
	public void testAssertFalse() {
		System.out.println("Assert.assertFalse");
		String str1 = "123";
		String str2 = "abc";
		Assert.assertFalse(str1 == str2, "两个字符串相等");
	}

	@Test
	public void testAssertSame() {
		System.out.println("Assert.assertSame");
		String[] str1 = {"case1", "case2", "case3"};
        String[] str2 = str1;
        // str1与str2是相同地址，判断相同，结果正确
        Assert.assertSame(str1, str2, "结果不同");
	}

	@Test
	public void testAssertNotSame() {
		System.out.println("Assert.assertNotSame");
		String[] str1 = {"case1", "case2", "case3"};
        String[] str2 = {"case1", "case2", "case3"};
        // str1与str2内容相同，地址不同，判断为不同，结果正确
        Assert.assertNotSame(str1, str2, "结果相同");
	}

	@Test
	public void testAssertNull() {
		System.out.println("Assert.assertNull");
		String[] str1 = null;
		Assert.assertNull(str1, "内容不为空");
	}

	@Test
	public void testAssertNotNull() {
		System.out.println("Assert.assertNotNull");
		String[] str1 = {"case1", "case2", "case3"};
		Assert.assertNotNull(str1, "内容为空");
	}

	@Test
	public void testSoftAssert() {
		System.out.println("softAssert.assertEquals");
		System.out.println("Test start");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("case1", "case2","第一处判断");
        System.out.println("Test complete");
        softAssert.assertEquals("case3", "case4","第二处判断");
        softAssert.assertAll();
	}
}
