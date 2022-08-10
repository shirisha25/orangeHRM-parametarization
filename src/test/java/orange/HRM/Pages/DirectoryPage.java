/**
 * 
 */
package orange.HRM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * @author Mallesh
 * 
 * This class will store all the locators and methods of directory page
 *
 */
public class DirectoryPage 
{
WebDriver driver;
	
	By directoryLink = By.id("menu_directory_viewDirectory");
	By logoDirectory = By.cssSelector(".head>h1");
	By searchNameField = By.id("searchDirectory_emp_name_empName");
	By searchButton = By.id("searchBtn");
	By resultsListName = By.xpath("//*[@id=\"resultTable\"]//b");
	By noRecordsFound = By.id("divNoResults");
	By resetButton = By.id("resetBtn");
	
	public DirectoryPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void verifyDirectoryLogo()
	{
		driver.findElement(directoryLink).click();
		String logoText = driver.findElement(logoDirectory).getText();
		Assert.assertEquals(logoText, "Search Directory");
	}
	
	public String searchDirectory(String srcStr) throws Exception
	{
		String returnStr = null;
		driver.findElement(searchNameField).sendKeys(srcStr);
		driver.findElement(searchButton).click();
		Thread.sleep(5000);
		
		if (driver.findElements(resultsListName).size()!=0)
		{
			String searchResultName = driver.findElement(resultsListName).getText();
			returnStr = "Found";
		}
		else if (driver.findElements(noRecordsFound).size()!=0)
		{
			returnStr = "Not Found";
		}
		return returnStr;
		
		
		
	}
	
	public void clickReset()
	{
		driver.findElement(resetButton).click();
	}
}
