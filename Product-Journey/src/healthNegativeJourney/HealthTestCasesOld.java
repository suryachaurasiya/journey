package healthNegativeJourney;

	import java.awt.AWTException;
	import java.awt.HeadlessException;
	import java.io.File;
	import java.io.IOException;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;
	import jxl.read.biff.BiffException;
	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxProfile;
	import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;
	import healthNegativeJourney.ReadExcel;

	public class HealthTestCasesOld {

		public static  WebDriver driver=null;
		//Open Browser
		@BeforeClass
		public void browser() 
		{
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile myprofile = profile.getProfile("TestAutomation");
			driver=new FirefoxDriver(myprofile);	
		}		
		//Open Web_site
		@Test(priority=0)
		public void openBrowser() throws Exception
		{
			driver.get("http://www.easypolicy.com/");		
			driver.manage().window().maximize();		
			driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);		
		}
		//Click on Heath Insurance 
		@Test(priority=1)
		public void clickOnHealth() throws InterruptedException
		{
			driver.findElement(By.xpath("html/body/div[1]/nav/div/div[2]/ul[1]/li[3]/a")).click();
			Thread.sleep(8000);
		}
		
		//Call Data From ReadExcel Class
			@DataProvider(name = "EnterAdultValues")	
			public Object[][] fetcheAdultNo() throws BiffException, IOException 
			{		
				ReadExcel RE = new ReadExcel();	
				Object[][] testdata = RE.DataFromNoOfAdultSheet();
				return testdata;	
			}
		//validations for adult text field 
		@Test(priority=2, dataProvider = "EnterAdultValues")
		public void adultValidation(String adult) throws InterruptedException
		{		
			WebElement adultTextField= driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[2]/md-input-container/input"));
			adultTextField.click();
			adultTextField.clear();
			adultTextField.sendKeys(adult);
			//Click on get live quotes
			clickOnCompareButton();		
			String adultvalidations=driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[2]/md-input-container/span")).getText();
			System.out.println(adultvalidations);		
		}
		@Test(priority=3)
		public void enterAdult() throws Exception
		{
			driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[2]/md-input-container/input")).click();
			driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[2]/md-input-container/input")).clear();
			driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[2]/md-input-container/input")).sendKeys("2");
			Thread.sleep(5000);
		}
		//Perform child negative testing
		@DataProvider(name = "EnterChildValues")	
		public Object[][] fetcheChildNo() throws BiffException, IOException 
		{		
			ReadExcel RE = new ReadExcel();	
			Object[][] testdata = RE.DataFromNoOfChildSheet();
			return testdata;	
		}
		@Test(priority=4, dataProvider = "EnterChildValues")
		public void childValidation(String child) throws InterruptedException
		{		
			WebElement childTextField= driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[3]/md-input-container/input"));
			childTextField.click();
			childTextField.clear();
			childTextField.sendKeys(child);
			//Click on get live quotes
			clickOnCompareButton();		
			//taking xpath of validation under text box
			String childvalidations=driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[3]/md-input-container/span")).getText();
			System.out.println(childvalidations);		
		}
		/*@DataProvider(name = "EnterDOBValues")	
		public Object[][] fetcheDOB() throws BiffException, IOException 
		{		
			ReadExcel RE = new ReadExcel();	
			Object[][] testdata = RE.DataFromNoOfDOBSheet();
			return testdata;	
		}
		@Test(priority=5, dataProvider = "EnterDOBValues")
		public void DOBValidation(String dob) throws InterruptedException
		{		
			WebElement DOBField= driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/div[2]"));
			DOBField.click();
			DOBField.clear();
			DOBField.sendKeys(dob);
			//Click on get live quotes
			clickOnCompareButton();		
			//taking xpath of validation under text box
			String dobvalidations=driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/span")).getText();
			System.out.println(dobvalidations);		
		}*/
		
		
		//Select Adult 
		/*@Test(priority=24)
		public void selectAdult()
		{
			driver.findElement(By.xpath("//img[@id='ContentPlaceHolder1_NewHealthBooking_imgup']")).click();
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-500)");
		}*/
		//Select Child
		/*@Test(priority=4)
		public void selectChild()
		{
			//Click only for one child
			//driver.findElement(By.xpath("//img[@id='ContentPlaceHolder1_NewHealthBooking_imgChildUp']")).click();
			
			//Click if more then one child
			driver.findElement(By.xpath("//img[@id='ContentPlaceHolder1_NewHealthBooking_imgup']")).click();
			for(int i=0; i<2;i++)
			{
			driver.findElement(By.xpath("//img[@id='ContentPlaceHolder1_NewHealthBooking_imgChildUp']")).click();
			}
		}*/
		//Select DOB
		/*@Test(priority=5)
		public void selectDOBHealth()
		{
			Select date = new Select(driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_NewHealthBooking_ucDateSelector_ddlDay']")));
			date.selectByIndex(3);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-500)");
			Select month = new Select(driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_NewHealthBooking_ucDateSelector_ddlMonth']")));
			month.selectByIndex(5);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-500)");
			Select years = new Select(driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_NewHealthBooking_ucDateSelector_ddlYear']")));
			years.selectByIndex(12);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-500)");
			
			clickOnCompareButton();
			String validations= driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_NewHealthBooking_lblError']/fieldset/div")).getText();
			System.out.println("Now DOB Validation Not Available In List"+validations);
		}*/
		//Select Radio Button 
		/*@Test(priority=6)
		public void selectMaleRadioButton()
		{
			WebElement   oRadioButt=  driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_NewHealthBooking_rdoGender']"));
			@SuppressWarnings("unused")
			boolean male=false;
			male =oRadioButt.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_NewHealthBooking_rdoGender_0']")).isSelected();
			
			if ( male=true)
			{
				
				oRadioButt.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_NewHealthBooking_rdoGender_1']")).click();
			}
			else
			{
				oRadioButt.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_NewHealthBooking_rdoGender_0']")).isSelected();
			}
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-500)");
		}*/
		//Call Data From ReadExcel Class
		/*@DataProvider(name = "InsuredPersonName")	
		public Object[][] fetchname() throws BiffException, IOException 
		{		
			ReadExcel RE = new ReadExcel();	
			Object[][] testdata = RE.DataFromNoOfAdultSheet();
			return testdata;	
		}
		//Name Field Test
		@Test(priority=7,dataProvider = "InsuredPersonName" )	
		public void testForNameFieldNegativeAndPositive(String name) throws InterruptedException, IOException
		 {
			
			System.out.println(name);
			WebElement EnterName = driver.findElement(By.id("ContentPlaceHolder1_NewHealthBooking_txtProposerName"));
			EnterName.click();
			EnterName.clear();
			EnterName.sendKeys(name);
			Thread.sleep(5000);
			clickOnCompareButton();
			
			Thread.sleep(5000);
			String namevalidation=driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_NewHealthBooking_lblError']/fieldset/div")).getText();		
			System.out.println("Validation For Name:-"+namevalidation);	
			
		 }*/
		//Call Data From ReadExcel Class
		/*@DataProvider(name = "EmailID")	
		public Object[][] fetchemail() throws BiffException, IOException 
		{		
			ReadExcel RE = new ReadExcel();	
			Object[][] testdata = RE.DataFromEmailIDSheet();
			return testdata;	
		}
		//Email ID Field Test
		@Test(priority=8, dataProvider="EmailID")
		public void testForEmailIDNegativeAndPositive(String email) throws InterruptedException, IOException
		{
			
			System.out.println(email);
			WebElement EnterEmail = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_NewHealthBooking_txtProposerEmail']"));
			EnterEmail.click();
			EnterEmail.clear();
			EnterEmail.sendKeys(email);
			Thread.sleep(5000);
			clickOnCompareButton();
			
			Thread.sleep(5000);
			String emailvalidation=driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_NewHealthBooking_lblError']/fieldset/div")).getText();
			System.out.println("Validation For Email_ID:-"+emailvalidation);
					
		}
		//Call Data from ReadExcel Class
		@DataProvider(name = "MobileNumber")	
		public Object[][] fetcheMobileNumber() throws BiffException, IOException 
		{		
			ReadExcel RE = new ReadExcel();	
			Object[][] testdata = RE.DataFromMobileNumberSheet();
			return testdata;	
		}
		//Mobile Number Test Cases
		@Test(priority=9, dataProvider="MobileNumber")
		public void testForMobileNumberNegativeAndPositive(String mobile) throws InterruptedException, IOException
		{
			
			System.out.println(mobile);
			WebElement EnterNumber = driver.findElement(By.xpath("//input[@id='ContentPlaceHolder1_NewHealthBooking_txtProposerMobile']"));
			EnterNumber.click();
			EnterNumber.clear();
			EnterNumber.sendKeys(mobile);
			Thread.sleep(5000);
			clickOnCompareButton();
			
			Thread.sleep(5000);
			String mobileNumbervalidation=driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_NewHealthBooking_lblError']/fieldset/div")).getText();
			System.out.println("Validation For Mobile Number:-"+mobileNumbervalidation);
					
		}*/
		//Data Call From ReadExcel Class
		@DataProvider(name = "CityInput")	
		public Object[][] fetcheCity() throws BiffException, IOException 
		{		
			ReadExcel RE = new ReadExcel();	
			Object[][] testdata = RE.DataFromCitySheet();
			return testdata;	
		}
		//City Test Cases
		@Test(priority=6, dataProvider="CityInput")
		public void testForCityNegativeAndPositive(String cityname) throws InterruptedException, IOException
		{
			
			System.out.println(cityname);
			WebElement EnterCity = driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[6]/md-input-container/md-autocomplete/md-autocomplete-wrap/input"));
			EnterCity.click();
			EnterCity.clear();
			EnterCity.sendKeys(cityname);
			Thread.sleep(5000);
			clickOnCompareButton();
			
			Thread.sleep(5000);
			String cityvalidation=driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[6]/md-input-container/span")).getText();
			System.out.println("Validation For City:-"+cityvalidation);
					
		}	
		//Enter City
		@Test(priority=7)
		public void enterCity() throws Exception
		{
			driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[6]/md-input-container/md-autocomplete/md-autocomplete-wrap/input")).click();
			driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[6]/md-input-container/md-autocomplete/md-autocomplete-wrap/input")).clear();
			driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[6]/md-input-container/md-autocomplete/md-autocomplete-wrap/input")).sendKeys("New Delhi");
			driver.findElement(By.xpath("html/body/md-virtual-repeat-container/div/div[2]/ul/li")).click();
			Thread.sleep(5000);
			//driver.findElement(By.className("ac_even ac_over")).click();	
			/*List<WebElement> el = driver.findElements(By.className("ac_even"));
			int a =el.size();
			System.out.println(el.size());
			List<WebElement> el2 = driver.findElements(By.className("ac_odd"));
	        int b =el2.size();
	        System.out.println(el2.size());
	        System.out.println(a+b);
	        for(int i=1;i<=a+b;i++)
	        {
	        	 System.out.println(driver.findElement(By.xpath("html/body/div[8]/ul/li["+i+"]")).getText());
	        	 if(i==1)
	             {
	             WebElement slectedCity=driver.findElement(By.xpath("html/body/div[8]/ul/li["+i+"]"));
	             Thread.sleep(2000);       
	             slectedCity.click();
	             }       
	        }*/
	        getscreenshot();
	        Thread.sleep(5000);
	        clickOnCompareButton();
	    //((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-500)");
		}
		
		//Print all plans and amount 
		@Test(priority=12)
		public void printAllPlansAndMaturityBenifitOnQuotesPage() throws InterruptedException
		{
			Thread.sleep(20000);
			List<WebElement> cols=driver.findElements(By.xpath("html/body/div[2]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[3]/div[3]/div[2]/md-card/md-card-content/div/div[2]/div/span"));
			int c=cols.size();                                 
			System.out.println(c);		
			for(WebElement col:cols)
			{						
					String a= col.getText(); 
					System.out.println("Price"+a);		
			}	
			
			List<WebElement> plans=driver.findElements(By.xpath("html/body/div[2]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[3]/div[3]/div[2]/md-card/md-card-content/div/div[1]/div[1]/div[1]/h5"));
			int p=plans.size();                                
			System.out.println(p);
			for(WebElement plan:plans)
			{		
					String a=plan.getText(); 
					System.out.println("Plan Nams:-"+a);		
			}
		}
		
		//Click on Compare Button, Accept PopUp and Take Screen Shot
		@Test(priority=13)
		public void clickOnCompareWithoutSelectingPlanTakePrinShot() throws InterruptedException, IOException, HeadlessException, AWTException
		{
			Thread.sleep(8000);
			//WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("")));
			//Pop_up not appear thats why code un_cummented
			driver.findElement(By.id("ContentPlaceHolder1_quotes_rptrQoutes_imgcompare")).click();	
			
			Thread.sleep(5000);					
			
			/*Alert alertCom = driver.switchTo().alert();			    
			BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	        ImageIO.write(image, "png", new File("D:\\ScreenShot\\ClickOnCompare"+System.currentTimeMillis()+".png"));     
	        alertCom.accept();*/
		}
		//Select plans and then click on compare button
		@Test(priority=14)
		public void selectPlansAndThenClickOnCompare() throws InterruptedException
		{
			driver.findElement(By.id("ContentPlaceHolder1_quotes_rptrQoutes_chkCompare_0")).click();
	        driver.findElement(By.id("ContentPlaceHolder1_quotes_rptrQoutes_chkCompare_1")).click();
	        driver.findElement(By.id("ContentPlaceHolder1_quotes_rptrQoutes_imgcompare")).click();
	        Thread.sleep(9000);
	        
	        Set <String> morehandles =driver.getWindowHandles();
	        Iterator<String> moreInfo = morehandles.iterator();
	        while (moreInfo.hasNext())
	        {
	       	 String parent = moreInfo.next();
	       	 String newwin = moreInfo.next();
	       	 driver.switchTo().window(newwin);
	       	 
	       	 driver.manage().window().maximize();
	       	 Thread.sleep(5000);
	       	 
	       	driver.findElement(By.id("ctl00_imgemail")).click();
			Thread.sleep(8000);
			
			driver.findElement(By.id("ComparisonPlans1_imgemail")).click();
			Thread.sleep(2000);
	       	 
	         Actions act = new Actions(driver);
			 act.doubleClick(driver.findElement(By.xpath("//img[@id='ComparisonPlans1_imgemail']"))).build().perform();
	       	 //driver.findElement(By.xpath(".//*[@name='ComparisonPlans1_imgemail']")).click();
	       	       	 
	       	 driver.findElement(By.id("ComparisonPlans1_btnOk")).click();
	       	 Thread.sleep(30000);
	       	 
	       	 driver.close();
	         driver.switchTo().window(parent);
	         }	 
		}
		//Click on Can't Decide
		@Test(priority=15)
		public void clickOnCantDecide() throws InterruptedException
		{
			 driver.findElement(By.id("ContentPlaceHolder1_quotes_rptrQoutes_imgbtncantdecide")).click();
	         Thread.sleep(30000);
	         
	         //New window open for phone number update
	         Set<String> cantDechandlers=driver.getWindowHandles();
	         Iterator<String>cantDecide=cantDechandlers.iterator();
	         while (cantDecide.hasNext())
	         {
	        	 //String parent = cantDecide.next();
	        	 String newwin = cantDecide.next();
	        	 driver.switchTo().window(newwin);        
	        	 driver.findElement(By.xpath(".//*[@id='aUpdateMobileCantDecide']")).click();
	        	 Thread.sleep(15000);
	        	 //If there is need to change phone number
	        	 driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_quotes_txtUpdatedContactNo']")).click();
	        	 driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_quotes_txtUpdatedContactNo']")).clear();
	        	 
	        	 driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_quotes_txtUpdatedContactNo']")).sendKeys("9871254730");
	        	 driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_quotes_imgbtnUpdateMobNo']")).click();
	        	 Thread.sleep(20000);
	        	 
	        	 //Verify confirmation text of updated phone number
	        	 String ActMsg;
	        	 ActMsg=driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_quotes_lblMobNoEntered']")).getText();
	        	 Thread.sleep(5000);
	        	 //Assert.assertEquals(ActMsg, "9871254730");
	        	 System.out.println(ActMsg);
	        	 driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_quotes_imgbtnCloseCantDecideDiv']")).click();
	        	 Thread.sleep(25000);
	        	 
	        	 //driver.close();
	             //driver.switchTo().window(parent);
	        	 
	         }
		}
		//Click On More Info
		@Test (priority=16)
		public void clickOnMoreInfo() throws InterruptedException
		{
			driver.findElement(By.id("ContentPlaceHolder1_quotes_rptrQoutes_imgmoreinfo_0")).click();
	        Thread.sleep(5000);
	        
	        //New Browser Open for More Info
	        Set <String> morehandles =driver.getWindowHandles();
	        Iterator<String> moreInfo = morehandles.iterator();
	        while (moreInfo.hasNext())
	        {
	       	 String parent = moreInfo.next();
	       	 String newwin = moreInfo.next();
	       	 driver.switchTo().window(newwin);
	       	 
	       	 driver.findElement(By.id("MoreInfo_imgemail")).click();
	       	 driver.manage().window().maximize();
	       	 Thread.sleep(2000);
	       	 driver.findElement(By.xpath("html/body/form/table/tbody/tr[4]/td/div[1]/table/tbody/tr[2]/td/input")).click();
	       	 Thread.sleep(15000);
	       	 
	       	 driver.close();
	         driver.switchTo().window(parent);
	        }
		}
		//click on Send as SMS
		@Test(priority=17)
		public void clickOnSendSMS() throws InterruptedException
		{
	    driver.findElement(By.id("ContentPlaceHolder1_quotes_rptrQoutes_lnkCallBacksms_0")).click();
	    
	    //Open Send SMS window 
	    Set<String> sendSMShandlers=driver.getWindowHandles();
	    Iterator<String>sendSMS=sendSMShandlers.iterator();
	    while (sendSMS.hasNext())
	    	{
	   	 	//String parent = sendSMS.next();
	   	 	String newwin = sendSMS.next();
	   	 	driver.switchTo().window(newwin);
	   	 
	   	 	driver.findElement(By.id("ContentPlaceHolder1_quotes_Button1")).click();
	   	 	Thread.sleep(20000);
	   	 
	   		 String insurence;
	   		 String policy;
	   		 insurence=driver.findElement(By.id("ContentPlaceHolder1_quotes_lblcompname")).getText();
	   		 //Don't un_comment to assert command because insurer could be changed 
	   		 //Assert.assertEquals(insurence, "IFFCO Tokio General Insurance Co. Ltd.");
	   		 System.out.println("Insurer Name"+insurence);
	   		 
	   		 Thread.sleep(5000);
	   		 policy=driver.findElement(By.id("ContentPlaceHolder1_quotes_lblplanname")).getText();
	   	     //Don't un_comment to assert command because insurer could be changed 
	   		 //Assert.assertEquals(policy, "Swasthya Kavach");
	   		 System.out.println("Insurer Plan Name"+policy);
	   		 
	   		 driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_quotes_divSmsmobile']/div/div/table/tbody/tr[6]/td/a/img")).click();
	   		 Thread.sleep(9000);
	   	     //driver.close();
	         //driver.switchTo().window(parent); 
	    	}
		}
		//Click on Email Quotes
		@Test (priority=18)
		public void clickOnEmailQuotes() throws InterruptedException
		{
			driver.findElement(By.id("ContentPlaceHolder1_quotes_btnsave")).click();
	        Thread.sleep(8000);
	        //Confirm msg appear but not able to get text and attribute 
	        String confirm;
			confirm=driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_quotes_spanEmailConfirmation']")).getText();
			System.out.println("Confirmation Message Appear "+confirm);
			//Assert.assertEquals(confirm, "You've saved a session. An email has been sent to \"qa@ep.com\".");
		}
		//Change SumInsured 
		@Test (priority=19)
		public void clickOnSumInsured() throws InterruptedException
		{
			Select sumAmt=new Select(driver.findElement(By.id("ContentPlaceHolder1_quotes_ddlSumInsured")));
	        Thread.sleep(3000);
	        sumAmt.selectByValue("100000");
		}
		//Click on Refine Search
		@Test(priority=20)
		public void clickOnRefineSearch() throws InterruptedException
		{
	    driver.findElement(By.id("ContentPlaceHolder1_quotes_imgbtnhealthrefine")).click();
	    Thread.sleep(20000);
		}
		//Click on Book Now
		@Test(priority=21)
		public void clickOnBookNow() throws InterruptedException 
		{  
	    driver.findElement(By.id("ContentPlaceHolder1_quotes_rptrQoutes_imgbtnBookPolicy_0")).click();
	    Thread.sleep(20000);
		}
		//Confirm Plan And Insurer Name
		@Test(priority=22)
		public void confirmPlanAndInsurerName()
		{
			String cnfrmPlan;
	        cnfrmPlan=driver.findElement(By.xpath("html/body/form/div[3]/div/div[3]/table/tbody/tr/td/div/span[1]/table/tbody/tr[2]/td[2]/b[1]")).getText();
	        System.out.println(cnfrmPlan);
	        
	        String cnfrmInsur;
	        cnfrmInsur=driver.findElement(By.xpath("html/body/form/div[3]/div/div[3]/table/tbody/tr/td/div/span[1]/table/tbody/tr[2]/td[2]/b[2]")).getText();
	        System.out.println(cnfrmInsur);
		}
		//Click On Back Link
		@Test(priority=23)
		public void clickOnBackLink()
		{
			driver.findElement(By.id("ContentPlaceHolder1_btnback")).click();
		}
		//Close Browser
		@AfterClass()
		public void closeBrowser()
		{
			driver.close();
			//driver.quit();
		}
		
		//Method for screen shot
		public static void getscreenshot() throws Exception 
	    {
			 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	         //The below method will save the screen shot in d drive with name "screenshot.png"
	         FileUtils.copyFile(scrFile, new File("D:\\BhartiAxaAutomate\\Product-Journey\\SaveScreenShot\\screenShotOfHealthPage"+System.currentTimeMillis()+".png"));            
	    }
		
		//A Method For Click on "Compare" Button
		private void clickOnCompareButton() 
		{
			driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[7]/button")).click();
			
		}
	}


