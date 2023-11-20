package nbarunner;


import org.testng.annotations.DataProvider;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;
import nbabase.BaseClass;

@CucumberOptions(features = "src/main/java/nbafeatures/Bulls.feature", 
glue = "nbapages", 
monochrome = true, 
dryRun = false, 
snippets = SnippetType.CAMELCASE, 
tags = "@Functional")
public class RunnerBulls extends BaseClass {

	/*
	 * @DataProvider(parallel = true) public Object[][] scenarios() { return
	 * super.scenarios(); }
	 */
}
