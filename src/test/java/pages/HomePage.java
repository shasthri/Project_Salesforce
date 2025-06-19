package pages;

import org.openqa.selenium.By;

import base.BaseClass;

public class HomePage extends MenuPage {
	
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