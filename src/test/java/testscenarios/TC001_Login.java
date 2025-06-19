package testscenarios;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class TC001_Login extends BaseClass {
	
	LoginPage login = new LoginPage();
	
	@Test (priority = 1)
	public void loginFieldValidation()
	{
		boolean result = new LoginPage().verifyLoginElements();
		Assert.assertTrue(result);
	}

	@Test (priority = 2)
	public void loginWithValidCredentials()
	{
		boolean result = login.
		enterUserName("ssshasthri991@agentforce.com").
		enterPassword("RCSeries#01").
		mClickLoginWithValidCredentials().
		verifyHomePageElements().
		clickUserImg().
		clickOnLogout().verifyLoginElements();
		
		Assert.assertTrue(result);
	}

	@Test (priority = 3)
	public void loginWithInValidCredentials()
	{
		boolean result = login.
		enterUserName("ssshasthri991@agentforce.com").
		enterPassword("RCSeries#10").mClickLoginWithInvalidCredentials().
		mValidateErrorMessage();
		Assert.assertTrue(result);
	}

	
	
}
