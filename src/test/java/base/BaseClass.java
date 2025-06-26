package base;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import libraries.HTMLReport;
import util.ExcelDataProvider;
import util.PropertyReader;

public class BaseClass extends HTMLReport {

	public WebDriver driver;
	public String sURL = PropertyReader.readDataFromPropertyFile("Environment", "sURL");
	public String sBrowser = PropertyReader.readDataFromPropertyFile("Environment", "browser");
	public ChromeOptions options = new ChromeOptions();
	public String sExcelFileName = "";
	public String sExcelSheetName = "";

	public String testName, testDescription, moduleName;

	/**
	 * This method is to Initialize the HTML test report before the test suite starts
	 * It is annotated with @BeforeSuite to ensure it runs before any tests in the suite
	 */
	@BeforeSuite
	public void reportInit() {
		startReport();
	}

	/**
	 * This method is to Finalize and closes the HTML test report after the test suite ends
	 * It is annotated with @AfterSuite to ensure it runs after all tests in the suite
	 */
	@AfterSuite
	public void reportEnd() {
		endReport();
	}


	/**
	 * This method is used to launch the browser specified in the property file, navigates to the test URL, and initializes test case reporting
	 * It is annotated with @BeforeClass to ensure it runs before any test methods in the class
	 */
	@BeforeClass
	public void mLaunchBrowser() {
		switch (sBrowser.toLowerCase()) {
		case "chrome":
			System.out.println("Launching chrome browser");
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			break;
		case "firefox":
			System.out.println("Launching firefox browser");
			driver = new FirefoxDriver();
			break;
		case "edge":
			System.out.println("Launching Edge browser");
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("Invalid option, Launching chrome browser");
			driver = new ChromeDriver();
			break;
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		startTestCase(testName, testDescription);
		startTestcase(moduleName);
	}

	/**
	 * This method is used to close the browser after all tests in the class have run
	 * It is annotated with @AfterClass to ensure it runs after all tests in the class
	 */
	@AfterClass
	public void mCloseBrowser() {
		driver.quit();
	}

	
	/**
	 * This method is used to set the Excel file name and sheet name for data-driven testing
	 * @param sExcelFileName - Name of the Excel file
	 * @param sExcelSheetName - Name of the sheet in the Excel file
	 */
	@DataProvider(name = "TestCaseData")
	public Object[][] excelData() {
		Object[][] values = ExcelDataProvider.mReadFromExcel(sExcelFileName, sExcelSheetName);
		return values;
	}

	/**
	 * This method is used to take screenshot and return the path of the screenshot
	 * @return String - path of the screenshot
	 * Captures a screenshot of the current browser window and saves it to the 'snap' directory
	 */
	@Override
	public String takeScreenshot() {
		String sPath = System.getProperty("user.dir")+"/snap/img"+System.currentTimeMillis()+".png";
		TakesScreenshot oShot = (TakesScreenshot)driver;
		File osrc = oShot.getScreenshotAs(OutputType.FILE);
		File dis = new File(sPath);
		try {
			FileUtils.copyFile(osrc, dis); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sPath;
	}
}
