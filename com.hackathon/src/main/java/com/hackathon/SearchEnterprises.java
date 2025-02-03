package com.hackathon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchEnterprises {
    WebDriver driver;

    public SearchEnterprises(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(xpath = "//span[text()='Courses']")
    WebElement under_courses;

    @FindBy(xpath = "(//div[@class='cds-ProductCard-gridCard'])[1]")
    WebElement course_details;

    @FindBy(xpath = "(//span[text()='Enroll for Free'])[1]")
    WebElement enroll;

    @FindBy(xpath = "//button[@data-track-component='login']")
    WebElement login;

    @FindBy(xpath = "//input[@placeholder='name@email.com']")
    WebElement user_email;

    @FindBy(xpath = "//input[@placeholder='Enter your password']")
    WebElement user_password;

    @FindBy(xpath = "(//button[@type='submit'])[3]")
    WebElement loginbtn;

    // Methods
    public void actionclick() {
        under_courses.click();
        course_details.click();
        Set<String> winids = driver.getWindowHandles();
        
        List<String> winLis = new ArrayList<>(winids);
        String parentid = winLis.get(0);
        String childid = winLis.get(1);
        driver.switchTo().window(childid);
    }

    // For Enrolling Course
    public void enrollfree(String email, String paswrd) {
        enroll.click();
        login.click();
        user_email.sendKeys(email);
        user_password.sendKeys(paswrd);
        loginbtn.click();
    }

    // Capturing error message
    public void errormsgcapturing() {
        String errormsg = driver.findElement(By.xpath("//div[@class='css-q1vc80']")).getText();
        
        TakesScreenshot takesscreenshot = (TakesScreenshot) driver;
        File src = takesscreenshot.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(src, new File("screenshots/screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
