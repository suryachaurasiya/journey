package healthProduct;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import extentReport.ExtentBaseClass;
import extentReport.ExtentManager;
import healthProduct.CignaProposal;

public class HealthProductJourneyWithExcel extends ExtentBaseClass {
	public static WebDriver driver;
	public UIMap uimap;
	public UIMap datafile;
	public UIMap locator;
	public String workingDir;
	
	// Open Easypolicy Website
	@Test(description ="Open Easypolicy Website for health Product Journey", priority = 1)
	public void LaunchWebsite() throws Exception {

		try {
			test = extent.createTest("Open URL, Navigate to website", "Easypolicy Website should be open");
			driver.get("http://easypolicy.com/");
			driver.manage().window().maximize();
			//String title = driver.getTitle();
			if (driver.getTitle().contains("Easypolicy - #1 from Insurance Comparison to Claim Support")) {
				test.pass(driver.getTitle() + " contain " + "Easypolicy");
			}
			else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Easypolicy");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
		
		}
	}

	// Click on Health Product link
	@Test(description = "Click on Health product link ", priority = 2)
	public void FillLoginDetails() throws Exception {

		try {
			test = extent.createTest("Click on Health Link on top bar", "Health product page should be open with form");
			WebElement health =driver.findElement(uimap.getLocator("health_product"));
			health.click();
			Thread.sleep(1000);
			if (driver.getTitle().contains("Health Insurance")) {
				test.pass(driver.getTitle() + " contains " + "Health Insurance");
			} else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Health Insurance");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			
		}
	}

	// Fill value in Adult
	@Test(description = "Enter adult value 2", priority = 3)
	public void Adult() throws Exception {
		try {
			test = extent.createTest("Enter value 2 for adults", "Value in numbers");
			WebElement adultNo = driver.findElement(uimap.getLocator("adult_text_field"));
			adultNo.clear();
			adultNo.sendKeys(datafile.getData("adultTwo"));
			Thread.sleep(1000);

			if (datafile.getData("adultTwo").equals("2")) {
				test.pass(datafile.getData("adultTwo").equals("2") + " equals " + "2");
			} else
				test.log(Status.FAIL, datafile.getData("adultTwo").equals("2") + " doesn't equals " + "2");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			
		}
	}

	// Fill Value in Child
	@Test(description = "Enter Child value 1-4", priority = 4)
	public void Child() throws Exception {

		try {
			test = extent.createTest("Enter value 1 for Child", "Value in numbers");
			WebElement adultNo = driver.findElement(uimap.getLocator("child_text_field"));
			adultNo.clear();
			adultNo.sendKeys(datafile.getData("ChildOne"));
			Thread.sleep(1000);

			if (datafile.getData("ChildOne").equals("1")) {
				test.pass(datafile.getData("ChildOne") + " equal " + "one");
			} else
				test.log(Status.FAIL, datafile.getData("ChildOne") + "does not equal " + "one");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
		}
	}

	// Enter Date of Birth
	@Test(description = "Enter DOB", priority = 5)
	public void DOB() throws Exception {

		try {
			test = extent.createTest("Enter value for DOB", "Value in numbers");
			WebElement DOBdate = driver.findElement(uimap.getLocator("DOB_text_fieldD"));
			DOBdate.clear();
			DOBdate.sendKeys(datafile.getData("DOBD"));
			Thread.sleep(1000);

			WebElement DOBmonth = driver.findElement(uimap.getLocator("DOB_text_fieldM"));
			DOBmonth.clear();
			DOBmonth.sendKeys(datafile.getData("DOBM"));
			Thread.sleep(1000);

			WebElement DOByear = driver.findElement(uimap.getLocator("DOB_text_fieldY"));
			DOByear.clear();
			DOByear.sendKeys(datafile.getData("DOBY"));
			Thread.sleep(1000);

			if (driver.getTitle().contains("Health Insurance")) {
				test.pass(driver.getTitle() + " contain " + "Health Insurance");
			} else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Health Insurance");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
		}
	}

