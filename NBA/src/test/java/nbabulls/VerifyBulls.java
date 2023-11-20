package nbabulls;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import nbabase.BaseClass;
import nbasteps.BullsPage;

public class VerifyBulls extends BaseClass
{
	        @BeforeTest
			public void setUp()
			{
				// Assign values for variables declared in base class for each test details
				testName = "VerifyBulls";
				testDescription = "Test Case for Bulls Functionality";
				author = "Sangeetha";
				category="Smoke";
			}
			
			@Test
			public void runVerifyBulls()
			{
				new BullsPage().
				launchBullsHomePage().
				getAllFooterLinks().
				writeLinksInCSVFile().
				sendDuplicateLinksReport();
						
			}
}
