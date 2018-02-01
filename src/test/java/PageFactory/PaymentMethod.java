package PageFactory;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constant;
import utility.ExcelUtils;
import utility.utilities;

public class PaymentMethod {

	/**
	 * 
	 * All WebElements are identified by @FindBy annotation
	 * 
	 */

	WebDriver driver;

	@FindBy(xpath = ".//label[text()='Card number']/following-sibling::input")

	WebElement inputCardNumber;

	@FindBy(xpath = ".//div[@class='core-select card-type-select']/select")

	WebElement inputCardType;

	@FindBy(xpath = ".//div[@class='date-expiry date-expiry-left']//select")

	WebElement cardExpiryMonth;

	@FindBy(xpath = ".//div[@class='date-expiry date-expiry-right']//select")

	WebElement cardExpiryYear;

	@FindBy(xpath = ".//div[@class='card-security-code']//input")

	WebElement cardSecurityCode;

	@FindBy(xpath = ".//input[@name='cardHolderName']")

	WebElement cardHolderName;

	/// Strings for the report

	String reportPassed = "Payment method performed correctly";
	String reportFailed = "Payment method failed";

	/**
	 * 
	 * Variables from excel
	 * 
	 */
	private static String sheet = "Booking";
	private static String cardNumber;
	private static String cardType;
	private static String expiryMonth;
	private static String expiryYear;
	private static String securityCode;
	private static String cardOwner;

	// Get all data from excel file and convert the arrays into singles strings
	public static void paymentVariables(int testCounter) throws Exception {
		try {
			ExcelUtils.setExcelFile(Constant.Path_TestData, sheet);
		
			String paymentInformation = ExcelUtils.getCellData(testCounter, Constant.Col_paymentInformation, sheet);
			String[] paymentElements = paymentInformation.split(",");
			// If there was not data for payment, some default parameters will
			// be set
			if (paymentElements.length > 5 == true) {
				cardNumber = paymentElements[0];
				cardType = paymentElements[1];
				expiryMonth = paymentElements[2];
				expiryYear = paymentElements[3];
				securityCode = paymentElements[4];
				cardOwner = paymentElements[5];
			} else {
				cardNumber = "5555555555555557";
				cardType = "MasterCard";
				expiryMonth = "12";
				expiryYear = "2022";
				securityCode = "123";
				cardOwner = "TEST";

			}
		} catch (Exception e) {
			throw (e);
		}
	}

	public PaymentMethod(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	// Selecting the Card Number

	public void fillCardNumber() {
		utilities.waitForElement(inputCardNumber);
		utilities.moveToElement(inputCardNumber);
		utilities.smallPause();
		inputCardNumber.sendKeys(cardNumber);

	}

	// Selecting the Card type

	public void selectCardType() {
		utilities.waitForElement(inputCardType);
		Select selectTitle = new Select(inputCardType);
		selectTitle.selectByVisibleText(cardType);

	}

	// Selecting the Card expiry month

	public void selectCardExpiryMonth() {
		utilities.waitForElement(cardExpiryMonth);
		Select selectTitle = new Select(cardExpiryMonth);
		selectTitle.selectByVisibleText(expiryMonth);

	}

	// Selecting the Card expiry Year

	public void selectCardExpiryYear() {
		utilities.waitForElement(cardExpiryYear);
		Select selectTitle = new Select(cardExpiryYear);
		selectTitle.selectByVisibleText(expiryYear);

	}

	// Fill the security code

	public void fillCardCode() {
		utilities.waitForElement(cardSecurityCode);
		utilities.moveToElement(cardSecurityCode);
		utilities.smallPause();
		cardSecurityCode.sendKeys(securityCode);
	}

	// Fill the Card holder

	public void fillCardHolder() {
		utilities.waitForElement(cardHolderName);
		utilities.moveToElement(cardHolderName);
		cardHolderName.sendKeys(cardOwner);

	}

	/**
	 * 
	 * Method for Fill the card details
	 * 
	 * @param testName
	 * @throws Exception
	 * 
	 * 
	 */

	public void fillCardDetails(int testCounter) throws Exception {
		try {
			// Set all the variables from the excel file

			PaymentMethod.paymentVariables(testCounter);
			// Selecting the Card Number
			this.fillCardNumber();

			// Selecting the Card type
			this.selectCardType();

			// Selecting the Card expiry month
			this.selectCardExpiryMonth();

			// Selecting the Card expiry Year
			this.selectCardExpiryYear();

			// Fill the security code
			this.fillCardCode();

			// Fill the Card holder
			this.fillCardHolder();
			// Log the info for the report
			utilities.logReports(reportPassed);

		} catch (Exception e) {
			// Log the info for the report
			utilities.logReports(reportFailed);
			throw (e);
		}

	}

}