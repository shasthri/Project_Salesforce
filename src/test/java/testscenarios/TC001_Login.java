package testscenarios;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class TC001_Login extends BaseClass {
	
	//LoginPage login = new LoginPage(driver);
	
	@BeforeTest
	public void setExcelDataProperties()
	{
		sExcelFileName = "TC01";
		sExcelSheetName = "SalesForce_Credentials";
	}
	
	@Test (priority = 1)
	public void loginFieldValidation()
	{
		boolean result = new LoginPage(driver).verifyLoginElements();
		Assert.assertTrue(result);
	}

	@Test (priority = 2, dataProvider ="TestCaseData")
	public void loginWithValidCredentials(String sUserName, String sPassword)
	{
		boolean result = new LoginPage(driver).
		enterUserName(sUserName).
		enterPassword(sPassword).
		mClickLoginWithValidCredentials().
		verifyHomePageElements().
		clickUserImg().
		clickOnLogout().verifyLoginElements();
		
		Assert.assertTrue(result);
	}

	@Test (priority = 3)
	public void loginWithInValidCredentials()
	{
		boolean result = new LoginPage(driver).
		enterUserName("ssshasthri991@agentforce.com").
		enterPassword("RCSeries#10").mClickLoginWithInvalidCredentials().
		mValidateErrorMessage();
		Assert.assertTrue(result);
	}
}
