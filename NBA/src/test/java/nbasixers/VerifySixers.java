package nbasixers;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import nbabase.BaseClass;

public class VerifySixers extends BaseClass
{
	        @BeforeTest
			public void setUp()
			{
				// Assign values for variables declared in base class for each test details
				testName = "VerifySixers";
				testDescription = "Test Case for Sixers Functionality";
				author = "Sangeetha";
				category="Smoke";
			}
			
			@Test
			public void runVerifySixers()
			{
				
				//TBD
						
			}
		
}
