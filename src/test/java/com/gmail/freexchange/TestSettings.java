package com.gmail.freexchange;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class TestSettings {

    private static String[] columns2 = {"Status", "Date of Test", "Page Name", "Page Title", "Url", "Company", "Company Times", "Address",
            "Address Times", "Email", "Email Times", "TFN", "TFN Times", "Descriptor", "Screenshot Name"};


    public static String[] getColumns2() {
        return columns2;
    }

    private static List<Pages> pages =  new ArrayList<>();

    public static List<Pages> getPages() {
        return pages;
    }

    public static WebDriver driver;
    public String expectedCompany = "Sapped LLC";
    public String expectedDescriptor = "iDeduction.com 18552475904";
    public String expectedTFN = "+1 (855) 247-5904";
    public String expectedFullAddress = "1304 N Hayworth Ave, Apt 18, West Hollywood, CA 90046 United States";
    public String expectedAddress = "1304 N Hayworth Ave, Apt 18";
    public String expectedCity = "West Hollywood";
    public String expectedState = "CA";
    public String expectedZip = "90046";
    public String expectedCountry = "United States";
    public String baseURL = "https://ideduction.com/";
    public List<String> listOfLinks = new ArrayList<String>();
    public List<WebElement> tempListOfLinks = new ArrayList<WebElement>();
    public List<String> tempListOfPurchase = new ArrayList<String>();
    public Map<String, String>  listOfUSStateAbbreviations = new HashMap<String, String>();

    public static String expectedEmail = "contact@ideduction.com";
    public static File folder = new File("D:/AutomationTests/" + expectedEmail.substring(expectedEmail
            .indexOf("@")+1)+ " " + new SimpleDateFormat("yyyy-MM-dd HH_mm_ss")
            .format(Calendar.getInstance().getTime()));

    public static String getExpectedEmail() {
        return expectedEmail;
    }

    public WebDriver getDriver() {
        return driver;
    }


    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\1\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        folder.mkdir();
    }

    @AfterClass
    public static void tearDown() throws IOException {
        writeReport();
        driver.close();
        driver.quit();
    }

    public static void writeReport() throws IOException {

        Workbook workbook = new XSSFWorkbook();
        //CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet(getExpectedEmail());
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        Row headerRow = sheet.createRow(0);
        for(int i = 0; i < getColumns2().length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(getColumns2()[i]);
            cell.setCellStyle(headerCellStyle);
        }
        int rowNum = 1;
        for(Pages page: getPages()) {
            Row row = sheet.createRow(rowNum++);

            if(page.getStatus().equals("Passed!")) {
                CellStyle style = workbook.createCellStyle();
                style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
                style.setFillPattern(FillPatternType.LEAST_DOTS);
                Cell cell = row.createCell(0);
                cell.setCellValue(page.getStatus());
                cell.setCellStyle(style);
            } else {
                // Orange "foreground", foreground being the fill foreground not the font color.
                CellStyle style = workbook.createCellStyle();
                style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.RED1.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell cell = row.createCell(0);
                cell.setCellValue(page.getStatus());
                cell.setCellStyle(style);
            }
            row.createCell(1)
                    .setCellValue(page.getDateOfTest());
            row.createCell(2)
                    .setCellValue(page.getPageName());
            if(!page.getPageTitleStatus().equals("Passed")) {
                CellStyle style = workbook.createCellStyle();
                style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell cell = row.createCell(3);
                cell.setCellValue(page.getPageTitle());
                cell.setCellStyle(style);
            } else {
                row.createCell(3)
                        .setCellValue(page.getPageTitle());
            }
            row.createCell(4)
                    .setCellValue(page.getUrl());
            if(!page.getCompanyStatus().equals("Passed")) {
                CellStyle style = workbook.createCellStyle();
                style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell cell = row.createCell(5);
                cell.setCellValue(page.getCompany());
                cell.setCellStyle(style);
            } else {
                row.createCell(5)
                        .setCellValue(page.getCompany());
            }
            if(!page.getCompanyTimesStatus().equals("Passed")) {
                CellStyle style = workbook.createCellStyle();
                style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell cell = row.createCell(6);
                cell.setCellValue(page.getCompanyTimes());
                cell.setCellStyle(style);
            } else {
                row.createCell(6)
                        .setCellValue(page.getCompanyTimes());
            }
            if(!page.getAddressStatus().equals("Passed")) {
                CellStyle style = workbook.createCellStyle();
                style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell cell = row.createCell(7);
                cell.setCellValue(page.getAddress());
                cell.setCellStyle(style);
            } else {
                row.createCell(7)
                        .setCellValue(page.getAddress());
            }
            if(!page.getAddressTimesStatus().equals("Passed")) {
                CellStyle style = workbook.createCellStyle();
                style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell cell = row.createCell(8);
                cell.setCellValue(page.getAddressTimes());
                cell.setCellStyle(style);
            } else {
                row.createCell(8)
                        .setCellValue(page.getAddressTimes());
            }
            if(!page.getEmailStatus().equals("Passed")) {
                CellStyle style = workbook.createCellStyle();
                style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell cell = row.createCell(9);
                cell.setCellValue(page.getEmail());
                cell.setCellStyle(style);
            } else {
                row.createCell(9)
                        .setCellValue(page.getEmail());
            }
            if(!page.getEmailTimesStatus().equals("Passed")) {
                CellStyle style = workbook.createCellStyle();
                style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell cell = row.createCell(10);
                cell.setCellValue(page.getEmailTimes());
                cell.setCellStyle(style);
            } else {
                row.createCell(10)
                        .setCellValue(page.getEmailTimes());
            }
            if(!page.getTfnStatus().equals("Passed")) {
                CellStyle style = workbook.createCellStyle();
                style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell cell = row.createCell(11);
                cell.setCellValue(page.getTfn());
                cell.setCellStyle(style);
            } else {
                row.createCell(11)
                        .setCellValue(page.getTfn());
            }
            if(!page.getTfnTimesStatus().equals("Passed")) {
                CellStyle style = workbook.createCellStyle();
                style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell cell = row.createCell(12);
                cell.setCellValue(page.getTfnTimes());
                cell.setCellStyle(style);
            } else {
                row.createCell(12)
                        .setCellValue(page.getTfnTimes());
            }
            if(!page.getDescriptorStatus().equals("Passed")) {
                CellStyle style = workbook.createCellStyle();
                style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                Cell cell = row.createCell(13);
                cell.setCellValue(page.getDescriptor());
                cell.setCellStyle(style);
            } else {
                row.createCell(13)
                        .setCellValue(page.getDescriptor());
            }
            row.createCell(14)
                    .setCellValue(page.getScreenshotName());
        }
        for(int i = 0; i < getColumns2().length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream(folder.toString()+"/"+"Report "+expectedEmail
                .substring(expectedEmail
                        .indexOf("@")+1)+ " " + new SimpleDateFormat("yyyy-MM-dd HH_mm_ss")
                .format(Calendar.getInstance().getTime())+".xlsx");

        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

    }
}

