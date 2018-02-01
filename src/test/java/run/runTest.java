package run;

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
import Tests.DeclinedPaymentOnBooking;
import utility.Constant;
import utility.ExcelUtils;
import utility.utilities;

public class runTest {
	WebDriver driver;

	DeclinedPaymentOnBooking objTest;

	private String excelTestName;
	private int testCounter = 1;
	private String testName;
	private String sheet = "General";

	@Test()
	/// This will run the test case included in the testng xml for each
	/// scenario included in the Test data file
	public void run(final ITestContext testContext) throws Exception {

		testName = testContext.getName();
		// Erase the previous Test results
		ExcelUtils.eraseExcel();

		// We got the path to excel document and the sheet
		ExcelUtils.setExcelFile(Constant.Path_TestData, sheet);

		excelTestName = ExcelUtils.getCellData(testCounter, Constant.Col_Test, sheet);

		while (excelTestName.contains(testName) == true) {
		
			// Create an object of the Declined test case and perform his
			// methods
			objTest = new DeclinedPaymentOnBooking();
			objTest.setup(excelTestName, testCounter);
			objTest.DeclinedPaymentOnBookingTest(testCounter);
			objTest.afterMethod();

			///// Add one more to the test case for look in the next row and know how many times we performed the test
			testCounter++;
			
			// Get the next test case scenario
			ExcelUtils.setExcelFile(Constant.Path_TestData, sheet);
			excelTestName = ExcelUtils.getCellData(testCounter, Constant.Col_Test, sheet);

		}

	}

}