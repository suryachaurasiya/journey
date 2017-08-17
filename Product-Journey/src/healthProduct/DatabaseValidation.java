package healthProduct;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class DatabaseValidation {

  private WebDriver driver = null;
  private Connection con = null;
  private Statement stmt = null;
  PreparedStatement stat = null;
  ResultSet rs = null;
  String baseUrl;

  @BeforeTest
  public void setUp() throws Exception {
    // use firefox browser
    driver = new FirefoxDriver();
    baseUrl = "https://www.easypolicy.com";
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
  }

  @Test(description = "Open Easypolicy Website for health Product Journey", priority = 1)
  public void test() throws SQLException, ClassNotFoundException, InterruptedException {
    // Load Microsoft SQL Server JDBC driver.
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    // Prepare connection url.
    String url = "jdbc:sqlserver://180.179.198.149:21443;instance=SQLEXPRESS;databaseName=SumpoornaEPDB;";
    // Get connection to DB.
    con = DriverManager.getConnection(url, "Vijay", "Satvik@28");
    // Create statement object which would be used in writing DDL and DML
    // SQL statement.
    stmt = con.createStatement();
    // Send SQL SELECT statements to the database via the
    // Statement.executeQuery
    // method which returns the requested information as rows of data in a
    // ResultSet object.
    // define query to read data
    try {
      String query = "select top 10 * from Customer_Product order by 1 desc ";
      ResultSet result = stmt.executeQuery(query);
      if (result.next()) {
        while (result.next()) {
          // Fetch value of "username" and "password" from "result"
          // object; this will return 2 existing users in the DB.

         
          String username = result.getString("Customer_Name");
          String email = result.getString("Email_Id");
          String mobileno = result.getString("Mobile_Number");
          // print them on the console
          System.out.println("username :" + username+' '+"Email: " + email+' '+ "Mobile_Number: " + mobileno);
        }
        result.close();
      }
    }

    catch (SQLException ex) {
      System.out.println(ex);
    }
    Thread.sleep(5000);
    // Add a new user on the UI
    String newcustomername = "Mahesh Babuji";
    String newmailid = "maheshbabuji@ep.com";
    String newmobileno = "8141077787";
    // navigate to the site
    driver.get(baseUrl + "/Application/Health?CPID=MjczODc1OQ&instantleadpop=2");
    // set new user name "NewTestUser"
    driver.findElement(By.id("txtProposerName")).sendKeys(newcustomername);
    // set new user password for the new user "NewTestUser"
    driver.findElement(By.id("txtEmailID")).sendKeys(newmailid);
    driver.findElement(By.id("txtMobileNumber")).sendKeys(newmobileno);
    // click on Add User button
    driver.findElement(By.xpath("html/body/div[2]/div/div[1]/section/div/div[1]/div/div[2]/div[5]/button")).click();
    // verify the welcome message displayed
    System.out.println("Is message displayed: "+ isElementPresent(By.xpath("html/body/div[2]/div/div[6]/div[1]/div[1]/div/div[2]/div[2]/div[2]/div[1]/div[1]/h3")));

    // verify the new user in the database
    // create a query
    String newuserquery = "select top 1 * from Customer_Product where Customer_Name = ?";
    // create a statement
    PreparedStatement stat = con.prepareStatement(newuserquery);
    stat.setString(1, newcustomername);
    
    //test code start
    //prSt = con.prepareStatement(query);
   // prSt.setInt(1, 1016);
    System.out.println("1");
    ResultSet rs = stat.executeQuery();
    System.out.println("1");
    try{
    while(rs.next()){
        System.out.println(rs.getString("Customer_Name"));
        System.out.println("2");
   // }
    //rs.close();  
    //test code end
   /* try {
        System.out.println("1");
      boolean hasResultSet = stat.execute();
      System.out.println("1");
      if (hasResultSet) {
        ResultSet result = stat.getResultSet();
        */
        /*result.last();
        System.out.println("2");
        int count = result.getRow();
        
        System.out.println(count);
        System.out.println("2");*/
        // get new user name from the table

        String newusername = rs.getString("Customer_Name");
        System.out.println("4");
        System.out.println(newusername);
        // assert that new user name should be
        assertEquals(newcustomername, newusername);
        System.out.println("1");
        Thread.sleep(5000);
        System.out.println("3");
        System.out.println(newusername);
        System.out.println("1");
      
      }
    } catch (SQLException ex)

    {
      System.out.println(ex);
      throw (ex);
    } 
        finally {
      con.close();
    }
   

  }

 /* @AfterTest
  public void tearDown() throws Exception {
    // close the driver
    driver.close();
  }*/

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

}