	// Select Eldest Member Gender
	@Test(description = "Select Eldest Member Gender", priority = 6)
	public void EldestMember() throws Exception {

		try {
			test = extent.createTest("Select eldest member gender", "Value in numbers");
			WebElement gender = driver.findElement(uimap.getLocator("eldest_member_gender_male"));
			gender.click();
			Thread.sleep(1000);

			if (datafile.getData("Elder_member_gender").equals("Female")) {
				test.pass(datafile.getData("Elder_member_gender") + " equal " + "Female");
			} else
				test.log(Status.FAIL, datafile.getData("Elder_member_gender") + " doesn't equal " + "Female");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
		}
	}

	// Select City from the list
	@Test(description = "Select City from list", priority = 7)
	public void City() throws Exception {

		try {
			test = extent.createTest("Select City From the List", "select by enter text");
			WebElement city = driver.findElement(uimap.getLocator("City_Input"));
			city.click();
			Thread.sleep(1000);
			city.sendKeys(datafile.getData("City"));
			WebElement cityclick = driver.findElement(uimap.getLocator("Click_city"));
			cityclick.click();
			
			if (datafile.getData("City").equals("New Delhi, Delhi")) {
				test.pass(datafile.getData("City") + " equal " + "New Delhi, Delhi");
			} else
				test.log(Status.FAIL, datafile.getData("City") + " doesn't equal " + "New Delhi, Delhi");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
		}
	}

	// Click On Get Live Quotes Button
	@Test(description = "Click Get Live Quotes Button", priority = 8)
	public void GetLiveQuotes() throws Exception {

		try {
			test = extent.createTest("Click get live quotes button", "Value in numbers");
			WebElement buttonlivequote = driver.findElement(uimap.getLocator("Click_GetLiveQuotes"));
			buttonlivequote.click();
			Thread.sleep(1000);
			if (driver.getTitle().contains("Easypolicy")) {
				test.pass(driver.getTitle() + " contain " + "Easypolicy");
			} else
				test.log(Status.FAIL, driver.getTitle() + " contain " + "Health Insurance");
			test.fail("details").addScreenCaptureFromPath("2.png");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
		}
	}

	@Test(description = "Fill Customer Name in Customer Popup", priority = 9)
	public void CustomerPopup() throws Exception {

		try {
			Thread.sleep(8000);
			test = extent.createTest("Fill Name in Customer Popup", "Name Should be entered");
			WebElement Custname = driver.findElement(uimap.getLocator("Customer_Name"));
			Custname.sendKeys(datafile.getData("Customername"));
			// String title = driver.getTitle();
			if (datafile.getData("Customername").equals("Vijay Kumar")) {
				test.pass(datafile.getData("Customername") + " contain " + "Vijay");
			} else
				test.log(Status.FAIL, datafile.getData("Customername") + " Input Is InCorrect");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			
		}
	}

	@Test(description = "Fill Customer Email in Customer Popup", priority = 10)
	public void CustomerEmail() throws Exception {

		try {
			test = extent.createTest("Fill Email in Customer Popup", "Email Should be entered");
			WebElement Custemail = driver.findElement(uimap.getLocator("Customer_Email"));
			Custemail.sendKeys(datafile.getData("CustomerEmailId"));
			// String title = driver.getTitle();
			Assert.assertEquals("vijay.kumar@ep.com", datafile.getData("CustomerEmailId"));
			
			if (datafile.getData("CustomerEmailId").equals("vijay.kumar@ep.com")) {
				test.pass(datafile.getData("CustomerEmailId") + " contain " + "binny");
			} else
				test.log(Status.FAIL, datafile.getData("CustomerEmailId") + " Input Is InCorrect");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			
		}
	}

