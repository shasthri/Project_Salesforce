package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import libraries.HTMLReport;
import util.ExcelDataProvider;
import util.PropertyReader;

public class BaseClass extends HTMLReport 
{
	
	public WebDriver driver;
	public String sURL = PropertyReader.readDataFromPropertyFile("Environment", "sURL");
	public String sBrowser = PropertyReader.readDataFromPropertyFile("Environment", "browser");
	public ChromeOptions options = new ChromeOptions();
	public String sExcelFileName = "";
	public String sExcelSheetName = "";
	
	@BeforeClass
	public void mLaunchBrowser()
	{
		switch (sBrowser.toLowerCase()) 
		{
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
	}

	@AfterClass
	public void mCloseBrowser()
	{
		driver.quit();
	}
	
	@DataProvider (name ="TestCaseData")
	public Object[][] excelData()
	{
		Object[][] values = ExcelDataProvider.mReadFromExcel(sExcelFileName, sExcelSheetName);
		return values;
	}

	@Override
	public String takeScreenshot() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
