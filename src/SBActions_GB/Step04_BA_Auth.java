package SBActions_GB;

import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import lib.Excel;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Step04_BA_Auth
{
	
private WebDriver driver;
public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
public String sheet="Login";

//Define Login element
@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
@FindBy ( id="btn_signin") private WebElement Signin_Button ;
@FindBy ( xpath=".//*[@id='desktop']") private WebElement Username_Box ;
@FindBy ( xpath=".//*[@id='body']/div[1]/div[2]/div/div/form/input[4]") private WebElement Password_Box ;

//Define all web elements under test displayed on home page
@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Awaiting Business Approver action')]") private WebElement Awaiting_BA_Action ;
@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'New/Renewal Approval')]") private WebElement New_Renewal_Approval ;

@FindBy (id="FLD_REQUEST_NUMBER") private WebElement Req_Num_Search ;
@FindBy (name="btnGo") private WebElement GO_reqnum ;

@FindBy (xpath =" .//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[2]/a" ) private WebElement Request_Number ;
@FindBy (xpath = ".//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[5]/a" ) private WebElement Skill_link ;
@FindBy (xpath = ".//*[@id='content-main']/form/table[3]/tbody/tr[2]/td[5]/a" ) private WebElement Resp_identification1 ;
@FindBy (xpath = ".//*[@id='content-main']/form/table[3]/tbody/tr[3]/td[5]/a" ) private WebElement Resp_identification2 ;
@FindBy (xpath = ".//*[@id='content-main']/form/table[3]/tbody/tr[4]/td[5]/a" ) private WebElement Resp_identification3 ;

@FindBy (name="btnAuthCandidate") private WebElement Authorize ;
@FindBy (name="FLD_JUSTIFICATION_COMMENTS") private WebElement Justification ;

@FindBy (name="FLD_CMTS_TO_REQSTR") private WebElement Auth_comments ;
@FindBy (name="btnAuthSel") private WebElement Auth_Selected_Button ;
@FindBy (id="content-main']/form/div[2]/span/span/input") private WebElement ExitReq;

//New login code
	@FindBy ( xpath= "//span[@id='credentialSignin']") private WebElement cred_login;
	//@FindBy ( xpath= "//input[@id='otp-input']") private WebElement otp_box;
	//@FindBy(id = "submit_btn")    private WebElement otp_submit_btn;
	@FindBy ( xpath= "//input[@name='username']") private WebElement login_username;
	@FindBy ( xpath= "//input[@name='password']") private WebElement login_password;
	@FindBy ( id="login-button") private WebElement login_Button ;
	@FindBy(id = "otp-input")	private WebElement passcodeBox;
	@FindBy(id = "submit_btn")	private WebElement SubmitPasscode;
	@FindBy (xpath = "//label[@id='totp_label']") private WebElement Authenticator_App_option;
	@FindBy (xpath = "//input[@id='newTotp-otp-input']") private WebElement EnterAccessCode_Box;
	@FindBy (xpath = "//button[contains(text(),'Next: Verify')]") private WebElement Verify_Button;
	@FindBy (xpath = ".//div[@id='totp_item']") private WebElement Totp_Link;
	@FindBy (xpath = "//input[@id='otp-input']") private WebElement OTP_TextBox;
	@FindBy (xpath = "//button[@id='submit_btn']") private WebElement OTP_Submit_Button;
	@FindBy (xpath = ".//*[@id='content-main']/form[1]/div/span/input") private WebElement Create_New_Request;


// Initialize the web elements 
public Step04_BA_Auth (WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}


// Function to login to the application
public void login() throws IOException, InterruptedException{

//	WebDriverWait wait = new WebDriverWait(driver, 180);
//	wait.until(ExpectedConditions.visibilityOf(loginToContractor_Link));
//
//	loginToContractor_Link.click();
	WebDriverWait wait00 = new WebDriverWait(driver, 180);
	wait00.until(ExpectedConditions.visibilityOf(cred_login));
	cred_login.click();
	
	//new login changes
	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(login_Button));
	wait01.until(ExpectedConditions.elementToBeClickable(login_Button));
	login_username.clear();
	login_username.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 18, 0));
	Thread.sleep(1000);
	login_password.clear();
	login_password.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 18, 1));
	
	//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\GB");
	login_Button.click();
	try{
		WebDriverWait wait1 = new WebDriverWait(driver, 50);
		wait1.until(ExpectedConditions.visibilityOf(Authenticator_App_option));
		
		Authenticator_App_option.click();
		System.out.println("Authenticator option is selected for authentication");
	}catch(NoSuchElementException | TimeoutException e)
	{
		System.out.println("Page to select Authenticator app option is not displayed");
		e.printStackTrace();
	}
	
	try {

		String otpKeyStr = "OP7M43FUMFSUS7HW";// bennett13@c25a0161.toronto.ca.ibm.com
		Totp totp = new Totp(otpKeyStr);
		String twoFactorCode = totp.now();
		
		

		OTP_TextBox.sendKeys(twoFactorCode);
		System.out.println("value fetched from box= "+OTP_TextBox.getAttribute("value"));
	
		OTP_Submit_Button.click();
		System.out.println("clicked on OTP submit button");
		WebDriverWait wait02 = new WebDriverWait(driver, 100);
		wait02.until(ExpectedConditions.visibilityOf(Create_New_Request));
		System.out.println("Title of page= "+driver.getTitle());
	}
	catch (Exception e) {
		System.out.println("no OTP screen");
		e.printStackTrace();
		WebDriverWait wait02 = new WebDriverWait(driver, 100);
		wait02.until(ExpectedConditions.visibilityOf(Create_New_Request));
		System.out.println("Title of page= "+driver.getTitle());
	}	
	
}

