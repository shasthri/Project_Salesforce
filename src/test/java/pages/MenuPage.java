package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
import libraries.SeleniumWrapper;

public class MenuPage extends BaseClass {
	
	/*
	 * Why `protected`?
	 * 
	 * • protected allows this locator to be accessed within the same package and by
	 * subclasses (even in different packages). • If it were private, only the
	 * MenuPage class could use it, making it inaccessible to subclasses. 
	 * • If it were public, any class could access it, which is not ideal for encapsulation
	 * and could expose internal details unnecessarily. 
	 * • protected strikes a balance, supporting inheritance and reuse while limiting exposure.
	 * • This approach promotes encapsulation and allows subclasses to use these locators.
	 * 
	 * How does the classes inside test scenarios package access these locators?
	 * 
	 * • The testscenarios package can access the protected locators in MenuPage only if its classes extend MenuPage (inheritance), 
	 * or if testscenarios is in the same package as MenuPage.
	 * • If testscenarios is in a different package and does not extend MenuPage, it cannot access protected members directly. 
	 * • If a class in testscenarios extends MenuPage, it can access the protected locators through inheritance. 
	 * • This is because protected allows access within the same package and to subclasses in any package.
	 */
	
	protected By appLauncher = By.xpath("//div[@class = 'appLauncher slds-context-bar__icon-action']");
	protected By viewAll = By.xpath("//button[text() = 'View All']");
	protected By salesLink = By.xpath("//p[text() = 'Sales']//ancestor::a");
	protected By logoutLink = By.xpath("//div[@class = 'profile-card-toplinks']/a[text() = 'Log Out']");
	protected By userImg = By.xpath("//span[@class = 'uiImage']/ancestor::span/div[@data-aura-class = 'forceEntityIcon']");
	private WebDriver driver;
	
	private SeleniumWrapper wrap;
	
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
