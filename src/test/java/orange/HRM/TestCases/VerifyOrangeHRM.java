/**
 * 
 */
package orange.HRM.TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import configuration.Webdriver;
import orange.HRM.Pages.DashboardPage;
import orange.HRM.Pages.DirectoryPage;
import orange.HRM.Pages.LoginPage;
import orange.HRM.Pages.LogoutPage;
import utils.ExcelUtil;

/**
 * @author Mallesh
 *
 */
public class VerifyOrangeHRM 
{
	WebDriver driver;
	
	@BeforeTest
	public void InitializeWebDriverAndLaunchBrowser()
	{
		//Initializing the WebDriver, launching the browser and navigating to OrangeHRM site
		driver = Webdriver.InitializeWebDriver();
	}
	
	@Test
	public void VerifyOrangeHRMSearch() throws Exception
	{
		//Creating the objects for pages
		LoginPage login = new LoginPage(driver);
		DashboardPage dashboard = new DashboardPage(driver);
		LogoutPage logout = new LogoutPage(driver);
		DirectoryPage directory = new DirectoryPage(driver);

		//Excel utility to open the test data sheet
		ExcelUtil.ExcelOpen();
		
		//Getting the count of no. of rows and columns in test data sheet
		int noOfRows = ExcelUtil.rows;
		int noOfCols = ExcelUtil.cols;
	
		//Login to OrangeHRM with username and password
		login.loginToOrangeHRM("Admin","admin123");
		
		//Validating the dashboard page
		dashboard.verifyDashboardLogo();
		
		//Validating the directory page
		directory.verifyDirectoryLogo();

		//Searching all the names from test data sheet in the directory page and updating the search results in the same test data sheet
		for (int i=1;i<noOfRows;i++)
		{	
			String searchName = ExcelUtil.ExcelRead(i, 2);
			String srcRes = directory.searchDirectory(searchName);
			ExcelUtil.ExcelWrite(i, 3, srcRes);		//writing search result to test data sheet
			directory.clickReset();
		}
		ExcelUtil.ExcelQuit();		//closing the test data sheet
		
		//Logout from OrangeHRM
		logout.logoutOrangeHRM();
	}
	
	@AfterTest
	public void TerminateBrowser()
	{
		//Quit the WebDriver instance
		Webdriver.QuitWebDriver();
	}
}