public void BA_open_req_GB()
{

	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(Awaiting_BA_Action));

	Awaiting_BA_Action.click();
	WebDriverWait wait02 = new WebDriverWait(driver, 180);
	wait02.until(ExpectedConditions.visibilityOf(New_Renewal_Approval));
	New_Renewal_Approval.click();

	WebDriverWait wait03 = new WebDriverWait(driver, 180);
	wait03.until(ExpectedConditions.visibilityOf(Req_Num_Search));
	
	Req_Num_Search.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 2, 15));
	GO_reqnum.click();

	WebDriverWait wait04 = new WebDriverWait(driver, 160);
	wait04.until(ExpectedConditions.visibilityOf(Request_Number)); 

	/* code to capture screenshot */
	//Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\SB\\US\\Core\\");

	Request_Number.click();

}

//auth 1st response

public void BA_auth1_GB()
{

	WebDriverWait wait03 = new WebDriverWait(driver, 160);
	wait03.until(ExpectedConditions.visibilityOf(Resp_identification1)); 

	/* code to capture screenshot */
	//Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).highlight(Resp_identification1).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\SB\\US\\Core\\");

	Resp_identification1.click();

	WebDriverWait wait04 = new WebDriverWait(driver, 160);
	wait04.until(ExpectedConditions.visibilityOf(Authorize)); 

	Select st = new Select(Justification);
	st.selectByVisibleText("Approved acceptable business cost");

	/* code to capture screenshot */
//	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).highlight(Authorize).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\SB\\US\\Core\\");

	Authorize.click();

	WebDriverWait wait05 = new WebDriverWait(driver, 160);
	wait05.until(ExpectedConditions.visibilityOf(Auth_Selected_Button)); 

	Auth_comments.sendKeys("Approved");

	/* code to capture screenshot */
	//Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\SB\\US\\Core\\");

	Auth_Selected_Button.click();

}

//auth resp 2

public void BA_auth2_GB()
{


	WebDriverWait wait06 = new WebDriverWait(driver, 160);
	wait06.until(ExpectedConditions.visibilityOf(Resp_identification2));

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).highlight(Resp_identification2).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\SB\\US\\Core\\");

	Resp_identification2.click();

	WebDriverWait wait04 = new WebDriverWait(driver, 160);
	wait04.until(ExpectedConditions.visibilityOf(Authorize)); 

	Select st = new Select(Justification);
	st.selectByVisibleText("Approved acceptable business cost");

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).highlight(Authorize).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\SB\\US\\Core\\");
	Authorize.click();

	WebDriverWait wait05 = new WebDriverWait(driver, 160);
	wait05.until(ExpectedConditions.visibilityOf(Auth_Selected_Button)); 

	Auth_comments.sendKeys("Approved");

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\SB\\US\\Core\\");

	Auth_Selected_Button.click();
	
}

//auth 3rd response

public void BA_auth3_GB()
{

	WebDriverWait wait03 = new WebDriverWait(driver, 160);
	wait03.until(ExpectedConditions.visibilityOf(Resp_identification3)); 

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).highlight(Resp_identification3).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\SB\\US\\Core\\");
	
	Resp_identification3.click();

	WebDriverWait wait04 = new WebDriverWait(driver, 160);
	wait04.until(ExpectedConditions.visibilityOf(Authorize)); 

	Select st = new Select(Justification);
	st.selectByVisibleText("Approved acceptable business cost");

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).highlight(Authorize).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\SB\\US\\Core\\");
	Authorize.click();

	WebDriverWait wait05 = new WebDriverWait(driver, 160);
	wait05.until(ExpectedConditions.visibilityOf(Auth_Selected_Button)); 

	Auth_comments.sendKeys("Approved");

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\IBM_ADMIN\\Desktop\\Screenshots\\SB\\US\\Core\\");

	Auth_Selected_Button.click();

}
}
