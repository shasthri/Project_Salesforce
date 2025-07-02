package testscenarios;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LeadsPage;
import pages.LoginPage;

public class TC004_DeleteLead extends BaseClass {
	
	
	@BeforeTest
	public void setProerties()
	{
		sExcelFileName = "TC01";
		sExcelSheetName = "SalesForce_Credentials";
		authors = "Shasthri";
		category = new String[] { "Regression", "Sanity"};
		testName = "Delete Lead";
		testDescription = "Delete lead and verify the deletion";
		moduleName = "Lead Deletion";
	}

	@Test(priority = 1, dataProvider = "TestCaseData")
	public void mVerifyLeadExists(String sUserName, String sPassword)
	{
		boolean checkLeadExists = new LoginPage(driver, node).
		enterUserName(sUserName).
		enterPassword(sPassword).
		mClickLoginWithValidCredentials().
		verifyHomePageElements().
		clickOnAppLauncher().
		clickOnViewAll().
		clickOnSales().
		mClickOnLeads().
		CheckLeadExists();
		Assert.assertTrue(checkLeadExists);
	}
	
	@Test(priority = 2, dependsOnMethods = "mVerifyLeadExists")
	public void mDeleteLead() {
		boolean verifyDeleteModalDisplayed = new LeadsPage(driver, node).
				leadDeleteActionsDropDown().
				clickDeleteDropDownOption().
				verifyDeleteModalDisplayed();
		
		Assert.assertTrue(verifyDeleteModalDisplayed, "Delete Modal is displayed");
		
		boolean deleteConfirmationMsgDisplayed = new LeadsPage(driver, node).clickOnDeleteButtonInModal().deleteConfirmationMsgDisplayed();
		Assert.assertTrue(deleteConfirmationMsgDisplayed, "Delete confirmation message is displayed");

	}
	
}
