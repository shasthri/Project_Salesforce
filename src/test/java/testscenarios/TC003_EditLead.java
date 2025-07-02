package testscenarios;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LeadsPage;
import pages.LoginPage;
import pages.MenuPage;

public class TC003_EditLead extends BaseClass {

	
	public String sLeadName = "Gill";
	
	@BeforeTest
	public void setProperties() {
		sExcelFileName = "TC01";
		sExcelSheetName = "SalesForce_Credentials";
		authors = "Shasthri";
		category = new String[] { "Sanity" };
		testName = "Edit lead";
		testDescription = "Edit lead and update the information";
		moduleName = "Lead editing";
	}
	
	@Test(priority = 1, dataProvider ="TestCaseData")
	public void editSalesLead(String sUserName, String sPassword) {
		boolean result = new LoginPage(driver, node).verifyLoginElements();
		Assert.assertTrue(result);
		
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
		Assert.assertTrue(checkLeadExists, "No Leads Exists to edit");
		
		boolean verifyleadTitle = new LeadsPage(driver, node).
		leadEditActionsDropDown().
		clickEditButton().
		editWindowDisplayed().
		editLeadInformation().
		mClickOnSaveButton().
		verifyleadTitle();
		Assert.assertTrue(verifyleadTitle, "Lead title matches");
		
		boolean verifyLoginElements = new MenuPage(driver, node).clickUserImg().clickOnLogout().verifyLoginElements();
		Assert.assertTrue(verifyLoginElements, "User logged out successfully");
	}		
}
