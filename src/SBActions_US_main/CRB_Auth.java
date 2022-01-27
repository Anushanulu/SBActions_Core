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

import SBActions_US.Step06_CRB_Auth;

import java.io.IOException;

import org.apache.log4j.Logger;

import lib.Excel;


public class CRB_Auth
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
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\SriSwathiAnushaNulu\\Documents\\Softwares\\geckodriver-v0.29.0-win64\\geckodriver.exe");
				
				//ProfilesIni ini = new ProfilesIni();
		    	//FirefoxProfile profile = ini.getProfile("default");
		    	//WebDriver driver =  new FirefoxDriver(profile);
					
		    	  driver = new FirefoxDriver();
			
				//System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
			    //driver = new ChromeDriver();
			    	
		        //id = Excel.getCellValue(xlsFilePath, sheet, 3, 0);
		   	    //paswd = Excel.getCellValue(xlsFilePath, sheet, 3, 1);
		     	url = Excel.getCellValue(xlsFilePath, sheet, 3, 2);
		    	
		    	//String url1 = "https://" +  id + ":" + paswd + "@" + url;
		    	      
		        driver.get(url);    
	  }	
		
		 // test to Login to the application as RIPC
		  
		 @Test(priority=0)
		  public void CRB_login() throws IOException, InterruptedException 
		  {
			  
			  Step06_CRB_Auth login = new Step06_CRB_Auth(driver);
			  login.login();
		  }
		  @Test(priority=1)
		  public void CRB_auth() 
		  {
			  
			  Step06_CRB_Auth crbauth = new Step06_CRB_Auth(driver);
			   crbauth.CRB_Auth();
		  }
		 
		 
			 
}
		  
	
