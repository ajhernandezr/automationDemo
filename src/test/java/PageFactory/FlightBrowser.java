package PageFactory;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import utility.Constant;
import utility.ExcelUtils;
import utility.utilities;

public class FlightBrowser {

	/**
	 * 
	 * All WebElements are identified by @FindBy annotation
	 * 
	 */

	WebDriver driver;

	@FindBy(xpath = ".//label[@class='flights']")

	WebElement flights;

	@FindBy(xpath = ".//input[@placeholder='Departure airport']")

	WebElement departureAirport;

	@FindBy(xpath = ".//input[@placeholder='Destination airport']")

	WebElement destinationAirport;

	@FindBy(xpath = ".//div[@aria-labelledby='label-airport-selector-from']")

	WebElement fromText;

	@FindBy(xpath = ".//div[@aria-labelledby='label-airport-selector-to']")

	WebElement toText;

	@FindBy(xpath = ".//div[@class='col-cal-from']//input[@name='dateInput0']")

	WebElement departureDay;

	@FindBy(xpath = ".//div[@class='col-cal-from']//input[@name='dateInput1']")

	WebElement departureMonth;

	@FindBy(xpath = ".//div[@class='col-cal-from']//input[@name='dateInput2']")

	WebElement departureYear;

	@FindBy(xpath = ".//div[@class='col-cal-to']//input[@name='dateInput0']")

	WebElement returnDay;

	@FindBy(xpath = ".//div[@class='col-cal-to']//input[@name='dateInput1']")

	WebElement returnMonth;

	@FindBy(xpath = ".//div[@class='col-cal-to']//input[@name='dateInput2']")

	WebElement returnYear;

	@FindBy(xpath = ".//div[@label='Passengers:']//div[@class='dropdown-handle']")

	WebElement passengersDropdown;

	@FindBy(xpath = ".//div[@label='Adults']//button[@class='core-btn inc core-btn-wrap']")

	WebElement adultIncrement;

	@FindBy(xpath = ".//div[@label='Teens']//button[@class='core-btn inc core-btn-wrap']")

	WebElement teenIncrement;

	@FindBy(xpath = ".//div[@label='Children']//button[@class='core-btn inc core-btn-wrap']")

	WebElement childrenIncrement;

	@FindBy(xpath = ".//div[@label='Infants']//button[@class='core-btn inc core-btn-wrap']")

	WebElement infantIncrement;

	@FindBy(xpath = ".//span[@class='terms-conditions-checkbox-span']")

	WebElement terms;

	@FindBy(xpath = ".//button/span[@translate='common.buttons.lets_go']")

	WebElement letsgo;

	/// Strings for the report

	String reportPassed = "Flight Browser performed correctly";
	String reportFailed = "Flight Browser performed failed";

	/**
	 * 
	 * Variables from excel
	 * 
	 */
	private static String sheet = "Booking";
	private static String departure;
	private static String destination;
	private static String departureDate;
	private static String departureDayDate;
	private static String departureMonthDate;
	private static String departureYearDate;
	private static String returnDate;
	private static String returnDayDate;
	private static String returnMonthDate;
	private static String returnYearDate;
	private static String passengersList;
	private static int adultPassengers;
	private static int teenPassengers;
	private static int childrenPassengers;
	private static int infantPassengers;

	// Get all data from excel file and convert the arrays into singles strings
	public static void bookingVariables(int testCounter) throws Exception {
		ExcelUtils.setExcelFile(Constant.Path_TestData, sheet);
		
		departure = ExcelUtils.getCellData(testCounter, Constant.Col_departure, sheet);
		destination = ExcelUtils.getCellData(testCounter, Constant.Col_destination, sheet);
		departureDate = ExcelUtils.getCellData(testCounter, Constant.Col_departureDate, sheet);
		String[] departureDateElements = departureDate.split(",");
		departureDayDate = departureDateElements[0];
		departureMonthDate = departureDateElements[1];
		departureYearDate = departureDateElements[2];

		returnDate = ExcelUtils.getCellData(testCounter, Constant.Col_returnDate, sheet);
		String[] returnDateElements = returnDate.split(",");
		returnDayDate = returnDateElements[0];
		returnMonthDate = returnDateElements[1];
		returnYearDate = returnDateElements[2];

		passengersList = ExcelUtils.getCellData(testCounter, Constant.Col_passengers, sheet);
		String[] passengersListElements = passengersList.split(",");
		int valueAdult = 0;
		int valueTeen = 1;
		int valueChildren = 2;
		int valueInfant = 3;
		int size = passengersListElements.length;

		if (valueAdult > size) {
			adultPassengers = 0;
		} else {
			adultPassengers = Integer.parseInt(passengersListElements[valueAdult]);
		}
		if (valueTeen >= size) {
			teenPassengers = 0;
		} else {
			teenPassengers = Integer.parseInt(passengersListElements[valueTeen]);
		}
		if (valueChildren >= size) {
			childrenPassengers = 0;
		} else {
			childrenPassengers = Integer.parseInt(passengersListElements[valueChildren]);
		}
		if (valueInfant >= size) {
			infantPassengers = 0;
		} else {
			infantPassengers = Integer.parseInt(passengersListElements[valueInfant]);
		}

	}

