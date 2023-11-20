package nbautils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.opencsv.CSVWriter;

public class FileWriterUtil {
	
	public static CSVWriter csvWriter;
	
	public static void writeStringArrayIntoCSVFile(String[] hyperLinksNameList, String fileName) throws IOException
	{
		try {
			csvWriter = new CSVWriter(new FileWriter("./reports/"+fileName+".csv"));
			
			csvWriter.writeNext(hyperLinksNameList,true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			csvWriter.flush();
		}
	}
	public static BufferedWriter writeStringIntoTextFile(List<WebElement> productsList, BufferedWriter writer) throws IOException {
		
		try {
				System.out.println("*******4444444444444444******");
		        for(int i=0; i<productsList.size();i++)
		        {
		        	WebElement products = productsList.get(i);
		            String title = products.findElement(By.xpath("(//div[@class='product-card-title']/a)["+(i+1)+"]")).getText();
		            System.out.println("Title : "+title);
		            String price = products.findElement(By.xpath("((//span[@class='price'])//span[@class='sr-only'])["+(i+1)+"]")).getText();
		            if(price == "" || price == null)
		            	price = products.findElement(By.xpath("((//span[@class='price primary'])//span[@class='sr-only'])["+(i+1)+"]")).getText();
		            
		            String topSellerMessage = products.findElement(By.xpath("(//span[@class='top-seller-vibrancy-message'])["+(i+1)+"]")).getText();
		            
		            System.out.println("*******555555555555555******");
		            System.out.println("Printing the product details");
		            // Write details to the text file
		            writer.write("Title: " + title + ",  Price: " + price + ",  Top Seller: " + topSellerMessage);
		            writer.newLine();
		            writer.newLine();
		        }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer;
	}
}
