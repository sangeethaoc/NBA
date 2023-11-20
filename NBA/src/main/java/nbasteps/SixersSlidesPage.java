package nbasteps;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nbabase.BaseClass;

public class SixersSlidesPage extends BaseClass{
	
	@Given("Launch Sixers home page")
	public SixersSlidesPage launchSixersHomePage()
	{
		// Load the url
		getDriver().get(properties.getProperty("sixersurl"));
		System.out.println("LaunchSixersHomePage");
		return this;
	}
	
	//TBD
	@When("Find total number of slides displayed")
	public SixersSlidesPage findTotalNumberOfSlidesDisplayed()
	{
		System.out.println("findTotalNumberOfSlidesDisplayed");
		return this;
	}
	
	//TBD
	@Then("Validate the title of each slide")
	public SixersSlidesPage validateSlideTitle() 
	{
		System.out.println("validateSlideTitle");
		return this;
	}
			
	//TBD
	@And("Validate the duration of each slide")
	public SixersSlidesPage validateSlideDuration()
	{
		System.out.println("validateSlideDuration");
		return this;
	}

}
