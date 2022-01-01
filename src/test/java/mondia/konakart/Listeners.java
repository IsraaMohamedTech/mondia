package mondia.konakart;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends Common implements ITestListener {

	ExtentTest test;
	ExtentReports extendReport = getExtendReport();
	ThreadLocal<ExtentTest> extendTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extendReport.createTest(result.getMethod().getMethodName());
		extendTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extendTest.get().log(Status.PASS, result.getMethod().getMethodName() + " passed");
		
		

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extendTest.get().fail(result.getThrowable());
		

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extendReport.flush();

	}

}
