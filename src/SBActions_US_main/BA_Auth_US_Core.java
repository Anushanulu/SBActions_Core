package SBActions_US_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import SBActions_US.Step04_BA_Auth;

import java.io.IOException;

import org.apache.log4j.Logger;

import lib.Excel;


public class BA_Auth_US_Core
{
	// TestNG logger

	public static Logger log = Logger.getLogger("TnM");

	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	public String sheet="Login"; 
	public String url;
	public String id;
	public String paswd;


	public WebDriver driver;


	@BeforeTest
	public void setup()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\SriSwathiAnushaNulu\\Documents\\Softwares\\geckodriver-v0.29.0-win64\\geckodriver.exe");

		//ProfilesIni ini = new ProfilesIni();
		//FirefoxProfile profile = ini.getProfile("default");
		//WebDriver driver =  new FirefoxDriver(profile);

		driver = new FirefoxDriver();

		//System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();

		//id = Excel.getCellValue(xlsFilePath, sheet, 17, 0);
		//paswd = Excel.getCellValue(xlsFilePath, sheet, 17, 1);
		url = Excel.getCellValue(xlsFilePath, sheet, 30, 2);

		//String url1 = "https://" +  id + ":" + paswd + "@" + url;

		driver.get(url);  
		driver.manage().window().maximize();
	}	



	// Test to add response
	@Test(priority=0)
	public void BA_Authorization() throws IOException, InterruptedException
	{
		log.debug("Inside open_CSA_Tab() function");

		Step04_BA_Auth baauth = new Step04_BA_Auth(driver); 
		baauth.login();
		baauth.BA_open_req_US();
		baauth.BA_auth1_US();
//		baauth.BA_auth2_US();
//		baauth.BA_auth3_US();
	}

}




