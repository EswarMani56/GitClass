package com.hackathon;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    public static ExtentReports getInstance() {
        if (extent == null) {
            sparkReporter = new ExtentSparkReporter("test-output/Extent-Report.html");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Selenium Test Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }
}

