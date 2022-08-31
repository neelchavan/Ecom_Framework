package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {
	static ExtentReports extent;

	public static ExtentReports getReporterObject() {
		String projectPath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(projectPath);
		reporter.config().setReportName("Flipcart Automation result");
		reporter.config().setDocumentTitle("Test results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Neel Chavan");
		return extent;
	}
}
