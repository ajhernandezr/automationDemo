package Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.ITestContext;
import PageFactory.BillingAdress;
import PageFactory.CheckoutAndExtras;
import PageFactory.PassengerDetails;
import PageFactory.FlightBrowser;
import PageFactory.Home;
import PageFactory.PaymentMethod;
import PageFactory.SelectFlights;
import PageFactory.SignUp;
import utility.Constant;
import utility.ExcelUtils;
import utility.utilities;

public class DeclinedPaymentOnBooking {

	WebDriver driver;

	FlightBrowser objFlightBrowser;

	Home objHomePage;

	SelectFlights objbookFlight;

	CheckoutAndExtras objcheckAndExtras;

	SignUp objlogApp;

	PassengerDetails objdetailsFormulary;

	PaymentMethod objpaymentSection;

	BillingAdress objfillBill;

	private String testName;
	private String sheet = "General";
	private String Browser;
	private int sTestCase;
	@BeforeTest
	public void setup(String excelTestName, int testCounter) throws Exception {
		
		
		// We got the path to excel document and the sheet
		ExcelUtils.setExcelFile(Constant.Path_TestData, sheet);

		Browser = ExcelUtils.getCellData(testCounter, Constant.Col_Browser, sheet);
		utilities.selectBrowser(Browser);
		this.driver = utilities.driver;
		// Get the test name
		 this.testName = excelTestName;
		// Start the report
		utilities.runReports(testName,testCounter);

	}

	@Test()

	public void DeclinedPaymentOnBookingTest(int testCounter) throws Exception {

		
		// Create Home Page object

		objHomePage = new Home(driver);

		// Verify Home page is displayed

		AssertJUnit.assertTrue(objHomePage.getHomePage().isDisplayed());

		// Perform Home operations

		objHomePage.getToHome();

		// Create Select flight Page object

		objFlightBrowser = new FlightBrowser(driver);

		// Perform Flight Browser operations

		objFlightBrowser.findAflight(testCounter);

		// Create Book flight Page object

		objbookFlight = new SelectFlights(driver);

		// Verify Booking page is displayed

		AssertJUnit.assertTrue(objbookFlight.getFlightPage().isDisplayed());

		// Perform Flight Booking operations

		objbookFlight.bookAFlight();

		// Create Checkout and Extras Page object

		objcheckAndExtras = new CheckoutAndExtras(driver);

		// Verify Booking Checkout and extras Page is displayed

		AssertJUnit.assertTrue(objcheckAndExtras.getcheckAndExtras().isDisplayed());

		// Perform Checkout and Extras operations

		objcheckAndExtras.selectExtrasAndCheckout();

		// Create Sign Up page

		objlogApp = new SignUp(driver);

		// Verify Sign Up page is displayed

		AssertJUnit.assertTrue(objlogApp.getSignLog().isDisplayed());

		// Perform Sign Up operations

		objlogApp.performSignUp(testCounter);

		// Create Details and Payment page

		objdetailsFormulary = new PassengerDetails(driver);

		// Verify Details and payment page is displayed

		AssertJUnit.assertTrue(objdetailsFormulary.getDetailsAndPaymentPage().isDisplayed());

		// Perform Details and payment operations

		objdetailsFormulary.fillDetails(testCounter);

		// Create Payment section page

		objpaymentSection = new PaymentMethod(driver);

		// Perform Details and payment operations

		objpaymentSection.fillCardDetails(testCounter);

		// Create Bill adress section page

		objfillBill = new BillingAdress(driver);

		// Perform Bill adress operations

		objfillBill.fillBillAdress(testCounter);

		/// Get payment declined message

		AssertJUnit.assertTrue(objfillBill.getPaymentErrorMessage().isDisplayed());

		// Input the results in the Test results excel

		ExcelUtils.inputResults("Pass",Constant.Col_DeclinedRPaymentResultRow, testCounter);
	}

	@AfterMethod
	public void afterMethod() throws Exception {
		// Close the reports
		utilities.closeReports();

		// Closing the browser

		driver.close();
		driver.quit();

	}

}