package libraries;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class SeleniumWrapper extends BaseClass{
	
	public SeleniumWrapper(WebDriver driver,ExtentTest node) {
		this.driver = driver;
		this.node = node;
	}

public WebElement locateElement(String locValue) {
	return driver.findElement(By.id(locValue));
}

public void type(WebElement ele, String data) {
	try {
		ele.clear();
		ele.sendKeys(data);
		reportStep("The data: "+data+" entered successfully in the field :", "PASS");
	} catch (InvalidElementStateException e) {
		reportStep("The data: "+data+" could not be entered in the field :","FAIL");
	} catch (WebDriverException e) {
		e.printStackTrace();
		reportStep("Unknown exception occured while entering "+data+" in the field :", "FAIL");
	}
}

public void typeAndChoose(WebElement ele, String data) {
	try {
		ele.clear();
		ele.sendKeys(data,Keys.TAB);
		reportStep("The data: "+data+" entered successfully in the field :", "PASS");
	} catch (InvalidElementStateException e) {
		reportStep("The data: "+data+" could not be entered in the field :","FAIL");
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while entering "+data+" in the field :", "FAIL");
	}
}

public void typeAndEnter(WebElement ele, String data) {
	try {
		ele.clear();
		ele.sendKeys(data, Keys.ENTER);
		reportStep("The data: "+data+" entered successfully in the field :", "PASS");
	} catch (InvalidElementStateException e) {
		reportStep("The data: "+data+" could not be entered in the field :","FAIL");
	} catch (WebDriverException e) {
		e.printStackTrace();
		reportStep("Unknown exception occured while entering "+data+" in the field :", "FAIL");
	}
}

public void click(WebElement ele) {
	String text = "";
	try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));			
		text = ele.getText();
		ele.click();
		reportStep("The element "+text+" is clicked", "PASS");
	} catch (InvalidElementStateException e) {
		reportStep("The element: "+text+" could not be clicked", "FAIL");
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while clicking in the field :", "FAIL");
	} 
}

public void click(WebElement ele,String eleName) {
	try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));			
		ele.click();
		reportStep("The element "+eleName+" is clicked", "PASS");
	} catch (InvalidElementStateException e) {
		reportStep("The element: "+eleName+" could not be clicked", "FAIL");
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while clicking in the field :", "FAIL");
	} 
}

public void moveToElementAndClick(WebElement ele,String eleName) {
	try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));			
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).click().perform();
		reportStep("The element "+eleName+" is clicked", "PASS");
	} catch (InvalidElementStateException e) {
		reportStep("The element: "+eleName+" could not be clicked", "FAIL");
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while clicking in the field :", "FAIL");
	} 
	
}

public void moveToElement(WebElement ele,String eleName) {
	try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));			
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).perform();
		reportStep("The control is moved to element "+eleName, "PASS");
	} catch (InvalidElementStateException e) {
		reportStep("The control is not moved to element "+eleName, "FAIL");
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while clicking in the field :", "FAIL");
	} 
	
}

public void clickWithNoSnap(WebElement ele) {
	String text = ""; 
	try {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));	
		text = ele.getText();
		ele.click();			
		reportStep("The element :"+text+"  is clicked.", "PASS");
	} catch (InvalidElementStateException e) {
		reportStep("The element: "+text+" could not be clicked", "FAIL");
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while clicking in the field :","FAIL");
	} 
}

public String getText(WebElement ele) {	
	String bReturn = "";
	try {
		bReturn = ele.getText();
		reportStep("The displayed Text is : "+bReturn+"", "PASS");
	} catch (WebDriverException e) {
		reportStep("The element could not be found.", "FAIL");
	}
	return bReturn;
}

public String getTitle() {		
	String bReturn = "";
	try {
		bReturn =  driver.getTitle();
		reportStep("The Title is : "+bReturn+"", "PASS");
	} catch (WebDriverException e) {
		reportStep("Unknown Exception Occured While fetching Title", "FAIL");
	} 
	return bReturn;
}

public String getAttribute(WebElement ele, String attribute) {		
	String bReturn = "";
	try {
		bReturn=  ele.getAttribute(attribute);
		reportStep("The Attribute is : "+bReturn+"", "PASS");
	} catch (WebDriverException e) {
		reportStep("The element: "+ele+" could not be found.", "FAIL");
	} 
	return bReturn;
}

public void selectDropDownUsingVisibleText(WebElement ele, String value) {
	try {
		new Select(ele).selectByVisibleText(value);
		reportStep("The dropdown is selected with text "+value,"PASS");
	} catch (WebDriverException e) {
		reportStep("The element: "+ele+" could not be found.", "FAIL");
	}

}

public void selectDropDownUsingIndex(WebElement ele, int index) {
	try {
		new Select(ele).selectByIndex(index);
		reportStep("The dropdown is selected with index "+index,"PASS");
	} catch (WebDriverException e) {
		reportStep("The element: "+ele+" could not be found.", "FAIL");
	} 

}

