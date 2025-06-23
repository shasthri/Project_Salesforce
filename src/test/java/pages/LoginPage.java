package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BaseClass;

public class LoginPage extends MenuPage {

	private By userName = By.xpath("//input[@id = 'username']");
	private By password = By.xpath("//input[@id = 'password']");
	private By loginBtn = By.xpath("//input[@id = 'Login']");
	private By forgotPassword = By.xpath("//a[@id = 'forgot_password_link']");
	private By rememberMeCheckBox = By.cssSelector("div.w0.pr.ln3.p16.remember>input");
	private By loginFailureMsg = By.xpath("//div[@id = 'error']");
	private WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}

	public boolean verifyLoginElements() {
		if ((driver.findElement(userName).isDisplayed()) && (driver.findElement(password).isDisplayed())
				&& (driver.findElement(loginBtn).isDisplayed()) && (driver.findElement(forgotPassword).isDisplayed())
				&& (driver.findElement(rememberMeCheckBox).isDisplayed())) {
			return true;
		} else {
			return false;
		}
	}

	public LoginPage enterUserName(String sUserName) {
		WebElement wUserName = driver.findElement(userName);
		wUserName.sendKeys(sUserName);
		return new LoginPage(driver);
	}

	public LoginPage enterPassword(String sPassword) {
		WebElement wPassword = driver.findElement(password);
		wPassword.sendKeys(sPassword);
		return this;
	}

	public HomePage mClickLoginWithValidCredentials() {
		WebElement wLoginBtn = driver.findElement(loginBtn);
		wLoginBtn.click();
		return new HomePage(driver);
	}

	public LoginPage mClickLoginWithInvalidCredentials() {
		WebElement wLoginBtn = driver.findElement(loginBtn);
		wLoginBtn.click();
		return this;
	}

	public boolean mValidateErrorMessage() {
		WebElement wLoginFailureMsg = driver.findElement(loginFailureMsg);
		if (wLoginFailureMsg.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
}
