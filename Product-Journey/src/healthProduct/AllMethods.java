package healthProduct;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllMethods {
	public static WebDriver driver;
	
	public static void getscreenshot() throws Exception 
    {
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in d drive with name "screenshot.png"
         FileUtils.copyFile(scrFile, new File("D:\\BhartiAxaAutomate\\Product-Journey\\SaveScreenShot\\screenShotOfHealthPage"+System.currentTimeMillis()+".png"));            
    }
	
	//A Method For Click on "Compare" Button
	public void clickOnCompareButton() 
	{
		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div/div/div[7]/button")).click();
		
	}
	public void clickOnProceedButton() 
	{
		driver.findElement(By.xpath("html/body/div[2]/div/section[5]/div/div[1]/div/div[2]/div[5]/button")).click();
		
	}

}
