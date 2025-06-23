package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BaseClass;

public class HomePage extends MenuPage {
	
	private WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}
	
	public HomePage verifyHomePageElements()
	{
		if((driver.findElement(appLauncher).isDisplayed()) &&
				(driver.findElement(userImg).isDisplayed()))
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