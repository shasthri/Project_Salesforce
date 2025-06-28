package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import libraries.FakerDataFactory;
import libraries.SeleniumWrapper;
import testscenarios.TC003_EditLead;
import util.PropertyReader;

public class LeadsPage extends MenuPage {
	
	
	public String sLeadname = PropertyReader.readDataFromPropertyFile("Environment", "EditLeadName");
	
	// Generate random data for last name and company name using FakerDataFactory
	private String sLastName = FakerDataFactory.getLastName();
	private String sCompanyName = FakerDataFactory.getCompanyName();
	private String sLeadTitle = FakerDataFactory.getTitle()+" "+sLeadname+" title";
			
	private WebDriver driver;
	private SeleniumWrapper wrap;

	private By newBtn = By.xpath("//div[@part = 'button-group']//button[@name = 'New']");
	private By lastName = By.xpath("//input[@name = 'lastName']");
	private By companyName = By.xpath("//input[@name = 'Company']");
	private By leadStatusField = By
			.xpath("//records-record-layout-item[@field-label = 'Lead Status']//button[@aria-label = 'Lead Status']");
	private By leadStatusDropDown = By.xpath(
			"//div[@aria-label = 'Lead Status']//lightning-base-combobox-item/span[@class = 'slds-media__body']/span");
	private By saveButton = By.xpath("//div[@class = 'footer-full-width']//li//button[@name = 'SaveEdit']");
	private By noOfEmployees = By.xpath("//input[@name = 'NumberOfEmployees']");
	private By leadTableBody = By.xpath("//lightning-datatable//table/tbody/tr[@class = 'slds-hint-parent']");
	
	private By leadDropDownOptions = By.xpath("//a//span[text() = '"+sLeadname+"']//ancestor::tr/td//lightning-button-menu[contains(@class ,'slds-dropdown-trigger_click')]");
	private By editButton = By.xpath("//div[@role = 'menu']//li/a[@title = 'Edit']");
	private By editWindow = By.xpath("//h2[contains(text(), 'Edit')]");
	private By title = By.xpath("//input[@name = 'Title']");
	private By website = By.xpath("//input[@name = 'Website']");
	private By tableTitleColumn = By.xpath("//lightning-primitive-cell-factory[@data-label= 'Title']//lst-formatted-text/span[text() = '"+sLeadTitle+"']");

	
	public LeadsPage(WebDriver driver, ExtentTest node) {
		// Call the constructor of the parent class MenuPage
		super(driver, node);
		this.driver = driver;
		this.node = node;
		wrap = new SeleniumWrapper(driver, node);
	}

	public LeadsPage mClickOnNewLeadButton() {
		wrap.click(driver.findElement(newBtn));
//		driver.findElement(newBtn).click();
		return this;
	}

	public LeadsPage mEnterlastName() {
		WebElement wLastName = driver.findElement(lastName);
		wrap.type(wLastName, sLastName);
//		wLastName.sendKeys(sLastName);
		return this;
	}

	public LeadsPage mEnterCompanyName() {
		WebElement wCompany = driver.findElement(companyName);
		wrap.type(wCompany, sCompanyName);
//		wCompany.sendKeys(sCompanyName);
		return this;
	}

	public LeadsPage selectLeadStatus(String sLeadStatus) {
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

	public LeadsPage mClickOnSaveButton() {

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

	public boolean CheckLeadExists() {
		if (wrap.verifyDisplayedwithReturn(driver.findElement(leadTableBody))) {
			System.out.println("Lead Exists");
			return true;
		} else {
			System.out.println("No Lead Exists");
			return false;
		}
	}
	
	public LeadsPage leadActionsDropDown()
	{
		wrap.click(driver.findElement(leadDropDownOptions));
		System.out.println("Clicked on Actions button");
		return this;
	}
	
	public LeadsPage clickEditButton()
	{
		wrap.click(driver.findElement(editButton));
		return this;
	}
	
	public LeadsPage editWindowDisplayed()
	{
		wrap.verifyDisplayedwithReturn(driver.findElement(editWindow));
		return this;
	}
	
	public LeadsPage editLeadInformation()
	{
		wrap.moveToElement(driver.findElement(website), "Website field");
		wrap.type(driver.findElement(title), sLeadTitle);
		return this;
	}
	
	public boolean verifyleadTitle()
	{
		if(wrap.getText(driver.findElement(tableTitleColumn)).trim().equalsIgnoreCase(sLeadTitle))
		{
			return true;
		} else {
			return false;
		}
	}
}