	@Test(description = "Fill Customer Mobile Number in Customer Popup", priority = 11)
	public void CustomerMobno() throws Exception {

		try {
			test = extent.createTest("Fill Mobile No in Customer Popup", "Mobile No Should be entered");
			WebElement CustMobNo = driver.findElement(uimap.getLocator("Customer_Number"));
			CustMobNo.sendKeys(datafile.getData("CustomerMobileNo"));
			// String title = driver.getTitle();
			Assert.assertEquals("6789678967", datafile.getData("CustomerMobileNo"));
			
			if (datafile.getData("CustomerMobileNo").equals("6789678967")) {
				test.pass(datafile.getData("CustomerMobileNo") + " contain " + "6789678967");
			} else
				test.log(Status.FAIL, datafile.getData("CustomerMobileNo") + " Input Is InCorrect");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			
		}
	}

	@Test(description = "Click Proceed button on Customer Popup", priority = 12)
	public void CustomerProceed() throws Exception {

		try {
			test = extent.createTest("Click on proceed button",
					"Button Should be clicked and quotes page should be displayed");
			WebElement CustMobNo = driver.findElement(uimap.getLocator("Click_Proceed_Button"));
			CustMobNo.click();
			if (driver.getTitle().contains("Claim Support")) {
				test.pass(driver.getTitle() + " contain " + "Claim Support");

			} else
				test.log(Status.FAIL, driver.getTitle() + "doesn't contain " + "Claim Support");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
		}
	}

	@Test(description = "Quotes page journey", priority = 13)
	public void QuotesBuyNow() throws Exception {
		driver.manage().deleteAllCookies();
		String urlquotes = driver.getCurrentUrl();
		System.out.println(urlquotes);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		List<WebElement> buyNowElement = driver.findElements(By.xpath("//span[text()='BUY NOW']"));
		String winScroll = "window.scrollBy(0,300)";
		RemoteWebDriver rwd = (RemoteWebDriver) driver;
		for (int i = 1; i <= buyNowElement.size() - 19; i++) {
			Thread.sleep(10000);
			driver.findElement(By.xpath("(//span[text()='BUY NOW'])[" + i + "]")).click();
			Thread.sleep(10000);
			String url = driver.getCurrentUrl();
			System.out.println(url);
			Thread.sleep(5000);
			if (i == 6) {
				CignaProposal cp = new CignaProposal();
				cp.driver = driver;
				cp.uimap = uimap;
				cp.datafile = datafile;
				cp.workingDir = workingDir;
				String name = "abc";
				cp.name = name;
				cp.CignasuiteSetUp();
				cp.CignaProposalFill();
				//cp.fetcheName();
				cp.CignaProposalFillName(name);				
				cp.FillLastName();
				cp.EmailID();
				cp.MobileNo();
				cp.Profession();
				cp.MaritalStatus();
				cp.AddressOne();
				cp.AddressTwo();
				cp.PinCode();
				cp.ProposerSaveandContinue();
				cp.AdultMaritalStatus();
				cp.AdultWeight();
				cp.AdultHeightFeet();
				cp.AdultHeightInches();
				cp.AdultSaveandContinue();
				cp.MedicalHistory();
				cp.MedicalHistorySaveAndContinue();
				cp.NomineeName();
				cp.NomineeRelation();
				cp.ProceedToPayment();
				Thread.sleep(5000);
				cp.CignasuiteTearDown();
			}
			/*
			 * if(i==3){ ReligareProposal rp=new ReligareProposal(driver);
			 * rp.enterPopupDetail(driver); Thread.sleep(3000);
			 * rp.enterProposarDetails(driver); Thread.sleep(3000);
			 * rp.enterMemberDetails(driver); }
			 */
			driver.navigate().to(urlquotes);

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

		try {

			// Get current working directory and load the data file
			workingDir = System.getProperty("user.dir");
			datafile = new UIMap(workingDir + "\\Resources\\datafile.properties");

			// Get the object map file
			uimap = new UIMap(workingDir + "\\Resources\\locator.properties");
			extent = ExtentManager.GetExtent();

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
		
		extent.flush();
		// driver.close();
		// driver.quit();
		// endreport();
		SendMailSSL.execute("HealthExtentReport.html");
		
	}

	 
/*	@AfterTest
	public void endreport() {
		driver.close();
		// extent.flush();
		// extent.close();
	}*/

}
