package nbarunner;


import org.testng.annotations.BeforeTest;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;
import nbabase.BaseClass;

@CucumberOptions(features = "src/main/java/nbafeatures/Warriors.feature", 
glue = "nbasteps", 
monochrome = true, 
dryRun = false, 
snippets = SnippetType.CAMELCASE, 
tags = "@Functional")
public class RunnerWarriors extends BaseClass {
	
	@BeforeTest
	public void setUp()
	{
		// Assign values for variables declared in base class for each test details
		testName = "VerifyWarriors";
		testDescription = "Test Case for Warriors Functionality";
		author = "Sangeetha";
		category="Smoke";
	}

	/*
	 * @DataProvider(parallel = true) public Object[][] scenarios() { return
	 * super.scenarios(); }
	 */
}
