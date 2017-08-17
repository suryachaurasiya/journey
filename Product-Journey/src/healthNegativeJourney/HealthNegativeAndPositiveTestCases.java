package healthNegativeJourney;


//import java.awt.AWTException;
//import java.awt.HeadlessException;

import java.io.File;
import java.io.IOException;
//import java.util.Iterator;
//import java.util.Set;
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
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.Select;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import healthNegativeJourney.ReadExcel;
import healthWithXpathFile.UIMap;

public class HealthNegativeAndPositiveTestCases 
{
	public static  WebDriver driver=null;
	public UIMap uimap;	
	public String workingDir;

	//Open Browser
	@BeforeClass
	public void browser() 
	{
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprofile = profile.getProfile("TestAutomation");
		driver=new FirefoxDriver(myprofile);
		uimap = new UIMap(workingDir + "\\locator.properties");//change path for locator.properties
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
	public void adultValidation(String adult) throws Exception
	{		
		WebElement adultTextField= driver.findElement(uimap.getLocator("AdultValue_textfield"));
		adultTextField.click();
		adultTextField.clear();
		adultTextField.sendKeys(adult);
		//Click on get live quotes
		//clickOnCompareButton();   It's commented for now	
			
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		//Click on get live quotes
		clickOnCompareButton();	
		Thread.sleep(3000);
		String adultvalidations=driver.findElement(uimap.getLocator("adultvalidation_text")).getText();
		System.out.println(adultvalidations);		
	}
	@Test(priority=3)
	public void enterAdult() throws Exception
	{
		driver.findElement(By.id("txtAdults")).click();
		driver.findElement(By.id("txtAdults")).clear();
		driver.findElement(By.id("txtAdults")).sendKeys("2");
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
		WebElement childTextField= driver.findElement(By.xpath("txtChildren"));
		childTextField.click();
		childTextField.clear();
		childTextField.sendKeys(child);
		//Click on get live quotes
		clickOnCompareButton();		
		//taking xpath of validation under text box
		String childvalidations=driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div/div/div[2]/span[2]")).getText();
		System.out.println(childvalidations);		
	}
	@Test(priority=5)
	public void enterChild() throws Exception
	{
		driver.findElement(By.id("txtChildren")).click();
		driver.findElement(By.id("txtChildren")).clear();
		driver.findElement(By.id("txtChildren")).sendKeys("1");
		Thread.sleep(5000);
	}
	@Test(priority=6)
	public void DOBValidation() throws Exception
	{
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/div[2]/input[1]")).sendKeys("00");
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/div[2]/input[2]")).sendKeys("00");
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/div[2]/input[3]")).sendKeys("1000");
		Thread.sleep(5000);
		clickOnCompareButton();
		String DOBvalidation=driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/span")).getText();
		System.out.println(DOBvalidation);
	}
	@Test(priority=7)
	public void enterDOB() throws Exception
	{
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/div[2]/input[1]")).click();
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/div[2]/input[1]")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/div[2]/input[1]")).sendKeys("01");
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/div[2]/input[2]")).click();
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/div[2]/input[2]")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/div[2]/input[2]")).sendKeys("07");
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/div[2]/input[3]")).click();
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/div[2]/input[3]")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[4]/div[2]/input[3]")).sendKeys("1986");
		Thread.sleep(5000);
	}
	//Data Call From ReadExcel Class
	@DataProvider(name = "CityInput")	
	public Object[][] fetcheCity() throws BiffException, IOException 
	{		
		ReadExcel RE = new ReadExcel();	
		Object[][] testdata = RE.DataFromCitySheet();
		return testdata;	
	}
	//City Test Cases
	@Test(priority=8, dataProvider="CityInput")
	public void testForCityNegativeAndPositive(String cityname) throws InterruptedException, IOException
	{
		
		System.out.println(cityname);
		WebElement EnterCity = driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[6]/md-input-container/md-autocomplete/md-autocomplete-wrap/input"));
		EnterCity.click();
		EnterCity.clear();
		EnterCity.sendKeys(cityname);
		clickOnCompareButton();
		Thread.sleep(2000);
		//String cityvalidation=driver.findElement(By.name("This is Required")).getText();
		//System.out.println(cityvalidation);
				
	}	
	//Enter City
	@Test(priority=9)
	public void enterCity() throws Exception
	{
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[6]/md-input-container/md-autocomplete/md-autocomplete-wrap/input")).click();
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[6]/md-input-container/md-autocomplete/md-autocomplete-wrap/input")).clear();
		Thread.sleep(5000);
		driver.findElement(By.xpath("html/body/div[1]/div/div[1]/div[2]/div/div/div/div[6]/md-input-container/md-autocomplete/md-autocomplete-wrap/input")).sendKeys("New ");
		driver.findElement(By.xpath("html/body/md-virtual-repeat-container/div/div[2]/ul/li")).click();
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
        Thread.sleep(3000);
        clickOnCompareButton();
    //((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-500)");
	}
	@DataProvider(name = "NameInput")	
	public Object[][] fetcheName() throws BiffException, IOException 
	{		
		ReadExcel RE = new ReadExcel();	
		Object[][] testdata = RE.DataFromNameSheet();
		return testdata;	
	}
	//customer popup handling
	@Test(priority=10, dataProvider="NameInput")
	public void CustomerpopupName(String custname) throws InterruptedException
	{
		//driver.findElement(By.id("txtProposerName")).click();
        //driver.findElement(By.id("ContentPlaceHolder1_quotes_rptrQoutes_chkCompare_1")).click();
        //driver.findElement(By.id("ContentPlaceHolder1_quotes_rptrQoutes_imgcompare")).click();
        //Thread.sleep(2000);
        
       /* Set <String> morehandles =driver.getWindowHandles();
        Iterator<String> moreInfo = morehandles.iterator();
        while (moreInfo.hasNext())
        {
       	 String parent = moreInfo.next();
       	 String newwin = moreInfo.next();
       	 driver.switchTo().window(newwin);
       	 
       	 driver.manage().window().maximize();
       	 Thread.sleep(5000);*/
       	System.out.println(custname);
       	WebElement CustomerName =	driver.findElement(By.id("txtProposerName"));
       	CustomerName.click();
       	CustomerName.clear();
       	CustomerName.sendKeys(custname);
		Thread.sleep(8000);
		clickOnProceedButton();		
		//taking xpath of validation under text box
		String custnamevalidations=driver.findElement(By.xpath("html/body/div[2]/div/section[5]/div/div[1]/div/div[2]/div[1]/span")).getText();
		System.out.println(custnamevalidations); 
       	 //driver.close();
        // driver.switchTo().window(parent);
         }	 
	/*}*/
	@DataProvider(name = "EmailInput")	
	public Object[][] fetcheEmail() throws BiffException, IOException 
	{		
		ReadExcel RE = new ReadExcel();	
		Object[][] testdata = RE.DataFromEmailIDSheet();
		return testdata;	
	}
	//customer popup email handling
	@Test(priority=11, dataProvider="EmailInput")
	public void CustomerpopupEmail(String custemail) throws InterruptedException
	{
       	System.out.println(custemail);
       	WebElement CustomerEmail =	driver.findElement(By.id("txtEmailID"));
       	CustomerEmail.click();
       	CustomerEmail.clear();
       	CustomerEmail.sendKeys(custemail);
		Thread.sleep(8000);
		clickOnProceedButton();		
		//taking xpath of validation under text box
		String custemailvalidations=driver.findElement(By.xpath("html/body/div[2]/div/section[5]/div/div[1]/div/div[2]/div[2]/span")).getText();
		System.out.println(custemailvalidations); 
       	 //driver.close();
        // driver.switchTo().window(parent);
         }	 
	@DataProvider(name = "MobileInput")	
	public Object[][] fetcheMobile() throws BiffException, IOException 
	{		
		ReadExcel RE = new ReadExcel();	
		Object[][] testdata = RE.DataFromMobileNumberSheet();
		return testdata;	
	}
	//customer popup email handling
	@Test(priority=12, dataProvider="MobileInput")
	public void CustomerpopupMobile(String custmobile) throws InterruptedException
	{
       	System.out.println(custmobile);
       	WebElement CustomerMobile =	driver.findElement(By.id("txtMobileNumber"));
       	CustomerMobile.click();
       	CustomerMobile.clear();
       	CustomerMobile.sendKeys(custmobile);
		Thread.sleep(8000);
		clickOnProceedButton();	
		//taking xpath of validation under text box
		String custemailvalidations=driver.findElement(By.xpath("html/body/div[2]/div/section[5]/div/div[1]/div/div[2]/div[3]/span[3]")).getText();
		System.out.println(custemailvalidations); 
       	 //driver.close();
        // driver.switchTo().window(parent);
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
		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div/div/div[7]/button")).click();
		
	}
	private void clickOnProceedButton() 
	{
		driver.findElement(By.xpath("html/body/div[2]/div/section[5]/div/div[1]/div/div[2]/div[5]/button")).click();
		
	}
	
}
