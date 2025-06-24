package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
import libraries.SeleniumWrapper;

public class MenuPage extends BaseClass {
	
	protected By appLauncher = By.xpath("//div[@class = 'appLauncher slds-context-bar__icon-action']");
	protected By viewAll = By.xpath("//button[text() = 'View All']");
	protected By salesLink = By.xpath("//p[text() = 'Sales']//ancestor::a");
	protected By logoutLink = By.xpath("//div[@class = 'profile-card-toplinks']/a[text() = 'Log Out']");
	protected By userImg = By.xpath("//span[@class = 'uiImage']/ancestor::span/div[@data-aura-class = 'forceEntityIcon']");
	private WebDriver driver;
	
	public SeleniumWrapper wrap;
	
	public MenuPage(WebDriver driver, ExtentTest node)
	{
		this.driver = driver;
		this.node = node;
		wrap = new SeleniumWrapper(driver, node);
	}
	
	public MenuPage clickOnAppLauncher()
	{
		wrap.click(driver.findElement(appLauncher), "App launcher");
//		driver.findElement(appLauncher).click();
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
		wrap.click(driver.findElement(viewAll), "View All button");
//		driver.findElement(viewAll).click();
		return this;
	}
	
	public SalesPage clickOnSales()
	{
		wrap.click(driver.findElement(salesLink), "Sales link");
//		driver.findElement(salesLink).click();
		return new SalesPage(driver, node);
	}
	
	public MenuPage clickUserImg()
	{
		wrap.click(driver.findElement(userImg), "User image");
//		driver.findElement(userImg).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	public LoginPage clickOnLogout() {
		wrap.click(driver.findElement(logoutLink), "Logout");
//		driver.findElement(logoutLink).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new LoginPage(driver, node);
	}

}
