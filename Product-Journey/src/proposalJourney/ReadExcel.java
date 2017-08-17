package proposalJourney;

import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel 
{
	
	public Object[][] DataFromNameSheet() throws BiffException, IOException
	{
	
	  File fo = new File(System.getProperty("user.dir")+ "\\Resources\\Simulation.xls");
	  Workbook xlWb = Workbook.getWorkbook(fo);	 
	  Sheet shob = xlWb.getSheet("DataSheet");
	  int rowcount, colcount;
	  rowcount = shob.getRows();
	  System.out.println("Number of Rows: " +rowcount);
	  colcount = shob.getColumns();
	  System.out.println("Number of Coloumn: " +colcount);
	  Cell cellobj;
	  Object[][] obj = new Object[rowcount-2][colcount-1];
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
