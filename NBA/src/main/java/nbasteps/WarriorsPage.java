package nbasteps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nbabase.BaseClass;
import nbapageobjects.ProductDetails;
import nbautils.FileWriterUtil;

public class WarriorsPage extends BaseClass{
	
	BufferedWriter writer;
	
	@Given("Launch warriors home page")
	public WarriorsPage launchWarriorsHomePage()
	{
		// Load the url
		getDriver().get(properties.getProperty("warriorsurl"));
		System.out.println("launchWarriorsHomePage - Completed");
		return this;
	}
	
	@When("Go to Shop Menu and click on Men's item")
	public WarriorsPage clickMensItem() throws InterruptedException
	{
		//Apply webdriver wait of 10 secs for the web element to be visible
		webDriverWait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[@class='w-full flex flex-end']/div")))).click();

		webDriverWait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//li[@class='menu-item']//span[text()='Shop']")))).click();

		Set<String> windowHandles = getDriver().getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		getDriver().switchTo().window(windowHandlesList.get(1));
		
		WebElement men = webDriverWait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//a[text()='men']"))));
		
		driver.executeScript("arguments[0].click();", men);
		
		System.out.println("clickMensItem - Completed");
		return this;
	}
	@And("Find all jacket details")
	public WarriorsPage writeProductDetailsInFile() throws IOException 
	{
		WebElement totalProductsList = getDriver().findElement(By.xpath("//div[@data-talos='itemCount']"));
		int totalProductsCount = getTotalProductsListed(totalProductsList.getText());
		System.out.println("totalProductsCount : "+totalProductsCount);
		
		System.out.println("*******111111111111******");
		WebElement nextPageButton = getDriver().findElement(By.xpath("(//a[@data-trk-id='next-page'])[2]"));
		
		try {
			writer = new BufferedWriter(new FileWriter("./reports/ProductDetails.txt"));
			System.out.println("*******2222222222222******");
			 while(nextPageButton.isEnabled())
				{
					List<WebElement> products = driver.findElements(By.xpath("//div[@class='product-card-title']"));
					
					System.out.println("******333333333333*******" + products.size());

					try {
						writer = FileWriterUtil.writeStringIntoTextFile(products, writer);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						nextPageButton.click();
						nextPageButton = getDriver().findElement(By.xpath("(//a[@data-trk-id='next-page'])[2]"));
					}

				} 
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       finally
       {
    	   if(writer != null)
    		   writer.close();
       }
        
		System.out.println("writeProductDetailsInFile - Completed");
		return this;
	}
			
	
	private int getTotalProductsListed(String text) {

		// Convert string to int
		int totalCountOfProducts = Integer.parseInt(text.split(" ")[4]);
		System.out.println("totalCountOfProducts" + totalCountOfProducts);

		return totalCountOfProducts;
	}

	@And("Send the file in the report")
	public WarriorsPage sendFileinReports()
	{
		System.out.println("sendFileinReports");
		return this;
	}
	

	
	
	@When("Go to Menu and click on News & Features")
	public WarriorsPage clickNewsAndFeatuesFromMenu() throws InterruptedException
	{
		
		//Apply webdriver wait of 10 secs for the web element to be visible
		webDriverWait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[@class='w-full flex flex-end']/div")))).click();
		
		Actions builder = new Actions(getDriver());
		
		builder.moveToElement(webDriverWait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("(//span[text()='...']/parent::a)[1]"))))).build().perform();
		
		builder.moveToElement(webDriverWait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("(//a[text()='News & Features'])[1]"))))).click().build().perform();
		
		builder.build().perform();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", getDriver().findElement(By.xpath("//h3[text()='VIDEOS']")));
		
		System.out.println("clickNewsAndFeatuesFromMenu - Completed");		
		return this;

	}
	@And("Count total number of videos feeds")
	public WarriorsPage findCountOfTotalVideos() 
	{
		List<WebElement> totalVideosList = getDriver().findElements(By.xpath("(//div[contains(@class,'ContentContainer_contentContainer___olka')])[2]/ul/li"));
		
		System.out.println("FindTotalCountOfVideos : "+totalVideosList.size());
		System.out.println("findCountOfTotalVideos - Completed");
		return this;
	}
			
	
	@Then("Find total number of video feeds greater than three days")
	public WarriorsPage findCountOfVideosGreaterThanThreeDays()
	{
		List<WebElement> totalVideosList = getDriver().findElements(By.xpath("(//div[contains(@class,'Container_contentContainer___olka ContentContainer')])[2]//time[@aria-label='3 days ago']"));
		System.out.println("CountOfVideosGreaterThanThreeDays : "+totalVideosList.size());
		System.out.println("findCountOfVideosGreaterThanThreeDays - Completed");
		return this;
	}


}
