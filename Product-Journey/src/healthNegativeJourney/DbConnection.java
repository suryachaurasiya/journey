package healthNegativeJourney;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException { 
        //Loading the required JDBC Driver class 
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");       
        //Creating a connection to the database 
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.3.5;instance=SQLEXPRESS;databaseName=EPQA;user=epqa;password=@@EpQa**"); 
        //Executing SQL query and fetching the result 
        Statement st = conn.createStatement(); 
        String sqlStr = "select top 10 * from Customer_Product"; 
        ResultSet rs = st.executeQuery(sqlStr); 
        while (rs.next()) 
        { 
               System.out.println(rs.getString("Customer_Name")); 
                            
        }      
        }


}
