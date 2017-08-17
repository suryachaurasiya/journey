package healthProduct;

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
	
	  File fo = new File(System.getProperty("user.dir")+ "\\Resources\\Simulations.xls");
	  Workbook xlWb = Workbook.getWorkbook(fo);	 
	  Sheet shob = xlWb.getSheet("DataSheet");
	  int rowcount, colcount;
	  rowcount = shob.getRows();
	  System.out.println("Number of Rows: " +rowcount);
	  colcount = shob.getColumns();
	  System.out.println("Number of Coloumn: " +colcount);
	  Cell cellobj;
	  Object[][] obj = new Object[rowcount][colcount];
	  for(int i=3; i<rowcount; i++)
	  {
	  for (int j=1; j<colcount; j++)
	  {
	  cellobj = shob.getCell(j,i);
	  obj[i][j] = cellobj.getContents();
	  System.out.print(cellobj.getContents() );
	  } 
	  }
	  return obj;
	}

}
