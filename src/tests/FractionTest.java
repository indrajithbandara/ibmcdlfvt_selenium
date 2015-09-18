package tests;

import com.beust.testng.annotations.*;
import org.apache.commons.lang.math.*;

/**
 * Tests fractions
 * @author FilippoDiotalevi
 */
public class FractionTest
{
	Fraction fraction;

	@Configuration(beforeTestMethod = true, groups = {"tests.math"})
	public void init()
	{
		fraction = Fraction.ZERO;
	}

	@Test(groups = {"tests.math"})
	public void testSum()
	{
		assert fraction.add(Fraction.ONE_HALF).equals(Fraction.ONE_HALF);
	}

	@Test(groups = {"tests.math"})
	public void testSubtract()
	{
		assert fraction.subtract(Fraction.ONE_HALF).doubleValue() == -0.5;
	}


}