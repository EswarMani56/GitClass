package com.hackathon;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Searchwebdevelopment {
	WebDriver driver;
	
	//Constructor for initialize driver
	Searchwebdevelopment(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Locators
	@FindBy(id="search-autocomplete-input")
	WebElement search;
	
	@FindBy(xpath="//div[@data-testid='English-true' or @data-testid='English-false']")
	WebElement filter_English;
	
	@FindBy(xpath="//div[@data-testid='English-true' or @data-testid='English-false']")
	WebElement filter_Beginner ;
	
	@FindBy(xpath="//h3[contains(@class,'cds-CommonCard-title')]")
	List<WebElement> allCoursesTitles;
	
	@FindBy(xpath="//*[contains(@class,'cds-CommonCard-metadata')]")
	List<WebElement>  allCoursesDuration ;
	
	@FindBy(xpath="//*[contains(@class, 'cds-CommonCard-ratings')]")
	List<WebElement> allCoursesRatings;
	
	
	//Methods
	public void searchoption(String searc) {
		
        search.sendKeys(Keys.CONTROL + "a");
		search.sendKeys(searc);
		Actions act = new Actions(driver);
        act.sendKeys(Keys.ENTER).perform();
		
	}
	
	public void filterEnglishAndBeginner() {
		filter_English.click();
		filter_Beginner.click();
	}
	
	public void displayDetails() {
		
        for (int i = 0; i < 2; i++) {
        	System.out.println("Course name: " + allCoursesTitles.get(i).getText());
            System.out.println("Duration: " + allCoursesDuration.get(i).getText().split("·")[2]);
            System.out.println("Ratings: " + allCoursesRatings.get(i).getText().split(", ")[1].split(" · ")[0]);
        }
	}
}
