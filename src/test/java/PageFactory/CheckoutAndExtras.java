package PageFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import utility.utilities;

public class CheckoutAndExtras {

	/**
	 * 
	 * All WebElements are identified by @FindBy annotation
	 * 
	 */

	WebDriver driver;

	@FindBy(xpath = ".//span[text()='RECOMMENDED FOR YOU']")

	WebElement recommended;

	@FindBy(xpath = ".//div[@class='trips-basket trips-cnt']//span[text()='Check out']")

	WebElement continueBooking;

	@FindBy(xpath = ".//button[@class='popup-msg__button popup-msg__button--cancel']")

	List<WebElement> seatPopUp;
	
	/// Strings for the report
	
		String reportPassed = "Checkout and extras performed correctly";
		String reportFailed = "Checkout and extras failed";
		

	public CheckoutAndExtras(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	// Confirm got to Checkout and Extras Website

	public WebElement getcheckAndExtras() {
		utilities.waitForElement(recommended);
		return recommended;

	}

	// Proceed to the Checkout

	public void proceedToCheckout() {

		utilities.waitForElement(continueBooking);
		utilities.moveToElement(continueBooking);
		continueBooking.click();

	}

	// Select the no thanks in the Seats pop up if is displayed

	public void closePopUp() {
		if (seatPopUp.size() > 0) {
			utilities.waitForElement(seatPopUp.get(0));
			utilities.moveToElement(seatPopUp.get(0));
			seatPopUp.get(0).click();
		}

	}

	/**
	 * 
	 * Method for perform the Checkout and extras
	 * @throws Exception 
	 * 
	 * 
	 */

	public void selectExtrasAndCheckout() throws Exception {
		try {

			// Press the checkout Button

			this.proceedToCheckout();

			// Select the no thanks in the pop up
			this.closePopUp();

			// Log the info for the report
			utilities.logReports(reportPassed);
		} catch (ElementNotFoundException e) {
			// Log the info for the report
			utilities.logReports(reportFailed);
			throw (e);
		}

	}

}