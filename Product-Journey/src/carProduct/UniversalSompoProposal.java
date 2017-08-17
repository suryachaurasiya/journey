package carProduct;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import extentReport.ExtentManager;
import healthProduct.HealthProductJourneyWithExcel;
import healthProduct.UIMap;

public class UniversalSompoProposal {
	
	public WebDriver driver;
	public UIMap uimap;
	public UIMap datafile;
	public String workingDir;
	public String name;
	public static ExtentReports extent;
	public static ExtentTest test;
	ExtentHtmlReporter htmlReporter;
	
	// Declare An Excel Work Book
		HSSFWorkbook workbook;
		// Declare An Excel Work Sheet
		HSSFSheet sheet;
		// Declare A Map Object To Hold TestNG Results
		Map<String, Object[]> TestNGResults;
		
		@Test(description = "Click on drop down and select the salutation", priority = 1)
		public void CignaProposalFill() throws Exception {

		try {
			test = extent.createTest("Click on drop down", "Drop Down should be selected");
			test.assignCategory("Cigna Proposal");
			WebElement salutation = driver.findElement(uimap.getLocator("Salutation_input"));
			salutation.click();
			WebElement salutationselect = driver.findElement(uimap.getLocator("Salutation_input_select"));
			salutationselect.click();
			Thread.sleep(3000);
			TestNGResults.put("2", new Object[] { 1d, "Click on proceed button", "Button Should be clicked and quotes page should be displayed", "Pass" });
			if (driver.getTitle().contains("Claim Support")) {
				test.pass(driver.getTitle() + " contain " + "Claim Support");	
			}
			else
				test.log(Status.FAIL, driver.getTitle() + "doesn't contain " + "Claim Support");	
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("2", new Object[] { 1d, "Click on proceed button", "Button Should be clicked and quotes page should be displayed", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	
	@Test(description = "Fill first name in proposal page", priority = 2)
	public void CignaProposalFillName() throws Exception {

		try {
			test = extent.createTest("Fill first name in proposal page", "Fill Name");
			test.assignCategory("Cigna Proposal");
			WebElement firstname = driver.findElement(uimap.getLocator("First_name_input"));
			firstname.sendKeys(datafile.getData("firstname"));
			
			TestNGResults.put("3", new Object[] { 2d, "Fill first name in proposal page", "Name should be filled in the input box", "Pass" });
			if (driver.getTitle().contains("Claim Support")) {
				test.pass(driver.getTitle() + " contain " + "Claim Support");
				//test.log(Status.PASS, datafile.getData("CustomerEmailId")+" Input Is Correct");
				//test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./2.jpg"));
				//test.log(Status.INFO, test.addScreenCapture(ExtentManager.this(driver, "./Send")));
				
			}
			else
				test.log(Status.FAIL, driver.getTitle() + "doesn't contain " + "Claim Support");	
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("3", new Object[] { 2d, "Fill first name in proposal page", "Name should be filled in the input box", "Fail" });
			//Assert.assertTrue(false);
		}
		
		
		
	}
	@Test(description = "Fill Last Name", priority = 3)
	public void FillLastName() throws Exception {

		try {
			test = extent.createTest("Fill last name in proposal page", "Fill Last name");	
			test.assignCategory("Cigna Proposal");
			WebElement lastname = driver.findElement(uimap.getLocator("Last_name_input"));
			lastname.clear();
			lastname.click();
			lastname.sendKeys(datafile.getData("lastname"));
			Thread.sleep(1000);
			TestNGResults.put("4", new Object[] { 3d, "Fill last name in proposal page", "Name should be filled in the input box", "Pass" });
			if (driver.getTitle().contains("Claim Support")) {
				test.pass(driver.getTitle() + " contain " + "Claim Support");
				//test.log(Status.PASS, datafile.getData("CustomerEmailId")+" Input Is Correct");
				//test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./2.jpg"));
				//test.log(Status.INFO, test.addScreenCapture(ExtentManager.this(driver, "./Send")));
				
			}
			else
				test.log(Status.FAIL, driver.getTitle() + "doesn't contain " + "Claim Support");	
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("4", new Object[] { 3d, "Fill last name in proposal page", "Name should be filled in the input box", "Fail" });
			//Assert.assertTrue(false);
		}
			
	}
	@Test(description = "Fill EmailId", priority = 4)
	public void EmailID() throws Exception {

		try {
			test = extent.createTest("Fill Email Id in proposal page", "Fill Email Id");	
			WebElement emailid = driver.findElement(uimap.getLocator("Email_id_input"));
			emailid.sendKeys(datafile.getData("emailid"));
			Thread.sleep(1000);
			TestNGResults.put("5", new Object[] { 4d, "Fill Email Id in proposal page", "Email id should be filled in the input box", "Pass" });
			if (driver.getTitle().contains("Claim Support")) {
				test.pass(driver.getTitle() + " contain " + "Claim Support");
				//test.log(Status.PASS, datafile.getData("CustomerEmailId")+" Input Is Correct");
				//test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./2.jpg"));
				//test.log(Status.INFO, test.addScreenCapture(ExtentManager.this(driver, "./Send")));
				
			}
			else
				test.log(Status.FAIL, driver.getTitle() + "doesn't contain " + "Claim Support");	
		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("5", new Object[] { 4d, "Fill Email Id in proposal page", "Email Id should be filled in the input box", "Fail" });
			//Assert.assertTrue(false);
		}
			
			
	}
	@Test(description = "Fill Mobile no in proposal page", priority =5)
	public void MobileNo() throws Exception {
		try {
			test = extent.createTest("Fill Mobile no in proposal page", "Mobile no should be filled");
			WebElement mobileno = driver.findElement(uimap.getLocator("Mobile_input"));
			mobileno.sendKeys(datafile.getData("mobileno"));
			Thread.sleep(1000);
			TestNGResults.put("6", new Object[] { 5d, "Fill Mobile no in proposal page", "Mobile no should be filled", "Pass" });
			
			if (datafile.getData("mobileno").equals("6789678967")) {
				test.pass(datafile.getData("mobileno") + " is " + "correct");
			} else
				test.log(Status.FAIL, datafile.getData("mobileno") + " is not " + "correct");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("6", new Object[] { 5d, "Fill Mobile no in proposal page", "Mobile no should be filled", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Fill Profession From Drop Down", priority = 6)
	public void Profession() throws Exception {

		try {
			test = extent.createTest("Select & fill Profession", "Profession should be selected");
			WebElement profession = driver.findElement(uimap.getLocator("Profession_input"));
			profession.click();
			WebElement professionSelect = driver.findElement(uimap.getLocator("Profession_input_select"));
			professionSelect.click();
			Thread.sleep(1000);
			TestNGResults.put("7", new Object[] { 6d, "Select & fill Profession", "Profession should be selected", "Pass" });
			
			if (datafile.getData("Profession_input_select").equals("Private Service")) {
				test.pass(datafile.getData("Profession_input_select") + " is " + "correct");
			} else
				test.log(Status.FAIL, datafile.getData("Profession_input_select") + " is not " + "correct");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("7", new Object[] { 6d, "Select & fill Profession", "Profession should be selected", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Fill DOB", priority = 7)
	public void DOB() throws Exception {

		try {
			test = extent.createTest("Enter value for DOB", "Value in numbers");
			WebElement DOBdate = driver.findElement(uimap.getLocator("DOB_DD_Input"));
			DOBdate.clear();
			DOBdate.sendKeys(datafile.getData("dob_dd"));
			Thread.sleep(1000);

			WebElement DOBmonth = driver.findElement(uimap.getLocator("DOB_MM_Input"));
			DOBmonth.clear();
			DOBmonth.sendKeys(datafile.getData("dob_mm"));
			Thread.sleep(1000);

			WebElement DOByear = driver.findElement(uimap.getLocator("DOB_YYYY_Input"));
			DOByear.clear();
			DOByear.sendKeys(datafile.getData("dob_yyyy"));
			Thread.sleep(1000);
			Assert.assertEquals("1986", datafile.getData("dob_yyyy"));
			TestNGResults.put("8", new Object[] { 7d, "Enter value for DOB", "Value in numbers", "Pass" });
			

			if (driver.getTitle().contains("Health Insurance")) {
				test.pass(driver.getTitle() + " contain " + "Health Insurance");
			} else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Health Insurance");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("8", new Object[] { 7d, "Enter DOB detail", "Date of Birth", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Fill Address", priority = 8)
	public void AddressOne() throws Exception {

		try {
			test = extent.createTest("Click on Address Input box & fill Value", "Address should be filled");
			WebElement address1 = driver.findElement(uimap.getLocator("Addressone_Input"));
			address1.sendKeys(datafile.getData("addressone"));
			Thread.sleep(1000);
			TestNGResults.put("9", new Object[] { 8d, "Click on Address Input box & fill Value", "Address should be filled", "Pass" });
			
			if (datafile.getData("addressone").equals("new delhi, testing")) {
				test.pass(datafile.getData("addressone") + " is " + "correct");
			} else
				test.log(Status.FAIL, datafile.getData("addressone") + " is not " + "correct");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("9", new Object[] { 8d, "Click on Address Input box & fill Value", "Address should be filled", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Fill Address", priority = 9)
	public void AddressTwo() throws Exception {

		try {
			test = extent.createTest("Click on Address Input box & fill Value", "Address should be filled");
			WebElement address2 = driver.findElement(uimap.getLocator("Addresstwo_Input"));
			address2.sendKeys(datafile.getData("addresstwo"));
			Thread.sleep(1000);
			TestNGResults.put("10", new Object[] { 9d, "Click on Address Input box & fill Value", "Address should be filled", "Pass" });
			
			if (datafile.getData("addresstwo").equals("new delhi, testing")) {
				test.pass(datafile.getData("addresstwo") + " is " + "correct");
			} else
				test.log(Status.FAIL, datafile.getData("addresstwo") + " is not " + "correct");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("10", new Object[] { 9d, "Click on Address Input box & fill Value", "Address should be filled", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Fill City", priority = 10)
	public void PinCode() throws Exception {

		try {
			test = extent.createTest("Click on City Input box & fill Value", "City should be filled");
			WebElement city = driver.findElement(uimap.getLocator("City_Input"));
			city.click();
			Thread.sleep(1000);
			WebElement cityselect = driver.findElement(uimap.getLocator("City_Input_select"));
			cityselect.click();
			Thread.sleep(1000);
			TestNGResults.put("11", new Object[] { 10d, "Click on City Input box & fill Value", "City should be filled", "Pass" });
			
			if (uimap.getLocator("City_Input_select").equals("New Delhi, Delhi")) {
				test.pass(uimap.getLocator("City_Input_select") + " is " + "correct");
			} else
				test.log(Status.FAIL, uimap.getLocator("City_Input_select") + " is not " + "correct");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("11", new Object[] { 10d, "Click on City Input box & fill Value", "City should be filled", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Click Save & Continue", priority = 11)
	public void ProposerSaveandContinue() throws Exception {

		try {
			test = extent.createTest("Click on Save & Continue Button", "Button should be clicked");
			WebElement savencontinue = driver.findElement(uimap.getLocator("Save_Continue_button1"));
			savencontinue.click();
			Thread.sleep(1000);
			TestNGResults.put("12", new Object[] { 11d, "Click on Save & Continue Button", "Button should be clicked", "Pass" });
			
			if (driver.getTitle().contains("Easypolicy")) {
				test.pass(driver.getTitle() + " contain " + "Easypolicy");
				test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./1.jpg"));
			}

			else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Easypolicy");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("12", new Object[] { 11d, "Click on Save & Continue Button", "Button should be clicked", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Fill Registration no", priority = 12)
	public void Registrationno() throws Exception {

		try {
			test = extent.createTest("Fill Registration no", "Registration no should be filled");
			WebElement registrationno = driver.findElement(uimap.getLocator("Registration_Input"));
			registrationno.sendKeys(datafile.getData("registrationno"));
			Thread.sleep(1000);
			TestNGResults.put("13", new Object[] { 12d, "Fill Registration no", "Registration no should be filled", "Pass" });
			
			if (datafile.getData("registrationno").equals("AB4001")) {
				test.pass(datafile.getData("registrationno") + " is " + "Correct");
				test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./1.jpg"));
			}

			else
				test.log(Status.FAIL, datafile.getData("registrationno") + " is not " + "Correct");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("13", new Object[] { 12d, "Fill Registration no", "Registration no should be filled", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Fill Engine no", priority = 13)
	public void Engineno() throws Exception {

		try {
			test = extent.createTest("Fill Engine no", "Engine no should be filled");
			WebElement adultweight = driver.findElement(uimap.getLocator("Engine_no_input"));
			adultweight.sendKeys(datafile.getData("engineno"));
			Thread.sleep(1000);
			TestNGResults.put("14", new Object[] { 13d, "Fill Engine no", "Engine no should be filled", "Pass" });
			
			if (datafile.getData("engineno").equals("789787986465")) {
				test.pass(datafile.getData("engineno") + " is " + "Correct");
				test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./1.jpg"));
			}

			else
				test.log(Status.FAIL, datafile.getData("engineno") + " is not " + "Correct");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("14", new Object[] { 13d, "Fill Engine no", "Engine no should be filled", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Fill Chasis no", priority = 14)
	public void Chasisno() throws Exception {

		try {
			test = extent.createTest("Fill Chasis no", "Chasis no should be filled");
			WebElement chasisno = driver.findElement(uimap.getLocator("Chasis_no_input"));
			chasisno.sendKeys(datafile.getData("chasisno"));
			Thread.sleep(1000);
			TestNGResults.put("15", new Object[] { 14d, "Fill Chasis no", "Chasis no should be filled", "Pass" });
			
			if (datafile.getData("chasisno").equals("5")) {
				test.pass(datafile.getData("chasisno") + " is " + "Correct");
				test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./1.jpg"));
			}

			else
				test.log(Status.FAIL, datafile.getData("chasisno") + " is not " + "Correct");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("15", new Object[] { 14d, "Fill Chasis no", "Chasis no should be filled", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Click on same address checkbox", priority = 15)
	public void SameAddress() throws Exception {

		try {
			test = extent.createTest("Click on same address checkbox", "Adult Height In inches should be filled");
			WebElement sameaddress = driver.findElement(uimap.getLocator("Same_address_checkbox"));
			sameaddress.click();
			Thread.sleep(1000);
			TestNGResults.put("16", new Object[] { 15d, "Click on same address checkbox", "given address should be filled", "Pass" });
			
			if (driver.getTitle().contains("Claim Support")) {
				test.pass(driver.getTitle() + " contain " + "Claim Support");
				//test.log(Status.PASS, datafile.getData("CustomerEmailId")+" Input Is Correct");
				//test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./2.jpg"));
				//test.log(Status.INFO, test.addScreenCapture(ExtentManager.this(driver, "./Send")));
				
			}
			else
				test.log(Status.FAIL, driver.getTitle() + "doesn't contain " + "Claim Support");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("16", new Object[] { 15d, "Click on same address checkbox", "given address should be filled", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Save and Continue button", priority = 16)
	public void ChildSalutation() throws Exception {

		try {
			test = extent.createTest("Click on save and continue button", "Click on save and continue button");
			WebElement savecontinue = driver.findElement(uimap.getLocator("Save_Continue_button2"));
			savecontinue.click();
			Thread.sleep(1000);
			TestNGResults.put("17", new Object[] { 16d, "Click on save and continue button", "Click on save and continue button", "Pass" });
			
			if (driver.getTitle().contains("Claim Support")) {
				test.pass(driver.getTitle() + " contain " + "Claim Support");
				//test.log(Status.PASS, datafile.getData("CustomerEmailId")+" Input Is Correct");
				//test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./2.jpg"));
				//test.log(Status.INFO, test.addScreenCapture(ExtentManager.this(driver, "./Send")));
				
			}
			else
				test.log(Status.FAIL, driver.getTitle() + "doesn't contain " + "Claim Support");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("17", new Object[] { 16d, "Click on save and continue button", "Click on save and continue button", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Fill Policy Expiry Date", priority = 17)
	public void PolicyExpiryDate() throws Exception {

		try {
			test = extent.createTest("Fill Policy Expiry Date", "Value in numbers");
			WebElement DOBdate = driver.findElement(uimap.getLocator("Policy_expiry_date_DD"));
			DOBdate.clear();
			DOBdate.sendKeys(datafile.getData("policyexpirydate_dd"));
			Thread.sleep(1000);

			WebElement DOBmonth = driver.findElement(uimap.getLocator("Policy_expiry_date_MM"));
			DOBmonth.clear();
			DOBmonth.sendKeys(datafile.getData("policyexpirydate_mm"));
			Thread.sleep(1000);

			WebElement DOByear = driver.findElement(uimap.getLocator("Policy_expiry_date_YYYY"));
			DOByear.clear();
			DOByear.sendKeys(datafile.getData("policyexpirydate_yyyy"));
			Thread.sleep(1000);
			Assert.assertEquals("2017", datafile.getData("policyexpirydate_yyyy"));
			TestNGResults.put("18", new Object[] { 17d, "Fill Policy Expiry Date", "Value in numbers", "Pass" });
			

			if (driver.getTitle().contains("Car Insurance")) {
				test.pass(driver.getTitle() + " contain " + "Car Insurance");
			} else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Car Insurance");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("18", new Object[] { 17d, "Fill Policy Expiry Date", "Date of Birth", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Existing Policy No", priority = 18)
	public void Existingpolicyno() throws Exception {

		try {
			test = extent.createTest("Fill Existing Policy No In Input Box", "Existing Policy No should be filled");
			WebElement existingpolicyno = driver.findElement(uimap.getLocator("Existing_policy_no"));
			existingpolicyno.sendKeys(datafile.getData("existingpolicyno"));
			Thread.sleep(1000);
			TestNGResults.put("19", new Object[] { 18d, "Fill Existing Policy No In Input Box", "Existing Policy No should be filled", "Pass" });
			
			if (datafile.getData("existingpolicyno").equals("87965464655456")) {
				test.pass(datafile.getData("existingpolicyno") + " is " + "Correct");
				test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./1.jpg"));
			}

			else
				test.log(Status.FAIL, datafile.getData("existingpolicyno") + " is not " + "Correct");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("19", new Object[] { 18d, "Fill Existing Policy No In Input Box", "Existing Policy No should be filled", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Fill Existing Insurer Name", priority = 19)
	public void ChildLastName() throws Exception {

		try {
			test = extent.createTest("Fill Existing Insurer Name", "Existing Insurer Name should be filled");
			WebElement insurername = driver.findElement(uimap.getLocator("Existing_Insurer"));
			insurername.click();
			insurername.sendKeys(datafile.getData("existinginsurer"));
			Thread.sleep(1000);
			WebElement insurernameSelect = driver.findElement(uimap.getLocator("Existing_Insurer_select"));
			insurernameSelect.click();
			Thread.sleep(1000);
			TestNGResults.put("20", new Object[] { 19d, "Fill Existing Insurer Name", "Existing Insurer Name should be filled", "Pass" });
			
			if (datafile.getData("existinginsurer").equals("HDFC")) {
				test.pass(datafile.getData("existinginsurer") + " is " + "Correct");
				test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./1.jpg"));
			}

			else
				test.log(Status.FAIL, datafile.getData("existinginsurer") + " is not " + "Correct");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("20", new Object[] { 19d, "Fill Existing Insurer Name", "Existing Insurer Name should be filled", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Save and Continue Button3", priority = 20)
	public void ChildWeight() throws Exception {

		try {
			test = extent.createTest("Click on save and continue button", "Button should be filled");
			WebElement childweight = driver.findElement(uimap.getLocator("Save_Continue_button3"));
			childweight.click();
			Thread.sleep(1000);
			TestNGResults.put("21", new Object[] { 20d, "Click on save and continue button", "Click on save and continue button", "Pass" });
			if (driver.getTitle().contains("Car Insurance")) {
				test.pass(driver.getTitle() + " contain " + "Car Insurance");
			} else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Car Insurance");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("21", new Object[] { 20d, "Click on save and continue button", "Click on save and continue button", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Fill Nominee name", priority = 21)
	public void Nominee() throws Exception {

		try {
			test = extent.createTest("Fill Nominee Name", "Nominee name should be filled");
			WebElement Nominee = driver.findElement(uimap.getLocator("Nominee_name"));
			Nominee.sendKeys(datafile.getData("nomineename"));
			Thread.sleep(1000);
			TestNGResults.put("22", new Object[] { 21d, "Fill Nominee Name", "Nominee name should be filled", "Pass" });
			
			if (datafile.getData("nomineename").equals("qa")) {
				test.pass(datafile.getData("nomineename") + " is " + "Correct");
				test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./1.jpg"));
			}

			else
				test.log(Status.FAIL, datafile.getData("nomineename") + " is not " + "Correct");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("22", new Object[] { 21d, "Fill Nominee Name", "Nominee name should be filled", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Fill Nominee DOB", priority = 22)
	public void NomineeDOB() throws Exception {

		try {
			test = extent.createTest("Enter value for DOB", "Value in numbers");
			WebElement DOBdate = driver.findElement(uimap.getLocator("nominee_dob_DD"));
			DOBdate.clear();
			DOBdate.sendKeys(datafile.getData("nominee_dob_dd"));
			Thread.sleep(1000);

			WebElement DOBmonth = driver.findElement(uimap.getLocator("nominee_dob_MM"));
			DOBmonth.clear();
			DOBmonth.sendKeys(datafile.getData("nominee_dob_mm"));
			Thread.sleep(1000);

			WebElement DOByear = driver.findElement(uimap.getLocator("nominee_dob_YYYY"));
			DOByear.clear();
			DOByear.sendKeys(datafile.getData("nominee_dob_yyyy"));
			Thread.sleep(1000);
			Assert.assertEquals("2012", datafile.getData("nominee_dob_yyyy"));
			TestNGResults.put("23", new Object[] { 22d, "Enter value for DOB", "Value in numbers", "Pass" });
			

			if (driver.getTitle().contains("Car Insurance")) {
				test.pass(driver.getTitle() + " contain " + "Car Insurance");
			} else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Car Insurance");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("23", new Object[] { 22d, "Enter DOB detail", "Date of Birth", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	
	
	@Test(description = "Fill Relation Nominee", priority = 22)
	public void RelationNominee() throws Exception {

		try {
			test = extent.createTest("Fill Child Height from dropdown", "Child Height In inches should be filled");
			WebElement RelationNominee = driver.findElement(uimap.getLocator("Relation_input"));
			RelationNominee.click();
			RelationNominee.sendKeys(datafile.getData("nominee_relation"));
			Thread.sleep(1000);
			WebElement RelationNomineeSelect = driver.findElement(uimap.getLocator("Relation_select"));
			RelationNomineeSelect.click();
			Thread.sleep(1000);
			TestNGResults.put("23", new Object[] { 22d, "Fill Child Height from dropdown", "Child Height In inches should be filled", "Pass" });
			
			if (datafile.getData("nominee_relation").equals("Son")) {
				test.pass(datafile.getData("nominee_relation") + " is " + "Correct");
				test.log(Status.INFO, "Snapshot" + test.addScreenCaptureFromPath("./1.jpg"));
			}

			else
				test.log(Status.FAIL, datafile.getData("nominee_relation") + " is not " + "Correct");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("23", new Object[] { 22d, "Fill Child Height from dropdown", "Child Height In inches should be filled", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	@Test(description = "Click Proceed to payment button", priority = 23)
	public void PaymentProceed() throws Exception {

		try {
			test = extent.createTest("Click Proceed to payment button", "Button should be clicked");
			WebElement proceedtopayment = driver.findElement(uimap.getLocator("Proceed_to_payment"));
			proceedtopayment.click();
			Thread.sleep(1000);
			TestNGResults.put("24", new Object[] { 23d, "Click Proceed to payment button", "Button should be clicked", "Pass" });
			
			if (driver.getTitle().contains("Car Insurance")) {
				test.pass(driver.getTitle() + " contain " + "Car Insurance");
			} else
				test.log(Status.FAIL, driver.getTitle() + " doesn't contain " + "Car Insurance");

		} catch (Exception e) {
			test.log(Status.ERROR, e.getMessage());
			TestNGResults.put("24", new Object[] { 23d, "Click Proceed to payment button", "Button should be clicked", "Fail" });
			//Assert.assertTrue(false);
		}
	}
	
	// write Test Cases in to excel file
		@BeforeClass(alwaysRun = true)
		public void UniversalsuiteSetUp() {

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
				datafile = new UIMap(workingDir + "\\Resources\\cardatafile.properties");

				// Get the object map file
				uimap = new UIMap(workingDir + "\\Resources\\carlocator.properties");
				extent = ExtentManager.GetExtentCar();

			} catch (Exception e) {
				throw new IllegalStateException("Can't start the Firefox Web Driver", e);
			}

		}

		@AfterClass
		public void UniversalsuiteTearDown() throws Exception {
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
				FileOutputStream out = new FileOutputStream(new File("UniversalSompoCarProposalReport.xls"));
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
			CarSendMail.execute("UniversalSompoExtentReport.html");
		}

		@AfterTest
		public void endreport() {
			driver.close();
			// extent.flush();
			// extent.close();
		}


}
