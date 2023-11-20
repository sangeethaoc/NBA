package nbawarriors;


import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import nbabase.BaseClass;
import nbasteps.WarriorsPage;

public class VerifyWarriors extends BaseClass
{
	@BeforeTest
	public void setUp()
	{
		// Assign values for variables declared in base class for each test details
		testName = "VerifyWarriors";
		testDescription = "Test Case for Warriors Functionality";
		author = "Sangeetha";
		category="Smoke";
	}
	//TC001
	@Test
	public void runVerifyWarriorsProductDetails() throws InterruptedException, IOException
	{
		
		new WarriorsPage().
		launchWarriorsHomePage().
		clickMensItem().
		writeProductDetailsInFile();
				
	}
	//TC002
	@Test
	public void runVerifyWarriorsVideoFeeds() throws InterruptedException
	{
		
		new WarriorsPage().
		launchWarriorsHomePage().
		clickNewsAndFeatuesFromMenu().
		findCountOfTotalVideos().
		findCountOfVideosGreaterThanThreeDays();
				
	}
}
