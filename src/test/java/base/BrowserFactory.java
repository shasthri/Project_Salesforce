package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BrowserFactory 
{
	
	public WebDriver driver;
	public String sURL = "https://login.salesforce.com/";
	public String sBrowser = "Chrome";
	
	@BeforeClass
	public void mLaunchBrowser()
	{
		switch (sBrowser.toLowerCase()) 
		{
		case "chrome":
			System.out.println("Launching chrome browser");
			driver = new ChromeDriver();
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
	
}
