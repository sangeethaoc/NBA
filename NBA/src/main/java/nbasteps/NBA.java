package nbasteps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class NBA {

	public static void main(String[] args) throws IOException, InterruptedException {

		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.nba.com/warriors");
		driver.findElement(By.xpath("//div[@class='p-2 absolute right-3 hover:cursor-pointer']")).click();
		driver.findElement(By.xpath("//button[text()='I Accept']")).click();
		Thread.sleep(2000);
		WebElement shop = driver.findElement(By.xpath("(//span[text()='Shop'])[1]"));
		Actions builder =new Actions(driver);
		builder.moveToElement(shop).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[contains(text(),'Men')])[1]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstWindow =new ArrayList<String>(windowHandles);
		driver.switchTo().window(lstWindow.get(1));
		 List<WebElement> jackets = driver.findElements(By.xpath("//div[@class='jacket']"));

	        BufferedWriter writer = new BufferedWriter(new FileWriter("jackets_details.txt"));

	        for (WebElement jacket : jackets) {
	            String title = jacket.findElement(By.xpath("//h2")).getText();
	            String price = jacket.findElement(By.xpath("//span[@class='sr-only']")).getText();
	            String topSellerMessage = jacket.findElement(By.xpath("//span[@class='top-seller-vibrancy-message']")).getText();

	            // Write details to the text file
	            writer.write("Title: " + title + ", Price: " + price + ", Top Seller: " + topSellerMessage);
	            writer.newLine();
	        
	        WebElement nextPageButton = driver.findElement(By.xpath("//a[@class='next-page']"));
           
	        if (nextPageButton.isEnabled()) {
                nextPageButton.click();
            } 
            else {
                // Exit the loop if there is no next page
                break;
            }
	        }
            writer.close();
	}

    
	    

	}
