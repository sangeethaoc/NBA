package nbabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.cucumber.testng.AbstractTestNGCucumberTests;

public class BaseClass extends AbstractTestNGCucumberTests {

	// Create Thread Local variable for achieving parallel execution by interacting
	// each operation individually
	private static final ThreadLocal<RemoteWebDriver> threadLocalDriver = new ThreadLocal<RemoteWebDriver>();
	
	// Global driver variable
	public static RemoteWebDriver driver;
	
	// Create WebDriverWait object with driver and wait time
	public static WebDriverWait webDriverWait;

	//Create object for setting physical path
	ExtentHtmlReporter reporter;

	// Create object for ExtentReports
	public static ExtentReports reports;

	// Create object for test data
	public static ExtentTest test, node;

	// Create variables for test details
	public String testName, testDescription, author, category;
	
	// Create object for Properties file
	public static Properties properties;

	@BeforeMethod
	public void preCondition() throws IOException {

		// Create node to generate reports individually for each test case
		node = reports.createTest(testName);

		// Launch the driver
		driver = new ChromeDriver();

		// Create the Chrome Driver object and set it in Thread Local
		setDriver(driver);

		// Create an object for Properties file
		properties = new Properties();
		
		FileInputStream fileInputStream = new FileInputStream(new File("./src/main/resources/config.properties"));
		
		properties.load(fileInputStream);

		// Maximize the browser window
		getDriver().manage().window().maximize();

		// Implicit Wait of 2 seconds
		getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

		// Initialize WebDriverWait object with wait time
		webDriverWait = new WebDriverWait(getDriver(), Duration.ofMillis(10000));

	}

	@AfterMethod
	public void postCondition() {
		// Close the driver
		// driver.close();
	}

	
	// Setter method for Driver object
	public void setDriver(RemoteWebDriver driver) {
		threadLocalDriver.set(driver);
	}

	// Getter method for Driver object
	public RemoteWebDriver getDriver() {
		return threadLocalDriver.get();
	}
	
	@BeforeSuite
	public void startReport()
	{
		//Set up the physical path for report
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./reports/result.html");
		
		//To keep report history
		reporter.setAppendExisting(true);
		
		//Create object for ExtentReports
		reports = new ExtentReports();
		
		//Attach the physical report to the data
		reports.attachReporter(reporter);
	}

	@BeforeClass
	public void testDetails() {
		
		test = reports.createTest(testName, testDescription);

		// Assign Author and Category
		test.assignAuthor(author);
		test.assignCategory(category);
	}
	
	public int takeSnap() throws IOException
	{
		int ranNum = (int) Math.random() * 9999999;
		
		File source = getDriver().getScreenshotAs(OutputType.FILE);
		
		File target = new File("./images/img"+ranNum+".png");
		
		FileUtils.copyFile(source, target);
		
		return ranNum;
		
	}

	public void reportStep(String message, String status) throws IOException {
		if (status.equals("Pass")) {
			node.pass(message);
		} else if (status.equalsIgnoreCase("Fail")) {
			node.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(".././images/img/"+takeSnap()+".png").build());
			throw new RuntimeException("Check reports for exception details");

		}
	}

	@AfterSuite
	public void stopReport() {
		reports.flush();
	}
}
