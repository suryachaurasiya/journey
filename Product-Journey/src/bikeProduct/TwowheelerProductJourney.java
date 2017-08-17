package bikeProduct;

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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
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

public class TwowheelerProductJourney {
	
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


	@BeforeTest
	public void config() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/BikeExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	// Open Easypolicy Website
	@Test(description = "Open Easypolicy Website for Bike Product Journey", priority = 1)
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
	@Test(description = "Click on Bike product link ", priority = 2)
	public void clickBikeProduct() throws Exception {

		try {
			test = extent.createTest("Click on Bike Link on top bar", "Bike product page should be open with form");
			WebElement car = driver.findElement(uimap.getLocator("bike_product"));
			car.click();
			Thread.sleep(1000);
			TestNGResults.put("3", new Object[] { 2d, "Click on Bike Link on top bar",
					"Car product page should be open with form", "Pass" });

			if (driver.getTitle().contains("Bike Insurance")) {
				test.pass(driver.getTitle() + " contain " + "Bike Insurance");
			} else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Bike Insurance");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("3", new Object[] { 2d, "Click on Bike Link on top bar",
					"Car product page should be open with form", "Fail" });
			//Assert.assertTrue(false);
		}
	}

	// click link for car details
	@Test(description = "click link for Bike details", priority = 3)
	public void BIkeDetail() throws Exception {

		try {
			test = extent.createTest("click link for Bike details", "click link for Bike details");
			WebElement cardetails = driver.findElement(uimap.getLocator("click_link"));
			//carmodel.clear();
			cardetails.click();
			Thread.sleep(1000);
			TestNGResults.put("4", new Object[] { 3d, "click link for Bike details", "click link for Bike details", "Pass" });

			if (driver.getTitle().contains("Bike Insurance")) {
				test.pass(driver.getTitle() + " contain " + "Bike Insurance");
			} else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Bike Insurance");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("4", new Object[] { 3d, "click link for Bike details", "click link for Bike details", "Fail" });
			Assert.assertTrue(false);
		}
	}

	// Enter car model
	@Test(description = "Enter Bike model", priority = 4)
	public void BikeModel() throws Exception {

		try {
			test = extent.createTest("Enter Bike model", "select Bike model from the list");
			WebElement carmodel = driver.findElement(uimap.getLocator("bike_input"));
			//carmodel.clear();
			carmodel.sendKeys(datafile.getData("bike_model"));
			Thread.sleep(3000);
			WebElement carmodelselect = driver.findElement(uimap.getLocator("bike_select"));
			//carmodel.clear();
			carmodelselect.click();
			Thread.sleep(1000);
			TestNGResults.put("5", new Object[] { 4d, "Enter Bike model", "select Bike model from the list", "Pass" });

			if (datafile.getData("bike_model").contains("BAJAJ PULSAR")) {
				test.pass(datafile.getData("bike_model") + " equal " + "one");
			} else
				test.log(Status.FAIL, datafile.getData("bike_model") + "does not equal " + "one");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("5", new Object[] { 4d, "Enter Bike model", "select Bike model from the list", "Fail" });
			//Assert.assertTrue(false);
		}
	}

	// Enter RTO
	@Test(description = "Select RTO", priority = 5)
	public void RTO() throws Exception {

		try {
			test = extent.createTest("Select RTO", "Select from the list");
			WebElement carRTO = driver.findElement(uimap.getLocator("bike_rto"));
			carRTO.sendKeys(datafile.getData("RTO"));
			Thread.sleep(1000);
			WebElement carRTOselect = driver.findElement(uimap.getLocator("bike_rto_select"));
			carRTOselect.click();
			Thread.sleep(1000);
			Assert.assertEquals("DL3S", datafile.getData("RTO"));
			TestNGResults.put("6", new Object[] { 5d, "Select RTO", "Select from the list", "Pass" });

			if (datafile.getData("RTO").contains("DL3S")) {
				test.pass(datafile.getData("RTO") + " contain " + "DL3S");
			} else
				test.log(Status.FAIL, datafile.getData("RTO") + " doesn't contain " + "DL3S");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("6", new Object[] { 5d, "Select RTO", "Select from the list", "Fail" });
			Assert.assertTrue(false);
		}
	}

	// Select Registration Year
	@Test(description = "Select Registration year", priority = 6)
	public void Registrationyear() throws Exception {

		try {
			test = extent.createTest("Select Registration year", "Select from the list");
			WebElement registrationyear = driver.findElement(uimap.getLocator("registration_year"));
			registrationyear.click();
			Thread.sleep(1000);
			WebElement registrationyearSelect = driver.findElement(uimap.getLocator("registration_year_select"));
			registrationyearSelect.click();
			Thread.sleep(1000);
			TestNGResults.put("7", new Object[] { 6d, "Select Registration year", "select by click", "Pass" });

			if (uimap.getLocator("registration_year_select").equals("2015")) {
				test.pass(uimap.getLocator("registration_year_select") + " equal " + "Female");
			} else
				test.log(Status.FAIL, uimap.getLocator("registration_year_select") + " doesn't equal " + "Female");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("7", new Object[] { 6d, "Select Registration year", "Registration year", "Fail" });
			Assert.assertTrue(false);
		}
	}
	// Policy Expired Year
		@Test(description = "Select Policy Expired Year", priority = 7)
		public void PolicyExpireYear() throws Exception {

			try {
				test = extent.createTest("Select Policy Expired Year", "Select from the list");
				WebElement registrationyear = driver.findElement(uimap.getLocator("policy_expired"));
				registrationyear.click();
				Thread.sleep(1000);
				WebElement registrationyearSelect = driver.findElement(uimap.getLocator("policy_expired_select"));
				registrationyearSelect.click();
				Thread.sleep(1000);
				TestNGResults.put("8", new Object[] { 7d, "Select Policy Expired Year", "select by click", "Pass" });

				if (uimap.getLocator("policy_expired_select").equals("2015")) {
					test.pass(uimap.getLocator("policy_expired_select") + " equal " + "2015");
				} else
					test.log(Status.FAIL, uimap.getLocator("policy_expired_select") + " doesn't equal " + "2015");

			} catch (Exception e) {
				test.log(Status.ERROR, e.getMessage());
				TestNGResults.put("8", new Object[] { 7d, "Select Policy Expired Year", "year", "Fail" });
				Assert.assertTrue(false);
			}
		}

	// Select City from the list
	@Test(description = "Select last claim", priority = 8)
	public void Lastclaim() throws Exception {

		try {
			WebElement lastclaim = driver.findElement(uimap.getLocator("last_claim"));
			lastclaim.click();
			Thread.sleep(1000);
			WebElement lastclaimselect = driver.findElement(uimap.getLocator("last_claim_select"));
			lastclaimselect.click();
			TestNGResults.put("9", new Object[] { 8d, "Select last claim", "select from the list", "Pass" });
			test = extent.createTest("Select last claim", "select from the list");

			if (uimap.getLocator("last_claim_select").equals("Never")) {
				test.pass(uimap.getLocator("last_claim_select") + " equal " + "Never");
			} else
				test.log(Status.FAIL, uimap.getLocator("last_claim_select") + " doesn't equal " + "Never");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("9", new Object[] { 8d, "Select City From the List", "select by click", "Fail" });
			Assert.assertTrue(false);
		}
	}

	// Click On Get Live Quotes Button
	@Test(description = "Click Get Live Quotes Button", priority = 9)
	public void GetLiveQuotes() throws Exception {
		Thread.sleep(3000);
		try {
			WebElement buttonlivequote = driver.findElement(uimap.getLocator("golivebutton"));
			buttonlivequote.click();
			Thread.sleep(1000);
			TestNGResults.put("10",
					new Object[] { 9d, "Click get live quotes button", "click Get Live Quotes", "Pass" });
			test = extent.createTest("Click get live quotes button", "Value in numbers");

			if (driver.getTitle().contains("Easypolicy")) {
				test.pass(driver.getTitle() + " contain " + "Easypolicy");
			} else
				test.log(Status.FAIL, driver.getTitle() + " contain " + "Bike Insurance");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("10", new Object[] { 9d, "Click get live quotes button", "Button", "Fail" });
			Assert.assertTrue(false);
		}
	}

	@Test(description = "Fill Customer Name in Customer Popup", priority = 10)
	public void CustomerPopup() throws Exception {

		try {
			Thread.sleep(8000);
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
		Thread.sleep(10000);
		String urlquotes = driver.getCurrentUrl();
		System.out.println(urlquotes);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement threeyearplan = driver.findElement(uimap.getLocator("Three_year_plans"));
		WebElement twoyearplan = driver.findElement(uimap.getLocator("Two_year_plans"));
		WebElement oneyearplan = driver.findElement(uimap.getLocator("One_year_plans"));
		if(threeyearplan.isDisplayed())
		{
		List<WebElement> buyNowElement = driver.findElements(By.xpath("//span[text()='BUY NOW']"));
		String winScroll = "window.scrollBy(0,300)";
		RemoteWebDriver rwd = (RemoteWebDriver) driver;
		for (int i = 7; i<9; i++) {
			Thread.sleep(10000);
			driver.findElement(By.xpath("(//span[contains(text(),'BUY NOW')])[" + i + "]")).click();
			Thread.sleep(10000);
			String url = driver.getCurrentUrl();
			System.out.println(url);
			Thread.sleep(5000);
		
			/*
			 * if(i==3){ ReligareProposal rp=new ReligareProposal(driver);
			 * rp.enterPopupDetail(driver); Thread.sleep(3000);
			 * rp.enterProposarDetails(driver); Thread.sleep(3000);
			 * rp.enterMemberDetails(driver); }
			 */
			driver.navigate().to(urlquotes);
			Thread.sleep(10000);
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
		}if(oneyearplan.isDisplayed()){
			List<WebElement> buyNowElement = driver.findElements(By.xpath("//span[text()='BUY NOW']"));
			String winScroll = "window.scrollBy(0,300)";
			RemoteWebDriver rwd = (RemoteWebDriver) driver;
			for (int i =18; i<24; i++) {
				Thread.sleep(10000);
				driver.findElement(By.xpath("(//span[contains(text(),'BUY NOW')])[" + i + "]")).click();
				Thread.sleep(10000);
				String url = driver.getCurrentUrl();
				System.out.println(url);
				Thread.sleep(5000);
			
				/*
				 * if(i==3){ ReligareProposal rp=new ReligareProposal(driver);
				 * rp.enterPopupDetail(driver); Thread.sleep(3000);
				 * rp.enterProposarDetails(driver); Thread.sleep(3000);
				 * rp.enterMemberDetails(driver); }
				 */
				driver.navigate().to(urlquotes);
				Thread.sleep(10000);
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
		}if(twoyearplan.isDisplayed()){
			List<WebElement> buyNowElement = driver.findElements(By.xpath("//span[text()='BUY NOW']"));
			String winScroll = "window.scrollBy(0,300)";
			RemoteWebDriver rwd = (RemoteWebDriver) driver;
			for (int i =14; i<16; i++) {
				Thread.sleep(10000);
				driver.findElement(By.xpath("(//span[contains(text(),'BUY NOW')])[" + i + "]")).click();
				Thread.sleep(10000);
				String url = driver.getCurrentUrl();
				System.out.println(url);
				Thread.sleep(5000);
			
				/*
				 * if(i==3){ ReligareProposal rp=new ReligareProposal(driver);
				 * rp.enterPopupDetail(driver); Thread.sleep(3000);
				 * rp.enterProposarDetails(driver); Thread.sleep(3000);
				 * rp.enterMemberDetails(driver); }
				 */
				driver.navigate().to(urlquotes);
				Thread.sleep(10000);
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
			datafile = new UIMap(workingDir + "\\Resources\\bikedatafile.properties");

			// Get the object map file
			uimap = new UIMap(workingDir + "\\Resources\\bikelocator.properties");
			extent = ExtentManager.GetExtentBike();

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
			FileOutputStream out = new FileOutputStream(new File("SaveHealthTestNGResultToExcel.xls"));
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
		BikeSendMail.execute("BikeExtentReport.html");
	}
	 
	@AfterTest
	public void endreport() {
		driver.close();
		// extent.flush();
		// extent.close();
	}


}
