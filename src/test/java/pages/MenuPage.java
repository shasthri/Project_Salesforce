package pages;

import org.openqa.selenium.By;

import base.BaseClass;

public class MenuPage extends BaseClass {
	
	protected By appLauncher = By.xpath("//div[@class = 'appLauncher slds-context-bar__icon-action']");
	protected By viewAll = By.xpath("//button[text() = 'View All']");
	protected By salesLink = By.xpath("//p[text() = 'Sales']//ancestor::a");
	protected By logoutLink = By.xpath("//div[@class = 'profile-card-toplinks']/a[text() = 'Log Out']");
	protected By userImg = By.xpath("//span[@class = 'uiImage']/ancestor::span/div[@data-aura-class = 'forceEntityIcon']");
	
	public MenuPage clickOnAppLauncher()
	{
		driver.findElement(appLauncher).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	public MenuPage clickOnViewAll()
	{
		driver.findElement(viewAll).click();
		return this;
	}
	
	public MenuPage clickOnSales()
	{
		driver.findElement(salesLink).click();
		return this;
	}
	
	public MenuPage clickUserImg()
	{
		driver.findElement(userImg).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	public LoginPage clickOnLogout() {
		driver.findElement(logoutLink).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new LoginPage();
	}

}
