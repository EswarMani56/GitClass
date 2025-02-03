package com.hackathon;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class DriverSetup {
    WebDriver driver;
    ExtentReports extent = ExtentReport.getInstance();
    ExtentTest test;
    Searchwebdevelopment tcwb;

    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("Chrome") String browser) {
        if (browser.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
            test = extent.createTest("ChromeTest");
        } else if (browser.equalsIgnoreCase("Edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\Users\\2372588\\Downloads\\edgedriver_win64\\msedgedriver.exe");
            driver = new EdgeDriver();
            test = extent.createTest("EdgeTest");
        }

        driver.get("https://www.coursera.org/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        tcwb = new Searchwebdevelopment(driver); // Initialize tcwb here
    }

    @Test(priority = 1)
    void testWebDevelopmentCourse() {
    	try {
    		 tcwb.searchoption("Web development");
    	        tcwb.filterEnglishAndBeginner();
    	        tcwb.displayDetails();
    	        test.log(Status.PASS, "testcase1");
    	}
        catch(Exception e) {
        	test.log(Status.FAIL, "testcase1 is Failed");
        }
    }

    @Test(priority = 2)
    void testLearningLanguage() throws IOException {
    	try {
    		SearchLearninglanguage tcll = new SearchLearninglanguage(driver);
            tcwb.searchoption("Learning Language");
//            tcll.extractLanguageAndLevel();
            tcll.writeDataToExcel();
            test.log(Status.PASS, "testcase2");
    	}
        
        catch(Exception e) {
        	test.log(Status.FAIL, "testcase2 is Failed");
        }
    }

    @Test(priority = 3)
    void testForEnterprises() throws InterruptedException {
    	try {
    		SearchEnterprises tcet = new SearchEnterprises(driver);
            tcwb.searchoption("For Enterprises");
            tcet.actionclick();
            tcet.enrollfree("askhghgd", "sHdgq2");
            Thread.sleep(10000);
            tcet.errormsgcapturing();
            test.log(Status.PASS, "testcase3");
    	}
    	 catch(Exception e) {
         	test.log(Status.FAIL, "testcase3 is Failed");
         }
        
    }

    @AfterClass
    void tearDown() {
        driver.quit();
        extent.flush();
    }
}
