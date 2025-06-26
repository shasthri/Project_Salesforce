package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
import libraries.SeleniumWrapper;

public class HomePage extends MenuPage {
	
	private WebDriver driver;
	private SeleniumWrapper wrap;
	
	public HomePage(WebDriver driver, ExtentTest node)
	{
		super(driver, node);
		this.driver = driver;
		this.node = node;
		wrap = new SeleniumWrapper(driver, node);
	}
	
	public HomePage verifyHomePageElements()
	{
		if((wrap.verifyDisplayedwithReturn(driver.findElement(appLauncher),"App launcher")) && 
				(wrap.verifyDisplayedwithReturn(driver.findElement(userImg), "User Img icon")))
			
		{
			System.out.println("User landed to home page");
			return this;
		} else
		{
			System.out.println("User not landed to home page");
			return this;
		}
	}
}