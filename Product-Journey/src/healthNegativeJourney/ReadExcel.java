package healthNegativeJourney;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel 
{
	public Object[][] DataFromNoOfAdultSheet() throws BiffException, IOException
	{
	
	  File fo = new File(System.getProperty("user.dir")+ "\\HealthNegativeTestData.xls");
	  Workbook xlWb = Workbook.getWorkbook(fo);	 
	  Sheet shob = xlWb.getSheet(0);
	  int rowcount, colcount;
	  rowcount = shob.getRows();
	  System.out.println("Number of Rows: " +rowcount);
	  colcount = shob.getColumns();
	  System.out.println("Number of Coloumn: " +colcount);
	  Cell cellobj;
	  Object[][] obj = new Object[rowcount-1][colcount];
	  for(int i=1; i<rowcount; i++)
	  {
	  for (int j=0; j<colcount; j++)
	  {
	  cellobj = shob.getCell(j,i);
	  obj[i-1][j] = cellobj.getContents();
	  } 
	  }
	  return obj;
	}
	public Object[][] DataFromNoOfChildSheet() throws BiffException, IOException
	{
	
	  File fo = new File(System.getProperty("user.dir")+ "\\HealthNegativeTestData.xls");
	  Workbook xlWb = Workbook.getWorkbook(fo);	 
	  Sheet shob = xlWb.getSheet(1);
	  int rowcount, colcount;
	  rowcount = shob.getRows();
	  System.out.println("Number of Rows: " +rowcount);
	  colcount = shob.getColumns();
	  System.out.println("Number of Coloumn: " +colcount);
	  Cell cellobj;
	  Object[][] obj = new Object[rowcount-1][colcount];
	  for(int i=1; i<rowcount; i++)
	  {
	  for (int j=0; j<colcount; j++)
	  {
	  cellobj = shob.getCell(j,i);
	  obj[i-1][j] = cellobj.getContents();
	  } 
	  }
	  return obj;
	}
	public Object[][] DataFromCitySheet() throws BiffException, IOException
	{
		File fo = new File(System.getProperty("user.dir")+ "\\HealthNegativeTestData.xls");
		  Workbook xlWb = Workbook.getWorkbook(fo);	 
		  Sheet shob = xlWb.getSheet(2);
		  int rowcount, colcount;
		  rowcount = shob.getRows();
		  System.out.println("Number of Rows: " +rowcount);
		  colcount = shob.getColumns();
		  System.out.println("Number of Coloumn: " +colcount);
		  Cell cellobj;
		  Object[][] obj = new Object[rowcount-1][colcount];
		  for(int i=1; i<rowcount; i++)
		  {
		  for (int j=0; j<colcount; j++)
		  {
		  cellobj = shob.getCell(j,i);
		  obj[i-1][j] = cellobj.getContents();
		  }
		  }
		  return obj;
	}
	public Object[][] DataFromNameSheet() throws BiffException, IOException
	{
		File fo = new File(System.getProperty("user.dir")+ "\\HealthNegativeTestData.xls");
		  Workbook xlWb = Workbook.getWorkbook(fo);	 
		  Sheet shob = xlWb.getSheet(3);
		  int rowcount, colcount;
		  rowcount = shob.getRows();
		  System.out.println("Number of Rows: " +rowcount);
		  colcount = shob.getColumns();
		  System.out.println("Number of Coloumn: " +colcount);
		  Cell cellobj;
		  Object[][] obj = new Object[rowcount-1][colcount];
		  for(int i=1; i<rowcount; i++)
		  {
		  for (int j=0; j<colcount; j++)
		  {
		  cellobj = shob.getCell(j,i);
		  obj[i-1][j] = cellobj.getContents();
		  }
		  }
		  return obj;
	}
	public Object[][] DataFromEmailIDSheet() throws BiffException, IOException
	{
		File fo = new File(System.getProperty("user.dir")+ "\\HealthNegativeTestData.xls");
		  Workbook xlWb = Workbook.getWorkbook(fo);	 
		  Sheet shob = xlWb.getSheet(4);
		  int rowcount, colcount;
		  rowcount = shob.getRows();
		  System.out.println("Number of Rows: " +rowcount);
		  colcount = shob.getColumns();
		  System.out.println("Number of Coloumn: " +colcount);
		  Cell cellobj;
		  Object[][] obj = new Object[rowcount-1][colcount];
		  for(int i=1; i<rowcount; i++)
		  {
		  for (int j=0; j<colcount; j++)
		  {
		  cellobj = shob.getCell(j,i);
		  obj[i-1][j] = cellobj.getContents();
		  }
		  }
		  return obj;
	}
	public Object[][] DataFromMobileNumberSheet() throws BiffException, IOException
	{
		File fo = new File(System.getProperty("user.dir")+ "\\HealthNegativeTestData.xls");
		  Workbook xlWb = Workbook.getWorkbook(fo);	 
		  Sheet shob = xlWb.getSheet(5);
		  int rowcount, colcount;
		  rowcount = shob.getRows();
		  System.out.println("Number of Rows: " +rowcount);
		  colcount = shob.getColumns();
		  System.out.println("Number of Coloumn: " +colcount);
		  Cell cellobj;
		  Object[][] obj = new Object[rowcount-1][colcount];
		  for(int i=1; i<rowcount; i++)
		  {
		  for (int j=0; j<colcount; j++)
		  {
		  cellobj = shob.getCell(j,i);
		  obj[i-1][j] = cellobj.getContents();
		  }
		  }
		  return obj;
	}
	
}