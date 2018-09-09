package com.gmail.freexchange;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestSettings {
    public static WebDriver driver;
    public String expectedTFN = "+1 (855) 229-9349";
    public String expectedEmail = "contact@accountdeduction.com";
    public String expectedFullAddress = "9449 S. Kedzie Avenue #351 Evergreen Park, IL 60805 United States";
    public String expectedAddress = "9449 S. Kedzie Avenue #351";
    public String expectedCity = "Evergreen Park";
    public String expectedState = "IL";
    public String expectedZip = "60805";
    public String expectedCountry = "United States";
    public String baseURL = "https://accountdeduction.com/";
    public List<String> listOfLinks = new ArrayList<String>();
    public List<WebElement> tempListOfLinks = new ArrayList<WebElement>();
    public List<String> tempListOfPurchase = new ArrayList<String>();
    public Map<String, String>  listOfUSStateAbbreviations = new HashMap<String, String>();

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
