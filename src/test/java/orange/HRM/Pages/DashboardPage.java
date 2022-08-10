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
 * This class will store all the locators and methods of dashboard page
 *
 */
public class DashboardPage 
{
	WebDriver driver;
	
	By logo = By.xpath("//*[@class=\"head\"]/h1");
	
	public DashboardPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void verifyDashboardLogo()
	{
		String logoText = driver.findElement(logo).getText();
		Assert.assertEquals(logoText, "Dashboard");
	}
	
}
