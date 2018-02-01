package PageFactory;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import utility.Constant;
import utility.ExcelUtils;
import utility.utilities;

public class PassengerDetails {

	/**
	 * 
	 * All WebElements are identified by @FindBy annotation
	 * 
	 */

	WebDriver driver;

	@FindBy(xpath = ".//h2[text()='Passenger details']")

	WebElement passengerDetails;

	@FindBy(xpath = ".//div[@class='pax-form-element']//select")

	List<WebElement> title;

	@FindBy(xpath = ".//label[text()='First name']/following-sibling::input")

	List<WebElement> passengerFirstName;

	@FindBy(xpath = ".//label[text()='Surname']/following-sibling::input")

	List<WebElement> passengerSurname;
	
	/// Strings for the report
	
		String reportPassed = "Passenger details performed correctly";
		String reportFailed = "Passenger details failed";

	/**
	 * 
	 * Variables from excel
	 * 
	 */
	private static String sheet = "Booking";
	private static ArrayList<String> titleList = new ArrayList<String>();
	private static ArrayList<String> surnameList = new ArrayList<String>();;
	private static ArrayList<String> nameList = new ArrayList<String>();;

	// Get all data from excel file and convert the arrays into singles strings
	public static void passengerVariables(int testCounter) throws Exception {
		try {
			ExcelUtils.setExcelFile(Constant.Path_TestData, sheet);

			String passengerParameters = ExcelUtils.getCellData(testCounter, Constant.Col_passengersDetails, sheet);
			String[] departureDateElements = passengerParameters.split(",");
			int size = departureDateElements.length;
			// Add the passenger titles
			for (int counter = 0; counter < size; counter = counter + 3) {
				titleList.add(departureDateElements[counter]);
			}

			// Add the passenger names
			for (int counter = 1; counter < size; counter = counter + 3) {
				nameList.add(departureDateElements[counter]);
			}
			// Add the passenger surnames
			for (int counter = 2; counter < size; counter = counter + 3) {
				surnameList.add(departureDateElements[counter]);
			}
		} catch (Exception e) {
			throw (e);
		}
	}

	public PassengerDetails(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	// Confirm got to Details and payment page

	public WebElement getDetailsAndPaymentPage() {
		utilities.waitForElement(passengerDetails);
		return passengerDetails;

	}

	// Selecting the Passenger Title

	public void selectPassengerTitle() {
		utilities.waitForElement(title.get(0));
		utilities.smallPause();
		for (int i = 0; i < title.size(); i++) {
			Select selectTitle = new Select(title.get(i));
			selectTitle.selectByVisibleText(titleList.get(i).toString());
		}
	}

	// Selecting the Passenger First Name

	public void fillPassengerName() throws Exception {
		try {
			utilities.waitForElement(passengerFirstName.get(0));
			for (int i = 0; i < passengerFirstName.size(); i++) {
				passengerFirstName.get(i).sendKeys(nameList.get(i));
			}
		} catch (Exception e) {
			throw (e);
		}

	}

	// Selecting the Passenger Surname

	public void fillPassengerSurname() {
		utilities.waitForElement(passengerSurname.get(0));
		for (int i = 0; i < passengerSurname.size(); i++) {
			passengerSurname.get(i).sendKeys(surnameList.get(i));

		}

	}

	/**
	 * 
	 * Method for Fill the passenger details
	 * 
	 * @throws Exception
	 * 
	 * 
	 */

	public void fillDetails(int testCounter) throws Exception {
		try {

			// Select the passenger variables
			PassengerDetails.passengerVariables(testCounter);

			// Select the passenger title
			this.selectPassengerTitle();

			// Selecting the Passenger First Name
			this.fillPassengerName();

			// Selecting the Passenger Surname
			this.fillPassengerSurname();
			// Log the info for the report
			utilities.logReports(reportPassed);

		} catch (Exception e) {
			// Log the info for the report
			utilities.logReports(reportFailed);
			throw (e);
		}
	}

}