package nbasteps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nbabase.BaseClass;
import nbautils.FileWriterUtil;
import nbautils.CommonUtils;

public class BullsPage extends BaseClass{
	
	List<WebElement> hyperLinksList = new ArrayList<WebElement>();
	
	@Given("Launch Bulls home page")
	public BullsPage launchBullsHomePage()
	{
		// Load the url
		getDriver().get(properties.getProperty("bullsurl"));
		System.out.println("launchBullsHomePage - Completed");
		return this;
	}
	
	@When("Scroll down to the footer")
	public BullsPage scrollDownToTheFooter()
	{
		System.out.println("scrollDownToTheFooter");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", getDriver().findElement(By.xpath("//a[text()='Team Roster']")));
		System.out.println("scrollDownToTheFooter - Completed");
		return this;
	}
	
	@Then("Get all the footer links")
	public BullsPage getAllFooterLinks() 
	{
		hyperLinksList = getDriver().findElements(By.xpath("//ul[@data-testid=\"footer-list\"]/li/a"));
		System.out.println("getAllFooterLinks - Completed");
		return this;
	}
	
	@And("Write the links into CSV file")
	public BullsPage writeLinksInCSVFile()
	{
		String[] hyperLinksNameList = new String[hyperLinksList.size()];
		for(int i=0; i<hyperLinksList.size();i++)
		{
			hyperLinksNameList[i] = hyperLinksList.get(i).getText();
		}
		try {
			FileWriterUtil.writeStringArrayIntoCSVFile(hyperLinksNameList,"ListOfFooterLinks");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("writeLinksInCSVFile - Completed");
		return this;
	}
	
	@And("Send the duplicate links in the report")
	public BullsPage sendDuplicateLinksReport()
	{
		List<String> duplicateStringsFromList = CommonUtils.getDuplicateStringsFromList(hyperLinksList);
		String duplicateString[] = CommonUtils.convertListToStringArray(duplicateStringsFromList);
		try {
			FileWriterUtil.writeStringArrayIntoCSVFile(duplicateString, "DuplicateFooterLinks");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("sendDuplicateLinksReport - Completed");
		return this;
	}
}
