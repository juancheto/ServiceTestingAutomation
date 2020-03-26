package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listeners extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    public void onStart(ITestContext testContext) {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "/Reports/myReport.html"); //Specify location

        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Rest API Testing Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Project name", "Employee Database API");
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "juan");
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName()); //Create new entry in the report
        test.log(Status.PASS, "Test Case PASSED is " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName()); //Create new entry in the report
        test.log(Status.FAIL, "Test Case FAILED is " + result.getName()); //to add name in extent report
        test.log(Status.FAIL, "Test Case FAILED is " + result.getThrowable()); //to add error/exception in extent report
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName()); //Create new entry in the report
        test.log(Status.SKIP, "Test Case SKIPPED is " + result.getName());
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
    }
}
