package tests;

import com.beust.testng.annotations.*;
import org.apache.commons.lang.math.*;

/**
 * Tests NumberUtils
 * @author FilippoDiotalevi
 */
public class  NumberUtilsTest
{
	@Test(groups = {"tests.math"})
	@ExpectedExceptions(NumberFormatException.class)
	public void test()
	{

		NumberUtils.createDouble("12.23.45");
		assert false : "exception not raised";
	}

}