package demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import resources.ExtentReporterNg;

public class Listeners extends Base implements ITestListener {

	// for creating data to enter in the RunResults.xlsx
	static ArrayList<Object[]> testResult = new ArrayList<Object[]>();

	private static Logger log = LogManager.getLogger(Listeners.class.getName());

	ExtentTest test;
	ExtentReports extent = ExtentReporterNg.getReporterObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		log.info("Test Start: " + result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Passed");
		System.out.println("Test Success: " + result.getMethod().getMethodName());
		log.info("Test End: " + result.getMethod().getMethodName() + " RESULT PASS");
		testResult.add(new Object[] { result.getName(), "PASS" });
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());

		WebDriver driver = null;
		String testMethodName = result.getMethod().getMethodName();
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			extentTest.get().addScreenCaptureFromPath(getScreenshotPath(testMethodName, driver),
					result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Test Failure: " + result.getMethod().getMethodName());
		log.info("Test End: " + result.getMethod().getMethodName() + " RESULT FAILED");
		testResult.add(new Object[] { result.getName(), "FAIL" });
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		testResult.add(new Object[] { result.getName(), "SKIPPED" });
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		testResult.add(new Object[] { "TestName: "+context.getName(), "Status" });
	}

	public void onFinish(ITestContext context) {
		try {
			extent.flush();
			writeToExcel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeToExcel() throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("AllTestResult");

		int rowCount = 0;
		for (Object[] emp : testResult) {
			XSSFRow row = sheet.createRow(rowCount++);
			int coulumnCount = 0;
			for (Object value : emp) {
				XSSFCell cell = row.createCell(coulumnCount++);
				if (value instanceof String)
					cell.setCellValue(value.toString());
				if (value instanceof Integer)
					cell.setCellValue((Integer) value);
				if (value instanceof Boolean)
					cell.setCellValue((Boolean) value);
			}
		}
		// System.out.println(System.getProperty("user.dir"));
		String filepath = System.getProperty("user.dir") + "\\reports\\TestRunReport.xlsx";
		FileOutputStream outStrem = new FileOutputStream(filepath);
		workbook.write(outStrem);
		outStrem.close();
		workbook.close();
	}
}
