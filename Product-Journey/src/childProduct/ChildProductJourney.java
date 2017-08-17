package childProduct;

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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import extentReport.ExtentManager;
import healthProduct.HealthProductJourneyWithExcel;
import healthProduct.SendMailSSL;
import healthProduct.UIMap;

public class ChildProductJourney {
	
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
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ChildExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	// Open Easypolicy Website
	@Test(description = "Open Easypolicy Website for health Product Journey", priority = 1)
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
	@Test(description = "Click on Child product link ", priority = 2)
	public void FillLoginDetails() throws Exception {

		try {
			test = extent.createTest("Click on Child Link on top bar", "Child product page should be open with form");
			WebElement child = driver.findElement(uimap.getLocator("child_product"));
			child.click();
			Thread.sleep(1000);
			TestNGResults.put("3", new Object[] { 2d, "Click on Child Link on top bar",
					"Child product page should be open with form", "Pass" });

			if (driver.getTitle().contains("Child Plans")) {
				test.pass(driver.getTitle() + " contain " + "Child Plans");
			} else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Child Plans | Compare Best Online Child Investment Plans");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("3", new Object[] { 2d, "Click on Child Link on top bar",
					"Health product page should be open with form", "Fail" });
			Assert.assertTrue(false);
		}
	}

	// Enter child Date of Birth
	@Test(description = "Enter child DOB", priority = 3)
	public void ChildDOB() throws Exception {

		try {
			test = extent.createTest("Enter value for DOB", "Value in numbers");
			WebElement DOBdate = driver.findElement(uimap.getLocator("child_dob_field_DD"));
			DOBdate.clear();
			DOBdate.sendKeys(datafile.getData("child_DOBD"));
			Thread.sleep(1000);

			WebElement DOBmonth = driver.findElement(uimap.getLocator("child_dob_field_MM"));
			DOBmonth.clear();
			DOBmonth.sendKeys(datafile.getData("child_DOBM"));
			Thread.sleep(1000);

			WebElement DOByear = driver.findElement(uimap.getLocator("child_dob_field_YYYY"));
			DOByear.clear();
			DOByear.sendKeys(datafile.getData("child_DOBY"));
			Thread.sleep(1000);
			Assert.assertEquals("2014", datafile.getData("child_DOBY"));
			TestNGResults.put("4", new Object[] { 3d, "Enter value for DOB", "Value in numbers", "Pass" });

			if (driver.getTitle().contains("Child Plans")) {
				test.pass(driver.getTitle() + " contain " + "Child Plans");
			} else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Child Plans | Compare Best Online Child Investment Plans");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("4", new Object[] { 3d, "Enter DOB detail", "Date of Birth", "Fail" });
			Assert.assertTrue(false);
		}
	}
	@Test(description = "Enter Parent DOB", priority = 4)
	public void ParentDOB() throws Exception {

		try {
			test = extent.createTest("Enter value for DOB", "Value in numbers");
			WebElement DOBdate = driver.findElement(uimap.getLocator("parent_dob_field_DD"));
			DOBdate.clear();
			DOBdate.sendKeys(datafile.getData("parent_DOBD"));
			Thread.sleep(1000);

			WebElement DOBmonth = driver.findElement(uimap.getLocator("parent_dob_field_MM"));
			DOBmonth.clear();
			DOBmonth.sendKeys(datafile.getData("parent_DOBM"));
			Thread.sleep(1000);

			WebElement DOByear = driver.findElement(uimap.getLocator("parent_dob_field_YYYY"));
			DOByear.clear();
			DOByear.sendKeys(datafile.getData("parent_DOBY"));
			Thread.sleep(1000);
			Assert.assertEquals("1986", datafile.getData("parent_DOBY"));
			TestNGResults.put("5", new Object[] { 4d, "Enter value for DOB", "Value in numbers", "Pass" });

			if (driver.getTitle().contains("Child Plans")) {
				test.pass(driver.getTitle() + " contain " + "Child Plans");
			} else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Child Plans");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("5", new Object[] { 4d, "Enter DOB detail", "Date of Birth", "Fail" });
			Assert.assertTrue(false);
		}
	}

	// Select Eldest Member Gender
	@Test(description = "Select Eldest Member Gender", priority = 5)
	public void EldestMember() throws Exception {

		try {
			test = extent.createTest("Select eldest member gender", "Value in numbers");
			WebElement gender = driver.findElement(uimap.getLocator("child_gender"));
			gender.click();
			Thread.sleep(1000);

			TestNGResults.put("6", new Object[] { 5d, "Select eldest member gender", "select by click", "Pass" });

			if (datafile.getData("child_gender").equals("Male")) {
				test.pass(datafile.getData("child_gender") + " equal " + "Female");
			} else
				test.log(Status.FAIL, datafile.getData("child_gender") + " doesn't equal " + "Female");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("6", new Object[] { 5d, "Select eldest member gender", "Gender", "Fail" });
			Assert.assertTrue(false);
		}
	}
	@Test(description = "Select annual income from drop down", priority = 6)
	public void AnnualIncome() throws Exception {

		try {
			WebElement annualincome = driver.findElement(uimap.getLocator("annual_income_dropdown"));
			annualincome.click();
			Thread.sleep(1000);
			WebElement annualincomeselect = driver.findElement(uimap.getLocator("annual_income_select"));
			annualincomeselect.click();
			Thread.sleep(1000);
			TestNGResults.put("7",
					new Object[] { 6d, "Select annual income from drop down", "Select annual income from drop down", "Pass" });
			test = extent.createTest("Select annual income from drop down", "Select annual income from drop down");

			if (driver.getTitle().contains("Child Plans | Compare Best Online Child Investment Plans contain Health Insurance")) {
				test.pass(driver.getTitle() + " contain " + "Child Plans | Compare Best Online Child Investment Plans contain Health Insurance");
			} else
				test.log(Status.FAIL, driver.getTitle() + " contain " + "Child Plans | Compare Best Online Child Investment Plans contain Health Insurance");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("7", new Object[] { 6d, "Click get live quotes button", "Button", "Fail" });
			Assert.assertTrue(false);
		}
	}

	// Click On Get Live Quotes Button
	@Test(description = "Click Get Live Quotes Button", priority = 7)
	public void GetLiveQuotes() throws Exception {

		try {
			WebElement buttonlivequote = driver.findElement(uimap.getLocator("GetLiveButton"));
			buttonlivequote.click();
			Thread.sleep(1000);
			TestNGResults.put("8",
					new Object[] { 7d, "Click get live quotes button", "click Get Live Quotes", "Pass" });
			test = extent.createTest("Click get live quotes button", "Value in numbers");

			if (driver.getTitle().contains("Easypolicy - Child Insurance contain Child Plans")) {
				test.pass(driver.getTitle() + " contain " + "Easypolicy - Child Insurance contain Child Plans");
			} else
				test.log(Status.FAIL, driver.getTitle() + " contain " + "Easypolicy - Child Insurance contain Child Plans");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("8", new Object[] { 7d, "Click get live quotes button", "Button", "Fail" });
			Assert.assertTrue(false);
		}
	}

	@Test(description = "Fill Customer Name in Customer Popup", priority = 8)
	public void CustomerPopup() throws Exception {

		try {
			Thread.sleep(8000);
			test = extent.createTest("Fill Name in Customer Popup", "Name Should be entered");
			WebElement Custname = driver.findElement(uimap.getLocator("Customer_Name"));
			Custname.sendKeys(datafile.getData("Customername"));
			// String title = driver.getTitle();
			Assert.assertEquals("Vijay Kumar", datafile.getData("Customername"));
			TestNGResults.put("9",
					new Object[] { 8d, "Fill The Customer name value", "Values should be entered", "Pass" });
			if (datafile.getData("Customername").equals("Vijay Kumar")) {
				test.pass(datafile.getData("Customername") + " contain " + "Vijay");
			} else
				test.log(Status.FAIL, datafile.getData("Customername") + " Input Is InCorrect");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("9",
					new Object[] { 8d, "Fill The Customer name value", "Values should be entered", "Fail" });
			Assert.assertTrue(false);
		}
	}

	@Test(description = "Fill Customer Email in Customer Popup", priority = 9)
	public void CustomerEmail() throws Exception {

		try {
			test = extent.createTest("Fill Email in Customer Popup", "Email Should be entered");
			WebElement Custemail = driver.findElement(uimap.getLocator("Customer_Email"));
			Custemail.sendKeys(datafile.getData("CustomerEmailId"));
			// String title = driver.getTitle();
			Assert.assertEquals("vijay.kumar@ep.com", datafile.getData("CustomerEmailId"));
			TestNGResults.put("10",
					new Object[] { 9d, "Fill The Customer email value", "Values should be entered", "Pass" });
			if (datafile.getData("CustomerEmailId").equals("vijay.kumar@ep.com")) {
				test.pass(datafile.getData("CustomerEmailId") + " contain " + "binny");
			} else
				test.log(Status.FAIL, datafile.getData("CustomerEmailId") + " Input Is InCorrect");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("10",
					new Object[] { 9d, "Fill The Customer email value", "Values should be entered", "Fail" });
			Assert.assertTrue(false);
		}
	}

	@Test(description = "Fill Customer Mobile Number in Customer Popup", priority = 10)
	public void CustomerMobno() throws Exception {

		try {
			test = extent.createTest("Fill Mobile No in Customer Popup", "Mobile No Should be entered");
			WebElement CustMobNo = driver.findElement(uimap.getLocator("Customer_Number"));
			CustMobNo.sendKeys(datafile.getData("CustomerMobileNo"));
			// String title = driver.getTitle();
			Assert.assertEquals("6789678967", datafile.getData("CustomerMobileNo"));
			TestNGResults.put("11",
					new Object[] { 10d, "Fill The Customer mobile no value", "Values should be entered", "Pass" });
			if (datafile.getData("CustomerMobileNo").equals("6789678967")) {
				test.pass(datafile.getData("CustomerMobileNo") + " contain " + "6789678967");
			} else
				test.log(Status.FAIL, datafile.getData("CustomerMobileNo") + " Input Is InCorrect");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("11",
					new Object[] { 10d, "Fill The Customer mobile no value", "Values should be entered", "Fail" });
			Assert.assertTrue(false);
		}
	}

	@Test(description = "Click Proceed button on Customer Popup", priority = 12)
	public void CustomerProceed() throws Exception {

		try {
			test = extent.createTest("Click on proceed button",
					"Button Should be clicked and quotes page should be displayed");
			WebElement CustMobNo = driver.findElement(uimap.getLocator("Click_Proceed_Button"));
			CustMobNo.click();
			TestNGResults.put("13", new Object[] { 12d, "Click on proceed button",
					"Button Should be clicked and quotes page should be displayed", "Pass" });
			if (driver.getTitle().contains("Easypolicy - Child Insurance")) {
				test.pass(driver.getTitle() + " contain " + "Easypolicy - Child Insurance");

			} else
				test.log(Status.FAIL, driver.getTitle() + "doesn't contain " + "Easypolicy - Child Insurance");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("13", new Object[] { 12d, "Click on proceed button",
					"Button Should be clicked and quotes page should be displayed", "Fail" });
			Assert.assertTrue(false);
		}
	}

	@Test(description = "Quotes page journey", priority = 13)
	public void QuotesBuyNow() throws Exception {
		String urlquotes = driver.getCurrentUrl();
		System.out.println(urlquotes);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		List<WebElement> buyNowElement = driver.findElements(By.xpath("//span[text()='BOOK NOW']"));
		String winScroll = "window.scrollBy(0,300)";
		RemoteWebDriver rwd = (RemoteWebDriver) driver;
		for (int i = 1; i <= buyNowElement.size()-14; i+=2) {
			Thread.sleep(10000);
			driver.findElement(By.xpath("(//span[text()='BOOK NOW'])[" + i + "]")).click();
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
	/*// @Test(description = "Click Proceed button on Customer Popup", priority =
	// 14)
	
	 * public void CignaProposalFill() throws Exception {
	 * 
	 * try { test = extent.createTest("Click on drop down",
	 * "Drop Down should be selected"); WebElement salutation =
	 * driver.findElement(uimap.getLocator("Salutation_text"));
	 * System.out.println(salutation);
	 * salutation.sendKeys(datafile.getData("SalutionProposar"));
	 * Thread.sleep(3000); WebElement firstname =
	 * driver.findElement(uimap.getLocator("FirstName_text_field"));
	 * firstname.sendKeys(datafile.getData("Proposer_first_name"));
	 * TestNGResults.put("15", new Object[] { 14d, "Click on proceed button",
	 * "Button Should be clicked and quotes page should be displayed", "Pass"
	 * }); if (driver.getTitle().contains("Claim Support")) {
	 * test.pass(driver.getTitle() + " contain " + "Claim Support");
	 * //test.log(Status.PASS, datafile.getData("CustomerEmailId")+
	 * " Input Is Correct"); //test.log(Status.INFO, "Snapshot" +
	 * test.addScreenCaptureFromPath("./2.jpg")); //test.log(Status.INFO,
	 * test.addScreenCapture(ExtentManager.this(driver, "./Send")));
	 * 
	 * } else test.log(Status.FAIL, driver.getTitle() + "doesn't contain " +
	 * "Claim Support"); } catch (Exception e) { test.log(Status.ERROR,
	 * e.getMessage()); TestNGResults.put("15", new Object[] { 14d,
	 * "Click on proceed button",
	 * "Button Should be clicked and quotes page should be displayed", "Fail"
	 * }); //Assert.assertTrue(false); } } //@Test(description =
	 * "Click Proceed button on Customer Popup", priority = 15) public void
	 * CignaProposalFillName() throws Exception {
	 * 
	 * try { test = extent.createTest("Fill Name", "Fill Name"); WebElement
	 * firstname = driver.findElement(uimap.getLocator("FirstName_text_field"));
	 * firstname.sendKeys(datafile.getData("Proposer_first_name"));
	 * TestNGResults.put("16", new Object[] { 15d, "Click on proceed button",
	 * "Button Should be clicked and quotes page should be displayed", "Pass"
	 * }); if (driver.getTitle().contains("Claim Support")) {
	 * test.pass(driver.getTitle() + " contain " + "Claim Support");
	 * //test.log(Status.PASS, datafile.getData("CustomerEmailId")+
	 * " Input Is Correct"); //test.log(Status.INFO, "Snapshot" +
	 * test.addScreenCaptureFromPath("./2.jpg")); //test.log(Status.INFO,
	 * test.addScreenCapture(ExtentManager.this(driver, "./Send")));
	 * 
	 * } else test.log(Status.FAIL, driver.getTitle() + "doesn't contain " +
	 * "Claim Support"); } catch (Exception e) { test.log(Status.ERROR,
	 * e.getMessage()); TestNGResults.put("16", new Object[] { 15d,
	 * "Click on proceed button",
	 * "Button Should be clicked and quotes page should be displayed", "Fail"
	 * }); //Assert.assertTrue(false); } }
	 

	// Edit Section Script
	
	 * @Test(description = "Click on Edit link on Quotes page", priority = 13)
	 * public void ClickEdit() throws Exception {
	 * 
	 * try { test = extent.createTest("Click on Edit Button",
	 * "Button Should be clicked and edit ppopup should be displayed");
	 * WebElement EditLink = driver.findElement(uimap.getLocator("Edit_link"));
	 * EditLink.click(); TestNGResults.put("13", new Object[] { 12d,
	 * "Click on Edit Link",
	 * "Button Should be clicked and edit ppopup should be displayed", "Pass"
	 * }); if (driver.getTitle().contains("Claim Support")) {
	 * test.pass(driver.getTitle() + " contain " + "Claim Support");
	 * //test.log(Status.PASS, datafile.getData("CustomerEmailId")+
	 * " Input Is Correct"); //test.log(Status.INFO, "Snapshot" +
	 * test.addScreenCaptureFromPath("./2.jpg")); //test.log(Status.INFO,
	 * test.addScreenCapture(ExtentManager.this(driver, "./Send"))); } else
	 * test.log(Status.FAIL, driver.getTitle() + "doesn't contain " +
	 * "Claim Support"); } catch (Exception e) { test.log(Status.ERROR,
	 * e.getMessage()); TestNGResults.put("13", new Object[] { 12d,
	 * "Click on Edit Link",
	 * "Button Should be clicked and edit ppopup should be displayed", "Fail"
	 * }); Assert.assertTrue(false); } }
	 * 
	 * @Test(description = "Change Values in Adult ", priority = 14) public void
	 * AdultEdit() throws Exception {
	 * 
	 * try { test = extent.createTest("Change adult value",
	 * "Button Should be changes and click on Get Live Quotes Button");
	 * WebElement adultedit =
	 * driver.findElement(uimap.getLocator("Adult_Edit")); adultedit.clear();
	 * adultedit.sendKeys(datafile.getData("AdultEditOne"));
	 * TestNGResults.put("14", new Object[] { 13d, "Change adult value",
	 * "Button Should be changes and click on Get Live Quotes Button", "Pass"
	 * }); if (datafile.getData("AdultEditOne").equals("1")) {
	 * test.pass(datafile.getData("AdultEditOne") + " is " + "Correct");
	 * //test.log(Status.PASS, datafile.getData("CustomerEmailId")+
	 * " Input Is Correct"); //test.log(Status.INFO, "Snapshot" +
	 * test.addScreenCaptureFromPath("./2.jpg")); //test.log(Status.INFO,
	 * test.addScreenCapture(ExtentManager.this(driver, "./Send"))); } else
	 * test.log(Status.FAIL, datafile.getData("AdultEditOne") + "is not " +
	 * "Correct"); } catch (Exception e) { test.log(Status.ERROR,
	 * e.getMessage()); TestNGResults.put("14", new Object[] { 13d,
	 * "Change adult value",
	 * "Button Should be changes and click on Get Live Quotes Button", "Fail"
	 * }); Assert.assertTrue(false); } }
	 * 
	 * @Test(description = "Change Values in ", priority = 15) public void
	 * GetliveQuoteEdit() throws Exception {
	 * 
	 * try { test = extent.createTest(
	 * "Click on Get live Quotes button in Edit window",
	 * "Button Should be clicked and quotes page should be display with change values"
	 * ); WebElement livequotesbuttonedit =
	 * driver.findElement(uimap.getLocator("GetLiveQuote_Edit"));
	 * livequotesbuttonedit.click(); TestNGResults.put("15", new Object[] { 14d,
	 * "Click on Get live Quotes button in Edit window",
	 * "Button Should be clicked and quotes page should be display with change values"
	 * , "Pass" }); if (driver.getTitle().contains("Easypolicy")) {
	 * test.pass(driver.getTitle() + " is " + "Correct");
	 * //test.log(Status.PASS, datafile.getData("CustomerEmailId")+
	 * " Input Is Correct"); //test.log(Status.INFO, "Snapshot" +
	 * test.addScreenCaptureFromPath("./2.jpg")); //test.log(Status.INFO,
	 * test.addScreenCapture(ExtentManager.this(driver, "./Send"))); } else
	 * test.log(Status.FAIL, driver.getTitle() + "is not " + "Correct"); } catch
	 * (Exception e) { test.log(Status.ERROR, e.getMessage());
	 * TestNGResults.put("15", new Object[] { 14d,
	 * "Click on Get live Quotes button in Edit window",
	 * "Button Should be clicked and quotes page should be display with change values"
	 * , "Fail" }); Assert.assertTrue(false); } }
	 * 
	 * @Test(description = "Get All Plans Values on Quotes Page ", priority =
	 * 16) public void GetAllQuote() throws Exception {
	 * 
	 * try { test = extent.createTest("All Plans Name & premium",
	 * "Name & Premium Should be display"); WebElement Plansnameandpremium =
	 * driver.findElement(uimap.getLocator("GetLiveQuote_Edit"));
	 * Plansnameandpremium.click(); TestNGResults.put("15", new Object[] { 14d,
	 * "Click on Get live Quotes button in Edit window",
	 * "Button Should be clicked and quotes page should be display with change values"
	 * , "Pass" }); if (driver.getTitle().contains("Easypolicy")) {
	 * test.pass(driver.getTitle() + " is " + "Correct");
	 * //test.log(Status.PASS, datafile.getData("CustomerEmailId")+
	 * " Input Is Correct"); //test.log(Status.INFO, "Snapshot" +
	 * test.addScreenCaptureFromPath("./2.jpg")); //test.log(Status.INFO,
	 * test.addScreenCapture(ExtentManager.this(driver, "./Send"))); } else
	 * test.log(Status.FAIL, driver.getTitle() + "is not " + "Correct"); } catch
	 * (Exception e) { test.log(Status.ERROR, e.getMessage());
	 * TestNGResults.put("15", new Object[] { 14d,
	 * "Click on Get live Quotes button in Edit window",
	 * "Button Should be clicked and quotes page should be display with change values"
	 * , "Fail" }); Assert.assertTrue(false); } }
	 */

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
			datafile = new UIMap(workingDir + "\\Resources\\childdatafile.properties");

			// Get the object map file
			uimap = new UIMap(workingDir + "\\Resources\\childlocator.properties");
			extent = ExtentManager.GetExtentChild();

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
			FileOutputStream out = new FileOutputStream(new File("ChildTestNGResultToExcel.xls"));
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
		ChildSendMail.execute("ChildExtentReport.html");
	}

	 
	@AfterTest
	public void endreport() {
		driver.close();
		// extent.flush();
		// extent.close();
	}

}



