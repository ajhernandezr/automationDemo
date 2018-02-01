package utility;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class utilities {
	public static WebDriver driver;
	static ExtentReports report;
	static ExtentTest logger;

	public static void smallPause() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// Handle here
		}
	}

	public static void mediumPause() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// Handle here
		}
	}

	public static void longPause() {
		try {
			Thread.sleep(14000);
		} catch (InterruptedException e) {
			// Handle here
		}
	}

	public static void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 25);
		wait.until(ExpectedConditions.elementToBeClickable((element)));

	}

	public static void moveToElement(WebElement element) {

		Actions builder = new Actions(driver);
		builder.moveToElement(element).build().perform();
	}

	public static void selectBrowser(String Browser) throws MalformedURLException {
		String browser = Browser.toString();
		switch (browser) {
		case "Firefox":

			System.setProperty("webdriver.gecko.driver", "./src/main/resources/drivers/geckodriver.exe");

			driver = new FirefoxDriver();

			driver.get(Constant.URL);
			driver.manage().window().maximize();
			// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			break;

		case "Chrome":
			try {
				System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");

				ChromeOptions options = new ChromeOptions();

				// Disable extensions and hide infobars
				options.addArguments("--disable-extensions");
				options.addArguments("disable-infobars");

				Map<String, Object> prefs = new HashMap<>();

				// Enable Flash
				prefs.put("profile.default_content_setting_values.plugins", 1);
				prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
				prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);

				// Hide save credentials prompt
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.get(Constant.URL);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			} catch (Exception e) {
				System.out.println(e);
			}

			break;
		case "Iexplorer":

			System.setProperty("webdriver.ie.driver", "./src/main/resources/drivers/IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			// this line of code is to resolve protected mode issue
			// capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			// true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new InternetExplorerDriver(capabilities);
			// driver = new InternetExplorerDriver();

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get(Constant.URL);

			break;
		
		}
	}

	// Method for interact with dificult web elements
	public static void pushDificultElements(WebDriver driver, final WebElement element2) throws Exception {
		Wait<WebDriver> fluent = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		Function<WebDriver, WebElement> push = new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver arg0) {
				WebElement element = element2;
				if (element != null) {

					element.click();
				}
				return element;
			}
		};
		fluent.until(push);
	}

	/// Configure the Extent Reports
	public static void  runReports(String testName,int testCounter ) throws Exception {

		/// Configure the Extent Reports
		report = new ExtentReports("./src/main/resources/extentReports/" + testName+testCounter + ".html", true);
		report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));

		logger = report.startTest(testName);
		logger.log(LogStatus.PASS, "LAUNCHING TEST CASE " + testName);

		
	}

	/// Log one step for the report
	public static void logReports(String logInfo) throws Exception {

		logger.log(LogStatus.PASS, logInfo);

	}

	/// Close the reports
	public static void closeReports() throws Exception {
		report.endTest(logger);
		report.flush();
		report.close();

	}
}
