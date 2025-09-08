package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestCases.BaseTest;


public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testcontext) {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
//		  Date dt = new Date();
//		  String currentdatetimestamp = df.format(dt);
//		 
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //
		repName="Test-Report-"+timestamp+".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); // specify the location of the file.
		
		sparkReporter.config().setDocumentTitle("opencart automation Report"); // title of the project
		sparkReporter.config().setReportName("opencart functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","opencart");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("Sub Module","Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		
		String os = testcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("operating system",os);
		
		String browser = testcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testcontext.getCurrentXmlTest().getIncludedGroups(); // it captures the groups which we include in xml include section.
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
		
		
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		test.log(Status.PASS,result.toString()+"got successfully executed");
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgpath = new Screenshots().takeScreenshot(null, result.getName()); // in baseclass what we add capture method taht retriev here. captureScreenshot method expecting name of the method.
			test.addScreenCaptureFromPath(imgpath); //attaching scren shot to report.
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void OnFinish(ITestContext testcontext) {
		extent.flush();
		
		// if i want to open report automatically for this we have  to write
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI()); // if it is not available it throw an exception show i=thats why i kept in try cactch block
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		// Automatically you want to send a report through email so write like this
		 
//		try {
//		    URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);
//
//		    // Create the email message
//		    ImageHtmlEmail email = new ImageHtmlEmail();
//		    email.setDataSourceResolver(new DataSourceUrlResolver(url));
//		    email.setHostName("smtp.googlemail.com");
//		    email.setSmtpPort(465);
//		    email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password")); // who is sending to whom we have to give our email address
//		    email.setSSLOnConnect(true);
//		    email.setFrom("pavanoltraining@gmail.com"); // Sender
//		    email.setSubject("Test Results");
//		    email.setMsg("Please find Attached Report....");
//		    email.addTo("pavankumar.busyaq@gmail.com"); // Receiver
//		    email.attach(url, "extent report", "please check report...");
//		    email.send(); // send the email
//		} catch (Exception e) {
//		    e.printStackTrace();
//		}
//		
// this above piece code automatically send a report to the team . through email for this we need to add dependency java -email
		// this piece of code only work for gmail
	}
	
	
	
}