public boolean verifyExactTitle(String title) {
	boolean bReturn =false;
	try {
		if(getTitle().equals(title)) {
			reportStep("The title of the page matches with the value :"+title,"PASS");
			bReturn= true;
		}else {
			reportStep("The title of the page:"+driver.getTitle()+" did not match with the value :"+title, "FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while verifying the title", "FAIL");
	} 
	return bReturn;
}

public boolean verifyPartialTitle(String title) {
	boolean bReturn =false;
	try {
		if(getTitle().contains(title)) {
			reportStep("The title of the page matches with the value :"+title,"PASS");
			bReturn= true;
		}else {
			reportStep("The title of the page:"+driver.getTitle()+" did not match with the value :"+title, "FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while verifying the title", "FAIL");
	} 
	return bReturn;		
}

public void verifyExactText(WebElement ele, String expectedText) {
	try {
		if(getText(ele).equals(expectedText)) {
			reportStep("The text: "+getText(ele)+" matches with the value :"+expectedText,"PASS");
		}else {
			reportStep("The text "+getText(ele)+" doesn't matches the actual "+expectedText,"FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while verifying the Text", "FAIL");
	} 

}

public void verifyPartialText(WebElement ele, String expectedText) {
	try {
		if(getText(ele).contains(expectedText)) {
			reportStep("The expected text contains the actual "+expectedText,"PASS");
		}else {
			reportStep("The expected text doesn't contain the actual "+expectedText,"FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while verifying the Text", "FAIL");
	} 
}

public void verifyExactAttribute(WebElement ele, String attribute, String value) {
	try {
		if(getAttribute(ele, attribute).equals(value)) {
			reportStep("The expected attribute :"+attribute+" value matches the actual "+value,"PASS");
		}else {
			reportStep("The expected attribute :"+attribute+" value does not matches the actual "+value,"FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while verifying the Attribute Text", "FAIL");
	} 

}

public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
	try {
		if(getAttribute(ele, attribute).contains(value)) {
			reportStep("The expected attribute :"+attribute+" value contains the actual "+value,"PASS");
		}else {
			reportStep("The expected attribute :"+attribute+" value does not contains the actual "+value,"FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("Unknown exception occured while verifying the Attribute Text", "FAIL");
	}
}

public void verifySelected(WebElement ele) {
	try {
		if(ele.isSelected()) {
			reportStep("The element "+ele+" is selected","PASS");
		} else {
			reportStep("The element "+ele+" is not selected","FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	}
}

public void verifyDisplayed(WebElement ele) {
	try {
		String attribute = ele.getAttribute("name");
		if(ele.isDisplayed()) {
			reportStep("The element "+attribute+"is visible","PASS");
		} else {
			reportStep("The element "+attribute+" is not visible","FAIL");
		}
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	} 
}
 
public boolean verifyDisplayedwithReturn(WebElement ele) {
	boolean result = false;
	try {
		String attribute = ele.getAttribute("name");
		if(ele.isDisplayed()) { 
			reportStep("The element "+attribute+" is visible","PASS");
			result = true;
			return result;
		} else {
			reportStep("The element "+attribute+" is not visible","FAIL");
			return result;
		}
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	}
	return result;
}

public boolean verifyDisplayedwithReturn(WebElement ele,String element) {
	boolean result = false;
	try {
		if(ele.isDisplayed()) { 
			reportStep("The element "+element+" is visible","PASS");
			result = true;
			return result;
		} else {
			reportStep("The element "+element+"  is not visible","FAIL");
			return result;
		}
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	}
	return result;
}



public void switchToWindow(int index) {
	try {
		Set<String> allWindowHandles = driver.getWindowHandles();
		List<String> allHandles = new ArrayList<String>();
		allHandles.addAll(allWindowHandles);
		driver.switchTo().window(allHandles.get(index));
	} catch (NoSuchWindowException e) {
		reportStep("The driver could not move to the given window by index "+index,"PASS");
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	}
}

public void switchToFrame(WebElement ele) {
	try {
		driver.switchTo().frame(ele);
		reportStep("switch In to the Frame "+ele,"PASS");
	} catch (NoSuchFrameException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	} 
}

public void acceptAlert() {
	String text = "";		
	try {
		Alert alert = driver.switchTo().alert();
		text = alert.getText();
		alert.accept();
		reportStep("The alert "+text+" is accepted.","PASS");
	} catch (NoAlertPresentException e) {
		reportStep("There is no alert present.","FAIL");
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	}  
}

public void dismissAlert() {
	String text = "";		
	try {
		Alert alert = driver.switchTo().alert();
		text = alert.getText();
		alert.dismiss();
		reportStep("The alert "+text+" is dismissed.","PASS");
	} catch (NoAlertPresentException e) {
		reportStep("There is no alert present.","FAIL");
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	} 

}

public String getAlertText() {
	String text = "";		
	try {
		Alert alert = driver.switchTo().alert();
		text = alert.getText();
	} catch (NoAlertPresentException e) {
		reportStep("There is no alert present.","FAIL");
	} catch (WebDriverException e) {
		reportStep("WebDriverException : "+e.getMessage(), "FAIL");
	} 
	return text;
}


public void closeActiveBrowser() {
	try {
		driver.close();
		reportStep("The browser is closed","PASS");
	} catch (Exception e) {
		reportStep("The browser could not be closed","FAIL");
	}
}

public void closeAllBrowsers() {
	try {
		driver.quit();
		reportStep("The opened browsers are closed","PASS");
	} catch (Exception e) {
		reportStep("Unexpected error occured in Browser","FAIL");
	}
}


public void selectDropDownUsingValue(WebElement ele, String value) {
	try {
		new Select(ele).selectByValue(value);
		reportStep("The dropdown is selected with text "+value,"PASS");
	} catch (WebDriverException e) {
		reportStep("The element: "+ele+" could not be found.", "FAIL");
	}

}
	

}
