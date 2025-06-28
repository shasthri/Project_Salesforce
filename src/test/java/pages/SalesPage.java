package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import libraries.FakerDataFactory;
import libraries.SeleniumWrapper;

public class SalesPage extends MenuPage {

	private WebDriver driver;
	
	private By leadsLink = By.xpath("//div[@role = 'list']//a[@title = 'Leads']");
	private By txtMyLeads = By.xpath("//div[@class = 'slds-media']//span[@data-aura-class = 'uiOutputText']");
	private String sMyLeads = "My Leads";


	private SeleniumWrapper wrap;

	
	public SalesPage(WebDriver driver, ExtentTest node)
	{
		super(driver, node);
		this.driver = driver;
		this.node = node;
		wrap = new SeleniumWrapper(driver, node);
	}
	
	public LeadsPage mClickOnLeads() {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;		
		jse.executeScript("arguments[0].click();", driver.findElement(leadsLink));
		
//		driver.findElement(leadsLink).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(driver.findElement(txtMyLeads).getText(), sMyLeads);
		return new LeadsPage(driver, node);
	}

}
