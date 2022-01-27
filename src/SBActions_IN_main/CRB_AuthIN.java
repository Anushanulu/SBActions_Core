package SBActions_IN_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import SBActions_IN.Step05_CRB_Auth_IN;

import org.apache.log4j.Logger;

import lib.Excel;


public class CRB_AuthIN
{
	// TestNG logger

	public static Logger log = Logger.getLogger("TnM");

	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	public String sheet="Login"; 
	public String url;
	public String id;
	public String paswd;

	WebDriver driver;


	@BeforeTest
	public void setup()
	{
//		System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
//		driver = new ChromeDriver();
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\SriSwathiAnushaNulu\\Documents\\Softwares\\geckodriver-v0.29.0-win64\\geckodriver.exe");

		driver = new FirefoxDriver();

		// System.setProperty("webdriver.gecko.driver", "C:\\Users\\IBM_ADMIN\\Desktop\\Selenium\\Jar files for Selenium\\geckodriver-v0.19.1-win64\\geckodriver.exe");



		// driver = new FirefoxDriver();

		//System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();

		//id = Excel.getCellValue(xlsFilePath, sheet, 15, 0);
		//paswd = Excel.getCellValue(xlsFilePath, sheet, 15, 1);
		url = Excel.getCellValue(xlsFilePath, sheet, 15, 2);

		//String url1 = "https://" +  id + ":" + paswd + "@" + url;

		driver.get(url);    
		driver.manage().window().maximize();
	}	

	// test to Login to the application as RIPC


	@Test(priority=0)
	public void CRB_Wlcmpage() throws InterruptedException 
	{

		Step05_CRB_Auth_IN crblogin = new Step05_CRB_Auth_IN(driver);
		crblogin.login();
	}
	@Test(priority=1)
	public void CRB_auth() 
	{

		Step05_CRB_Auth_IN auth = new Step05_CRB_Auth_IN(driver);
		auth.crb_auth();
	}

}


