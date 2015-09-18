package tests;

import com.beust.testng.annotations.*;

/**
 * Example that shows the workflow of a test class:
 * beforeTestClass, beforeTestMethod, afterTestMethod, afterTestClass
 * @author FilippoDiotalevi
 */
public class Workflow
{

	@Configuration(beforeTestMethod = true, groups = {"tests.workflow"})
	public void beforeMethod()
	{
		System.out.println("I'm a BTM");
	}

	@Configuration(afterTestMethod = true, groups = {"tests.workflow"})
	public void afterMethod()
	{
		System.out.println("I'm a ATM");
	}

	@Configuration(afterTestMethod = true, beforeTestMethod = true, groups = {"tests.workflow"})
	public void aroundMethod()
	{
			System.out.println("I'm both before and after a test method");
	}

	@Configuration(beforeTestClass = true, groups = {"tests.workflow"})
	public void beforeClass()
	{
		System.out.println("I'm starting to test object instance "+this);
		System.out.println("I'm a BTC");
	}

	@Configuration(afterTestClass = true, groups = {"tests.workflow"})
	public void afterClass()
	{
		System.out.println("I'm a ATC");
	}

	@Test(groups = {"tests.workflow"})
	public void firstTestMethod()
	{
		System.out.println("Executing firstTestMethod on instance "+this);
	}

	@Test(groups = {"tests.workflow"})
	public void secondTestMethod()
	{
		System.out.println("Executing secondTestMethod on instance "+this);
	}


}