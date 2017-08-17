package healthProduct;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Types;



import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DbConnection {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException { 
        //Loading the required JDBC Driver class 
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");       
        //Creating a connection to the database 
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.3.158;instance=SQLEXPRESS;databaseName=EPQA;user=Vijay;password=Satvik@28"); 
        //Executing SQL query and fetching the result
        //Statement st = conn.createStatement(); 
        
        CallableStatement cStmt = conn.prepareCall("{call spVijayJavaTest(?)}");
        cStmt.setString(1, "2360295");        
       // String sqlStr = "select top 10 * from Insurer_Plans a, Insurer_Master b, Health_Rates c where a.Insurer_Id=b.Insurer_Id and Product_Id='2' and NumberOfAdults='1'"; 
       //Sabse pahale result print krao...fir aage dekhte h
        ResultSet rs = cStmt.executeQuery(); //query shayad shi nhi h
        System.out.println(rs);//There is no result set
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet spreadsheet = workbook.createSheet("employe db");
        XSSFRow row=spreadsheet.createRow(0);
        XSSFCell cell;
        cell=row.createCell(0);
        cell.setCellValue("CpId");       
        cell=row.createCell(1);
       /*cell.setCellValue("PLAN NAME");
        cell=row.createCell(2);
        cell.setCellValue("PRODUCT ID");
        cell=row.createCell(3);
        cell.setCellValue("INSURER ID");*/
        int i=1;
        while(rs.next())// loop bhi sahi nhi chal rhah
        {
           row=spreadsheet.createRow(i);
           cell=row.createCell(i);
           cell.setCellValue(rs.getInt("CpId"));
           cell=row.createCell(i);
//           System.out.println(cell);// No result available to save in cell
//           cell.setCellValue(rs.getString("Plan_Name"));
//           cell=row.createCell(i);
//           cell.setCellValue(rs.getString("Product_Id"));
//           cell=row.createCell(i);
//           cell.setCellValue(rs.getString("Insurer_Id"));
           i++;
        }
        FileOutputStream out = new FileOutputStream(new File("D:\\BhartiAxaAutomate\\Product-Journey\\ConsoleOutPutexceldatabase.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("exceldatabase.xls written successfully");
   
       System.out.println(rs);
        while (rs.next()) 
        { 
               System.out.println(rs.getString("Plan_Name")+' '+rs.getString("Product_Id")); 
        	
                            
        }  
        
       
        
        }
	


}




