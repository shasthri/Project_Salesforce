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

	private By newBtn = By.xpath("//div[@part = 'button-group']//button[@name = 'New']");
	private By lastName = By.xpath("//input[@name = 'lastName']");
	private By companyName = By.xpath("//input[@name = 'Company']");
	private By leadStatusField = By .xpath("//records-record-layout-item[@field-label = 'Lead Status']//button[@aria-label = 'Lead Status']");
	private By leadStatusDropDown = By.xpath("//div[@aria-label = 'Lead Status']//lightning-base-combobox-item/span[@class = 'slds-media__body']/span");
	private By saveButton = By.xpath("//div[@class = 'footer-full-width']//li//button[@name = 'SaveEdit']");
	private By noOfEmployees = By.xpath("//input[@name = 'NumberOfEmployees']");

	private String sLastName = FakerDataFactory.getLastName();
	private String sCompanyName = FakerDataFactory.getCompanyName();
	private SeleniumWrapper wrap;

	
	public SalesPage(WebDriver driver, ExtentTest node)
	{
		super(driver, node);
		this.driver = driver;
		this.node = node;
		wrap = new SeleniumWrapper(driver, node);
	}
	
	public SalesPage mClickOnLeads() {
		
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
		return this;
	}

	public SalesPage mClickOnNewLeadButton() {
		wrap.click(driver.findElement(newBtn));
//		driver.findElement(newBtn).click();
		return this;
	}

	public SalesPage mEnterlastName() {
		WebElement wLastName = driver.findElement(lastName);
		wrap.type(wLastName, sLastName);
//		wLastName.sendKeys(sLastName);
		return this;
	}

	public SalesPage mEnterCompanyName() {
		WebElement wCompany = driver.findElement(companyName);
		wrap.type(wCompany, sCompanyName);
//		wCompany.sendKeys(sCompanyName);
		return this;
	}

	public SalesPage selectLeadStatus(String sLeadStatus) {
//		Actions oAction = new Actions(driver);
//		oAction.moveToElement(driver.findElement(noOfEmployees)).perform();
		
		wrap.moveToElement(driver.findElement(noOfEmployees), "Number of Employees");
		
		WebElement wLeadStatusDropDown = driver.findElement(leadStatusField);
		wrap.click(wLeadStatusDropDown, "Lead status");
//		wLeadStatusDropDown.click();

		List<WebElement> lLeadStatus = driver.findElements(leadStatusDropDown);
		for (WebElement wLeadStatus : lLeadStatus) {
			if (wLeadStatus.getText().trim().equalsIgnoreCase(sLeadStatus)) {
				wLeadStatus.click();
			}
		}
		return this;
	}

	public SalesPage mClickOnSaveButton() {

		wrap.click(driver.findElement(saveButton));
//		driver.findElement(saveButton).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

}
