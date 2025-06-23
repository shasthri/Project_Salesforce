package testscenarios;


import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class TC002_CreateLead extends BaseClass {
	
	public LoginPage login = new LoginPage(driver);
	public String sLeadStatus= "Working - Contacted";

	@BeforeTest
	public void setExcelDataProperties()
	{
		sExcelFileName = "TC02";
		sExcelSheetName = "SalesForce_Credentials";
	}
	
	@Test(priority = 1, dataProvider ="TestCaseData")
	public void createSalesLeadWithMandatoryFields(String sUserName, String sPassword)
	{
		boolean result = new LoginPage(driver).verifyLoginElements();
		Assert.assertTrue(result);
		
		boolean results = new LoginPage(driver).
		enterUserName(sUserName).
		enterPassword(sPassword).
		mClickLoginWithValidCredentials().
		verifyHomePageElements().
		clickOnAppLauncher().
		clickOnViewAll().
		clickOnSales().
		mClickOnLeads().
		mClickOnNewLeadButton().
		mEnterlastName().
		mEnterCompanyName().
		selectLeadStatus(sLeadStatus).
		mClickOnSaveButton().
		clickUserImg().
		clickOnLogout().
		verifyLoginElements();
		
		Assert.assertTrue(results);
	}
	
}
