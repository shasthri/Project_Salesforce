package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
import libraries.SeleniumWrapper;

public class LoginPage extends MenuPage {

	private By userName = By.xpath("//input[@id = 'username']");
	private By password = By.xpath("//input[@id = 'password']");
	private By loginBtn = By.xpath("//input[@id = 'Login']");
	private By forgotPassword = By.xpath("//a[@id = 'forgot_password_link']");
	private By rememberMeCheckBox = By.cssSelector("div.w0.pr.ln3.p16.remember>input");
	private By loginFailureMsg = By.xpath("//div[@id = 'error']");
	private WebDriver driver;
	public SeleniumWrapper wrap;
	
	public LoginPage(WebDriver driver, ExtentTest node)
	{
		super(driver, node);
		this.driver = driver;
		this.node = node;
		wrap = new SeleniumWrapper(driver, node);
	}

	public boolean verifyLoginElements() {
		if(wrap.verifyDisplayedwithReturn(driver.findElement(userName)) && 
				wrap.verifyDisplayedwithReturn(driver.findElement(password)) &&
				wrap.verifyDisplayedwithReturn(driver.findElement(loginBtn)) &&
				wrap.verifyDisplayedwithReturn(driver.findElement(forgotPassword)) &&
				wrap.verifyDisplayedwithReturn(driver.findElement(rememberMeCheckBox)))
		{
			return true;
		} else {
			return false;
		}
	}
//		if ((driver.findElement(userName).isDisplayed()) && (driver.findElement(password).isDisplayed())
//				&& (driver.findElement(loginBtn).isDisplayed()) && (driver.findElement(forgotPassword).isDisplayed())
//				&& (driver.findElement(rememberMeCheckBox).isDisplayed())) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	public LoginPage enterUserName(String sUserName) {		
		WebElement wUserName = driver.findElement(userName);
		wrap.type(wUserName, sUserName);
//		wUserName.sendKeys(sUserName);
		return new LoginPage(driver, node);
	}

	public LoginPage enterPassword(String sPassword) {
		WebElement wPassword = driver.findElement(password);
		wrap.type(wPassword, sPassword);
//		wPassword.sendKeys(sPassword);
		return this;
	}

	public HomePage mClickLoginWithValidCredentials() {
		WebElement wLoginBtn = driver.findElement(loginBtn);
		wrap.click(wLoginBtn, "Login Button");
//		wLoginBtn.click();
		return new HomePage(driver, node);
	}

	public LoginPage mClickLoginWithInvalidCredentials() {
		WebElement wLoginBtn = driver.findElement(loginBtn);
		wrap.click(wLoginBtn, "Login Button");
//		wLoginBtn.click();
		return this;
	}

	public boolean mValidateErrorMessage() {
		WebElement wLoginFailureMsg = driver.findElement(loginFailureMsg);
		
		if(wrap.verifyDisplayedwithReturn(wLoginFailureMsg))
		{
			return true;
		} else {
			return false;
		}
	}
}
