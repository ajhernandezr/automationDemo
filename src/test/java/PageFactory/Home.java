package PageFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import utility.utilities;

public class Home {

	WebDriver driver;

	@FindBy(id = "home")

	WebElement homePageElement;

	@FindBy(xpath = "//div[@class='cookie-popup__close-btn']//core-icon[@icon-id='glyphs.close']")

	List<WebElement> closeCookies;
	
	/// Strings for the report
	
	String reportPassed = "Get to home correctly";
	String reportFailed = "Get to home failed";

	public Home(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	// Close the cookies banner

	public void closeCookiesBanner() {
		if (closeCookies.size() > 0) {
			closeCookies.get(0).click();

		}

	}

	// Confirm got to Ryanair website

	public WebElement getHomePage() {

		utilities.waitForElement(homePageElement);
		return homePageElement;

	}

	/**
	 * 
	 * This POM method will be used for get to home
	 * 
	 * @param testName
	 * @throws Exception
	 * 
	 * 
	 */

	public void getToHome() throws Exception {

		try {
			
			// Close the cookies banner
			this.closeCookiesBanner();
			
			// Log the info for the report
			utilities.logReports(reportPassed);
			

		} catch (Exception e) {
			// Log the info for the report
			utilities.logReports(reportFailed);
			throw (e);
		}
	}

}