package SBActions_US_main;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import SBActions_US.Step05_SBAuth_Core;
import lib.Excel;


public class SB_AllActions
{
	// TestNG logger

	
	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	public String sheet="Login"; 
	public String url;
	public String id;
	public String paswd;
	public String id_green;
	public String paswd_green;
	public String url_green;
	public String url2;

	public WebDriver driver;


	@BeforeTest
	public void setup()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\SriSwathiAnushaNulu\\Documents\\Softwares\\geckodriver-v0.29.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();

		//id = Excel.getCellValue(xlsFilePath, sheet, 5, 0);
		//paswd = Excel.getCellValue(xlsFilePath, sheet, 5, 1);
		url = Excel.getCellValue(xlsFilePath, sheet, 5, 2);

		//String url1 = "https://" +  id + ":" + paswd + "@" + url;

		driver.get(url);  
		driver.manage().window().maximize();
	}	


	//Test for Skill Request page
	@Test(priority=1)
	public void SB_auth() throws IOException, InterruptedException 
	{

		Step05_SBAuth_Core sb= new Step05_SBAuth_Core (driver);
		sb.login();
		sb.SB_open_Request();
		//sb.SB_Auth_req();
		//sb.SB_Rej_req();
		sb.SB_Return_req();
		
		
	}




}

