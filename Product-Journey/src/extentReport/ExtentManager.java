package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManager {

	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	private static String filePath = "./test-output/HealthExtentReport.html";
	private static String filePathchild = "./test-output/ChildExtentReport.html";
	private static String filePathpension = "./test-output/PensionExtentReport.html";
	private static String filePathinvestment = "./test-output/InvestmentExtentReport.html";
	private static String filePathterm = "./test-output/TermExtentReport.html";
	private static String filePathtravel = "./test-output/TravelExtentReport.html";
	private static String filePathcar = "./test-output/CarExtentReport.html";
	private static String filePathbike = "./test-output/CarExtentReport.html";
	
	
	public static ExtentReports GetExtent(){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports();		
		extent.attachReporter(getHtmlReporter());
		return extent;
	}
	public static ExtentReports GetExtentChild(){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports();		
		extent.attachReporter(getHtmlReporterchild());
		return extent;
	}
	public static ExtentReports GetExtentPension(){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports();		
		extent.attachReporter(getHtmlReporterpension());
		return extent;
	}
	public static ExtentReports GetExtentInvestment(){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports();		
		extent.attachReporter(getHtmlReporterinvestment());
		return extent;
	}
	public static ExtentReports GetExtentTerm(){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports();		
		extent.attachReporter(getHtmlReporterterm());
		return extent;
	}
	public static ExtentReports GetExtentTravel(){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports();		
		extent.attachReporter(getHtmlReportertravel());
		return extent;
	}
	public static ExtentReports GetExtentCar(){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports();		
		extent.attachReporter(getHtmlReportercar());
		return extent;
	}
	public static ExtentReports GetExtentBike(){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports();		
		extent.attachReporter(getHtmlReporterbike());
		return extent;
	}
 
	private static ExtentHtmlReporter getHtmlReporter() {
	
        htmlReporter = new ExtentHtmlReporter(filePath);
        extent.setSystemInfo("OS", "Windows 7");
        extent.setSystemInfo("Host Name", "Easypolicy");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "QA Team");
	// make the charts visible on report open
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Health automation report");
        htmlReporter.config().setReportName("Health Automation cycle");
        return htmlReporter;
	}
	private static ExtentHtmlReporter getHtmlReporterchild() {
		
        htmlReporter = new ExtentHtmlReporter(filePathchild);
		
	// make the charts visible on report open
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Child automation report");
        htmlReporter.config().setReportName("Child Automation cycle");
        return htmlReporter;
	}
private static ExtentHtmlReporter getHtmlReporterpension() {
		
        htmlReporter = new ExtentHtmlReporter(filePathpension);
		
	// make the charts visible on report open
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Pension automation report");
        htmlReporter.config().setReportName("Pension Automation cycle");
        return htmlReporter;
	}
private static ExtentHtmlReporter getHtmlReporterinvestment() {
	
    htmlReporter = new ExtentHtmlReporter(filePathinvestment);
	
// make the charts visible on report open
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    htmlReporter.config().setTheme(Theme.DARK);
    htmlReporter.config().setDocumentTitle("Investment automation report");
    htmlReporter.config().setReportName("Investment Automation cycle");
    return htmlReporter;
}
private static ExtentHtmlReporter getHtmlReporterterm() {
	
    htmlReporter = new ExtentHtmlReporter(filePathterm);
	
// make the charts visible on report open
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    htmlReporter.config().setTheme(Theme.DARK);
    htmlReporter.config().setDocumentTitle("Term automation report");
    htmlReporter.config().setReportName("Term Automation cycle");
    return htmlReporter;
}
private static ExtentHtmlReporter getHtmlReportertravel() {
	
    htmlReporter = new ExtentHtmlReporter(filePathtravel);
	
// make the charts visible on report open
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    htmlReporter.config().setTheme(Theme.DARK);
    htmlReporter.config().setDocumentTitle("Travel automation report");
    htmlReporter.config().setReportName("Travel Automation cycle");
    return htmlReporter;
}
private static ExtentHtmlReporter getHtmlReportercar() {
	
    htmlReporter = new ExtentHtmlReporter(filePathcar);
	
// make the charts visible on report open
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    htmlReporter.config().setTheme(Theme.DARK);
    htmlReporter.config().setDocumentTitle("Car automation report");
    htmlReporter.config().setReportName("Car Automation cycle");
    return htmlReporter;
}
private static ExtentHtmlReporter getHtmlReporterbike() {
	
    htmlReporter = new ExtentHtmlReporter(filePathbike);
	
// make the charts visible on report open
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    htmlReporter.config().setTheme(Theme.DARK);
	
    htmlReporter.config().setDocumentTitle("Bike automation report");
    htmlReporter.config().setReportName("Bike Automation cycle");
    return htmlReporter;
}
	
	public static ExtentTest createTest(String name, String description){
		test = extent.createTest(name, description);
		return test;
	}
	
			
}
