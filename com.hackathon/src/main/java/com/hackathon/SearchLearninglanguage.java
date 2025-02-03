package com.hackathon;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchLearninglanguage {
	
	
    WebDriver driver;
    //Locators
    @FindBy(xpath="//*[starts-with(@id,'cds-react-aria-') and contains(@id,'-product-card-title')]")
    List<WebElement> languagetext;
    
    @FindBy(xpath="//*[@class='cds-CommonCard-metadata']")
    List<WebElement> level ;


    
  //Constructor for initialize driver
    SearchLearninglanguage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }




    public void writeDataToExcel() throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet st = wb.createSheet("languages");
        XSSFRow r1 = st.createRow(0);

        XSSFCell c1 = r1.createCell(0);
        c1.setCellValue("Language");

        c1 = r1.createCell(1);
        c1.setCellValue("Level");

        for (int j = 0; j < languagetext.size(); j++) {
            r1 = st.createRow(j + 1);
            c1 = r1.createCell(0);
            c1.setCellValue(languagetext.get(j).getText());

            c1 = r1.createCell(1);
            c1.setCellValue(level.get(j).getText());
        }

        try (FileOutputStream fos = new FileOutputStream("LanguageLearning.xlsx")) {
            wb.write(fos);
        }
        wb.close();
    }
}


