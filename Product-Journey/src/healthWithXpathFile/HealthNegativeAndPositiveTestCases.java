package healthWithXpathFile;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;
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
		FirefoxProfile myprofile = profile.getProfile("AutomationProfileCABdirect");//change profile name
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
	public void clickOnHealth() throws Exception
	{		
		// Heathyper_link keyword created in locater.property file
		WebElement signout = driver.findElement(uimap.getLocator("Heathyper_link"));
		signout.click();

	}
	
	//Call Data From ReadExcel Class
		@DataProvider(name = "EnterAdultValues")	
		public Object[][] fetcheAdultNo() throws  IOException, BiffException 
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
		String adultvalidations=driver.findElement(uimap.getLocator("adultvalidation_text")).getText();
		System.out.println(adultvalidations);		
	}

}
