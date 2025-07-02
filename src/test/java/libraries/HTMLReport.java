package libraries;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public abstract class HTMLReport {
	
	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	public ExtentTest test,node;
	public String authors;
	public String[] category = null;
	
	
	/**
	 * Initializes the ExtentReports and attaches the HTML reporter for generating test reports
	 * This method initializes the HTML report with a specified file path.
	 * It should be called before any test cases are executed to set up the reporting environment.
	 */
	public void startReport() {
		spark = new ExtentSparkReporter("./report/SalesForceReport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}

	/**
	 * Flushes and writes all test information to the HTML report, finalizing it,
	 * Finalizes the ExtentReports and flushes the report data to the output file
	 * This method should be called after all test cases have been executed to ensure that the report is properly generated.
	 */
	public void endReport() {
		extent.flush();
	}
	
	/**
	 * Starts a new test case in the HTML report with the specified name and description
	 * Creates a new main test case in the report with the given name and description, assigns author and category
	 * @param testCaseName The name of the test case
	 * @param testDescription A brief description of the test case
	 * @return ExtentTest object representing the started test case
	 */
	public ExtentTest startTestCase(String testCaseName, String testDescription) {
		test = extent.createTest(testCaseName, testDescription);
		test.assignAuthor(authors);
		test.assignCategory(category);
		return test;
	}

	/**
	 * Starts a new test node in the HTML report under the current test case
	 * Creates a sub-test (node) under the current test case with the specified name
	 * @param nodes The name of the node to be created
	 * @return ExtentTest object representing the started node
	 */
	public ExtentTest startTestcase(String nodes) {
		node = test.createNode(nodes);
		return node;
	}
	
	/**
	 * Reports a step in the HTML report with a description and status
	 * Logs a test step with the given description and status, attaching a screenshot for pass/fail
	 * @param desc The description of the step
	 * @param status The status of the step (e.g., "pass", "fail", "info")
	 */
	public void reportStep(String desc,String status) {
		if(status.equalsIgnoreCase("pass")){
			node.pass(desc, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
			} else if(status.equalsIgnoreCase("fail")) {
				node.fail(desc, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());	
			} else {
				node.info(desc);	
			}
	}
	
	/**
	 * Abstract method to take a screenshot
	 * This method should be implemented in subclasses to capture screenshots during test execution
	 * Abstract method to capture and return the path of a screenshot; must be implemented by subclasses
	 * @return The path to the screenshot file
	 */
	public abstract String takeScreenshot();

}
