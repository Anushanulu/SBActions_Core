
package SBActions_US_main;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import SBActions_US.Step02_AddResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import lib.Excel;

public class AddResponse
{
	// TestNG logger

	public static Logger log = Logger.getLogger("TnM");

	public static String xlsFilePath =System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
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

		//id = Excel.getCellValue(xlsFilePath, sheet, 2, 0);
		//paswd = Excel.getCellValue(xlsFilePath, sheet, 2, 1);
		url = Excel.getCellValue(xlsFilePath, sheet, 2, 2);

		//String url1 = "https://" +  id + ":" + paswd + "@" + url;

		driver.get(url);  
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}	



	// Test to add response
	@Test(priority=0)
	public void RIPC_Login() throws IOException, InterruptedException 
	{

		Step02_AddResponse addresp = new Step02_AddResponse(driver); 
	    addresp.login();
	}
	@Test(priority=1)
	public void search_request() throws InterruptedException 
	{

		Step02_AddResponse search = new Step02_AddResponse(driver); 
		search.openRequest();
		//search.waitForFormOverlayDisappear();
	}
	@Test(priority=2)
 	public void create_res() throws InterruptedException, IOException 
 	{

		Step02_AddResponse btnclick = new Step02_AddResponse(driver);
	  btnclick.buttonClick();
 	}
	
  @Test(priority=3)
 	public void add_Attachement() 
 	{

	  Step02_AddResponse attachemnt = new Step02_AddResponse(driver); 
	  attachemnt.addAttachement();
 	}
  @Test(priority=4)
	public void fill_resdetails() throws IOException, InterruptedException  
	{
	  Step02_AddResponse resdetails = new Step02_AddResponse(driver); 
	  resdetails.resDetails("fresp", "mresp", "lresp");
	  //resdetails.resDetails();
	}
  @Test(priority=5)
	public void fill_resAttrdetails() throws IOException, InterruptedException  
	{
	  Step02_AddResponse resattrs = new Step02_AddResponse(driver); 
	  resattrs.resAttributeDetails();
	}
  @Test(priority=6)
	public void fill_ratesdetails() throws IOException, InterruptedException  
	{
	  Step02_AddResponse rates = new Step02_AddResponse(driver); 
	  rates.resRates("100","100","100");
	}
  @Test(priority=7)
	public void save_Form() throws IOException, InterruptedException  
	{
	  Step02_AddResponse save = new Step02_AddResponse(driver); 
	  save.saveForm();
	}
}
