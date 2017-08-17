package healthProduct;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import healthWithXpathFile.UIMap;

public class AllPlans {
	public static  WebDriver driver=null;
	String plansname;
	String premium;
	@BeforeClass
	public void browser() 
	{
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprofile = profile.getProfile("TestAutomation");
		driver=new FirefoxDriver(myprofile);
		//uimap = new UIMap(workingDir + "\\locator.properties");//change path for locator.properties
	}	
	@Test(priority=0)
	public void openBrowser() throws Exception
	{
		driver.get("https://www.easypolicy.com/Application/Health?CPID=MjcwMTQ4MA");		
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);		
	}
	@Test(priority=1)
     public void printAllPlansAndMaturityBenifitOnQuotesPage() throws InterruptedException, IOException
     {
         Thread.sleep(20000);
           //Take plan name
          List<WebElement> plans=driver.findElements(By.xpath("html/body/div[2]/div/div[5]/div[1]/div[1]/div/div[2]/div[2]/div[2]/div[2]/div/div/div[2]/md-card/md-card-content/div/div[1]/div[1]/div[1]/h5"));
          int icols=plans.size();
          List<String> planVal= new ArrayList<String>();        
          for(WebElement plan:plans)
          {
          plansname=plan.getText(); 
          planVal.add(plansname) ;  
          }
          //Take premium amnt
          List<WebElement> amnts=driver.findElements(By.xpath("html/body/div[2]/div/div[5]/div[1]/div[1]/div/div[2]/div[2]/div[2]/div[2]/div/div/div[2]/md-card/md-card-content/div/div[2]/div/span"));            
          List<String> premiumVal = new ArrayList<String>(); 
          for(WebElement camnt:amnts)
          {
          premium= camnt.getText(); 
          premiumVal.add(premium);       
          }            
          System.out.println(premiumVal.size());
          //Print list 
          for(int i=0;i<premiumVal.size();i++)
          {
          System.out.print("Plan Name:-"+planVal.get(i)+"\t"+" Premium Amt:-"+premiumVal.get(i)+"\n");            
         
          } 
     }
	@Test(priority = 2)
    public void printAllPlansToExcel() throws InterruptedException, IOException

    {

        Thread.sleep(20000);
        List<WebElement> irows = driver.findElements(By.xpath("html/body/div[2]/div/div[5]/div[1]/div[1]/div/div[2]/div[2]/div[2]/div[2]/div/div/div[2]/md-card/md-card-content/div/div[1]/div[1]/div[1]/h5"));
        int iRowsCount = irows.size();
        int j = 2;
        System.out.println("Selected web table has " + iRowsCount + " Rows and " + j + " Columns");
        System.out.println();
        FileOutputStream fos = new FileOutputStream("D:\\BhartiAxaAutomate\\Product-Journey\\PlanstoSpreadsheet.xls");
        XSSFWorkbook wkb = new XSSFWorkbook();
        XSSFSheet sheet1 = wkb.createSheet("DataStorage");
    
        for (int i = 3; i <iRowsCount+3; i++) {
            XSSFRow excelRow = sheet1.createRow(i);
            WebElement val = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div[1]/div[1]/div/div[2]/div[2]/div[2]/div[2]/div/div[" + i+ "]/div[" + j + "]/md-card/md-card-content/div/div[1]/div[1]/div[1]/h5"));
            String a = val.getText();                   
            System.out.print(a + "\n");
            // XSSFRow excelRows = sheet1.createRow(i);
             
            XSSFCell excelCell = excelRow.createCell(j);
            //excelCell.setCellType(XSSFCell.CELL_TYPE_STRING);
            excelCell.setCellValue(a);
            }           
        fos.flush();
        wkb.write(fos);
        fos.close();
    }
    
    
    @Test(priority = 3)
    public void printMaturityBenifitInToExcel() throws InterruptedException, IOException

    {  
        Thread.sleep(2000);
        List<WebElement> irows = driver.findElements(By.xpath("html/body/div[2]/div/div[5]/div[1]/div[1]/div/div[2]/div[2]/div[2]/div[2]/div/div/div[2]/md-card/md-card-content/div/div[2]/div/span"));
        int iRowsCount = irows.size();
        int l = 2;
        
        
        FileInputStream fis = new FileInputStream(new File("D:\\BhartiAxaAutomate\\Product-Journey\\PlanstoSpreadsheet.xls"));
        XSSFWorkbook workbook = new XSSFWorkbook (fis);
        XSSFSheet sheet = workbook.getSheetAt(0);    
        
        for (int k = 3; k<iRowsCount+3; k++) 
        {            
            XSSFRow row1 = sheet.createRow(k);    
            WebElement val = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div[1]/div[1]/div/div[2]/div[2]/div[2]/div[2]/div/div["+k+"]/div["+l+"]/md-card/md-card-content/div/div[2]/div/span"));
            String a = val.getText();                    
            System.out.print(a + "\n");                
            XSSFCell cell1 = row1.createCell(l);
            cell1.setCellValue(a);                
            
            
            }
        
        fis.close();
        FileOutputStream fos =new FileOutputStream(new File("D:\\BhartiAxaAutomate\\Product-Journey\\PlanstoSpreadsheet.xls"));
            workbook.write(fos);
            fos.close();
        System.out.println("Done");



        
    }
	 
       
}

