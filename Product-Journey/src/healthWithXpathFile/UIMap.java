package healthWithXpathFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;

import org.openqa.selenium.By;

public class UIMap {
	static final String filePath="D:\\BhartiAxaAutomate\\Product-Journey\\simulationExcelData\\simulation.xlsx";
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	Properties properties;
	 
	public UIMap(String FilePath) {
 
		try { 
			FileInputStream Locator = new FileInputStream(FilePath);
			properties = new Properties();
			properties.load(Locator);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
 
	public String getData(String ElementName) throws Exception {
		// Read value using the logical name as Key
		String data = properties.getProperty(ElementName);
		return data;
	}
 
	public By getLocator(String ElementName) throws Exception {
		// Read value using the logical name as Key
		String locator = properties.getProperty(ElementName);
		// Split the value which contains locator type and locator value
		String locatorType = locator.split(":")[0];
		String locatorValue = locator.split(":")[1];
		// Return a instance of By class based on type of locator
		if (locatorType.toLowerCase().equals("id"))
			return By.id(locatorValue);
		else if (locatorType.toLowerCase().equals("name"))
			return By.name(locatorValue);
		else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class")))
			return By.className(locatorValue);
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return By.className(locatorValue);
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return By.linkText(locatorValue);
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return By.partialLinkText(locatorValue);
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return By.cssSelector(locatorValue);
		else if (locatorType.toLowerCase().equals("xpath"))
			return By.xpath(locatorValue);
		else
			throw new Exception("Locator type '" + locatorType
					+ "' not defined!!");
	}

	// This Method Create the Excel Sheet
	public static void setExcelFile(String Path, String SheetName) throws Exception {
		try {
			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String readData(String sheetNum,int rowNum,int cellNum) {
		String data="";
		try{
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File(filePath)));
		data=wb.getSheet(sheetNum).getRow(rowNum).getCell(cellNum).getStringCellValue();
		}catch(InvalidFormatException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return data;
	}
	@DataProvider(name="verify")
	public static Object[][] verification(){
		Object[][] obj=new Object[1][23];
		for(int i=0;i<obj.length;i++){
			for(int j=0;j<obj[i].length;j++){
		 obj[i][j]=readData("Sheet1",i,j);
		}
		}
		return obj;
	}
	public static void writeDataInExcel() throws IOException{

		 try {
		    
		  FileInputStream file = new FileInputStream(new File(filePath)); 
		  XSSFWorkbook workbook = new XSSFWorkbook(file);
		 
		  XSSFSheet sheet = workbook.getSheetAt(0);
		   
		  Cell searchText3 = sheet.getRow(0).getCell(23);
		  searchText3.setCellValue("Test Search Keyword");
		 
		  file.close();
		 
		  FileOutputStream outFile =new FileOutputStream(new File(".\\creatExcel\\excelData.xlsx"));
		  workbook.write(outFile);
		  outFile.close();
		 
		 } catch (FileNotFoundException fnfe) {
		  fnfe.printStackTrace();
		 } catch (IOException ioe) {
		  ioe.printStackTrace();
		 }
    }
}