	public FlightBrowser(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	// Click on the flight Browser

	public void clickFlightsBrowser() {

		utilities.waitForElement(flights);
		flights.click();

	}

	// Clear departure airport

	public void clearDeparture() {
		utilities.waitForElement(departureAirport);
		departureAirport.clear();

	}

	// Set departure airport

	public void setDeparture(String departure) {
		utilities.waitForElement(departureAirport);
		departureAirport.sendKeys(departure);
		departureAirport.sendKeys(Keys.ENTER);
		utilities.smallPause();

	}

	// Set destination airport

	public void setDestination(String destination) {
		utilities.waitForElement(destinationAirport);
		destinationAirport.sendKeys(destination);
		destinationAirport.sendKeys(Keys.ENTER);
		utilities.smallPause();

	}

	// Set departure date

	public void departureDate(String departureDayDate, String departureMonthDate, String departureYearDate) {
		utilities.waitForElement(departureDay);
		departureDay.sendKeys(departureDayDate);
		departureMonth.sendKeys(departureMonthDate);
		departureYear.sendKeys(departureYearDate);

	}
	// Set return date

	public void returnDate(String returnDayDate, String returnMonthDate, String returnYearDate) {
		utilities.waitForElement(returnDay);
		returnDay.sendKeys(returnDayDate);
		returnMonth.sendKeys(returnMonthDate);
		returnYear.sendKeys(returnYearDate);

	}

	// Select the number of passengers

	public void selectPassengers(int adultPassengers, int teenPassengers, int childrenPassengers, int infantPassengers)
			throws Exception {
		utilities.waitForElement(passengersDropdown);
		passengersDropdown.click();
		utilities.waitForElement(adultIncrement);
		for (int i = 1; i < adultPassengers; i++) {
			utilities.pushDificultElements(driver, adultIncrement);

		}
		for (int i = 0; i < teenPassengers; i++) {
			utilities.pushDificultElements(driver, teenIncrement);

		}

		for (int i = 0; i < childrenPassengers; i++) {
			utilities.pushDificultElements(driver, childrenIncrement);

		}
		for (int i = 0; i < infantPassengers; i++) {
			utilities.pushDificultElements(driver, infantIncrement);

		}
		passengersDropdown.click();
	}

	// Click on TermsAndConditions

	public void clickTerms() {
		utilities.moveToElement(terms);
		terms.click();

	}

	// Click on Lets go

	public void clickletsgo() {
		utilities.moveToElement(letsgo);
		letsgo.click();

	}

	/**
	 * 
	 * This POM method will be used for find a flight
	 * 
	 * @param testName
	 * @throws Exception
	 * 
	 * 
	 */

	public void findAflight(int testCounter) throws Exception {

		try {

			// Set all the variables from the excel file

			FlightBrowser.bookingVariables(testCounter);
			// Click on the flight Browser

			this.clickFlightsBrowser();

			// Clear departure airport

			this.clearDeparture();

			// Set departure airport

			this.setDeparture(departure);

			// Set destination airport

			this.setDestination(destination);

			// Set departure date
			this.departureDate(departureDayDate, departureMonthDate, departureYearDate);

			// Set return date

			this.returnDate(returnDayDate, returnMonthDate, returnYearDate);

			// Select the number of passengers

			this.selectPassengers(adultPassengers, teenPassengers, childrenPassengers, infantPassengers);

			// Click on TermsAndConditions

			this.clickTerms();

			// Click on Lets go
			this.clickletsgo();
			utilities.logReports(reportPassed);

		} catch (Exception e) {
			// Log the info for the report
			utilities.logReports(reportFailed);
			throw (e);
		}
	}

}