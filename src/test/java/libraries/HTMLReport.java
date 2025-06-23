package libraries;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public abstract class HTMLReport {
	
	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	public ExtentTest test,node;
	public String authors,category;
	
	public void startReport() {
		spark = new ExtentSparkReporter("./report/SalesForceReport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}
	
	public void endReport() {
		extent.flush();
	}
	
	public ExtentTest startTestCase(String testCaseName, String testDescription) {
		test = extent.createTest(testCaseName, testDescription);
		test.assignAuthor(authors);
		test.assignCategory(category);
		return test;
	}

	public ExtentTest startTestcase(String nodes) {
		node = test.createNode(nodes);
		return node;
	}
	
	public void reportStep(String desc,String status) {
		if(status.equalsIgnoreCase("pass")){
			node.pass(desc, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
			} else if(status.equalsIgnoreCase("fail")) {
				node.fail(desc, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());	
			} else {
				node.info(desc);	
			}
	}
	
	public abstract String takeScreenshot();

}
