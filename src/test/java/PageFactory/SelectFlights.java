package PageFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import utility.utilities;

public class SelectFlights {

	/**
	 * 
	 * All WebElements are identified by @FindBy annotation
	 * 
	 */

	WebDriver driver;

	@FindBy(id = "booking-results")

	WebElement bookingResults;

	@FindBy(xpath = ".//flight-list[@id='outbound']//div[@class='flight-header__min-price hide-mobile']")

	List<WebElement> outboundFlights;

	@FindBy(xpath = ".//flight-list[@id='inbound']//div[@class='flight-header__min-price hide-mobile']")

	List<WebElement> inboundFlights;

	@FindBy(xpath = ".//*[@id='outbound']//div[@class='flights-table-fares__head']/span[text()='Standard fare']")

	WebElement outboundStandardFare;

	@FindBy(xpath = ".//*[@id='inbound']//div[@class='flights-table-fares__head']/span[text()='Standard fare']")

	WebElement inboundStandardFare;

	@FindBy(xpath = ".//div[@class='trips-basket trips-cnt']//span[text()='Continue']")

	WebElement continueBooking;
	
	// Strings for the report
	
		String reportPassed = "Select flights performed correctly";
		String reportFailed = "Select flights failed";

	public SelectFlights(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	// Confirm got to Select flight section

	public WebElement getFlightPage() {
		utilities.waitForElement(bookingResults);
		return bookingResults;

	}

	// Select the first available outbound flight

	public void chooseOutboundFlight() {
		utilities.waitForElement(outboundFlights.get(0));
		outboundFlights.get(0).click();
	}

	// Select the Standard fee for outbound flight

	public void selectStandardFeeOutbound() {

		utilities.waitForElement(outboundStandardFare);
		utilities.moveToElement(outboundStandardFare);
		utilities.smallPause();

		try {
			utilities.pushDificultElements(driver, outboundStandardFare);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Select the first available inbound flight

	public void chooseInboundFlight() {

		utilities.waitForElement(inboundFlights.get(0));
		utilities.moveToElement(inboundFlights.get(0));
		utilities.smallPause();
		try {
			utilities.pushDificultElements(driver, inboundFlights.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Select the Standard fee for inbound flight

	public void selectStandardFeeInbound() {
		utilities.waitForElement(inboundStandardFare);
		utilities.moveToElement(inboundStandardFare);
		utilities.smallPause();
		try {
			utilities.pushDificultElements(driver, inboundStandardFare);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Wait and push the Continue

	public void continueBooking() {
		utilities.waitForElement(continueBooking);
		utilities.moveToElement(continueBooking);
		utilities.smallPause();
		continueBooking.click();

	}

	/**
	 * 
	 * Method for Booking the Outbound and Inbound flight
	 * @throws Exception 
	 * 
	 * 
	 */

	public void bookAFlight() throws Exception {
		try {

			// Select the first available outbound flight

			this.chooseOutboundFlight();

			// Select the Standard fee for outbound flight

			this.selectStandardFeeOutbound();

			// Select the first available inbound flight

			this.chooseInboundFlight();

			// Select the Standard fee for inbound flight

			this.selectStandardFeeInbound();

			// Wait and push the Continue

			this.continueBooking();

			// Log the info for the report
			utilities.logReports(reportPassed);

		} catch (Exception e) {
			// Log the info for the report
			utilities.logReports(reportFailed);
			throw (e);
		}

	}

}