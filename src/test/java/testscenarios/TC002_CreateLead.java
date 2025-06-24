package testscenarios;


import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class TC002_CreateLead extends BaseClass {
	
//	public LoginPage login = new LoginPage(driver, node);
	public String sLeadStatus= "Working - Contacted";

	@BeforeTest
	public void setExcelDataProperties()
	{
		sExcelFileName = "TC02";
		sExcelSheetName = "SalesForce_Credentials";
		authors = "Shasthri";
		category = "Sanity";
		testName = "Create lead";
		testDescription = "Create lead with only mandatory fields";
		moduleName = "Lead creation";
	}
	
	@Test(priority = 1, dataProvider ="TestCaseData")
	public void createSalesLeadWithMandatoryFields(String sUserName, String sPassword)
	{
		boolean result = new LoginPage(driver, node).verifyLoginElements();
		Assert.assertTrue(result);
		
		boolean results = new LoginPage(driver, node).
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
