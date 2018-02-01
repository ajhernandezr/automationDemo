package PageFactory;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import utility.Constant;
import utility.ExcelUtils;
import utility.utilities;

public class SignUp {

	/**
	 * 
	 * All WebElements are identified by @FindBy annotation
	 * 
	 */

	WebDriver driver;

	@FindBy(xpath = ".//button/span[text()='Log in']")

	WebElement SignUp;

	@FindBy(xpath = ".//span[text()='Please sign up or log into']")

	WebElement signOrLog;

	@FindBy(xpath = ".//input[@type='email']")

	WebElement emailTextField;

	@FindBy(xpath = ".//input[@type='password']")

	WebElement passwordTextField;

	@FindBy(xpath = ".//div[@class='form-field password']/following-sibling::div//button[@class='core-btn-primary']")

	WebElement signButton;
	
	/// Strings for the report
	
		String reportPassed = "Perform sign up correctly";
		String reportFailed = "Perform sign up failed";

	/**
	 * 
	 * Variables from excel
	 * 
	 */

	private static String sheet = "General";
	private static String user;
	private static String password;

	// Get all data from excel file and conver the arrays into singles strings
	public void SignVariables(int testCounter) throws Exception {
		ExcelUtils.setExcelFile(Constant.Path_TestData, sheet);
	

		user = ExcelUtils.getCellData(testCounter, Constant.Col_User, sheet);
		password = ExcelUtils.getCellData(testCounter, Constant.Col_Password, sheet);

	}

	public SignUp(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	// Confirm got to Sign or Log page

	public WebElement getSignLog() {
		utilities.waitForElement(signOrLog);
		return signOrLog;

	}

	// Press the SignUp Button

	public void pressSignUp() {

		utilities.waitForElement(SignUp);
		SignUp.click();

	}

	// Input the email

	public void inputMail(String mail) {

		utilities.waitForElement(emailTextField);
		emailTextField.sendKeys(mail);

	}

	// Input the password

	public void inputPassword(String password) {

		utilities.waitForElement(passwordTextField);
		passwordTextField.sendKeys(password);

	}

	// Press the Sign up button

	public void pressSignUpButton() {

		utilities.waitForElement(signButton);
		signButton.click();

	}

	/**
	 * 
	 * Method for perform the Login
	 * 
	 * @throws Exception
	 * 
	 * 
	 */

	public void performSignUp(int testCounter) throws Exception {
		try {

			// Set all the variables from the excel

			this.SignVariables(testCounter);

			// Press the SignUp Button
			this.pressSignUp();

			// Input the email
			this.inputMail(user);

			// Input the password
			this.inputPassword(password);

			// Press the Sign up button
			this.pressSignUpButton();

			// Log the info for the report
			utilities.logReports(reportPassed);

		} catch (Exception e) {
			// Log the info for the report
			utilities.logReports(reportFailed);
			throw (e);
		}

	}

}