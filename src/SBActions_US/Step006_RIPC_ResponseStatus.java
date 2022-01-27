package SBActions_US;

import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import lib.Excel;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Step006_RIPC_ResponseStatus {

String str="Finalist";
String str1 = "Rejected";
public String str2;
public String str3;
public String str4="Awaiting authorization";
public String str5;
private WebDriver driver;
public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
public String sheet="Login";	
	
// Define the element 
@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
@FindBy ( id="btn_signin") private WebElement Signin_Button ;
@FindBy ( xpath=".//*[@id='desktop']") private WebElement Username_Box ;
@FindBy ( xpath=".//*[@id='body']/div[1]/div[2]/div/div/form/input[4]") private WebElement Password_Box ;
@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Search')]") private WebElement Search ;
@FindBy ( id="FLD_REQ_NUM_SEARCH") private WebElement Request_Num ;
@FindBy ( name="GO") private WebElement Go_btn ;
@FindBy  ( xpath = ".//*[@id='content-main']/form/table[3]/tbody/tr[2]/td[1]/a") private WebElement ReqNumlink ;
@FindBy (xpath=".//*[@id='content-main']/form/table[3]/tbody/tr[3]/td[4]") private WebElement Reject_status;
@FindBy (xpath =".//*[@id='content-main']/form/table[3]/tbody/tr[4]/td[4]") private WebElement Finalist;
@FindBy (xpath =".//*[@id='content-main']/form/table[3]/tbody/tr[2]/td[4]/a") private WebElement AwaitingAuth;
@FindBy ( xpath = ".//*[@id='content-main']/form/table[1]/tbody/tr[11]/td[3]/a") private WebElement Supplink ;




// Initialize the web elements 
public Step006_RIPC_ResponseStatus(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

// Function to login to the application
// Click on the link again as workaround 
public void status(){

	WebDriverWait wait = new WebDriverWait(driver, 180);
	wait.until(ExpectedConditions.visibilityOf(loginToContractor_Link));
	
	loginToContractor_Link.click();
	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(Signin_Button));

	Username_Box.clear();
	Username_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 0));
	Password_Box.clear();
	Password_Box.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 1));

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\SB\\US\\Core\\");

	Signin_Button.click();

	WebDriverWait wait10 = new WebDriverWait(driver, 180);
	wait10.until(ExpectedConditions.visibilityOf(Search));
	Search.click();

	WebDriverWait wait11 = new WebDriverWait(driver, 180);
	wait11.until(ExpectedConditions.visibilityOf(Request_Num));
	
	Request_Num.sendKeys(Excel.getCellValue(xlsFilePath,"Request_creation", 1, 15));
	
	Go_btn.click();

	WebDriverWait wait12 = new WebDriverWait(driver, 180);
	wait12.until(ExpectedConditions.visibilityOf(ReqNumlink));
	
	ReqNumlink.click();
	
}
public void status_verf()
{
	str2 = Finalist.getText();
	System.out.println(str2);
	if (str2.equals(str))
	{ 
		System.out.println("Status Passed : Finalist_Response");
	} else
	{ 
		System.out.println("Status Failed : Finalist_response");
		driver.quit();
	}

	str3 = Reject_status.getText();
	if (str3.equals(str1))
	{
		System.out.println("Status Passed : Rejected_response");
	}else
	{ 
		System.out.println("Status Failed : Rejected_response");
		driver.quit();
	}

	str5= AwaitingAuth.getText();
	System.out.println(str5);
	if (str5.equals(str4))
	{
		System.out.println("Status Passed : Authorize_response");
	}else
	{ 
		System.out.println("Status Failed : Authorize_response");
		driver.quit();
	}

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\SB\\US\\Core\\");

}



}

