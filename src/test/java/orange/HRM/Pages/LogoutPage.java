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
 * This class will store all the locators and methods of logout page
 */
public class LogoutPage 
{
	WebDriver driver;
	
	By logoutMenu = By.id("welcome");
	By logoutBtn = By.linkText("Logout");
	
	public LogoutPage(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	public void logoutOrangeHRM()
	{
		
		driver.findElement(logoutMenu).click();
		driver.findElement(logoutBtn).click();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("login"));
	}
}
