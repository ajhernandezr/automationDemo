package PageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constant;
import utility.ExcelUtils;
import utility.utilities;

public class BillingAdress {

	/**
	 * 
	 * All WebElements are identified by @FindBy annotation
	 * 
	 */

	WebDriver driver;

	@FindBy(xpath = ".//input[@name='billingAddressAddressLine1']")

	WebElement adress1;

	@FindBy(xpath = ".//input[@name='billingAddressAddressLine2']")

	WebElement adress2;

	@FindBy(xpath = ".//input[@name='billingAddressCity']")

	WebElement adressCity;

	@FindBy(xpath = ".//input[@name='billingAddressPostcode']")

	WebElement zipCode;

	@FindBy(id = "billingAddressCountry")

	WebElement countryDropwdown;

	@FindBy(xpath = ".//div/input[@name='acceptPolicy']")

	WebElement clickGeneralTerms;

	@FindBy(xpath = ".//button[@class='core-btn-primary core-btn-medium']")

	WebElement payNow;

	@FindBy(xpath = ".//prompt[@class='error prompt-text']/div/span")

	WebElement paymentError;
	
	/// Strings for the report
	
		String reportPassed = "Billing address section correctly";
		String reportFailed = "Billing address section failed";
		
	/**
	 * 
	 * Variables from excel
	 * 
	 */
	private static String sheet = "Booking";
	private static String inputAdress1;
	private static String inputAdress2;
	private static String inputAdressCity;
	private static String inputZipCode;
	private static String inputCountry;

	// Get all data from excel file and convert the arrays into singles strings
	public static void billingVariables(int testCounter) throws Exception {
		try {
			ExcelUtils.setExcelFile(Constant.Path_TestData, sheet);
			String billInformation = ExcelUtils.getCellData(testCounter, Constant.Col_billInformation, sheet);
			String[] billElements = billInformation.split(",");
			// If there was not data for payment, some default parameters will
			// be set
			if (billElements.length > 4 == true) {
				inputAdress1 = billElements[0];
				inputAdress2 = billElements[1];
				inputAdressCity = billElements[2];
				inputZipCode = billElements[3];
				inputCountry = billElements[4];

			} else {
				inputAdress1 = "test";
				inputAdress2 = "test";
				inputAdressCity = "test";
				inputZipCode = "22222";
				inputCountry = "Spain";

			}
		} catch (Exception e) {
			throw (e);
		}
	}

	public BillingAdress(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	// Selecting the Adress1

	public void fillAdress1() {
		utilities.waitForElement(adress1);
		utilities.moveToElement(adress1);
		utilities.smallPause();
		adress1.sendKeys(inputAdress1);

	}

	// Selecting the Adress2

	public void fillAdress2() {
		utilities.waitForElement(adress2);
		utilities.moveToElement(adress2);
		utilities.smallPause();
		adress2.sendKeys(inputAdress2);

	}

	// Selecting the City

	public void selectCity() {
		utilities.waitForElement(adressCity);
		utilities.moveToElement(adressCity);
		utilities.smallPause();
		adressCity.sendKeys(inputAdressCity);

	}

	// Selecting the Zip

	public void fillTheZip() {
		utilities.waitForElement(zipCode);
		utilities.moveToElement(zipCode);
		utilities.smallPause();
		zipCode.sendKeys(inputZipCode);

	}

	// Select the country

	public void selectCountry() {
		utilities.waitForElement(countryDropwdown);
		Select selectTitle = new Select(countryDropwdown);
		selectTitle.selectByVisibleText(inputCountry);

	}

	// Click the General terms

	public void clickGeneralTerms() {
		try {
			// Using JavascriptExecutor because the element is overlapped by
			// another
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", clickGeneralTerms);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Click the pay now button

	public void clickPayNow() {
		utilities.waitForElement(payNow);
		utilities.moveToElement(payNow);
		utilities.smallPause();
		payNow.click();

	}

	// Confirm got the invalid payment message

	public WebElement getPaymentErrorMessage() {
		utilities.waitForElement(paymentError);
		return paymentError;

	}

	/**
	 * 
	 * Method for Bill adress
	 * 
	 * @param testName
	 * @throws Exception
	 * 
	 * 
	 */

	public void fillBillAdress(int testCounter) throws Exception {
		try {

			// Set all the variables from the excel file

			BillingAdress.billingVariables(testCounter);

			// Selecting the Adress1

			this.fillAdress1();

			// Selecting the Adress2
			this.fillAdress2();

			// Selecting the City
			this.selectCity();

			// Selecting the Zip
			this.fillTheZip();

			// Selecting the country
			this.selectCountry();

			// Click the General terms
			this.clickGeneralTerms();

			// Click the pay now button
			this.clickPayNow();

			// Log the info for the report
			utilities.logReports(reportPassed);

		} catch (Exception e) {
			// Log the info for the report
			utilities.logReports(reportFailed);
			throw (e);
		}

	}

}