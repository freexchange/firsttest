package com.gmail.freexchange;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSettings {
    public static WebDriver driver;
    public String expectedTFN = "+1 (855) 229-9349";
    public String expectedEmail = "contact@accountdeduction.com";
    public String baseURL = "https://accountdeduction.com/";
    public List<String> listOfLinks = new ArrayList<String>();
    public List<WebElement> tempListOfLinks = new ArrayList<WebElement>();
    public List<String> tempListOfPurchase = new ArrayList<String>();

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\1\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    @AfterClass
    public static void tearDown() {
        driver.close();
        driver.quit();

    }
}
