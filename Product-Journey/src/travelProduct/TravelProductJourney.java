package travelProduct;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import extentReport.ExtentManager;
import healthProduct.UIMap;
import termProduct.TermSendMail;

public class TravelProductJourney {
	
	public static WebDriver driver;
	public UIMap uimap;
	public UIMap datafile;
	public UIMap locator;
	public String workingDir;
	public static ExtentReports extent;
	public static ExtentTest test;
	ExtentHtmlReporter htmlReporter;

	// Declare An Excel Work Book
	HSSFWorkbook workbook;
	// Declare An Excel Work Sheet
	HSSFSheet sheet;
	// Declare A Map Object To Hold TestNG Results
	Map<String, Object[]> TestNGResults;

	// Capture Screenshot in Folder
	public static String capture(WebDriver driver, String screenShotName) throws IOException {
		screenShotName = driver.getTitle();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\ErrorScreenshots\\" + screenShotName + ".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);

		return dest;
	}

	@BeforeTest
	public void config() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/HealthExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	// Open Easypolicy Website
	@Test(description = "Open Easypolicy Website for Travel Product Journey", priority = 1)
	public void LaunchWebsite() throws Exception {

		try {
			test = extent.createTest("Open URL, Navigate to website", "Easypolicy Website should be open");
			driver.get("http://easypolicy.com/");
			driver.manage().window().maximize();
			String title = driver.getTitle();
			Assert.assertEquals("Easypolicy - #1 from Insurance Comparison to Claim Support", title);
			TestNGResults.put("2",
					new Object[] { 1d, "Open URL, Navigate to website", "Easypolicy Website should be open", "Pass" });

			if (driver.getTitle().contains("Easypolicy")) {
				test.pass(driver.getTitle() + " contain " + "Easypolicy");
				test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./1.jpg"));
			}

			else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Easypolicy");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("2",
					new Object[] { 1d, "Open URL, Navigate to website", "Easypolicy Website should be open", "Fail" });
			Assert.assertTrue(false);
		}
	}

	// Click on Health Product link
	@Test(description = "Click on Travel product link ", priority = 2)
	public void FillLoginDetails() throws Exception {

		try {
			test = extent.createTest("Click on Travel Link on top bar", "Travel product page should be open with form");
			WebElement child = driver.findElement(uimap.getLocator("travel_product"));
			child.click();
			Thread.sleep(1000);
			TestNGResults.put("3", new Object[] { 2d, "Click on Travel Link on top bar",
					"Travel product page should be open with form", "Pass" });

			if (driver.getTitle().contains("Travel Insurance")) {
				test.pass(driver.getTitle() + " contain " + "Travel Insurance");
			} else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Travel Insurance");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("3", new Object[] { 2d, "Click on Travel Link on top bar",
					"Travel product page should be open with form", "Fail" });
			Assert.assertTrue(false);
		}
	}
	
	// Select Destination Country
		@Test(description = "Select Country", priority = 3)
		public void destinationcountry() throws Exception {

			try {
				test = extent.createTest("Select Country", "Input & select");
				WebElement country = driver.findElement(uimap.getLocator("destination_input"));
				country.sendKeys(datafile.getData("destination_input_one"));
				Thread.sleep(1000);
				WebElement countrySelectone = driver.findElement(uimap.getLocator("destination_input1_select"));
				countrySelectone.click();
				Thread.sleep(1000);
				WebElement country2 = driver.findElement(uimap.getLocator("destination_input"));
				country2.sendKeys(datafile.getData("destination_input_two"));
				Thread.sleep(1000);
				WebElement countrySelecttwo = driver.findElement(uimap.getLocator("destination_input2_select"));
				countrySelecttwo.click();
				Thread.sleep(2000);
				TestNGResults.put("4", new Object[] { 3d, "Select gender", "select by click", "Pass" });

				if (driver.getTitle().contains("Easypolicy")) {
					test.pass(driver.getTitle() + " contain " + "Easypolicy");
					test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./1.jpg"));
				}

				else
					test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Easypolicy");
			} catch (Exception e) {
				test.log(Status.ERROR, e.getMessage());
				TestNGResults.put("4",
						new Object[] { 3d, "Open URL, Navigate to website", "Easypolicy Website should be open", "Fail" });
				Assert.assertTrue(false);
			}
		}
		// Select Journey start date
				@Test(description = "Select Journey start Date", priority = 4)
				public void JourneyStartDate() throws Exception {

					try {
						test = extent.createTest("Select Journey start Date", "Select from calendar");
						WebElement startdate = driver.findElement(uimap.getLocator("journey_start_date"));
						startdate.click();
						Thread.sleep(3000);
						WebElement startdateselect = driver.findElement(uimap.getLocator("journey_startdate_select"));
						startdateselect.click();
						Thread.sleep(3000);

						TestNGResults.put("5", new Object[] { 4d, "Select Journey start Date", "select by click", "Pass" });

						if (uimap.getLocator("journey_startdate_select").equals("30")) {
							test.pass(uimap.getLocator("journey_startdate_select") + " equal " + "No");
						} else
							test.log(Status.FAIL, uimap.getLocator("journey_startdate_select") + " doesn't equal " + "30");

					} catch (Exception e) {
						test.log(Status.ERROR, e.getMessage());
						TestNGResults.put("5", new Object[] { 4d, "Select Journey start Date", "No", "Fail" });
						Assert.assertTrue(false);
					}
				}
				// Select Journey End date
				@Test(description = "Select Journey End Date", priority = 5)
				public void JourneyEndDate() throws Exception {

					try {
						test = extent.createTest("Select Journey End Date", "Select from calendar");
						WebElement enddate = driver.findElement(uimap.getLocator("journey_end_date"));
						enddate.click();
						Thread.sleep(1000);
						WebElement enddateselect = driver.findElement(uimap.getLocator("journey_enddate_select"));
						enddateselect.click();
						Thread.sleep(1000);

						TestNGResults.put("6", new Object[] { 5d, "Select Journey end Date", "select by click", "Pass" });

						if (uimap.getLocator("journey_startdate_select").equals("30")) {
							test.pass(uimap.getLocator("journey_startdate_select") + " equal " + "No");
						} else
							test.log(Status.FAIL, uimap.getLocator("journey_startdate_select") + " doesn't equal " + "30");

					} catch (Exception e) {
						test.log(Status.ERROR, e.getMessage());
						TestNGResults.put("6", new Object[] { 5d, "Select Journey end Date", "No", "Fail" });
						Assert.assertTrue(false);
					}
				}
				//Enter no of travellers
				@Test(description = "Enter no of Travellers", priority = 6)
				public void NoofTravellers() throws Exception {

					try {
						test = extent.createTest("Input no of travellers", "values in numbers");
						WebElement enddate = driver.findElement(uimap.getLocator("no_of_traveller"));
						enddate.sendKeys(datafile.getData("numoftraveller"));
						Thread.sleep(1000);
						TestNGResults.put("7", new Object[] { 6d, "Input no of travellers", "values in numbers", "Pass" });

						if (datafile.getData("numoftraveller").equals("2")) {
							test.pass(datafile.getData("numoftraveller") + " equal " + "No");
						} else
							test.log(Status.FAIL, datafile.getData("numoftraveller") + " doesn't equal " + "30");

					} catch (Exception e) {
						test.log(Status.ERROR, e.getMessage());
						TestNGResults.put("7", new Object[] { 6d, "Input no of travellers", "No", "Fail" });
						Assert.assertTrue(false);
					}
				}
				// Click On Continue Button
				@Test(description = "Click on Continue button", priority = 7)
				public void ContinueButton() throws Exception {

					try {
						WebElement buttoncontinue = driver.findElement(uimap.getLocator("contniue_button"));
						buttoncontinue.click();
						Thread.sleep(1000);
						TestNGResults.put("8",
								new Object[] { 7d, "Click on Continue button", "click Get Live Quotes", "Pass" });
						test = extent.createTest("Click on Continue button", "Click on Continue button");

						if (driver.getTitle().contains("Easypolicy")) {
							test.pass(driver.getTitle() + " contain " + "Easypolicy");
						} else
							test.log(Status.FAIL, driver.getTitle() + " contain " + "Health Insurance");

					} catch (Exception e) {
						test.log(Status.ERROR, e.getMessage());
						TestNGResults.put("8", new Object[] { 7d, "Click on Continue button", "Button", "Fail" });
						Assert.assertTrue(false);
					}
				}
				// input age of first member
				@Test(description = "input age of members", priority = 8)
				public void memberage() throws Exception {

					try {
						WebElement membersageone = driver.findElement(uimap.getLocator("age_member1"));
						membersageone.sendKeys(datafile.getData("first_member_age"));
						Thread.sleep(1000);
						WebElement membersagetwo = driver.findElement(uimap.getLocator("age_member2"));
						membersagetwo.sendKeys(datafile.getData("second_member_age"));
						Thread.sleep(1000);
						TestNGResults.put("9",
								new Object[] { 8d, "input age of members", "value in numbers", "Pass" });
						test = extent.createTest("input age of members", "value in numbers");

						if (datafile.getData("second_member_age").equals("30")) {
							test.pass(datafile.getData("second_member_age") + " equal " + "30");
						} else
							test.log(Status.FAIL, driver.getTitle() + " equal " + "30");

					} catch (Exception e) {
						test.log(Status.ERROR, e.getMessage());
						TestNGResults.put("9", new Object[] { 8d, "input age of members", "value in numbers", "Fail" });
						Assert.assertTrue(false);
					}
				}
	// Click On Get Live Quotes Button
	@Test(description = "Click Get Live Quotes Button", priority = 9)
	public void GetLiveQuotes() throws Exception {

		try {
			WebElement buttonlivequote = driver.findElement(uimap.getLocator("get_live_quotes"));
			buttonlivequote.click();
			Thread.sleep(1000);
			TestNGResults.put("10",
					new Object[] { 9d, "Click get live quotes button", "click Get Live Quotes", "Pass" });
			test = extent.createTest("Click get live quotes button", "Value in numbers");

			if (driver.getTitle().contains("Easypolicy")) {
				test.pass(driver.getTitle() + " contain " + "Easypolicy");
			} else
				test.log(Status.FAIL, driver.getTitle() + " contain " + "Health Insurance");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("10", new Object[] { 9d, "Click get live quotes button", "Button", "Fail" });
			Assert.assertTrue(false);
		}
	}

	@Test(description = "Fill Customer Name in Customer Popup", priority = 10)
	public void CustomerPopup() throws Exception {

		try {
			test = extent.createTest("Fill Name in Customer Popup", "Name Should be entered");
			WebElement Custname = driver.findElement(uimap.getLocator("Customer_Name"));
			Custname.sendKeys(datafile.getData("Customername"));
			// String title = driver.getTitle();
			Assert.assertEquals("Vijay Kumar", datafile.getData("Customername"));
			TestNGResults.put("11",
					new Object[] { 10d, "Fill The Customer name value", "Values should be entered", "Pass" });
			if (datafile.getData("Customername").equals("Vijay Kumar")) {
				test.pass(datafile.getData("Customername") + " contain " + "Vijay");
			} else
				test.log(Status.FAIL, datafile.getData("Customername") + " Input Is InCorrect");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("11",
					new Object[] { 10d, "Fill The Customer name value", "Values should be entered", "Fail" });
			Assert.assertTrue(false);
		}
	}

	@Test(description = "Fill Customer Email in Customer Popup", priority = 11)
	public void CustomerEmail() throws Exception {

		try {
			test = extent.createTest("Fill Email in Customer Popup", "Email Should be entered");
			WebElement Custemail = driver.findElement(uimap.getLocator("Customer_Email"));
			Custemail.sendKeys(datafile.getData("CustomerEmailId"));
			// String title = driver.getTitle();
			Assert.assertEquals("vijay.kumar@ep.com", datafile.getData("CustomerEmailId"));
			TestNGResults.put("12",
					new Object[] { 11d, "Fill The Customer email value", "Values should be entered", "Pass" });
			if (datafile.getData("CustomerEmailId").equals("vijay.kumar@ep.com")) {
				test.pass(datafile.getData("CustomerEmailId") + " contain " + "binny");
			} else
				test.log(Status.FAIL, datafile.getData("CustomerEmailId") + " Input Is InCorrect");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("12",
					new Object[] { 11d, "Fill The Customer email value", "Values should be entered", "Fail" });
			Assert.assertTrue(false);
		}
	}

	@Test(description = "Fill Customer Mobile Number in Customer Popup", priority = 12)
	public void CustomerMobno() throws Exception {

		try {
			test = extent.createTest("Fill Mobile No in Customer Popup", "Mobile No Should be entered");
			WebElement CustMobNo = driver.findElement(uimap.getLocator("Customer_Number"));
			CustMobNo.sendKeys(datafile.getData("CustomerMobileNo"));
			// String title = driver.getTitle();
			Assert.assertEquals("6789678967", datafile.getData("CustomerMobileNo"));
			TestNGResults.put("13",
					new Object[] { 12d, "Fill The Customer mobile no value", "Values should be entered", "Pass" });
			if (datafile.getData("CustomerMobileNo").equals("6789678967")) {
				test.pass(datafile.getData("CustomerMobileNo") + " contain " + "6789678967");
			} else
				test.log(Status.FAIL, datafile.getData("CustomerMobileNo") + " Input Is InCorrect");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("13",
					new Object[] { 12d, "Fill The Customer mobile no value", "Values should be entered", "Fail" });
			Assert.assertTrue(false);
		}
	}

	@Test(description = "Click Proceed button on Customer Popup", priority = 13)
	public void CustomerProceed() throws Exception {

		try {
			test = extent.createTest("Click on proceed button",
					"Button Should be clicked and quotes page should be displayed");
			WebElement CustMobNo = driver.findElement(uimap.getLocator("Click_Proceed_Button"));
			CustMobNo.click();
			TestNGResults.put("14", new Object[] { 13d, "Click on proceed button",
					"Button Should be clicked and quotes page should be displayed", "Pass" });
			if (driver.getTitle().contains("Claim Support")) {
				test.pass(driver.getTitle() + " contain " + "Claim Support");

			} else
				test.log(Status.FAIL, driver.getTitle() + "doesn't contain " + "Claim Support");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("14", new Object[] { 13d, "Click on proceed button",
					"Button Should be clicked and quotes page should be displayed", "Fail" });
			Assert.assertTrue(false);
		}
	}

	@Test(description = "Quotes page journey", priority = 14)
	public void QuotesBuyNow() throws Exception {
		String urlquotes = driver.getCurrentUrl();
		System.out.println(urlquotes);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		List<WebElement> buyNowElement = driver.findElements(By.xpath("//span[contains(text(),'BUY NOW')]"));
		String winScroll = "window.scrollBy(0,300)";
		RemoteWebDriver rwd = (RemoteWebDriver) driver;
		for (int i = 1; i <= buyNowElement.size()-2; i++) {
			Thread.sleep(10000);
			driver.findElement(By.xpath("(//span[contains(text(),'BUY NOW')])[" + i + "]")).click();
			Thread.sleep(10000);
			String url = driver.getCurrentUrl();
			System.out.println(url);
			Thread.sleep(5000);
			driver.navigate().to(urlquotes);
			Thread.sleep(5000);
			CustomerPopup();
			CustomerEmail();
			CustomerMobno();
			CustomerProceed();
			for (int j = 0; j < 1; j++) {
				Thread.sleep(1000);
				rwd.executeScript(winScroll);
			}
			 /*driver.navigate().back();
			 Thread.sleep(3000);
			 WebElement buyNow =
			 driver.findElement(By.xpath("(//h5[text()='ProHealthPlus']/../../../../..//span[text()='BUY NOW'])[1]"));
			 buyNow.click();
			 break;*/
		}
	}
	
	// write Test Cases in to excel file
	@BeforeClass(alwaysRun = true)
	public void suiteSetUp() {

		// create a new work book
		workbook = new HSSFWorkbook();
		// create a new work sheet
		sheet = workbook.createSheet("TestNG Result Summary");
		TestNGResults = new LinkedHashMap<String, Object[]>();
		// add test result excel file column header
		// write the header in the first row
		TestNGResults.put("1", new Object[] { "Test Step No.", "Action", "Expected Output", "Actual Output" });

		try {

			// Get current working directory and load the data file
			workingDir = System.getProperty("user.dir");
			datafile = new UIMap(workingDir + "\\Resources\\traveldatafile.properties");

			// Get the object map file
			uimap = new UIMap(workingDir + "\\Resources\\travellocator.properties");
			extent = ExtentManager.GetExtentTravel();

			// Setting up FireFox driver path.
			System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile myprofile = profile.getProfile("TestAutomation");

			// Launching FireFox browser.
			driver = new FirefoxDriver(myprofile);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new IllegalStateException("Can't start the Firefox Web Driver", e);
		}

	}

	@AfterClass
	public void suiteTearDown() throws Exception {
		// Create excel file and file name is SaveHealthTestNGResultToExcel.xls
		Set<String> keyset = TestNGResults.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = TestNGResults.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof Date)
					cell.setCellValue((Date) obj);
				else if (obj instanceof Boolean)
					cell.setCellValue((Boolean) obj);
				else if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(new File("TravelTestNGResultToExcel.xls"));
			workbook.write(out);
			out.close();
			System.out.println("Successfully saved Selenium WebDriver TestNG result to Excel File!!!");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// close the browser
		extent.flush();
		// driver.close();
		// driver.quit();
		// endreport();
		TravelSendMail.execute("TravelExtentReport.html");
	}

	/*@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenShotPath = HealthProductJourneyWithExcel.capture(driver, "screenShotName");
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			test.fail(result.getThrowable());
			test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
		extent.flush();
	}*/
	
	/*@AfterMethod(alwaysRun = true)
	public void catchExceptions(ITestResult result) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String methodName = result.getName();
		if (!result.isSuccess()) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File((String)PathConverter.convert("failure_screenshots/" + methodName + "_" + formater.format(calendar.getTime()) + ".png")));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}*/
	 
	@AfterTest
	public void endreport() {
		//driver.close();
		// extent.flush();
		// extent.close();
	}


}
