package tests;

import com.beust.testng.annotations.*;
import org.apache.commons.lang.*;

/**
 * Tests StringUtils
 * @author FilippoDiotalevi
 */
public class StringUtilsTest
{
	@Test(groups = { "tests.string" })
	public void isEmpty()
	{
		assert StringUtils.isBlank(null);
		assert StringUtils.isBlank("");
	}

	@Test(groups = { "tests.string" })
	public void trim()
	{
		assert "foo".equals(StringUtils.trim("  foo   "));
	}

}