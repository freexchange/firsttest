package com.gmail.freexchange;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
import java.io.*;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WebsiteTests extends TestSettings {

    @Ignore
    public void userLogin() {
        System.out.println("=======userLogin========");
        driver.get(baseURL + "members-login/");
        WebElement loginField = driver.findElement(By.id("user_login"));
        loginField.sendKeys("demo");
        WebElement passwordField = driver.findElement(By.id("user_pass"));
        passwordField.sendKeys("demo");
        WebElement loginButton = driver.findElement(By.id("wp-submit"));
        loginButton.click();
        WebElement profileUser = driver.findElement(By.cssSelector(".entry-title"));
        String mailUser = profileUser.getText();
        //System.out.println(mailUser + "---------------------------------------------------");
        Assert.assertEquals("Welcome to the Secure Members Area".toUpperCase(), mailUser.toUpperCase());
        //System.out.println("--------------------------------------------------- "+driver.findElement(By.tagName("a")).getText());
        String expectedTitle = "Welcome to the Secure Members Area – Account Deduction";
        String actualTitle = "";
        actualTitle = driver.getTitle();

        /*
         * compare the actual title of the page with the expected one and print
         * the result as "Passed" or "Failed"
         */
        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
            System.out.println(actualTitle);
            System.out.println(expectedTitle);
        }
        driver.findElement(By.linkText("LOGOUT")).click();
    }

    @Ignore
    public void siteTitles() {
        System.out.println("=======siteTitles========");
        listOfLinks.add(baseURL);
        listOfLinks.add(baseURL + "terms");
        listOfLinks.add(baseURL + "refund-policy");
        listOfLinks.add(baseURL + "privacy-policy");
        listOfLinks.add(baseURL + "our-products");
        listOfLinks.add(baseURL + "contact-us");
        listOfLinks.add(baseURL + "members-login");
        listOfLinks.add(baseURL + "members-area");
        driver.get(baseURL);
        listOfLinks.add(driver.findElement(By.cssSelector(".social>a")).getAttribute("href"));

        for (String b : listOfLinks) {
            System.out.print(b + " ");
            if (!b.contains("facebook")) {
                driver.get(b);
                String pageTitle = driver.getTitle();
                String h1 = driver.findElement(By.tagName("h1")).getText();
                pageTitle = pageTitle.replace(" – ", " ");
                pageTitle = pageTitle.replace(" - ", " ");
                Assert.assertEquals((h1 + " Account Deduction").toUpperCase().trim(), pageTitle.toUpperCase().trim());
                System.out.println("Passed! " + pageTitle + " - " + (h1 + " Account Deduction"));
                if (b.contains("login")) {
                    WebElement login = driver.findElement(By.id("user_login"));
                    login.sendKeys("demo");
                    WebElement password = driver.findElement(By.id("user_pass"));
                    password.sendKeys("demo");
                    WebElement logButton = driver.findElement(By.id("wp-submit"));
                    logButton.click();
                    pageTitle = driver.getTitle();
                    pageTitle = pageTitle.replace(" – ", " ");
                    pageTitle = pageTitle.replace(" - ", " ");
                    //Assert.assertEquals((h1 + " Account Deduction").toUpperCase().trim(), pageTitle.toUpperCase().trim());
                    System.out.println("Passed! " + pageTitle + " - " + (h1 + " Account Deduction"));
                }
            }
        }
    }

    @Ignore
    public void siteTFN() {
        System.out.println((char) 13 + "=======siteTFN========");
        driver.get(baseURL);
        String actualTFN = driver.findElement(By
                .cssSelector("#colophon > div > div > div.footer-col-left > div:nth-child(4)"))
                .getText()
                .substring(9, 26);
        if (actualTFN.contentEquals(expectedTFN)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualTFN);
        }
        driver.get(baseURL + "terms");
        actualTFN = driver.findElement(By.cssSelector("#post-303 > div > article > div > p:nth-child(18)")).getText();
        actualTFN = actualTFN.substring(actualTFN.length() - 18, actualTFN.length() - 1);
        if (actualTFN.contentEquals(expectedTFN)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualTFN);
        }
        driver.get(baseURL + "refund-policy");
        actualTFN = driver.findElement(By.cssSelector("#post-19 > div > article > div > p:nth-child(2)")).getText();
        actualTFN = actualTFN.substring(actualTFN.length() - 18, actualTFN.length() - 1);
        if (actualTFN.contentEquals(expectedTFN)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualTFN);
        }
        driver.get(baseURL + "contact-us");
        actualTFN = driver.findElement(By.cssSelector("#post-11 > div > div > p:nth-child(2)")).getText();
        actualTFN = actualTFN.substring(actualTFN.length() - 17, actualTFN.length());
        if (actualTFN.contentEquals(expectedTFN)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualTFN);
        }
        driver.get(baseURL + "our-products");
        tempListOfLinks = driver.findElements(By.cssSelector(".product-list"));
        for (WebElement we : tempListOfLinks) {
            String str = we.findElement(By.cssSelector("div.buynow > p:nth-child(2) > a")).getAttribute("href");
            tempListOfPurchase.add(str);
        }
        for (String str : tempListOfPurchase) {
            driver.get(str);
            actualTFN = driver.findElement(By.cssSelector(" body > div.containerWrap > div > div:nth-child(5) > div > div > p:nth-child(5)")).getText();
            actualTFN = actualTFN.substring(actualTFN.length() - 37, actualTFN.length() - 20);
            if (actualTFN.contentEquals(expectedTFN)) {
                System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
            } else {
                System.out.println(driver.getCurrentUrl() + " Test Failed " + actualTFN);
            }
        }
        driver.get(baseURL);
        driver.get(driver.findElement(By.cssSelector("p.social > a")).getAttribute("href"));
        actualTFN = driver.findElement(By.cssSelector("div._4bl9 > div")).getText();
        System.out.println(actualTFN);
        driver.get(baseURL);
        driver.get(driver.findElement(By.cssSelector("p.social > a")).getAttribute("href"));
        actualTFN = driver.findElement(By.cssSelector("._1xnd > div:nth-child(1)")).getText();
        actualTFN = actualTFN.substring(actualTFN.indexOf("+"), actualTFN.indexOf("+") + 15);
        actualTFN = actualTFN.substring(0, 3).concat("(").concat(actualTFN.substring(3, 6).concat(") ").concat(actualTFN.substring(7)));
        if (actualTFN.contentEquals(expectedTFN)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualTFN);
        }
        driver.get(driver.findElement(By.cssSelector("._2yau")).getAttribute("href"));
    }

    @Ignore
    public void siteEmail() {
        System.out.println((char) 13 + "=======siteEmail========");
        driver.get(baseURL + "terms");
        String actualEmail = driver.findElement(By
                .cssSelector("#post-303 > div > article > div > p:nth-child(18) > a"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        driver.get(baseURL + "refund-policy");
        actualEmail = driver.findElement(By
                .cssSelector("#post-19 > div > article > div > p:nth-child(2) > a"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        driver.get(baseURL + "privacy-policy");
        actualEmail = driver.findElement(By
                .cssSelector("#post-21 > div > article > div > p:nth-child(9) > a"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        actualEmail = driver.findElement(By
                .cssSelector("#post-21 > div > article > div > p:nth-child(46) > a:nth-child(3)"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        actualEmail = driver.findElement(By
                .cssSelector("#post-21 > div > article > div > p:nth-child(48) > a:nth-child(3)"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        actualEmail = driver.findElement(By
                .cssSelector("#post-21 > div > article > div > p:nth-child(53) > a"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        actualEmail = driver.findElement(By
                .cssSelector("#post-21 > div > article > div > p:nth-child(57) > a"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        driver.get(baseURL + "contact-us");
        actualEmail = driver.findElement(By
                .cssSelector("#post-11 > div > div > p:nth-child(2) > a"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        driver.get(baseURL + "members-login");
        WebElement login = driver.findElement(By.id("user_login"));
        login.sendKeys("demo");
        WebElement password = driver.findElement(By.id("user_pass"));
        password.sendKeys("demo");
        WebElement logButton = driver.findElement(By.id("wp-submit"));
        logButton.click();
        actualEmail = driver.findElement(By
                .cssSelector("#post-15 > div > div:nth-child(2) > a"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        driver.get(baseURL);
        driver.get(driver.findElement(By.cssSelector("p.social > a")).getAttribute("href"));
        List<WebElement> links = driver.findElements(By.cssSelector("._2yau"));
        driver.get(links.get(4).getAttribute("href"));
        List<WebElement> links2 = driver.findElements(By.cssSelector("._50f4"));
        actualEmail = links2.get(1).getText();
        if (actualEmail.contentEquals(expectedTFN)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        driver.get(driver.findElement(By.cssSelector("._2yau")).getAttribute("href"));
    }

    @Ignore
    public void siteAddress() {
        System.out.println((char) 13 + "=======siteAddress========");
        driver.get(baseURL);
        String actualAddress = driver.findElement(By
                .cssSelector("#colophon > div > div > div.footer-col-right > div"))
                .getText();
        String[] CitySateCountry = actualAddress.substring(actualAddress.lastIndexOf(10) + 1)
                .replaceAll("[0-9]", "").replaceAll("  ", ", ").split(", ");
        String actualCity = CitySateCountry[0];
        String actualState = CitySateCountry[1];
        String actualCountry = CitySateCountry[2];
        String actualZip = actualAddress.substring(actualAddress.lastIndexOf(10))
                .replaceAll("[^0-9]", "");
        actualAddress = actualAddress.substring(actualAddress.indexOf("| ") + 2, actualAddress.length());
        String Address = actualAddress.substring(0, actualAddress.lastIndexOf(10));
        String actualFullAddress = Address + " " + actualCity + ", " + actualState + " " + actualZip + " " + actualCountry;
        System.out.println(actualFullAddress);
        System.out.println(expectedFullAddress);
        if (actualFullAddress.contains(expectedFullAddress)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualFullAddress);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualFullAddress);
        }

        driver.get(baseURL + "terms");
        actualAddress = driver.findElement(By
                .cssSelector("#post-303 > div > article > div > p:nth-child(35)"))
                .getText();
        listOfUSStateAbbreviations.put("AL", "Alabama");
        listOfUSStateAbbreviations.put("AK", "Alaska");
        listOfUSStateAbbreviations.put("AZ", "Arizona");
        listOfUSStateAbbreviations.put("AR", "Arkansas");
        listOfUSStateAbbreviations.put("CA", "California");
        listOfUSStateAbbreviations.put("CO", "Colorado");
        listOfUSStateAbbreviations.put("CT", "Connecticut");
        listOfUSStateAbbreviations.put("DE", "Delaware");
        listOfUSStateAbbreviations.put("DC", "District of Columbia");
        listOfUSStateAbbreviations.put("FL", "Florida");
        listOfUSStateAbbreviations.put("GA", "Georgia");
        listOfUSStateAbbreviations.put("HI", "Hawaii");
        listOfUSStateAbbreviations.put("ID", "Idaho");
        listOfUSStateAbbreviations.put("IL", "Illinois");
        listOfUSStateAbbreviations.put("IN", "Indiana");
        listOfUSStateAbbreviations.put("IA", "Iowa");
        listOfUSStateAbbreviations.put("KS", "Kansas");
        listOfUSStateAbbreviations.put("KY", "Kentucky");
        listOfUSStateAbbreviations.put("LA", "Louisiana");
        listOfUSStateAbbreviations.put("ME", "Maine");
        listOfUSStateAbbreviations.put("MD", "Maryland");
        listOfUSStateAbbreviations.put("MA", "Massachusetts");
        listOfUSStateAbbreviations.put("MI", "Michigan");
        listOfUSStateAbbreviations.put("MN", "Minnesota");
        listOfUSStateAbbreviations.put("MS", "Mississippi");
        listOfUSStateAbbreviations.put("MO", "Missouri");
        listOfUSStateAbbreviations.put("MT", "Montana");
        listOfUSStateAbbreviations.put("NE", "Nebraska");
        listOfUSStateAbbreviations.put("NV", "Nevada");
        listOfUSStateAbbreviations.put("NH", "New Hampshire");
        listOfUSStateAbbreviations.put("NJ", "New Jersey");
        listOfUSStateAbbreviations.put("NM", "New Mexico");
        listOfUSStateAbbreviations.put("NY", "New York");
        listOfUSStateAbbreviations.put("NC", "North Carolina");
        listOfUSStateAbbreviations.put("ND", "North Dakota");
        listOfUSStateAbbreviations.put("OH", "Ohio");
        listOfUSStateAbbreviations.put("OK", "Oklahoma");
        listOfUSStateAbbreviations.put("OR", "Oregon");
        listOfUSStateAbbreviations.put("PA", "Pennsylvania");
        listOfUSStateAbbreviations.put("RI", "Rhode Island");
        listOfUSStateAbbreviations.put("SC", "South Carolina");
        listOfUSStateAbbreviations.put("SD", "South Dakota");
        listOfUSStateAbbreviations.put("TN", "Tennessee");
        listOfUSStateAbbreviations.put("TX", "Texas");
        listOfUSStateAbbreviations.put("UT", "Utah");
        listOfUSStateAbbreviations.put("VT", "Vermont");
        listOfUSStateAbbreviations.put("VA", "Virginia");
        listOfUSStateAbbreviations.put("WA", "Washington");
        listOfUSStateAbbreviations.put("WV", "West Virginia");
        listOfUSStateAbbreviations.put("WI", "Wisconsin");
        listOfUSStateAbbreviations.put("WY", "Wyoming");
        listOfUSStateAbbreviations.put("AS", "American Samoa");
        listOfUSStateAbbreviations.put("GU", "Guam");
        listOfUSStateAbbreviations.put("MP", "Northern Mariana Islands");
        listOfUSStateAbbreviations.put("PR", "Puerto Rico");
        listOfUSStateAbbreviations.put("VI", "U.S. Virgin Islands");
        listOfUSStateAbbreviations.put("UM", "U.S. Minor Outlying Islands");
        listOfUSStateAbbreviations.put("FM", "Micronesia");
        listOfUSStateAbbreviations.put("MH", "Marshall Islands");
        listOfUSStateAbbreviations.put("PW", "Palau");
        actualFullAddress = actualCity + ", " + listOfUSStateAbbreviations.get(actualState) + ", " + actualCountry;
        if (actualFullAddress.contains(expectedCity + ", " + listOfUSStateAbbreviations.get(expectedState) +
                ", " + expectedCountry)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + expectedCity + ", " +
                    listOfUSStateAbbreviations.get(expectedState) + ", " + expectedCountry);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + expectedCity +
                    ", " + listOfUSStateAbbreviations.get(expectedState) + ", " + expectedCountry);
        }

        driver.get(baseURL + "terms");
        actualAddress = driver.findElement(By
                .cssSelector("#post-303 > div > article > div > p:nth-child(28)"))
                .getText();
        if (("the" + actualAddress).contains(" the " + expectedCountry)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + " the " + expectedCountry);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + " the " + expectedCountry);
        }

        driver.get(baseURL + "privacy-policy");
        actualAddress = driver.findElement(By
                .cssSelector("#post-21 > div > article > div > p:nth-child(4)"))
                .getText();
        actualAddress += driver.findElement(By
                .cssSelector("#post-21 > div > article > div > p:nth-child(57)"))
                .getText();
        final String WORD = " the United States";

        int i = 0;
        Pattern pattern = Pattern.compile(WORD);
        Matcher matcher = pattern.matcher(actualAddress);//указываем свой текст
        while (matcher.find()) {
            i++;
        }
        if (i == 6) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + " \" the United States\" " + i + " times");
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + " \" the United States\" " + i + " times");
        }

        driver.get(baseURL + "contact-us");
        actualAddress = driver.findElement(By
                .cssSelector("#post-11 > div > div > p:nth-child(2)"))
                .getText();

        String[] add = actualAddress.split("\n");

        if ((add[1] + " " + add[2] + " " + add[3]).contains(expectedFullAddress)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + add[1] + " " + add[2] + " " + add[3]);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + add[1] + " " + add[2] + " " + add[3]);
        }
        driver.get(baseURL + "our-products");
        tempListOfLinks = driver.findElements(By.cssSelector(".product-list"));
        for (WebElement we : tempListOfLinks) {
            String str = we.findElement(By.cssSelector("div.buynow > p:nth-child(2) > a")).getAttribute("href");
            tempListOfPurchase.add(str);
        }
        for (String str : tempListOfPurchase) {
            driver.get(str);
            actualAddress = driver.findElement(By.cssSelector("body > div.containerWrap > div > div:nth-child(5) > div > div > p:nth-child(5)")).getText();
            add = actualAddress.split("\n");
            actualFullAddress = add[2].substring(add[2].indexOf("|") + 2);
            if (actualFullAddress.contentEquals(expectedFullAddress)) {
                System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualFullAddress);
            } else {
                System.out.println(driver.getCurrentUrl() + " Test Failed " + actualFullAddress);
            }
        }
        driver.get(baseURL);
        driver.get(driver.findElement(By.cssSelector("p.social > a")).getAttribute("href"));
        List<WebElement> links = driver.findElements(By.cssSelector("._2yau"));
        driver.get(links.get(4).getAttribute("href"));
        WebElement links2 = driver.findElement(By.cssSelector("._3-8w"));
        actualFullAddress = links2.getText();
        add = actualFullAddress.split("\n");
        actualFullAddress = add[1] + " " + add[2] + " " + add[3];
        if (actualFullAddress.contentEquals(expectedFullAddress)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualFullAddress);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualFullAddress);
        }
    }

    @Ignore
    public void siteCompany() {
        System.out.println((char) 13 + "=======siteAddress========");
        driver.get(baseURL);
        String actualCompany = driver.findElement(By
                .cssSelector("#colophon > div > div > div.footer-col-right > div"))
                .getText();
        actualCompany = actualCompany.substring(actualCompany.indexOf("\n") + 1, actualCompany.indexOf("|") - 1);
        if (actualCompany.contentEquals(expectedCompany)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualCompany);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualCompany);
        }

        driver.get(baseURL + "terms");
        actualCompany = driver.findElement(By
                .cssSelector("html"))
                .getText();
        final String WORD = expectedCompany;

        int i = 0;
        Pattern pattern = Pattern.compile(WORD.toUpperCase());
        Matcher matcher = pattern.matcher(actualCompany);//указываем свой текст
        while (matcher.find()) {
            i++;
        }
        if (i == 9) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + " " + expectedCompany.toUpperCase() + " " + i + " times");
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + " " + expectedCompany.toUpperCase() + " " + i + " times");
        }
        i = 0;
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(actualCompany);//указываем свой текст
        while (matcher.find()) {
            i++;
        }
        if (i == 20) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + " " + expectedCompany + " " + i + " times");
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + " " + expectedCompany + " " + i + " times");
        }
        i = 0;
        driver.get(baseURL + "privacy-policy");
        actualCompany = driver.findElement(By
                .cssSelector("html"))
                .getText();
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(actualCompany);//указываем свой текст
        while (matcher.find()) {
            i++;
        }
        if (i == 5) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + " " + expectedCompany + " " + i + " times");
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + " " + expectedCompany + " " + i + " times");
        }
        i = 0;
        driver.get(baseURL + "refund-policy");
        actualCompany = driver.findElement(By
                .cssSelector("html"))
                .getText();
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(actualCompany);//указываем свой текст
        while (matcher.find()) {
            i++;
        }
        if (i == 2) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + " " + expectedCompany + " " + i + " times");
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + " " + expectedCompany + " " + i + " times");
        }
        i = 0;
        driver.get(baseURL + "contact-us");
        actualCompany = driver.findElement(By
                .cssSelector("html"))
                .getText();
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(actualCompany);//указываем свой текст
        while (matcher.find()) {
            i++;
        }
        if (i == 2) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + " " + expectedCompany + " " + i + " times");
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + " " + expectedCompany + " " + i + " times");
        }
        driver.get(baseURL + "our-products");
        tempListOfLinks = driver.findElements(By.cssSelector(".product-list"));
        for (WebElement we : tempListOfLinks) {
            String str = we.findElement(By.cssSelector("div.buynow > p:nth-child(2) > a")).getAttribute("href");
            tempListOfPurchase.add(str);
        }
        for (String str : tempListOfPurchase) {
            driver.get(str);
            actualCompany = driver.findElement(By.cssSelector("body > div.containerWrap > div > div:nth-child(5) > div > div > p:nth-child(5)")).getText();
            String[] add = actualCompany.split("\n");
            actualCompany = add[2].substring(0, add[2].indexOf("|") - 1);
            if (actualCompany.contentEquals(expectedCompany)) {
                System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualCompany);
            } else {
                System.out.println(driver.getCurrentUrl() + " Test Failed " + actualCompany);
            }
        }

        driver.get(baseURL);
        driver.get(driver.findElement(By.cssSelector("p.social > a")).getAttribute("href"));
        List<WebElement> links = driver.findElements(By.cssSelector("._2yau"));
        driver.get(links.get(4).getAttribute("href"));
        WebElement links2 = driver.findElement(By.cssSelector("._3-8w"));
        actualCompany = links2.getText();
        String[] add = actualCompany.split("\n");
        actualCompany = add[0];
        if (actualCompany.contentEquals(expectedCompany)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualCompany);
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualCompany);
        }
    }

    @Ignore
    public void siteDescriptor() throws IOException, InterruptedException {
        System.out.println((char) 13 + "=======siteDescriptor========");
        driver.get(baseURL + "our-products");

        Thread.sleep(2000);
        Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(fpScreenshot.getImage(), "PNG", new File("D:///FullPageScreenshot.png"));


        tempListOfLinks = driver.findElements(By.cssSelector(".product-list"));
        for (WebElement we : tempListOfLinks) {
            String str = we.findElement(By.cssSelector("div.buynow > p:nth-child(2) > a")).getAttribute("href");
            tempListOfPurchase.add(str);
        }
        for (String str : tempListOfPurchase) {
            driver.get(str);
            String actualDescriptor = driver.findElement(By.cssSelector("body > div.containerWrap > div > div:nth-child(5) > div > div > p:nth-child(1)")).getText();
            //System.out.println(actualDescriptor);
            String[] add = actualDescriptor.split("\n");
            actualDescriptor = add[1].substring(add[1].indexOf("\"") + 1, add[1].length() - 1);
            if (actualDescriptor.contentEquals(expectedDescriptor)) {
                System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualDescriptor);
            } else {
                System.out.println(driver.getCurrentUrl() + " Test Failed " + actualDescriptor);
            }
        }
        int c = 0;
    }
    @Test
    public void ContactUs() throws IOException, InterruptedException {

        Pages contactUs = new Pages();
        int status = 0;
        System.out.println("=======ContactUs========");
        contactUs.setPageName("Contact Us");
        contactUs.setDateOfTest(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                .format(Calendar.getInstance().getTime()));
        contactUs.setUrl(baseURL + "contact-us");
        driver.get(baseURL + "contact-us");
        /**
         * =======ContactUsScreenshot========
         */
        Thread.sleep(2000);

        String screenshotName = contactUs.getPageName() + " " + new SimpleDateFormat("yyyy-MM-dd HH_mm_ss")
                .format(Calendar.getInstance().getTime()) + ".png";

        Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);
        ImageIO.write(fpScreenshot.getImage(), "PNG",
                new File(folder.toString() + "/" + screenshotName));
        contactUs.setScreenshotName(screenshotName);
        /**
         * =======ContactUsTitle========
         */
        driver.get(baseURL + "contact-us");
        String actualPageTitle = driver.getTitle();
        String h1 = driver.findElement(By.tagName("h1")).getText();
        actualPageTitle = actualPageTitle.replace(" – ", " ");
        driver.get(baseURL + "terms");
        String termsText = driver.findElement(By.tagName("html")).getText();
        String siteName = termsText.substring(termsText.indexOf("(") + 2, termsText.indexOf(")") - 1);
        driver.get(baseURL + "contact-us");
        if ((h1 + " " + siteName).toUpperCase().trim().contentEquals(actualPageTitle.toUpperCase().trim())) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualPageTitle);
            contactUs.setPageTitle(actualPageTitle);
            contactUs.setPageTitleStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualPageTitle);
            contactUs.setPageTitle(actualPageTitle);
            contactUs.setPageTitleStatus("Failed");
        }
        /**
         * ========ContactUsEmail==========
         */
        driver.get(baseURL + "contact-us");
        String actualEmail = driver.findElement(By
                .cssSelector("#post-11 > div > div > p:nth-child(2) > a"))
                .getText();
        String text = driver.findElement(By
                .cssSelector("html"))
                .getText();
        String WORD = actualEmail;

        int a = 0;
        Pattern pattern = Pattern.compile(WORD);
        Matcher matcher = pattern.matcher(text);//указываем свой текст
        while (matcher.find()) {
            a++;
        }
        if (actualEmail.contains(TestSettings.getExpectedEmail()) && a == 1) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail + " " + a + " Times");
            contactUs.setEmail(actualEmail);
            contactUs.setEmailStatus("Passed");
            contactUs.setEmailTimes(a);
            contactUs.setEmailTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail + " " + a + " Times");
            contactUs.setEmail(actualEmail);
            contactUs.setEmailStatus("Failed");
            contactUs.setEmailTimes(a);
            contactUs.setEmailTimesStatus("Failed");
        }
         /**
         * =======ContactUsAddress========
         */
        driver.get(baseURL + "contact-us");
        String actualAddress = driver.findElement(By
                .cssSelector("#post-11 > div > div > p:nth-child(2)"))
                .getText();
        String[] add = actualAddress.split("\n");
        text = driver.findElement(By
                .cssSelector("html"))
                .getText();
        //String WORD = add[1] + " " + add[2] + " " + add[3];

        a = 0;
        for (int i = 1; i < add.length - 2; i++) {
        pattern = Pattern.compile(add[i]);
        matcher = pattern.matcher(text);//указываем свой текст
            while (matcher.find()) {
                a++;
            }
        }
        if ((add[1] + " " + add[2] + " " + add[3]).contains(expectedFullAddress) && a == 6) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + add[1] + " " + add[2] + " " + add[3] + " " +
            a + " Times");
            contactUs.setAddress(add[1] + " " + add[2] + " " + add[3]);
            contactUs.setAddressStatus("Passed");
            contactUs.setAddressTimes(a);
            contactUs.setAddressTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + add[1] + " " + add[2] + " " + add[3] + " " +
                    a + " Times");
            contactUs.setAddress(add[1] + " " + add[2] + " " + add[3]);
            contactUs.setAddressStatus("Failed");
            contactUs.setAddressTimes(a);
            contactUs.setAddressTimesStatus("Failed");
        }
        /**
         * ========ContactUsCompany==========
         */
        driver.get(baseURL + "contact-us");
        WORD = expectedCompany;
        int count = 0;
        int i = 0;
        pattern = Pattern.compile(WORD.toUpperCase());
        matcher = pattern.matcher(text);//указываем свой текст
        while (matcher.find()) {
            i++;
        }
        count += i;
        i = 0;
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(text);//указываем свой текст
        while (matcher.find()) {
            i++;
        }
        count += i;
        if (count == 2) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + expectedCompany + " " + count + " times");
            contactUs.setCompany(expectedCompany);
            contactUs.setCompanyStatus("Passed");
            contactUs.setCompanyTimes(count);
            contactUs.setCompanyTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + expectedCompany + " " + count + " times");
            contactUs.setCompany("Wrong Company");
            contactUs.setCompanyStatus("Failed");
            contactUs.setCompanyTimes(count);
            contactUs.setCompanyTimesStatus("Failed");
        }
        count = 0;
        i = 0;
        /**
         * ==========ContactUsTFN===========
         */
        WORD = "\\+" + expectedTFN.substring(1, 3) + "\\(" + expectedTFN.substring(4, 7) + "\\)" + expectedTFN.substring(8);

        a = 0;
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(text);//указываем свой текст
        while (matcher.find()) {
            a++;
        }
        if (a == 2) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + expectedTFN + " " + a + " Times");
            contactUs.setTfn(expectedTFN);
            contactUs.setTfnStatus("Passed");
            contactUs.setTfnTimes(a);
            contactUs.setTfnTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + expectedTFN + " " + a + " Times");
            contactUs.setTfn(expectedTFN);
            contactUs.setTfnStatus("Failed");
            contactUs.setTfnTimes(a);
            contactUs.setTfnTimesStatus("Failed");
        }

        contactUs.setDescriptor("N/A");
        contactUs.setDescriptorStatus("Passed");
        status += 1;

        if (status == 6) {
            contactUs.setStatus("Passed!");
        } else {
            contactUs.setStatus("Failed");
        }
        status = 0;
        getPages().add(contactUs);

    }
    @Test
    public void RefundPolicy() throws IOException, InterruptedException {
        Pages refundPolicy = new Pages();
        int status = 0;
        System.out.println("=======RefundPolicy========");
        refundPolicy.setPageName("Refund Policy");
        refundPolicy.setDateOfTest(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                .format(Calendar.getInstance().getTime()));
        refundPolicy.setUrl(baseURL + "refund-policy");
        driver.get(baseURL + "refund-policy");
        /**
         * =======RefundPolicyScreenshot========
         */
        Thread.sleep(2000);

        String screenshotName = refundPolicy.getPageName() + " " + new SimpleDateFormat("yyyy-MM-dd HH_mm_ss")
                .format(Calendar.getInstance().getTime()) + ".png";

        Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);
        ImageIO.write(fpScreenshot.getImage(), "PNG",
                new File(folder.toString() + "/" + screenshotName));
        refundPolicy.setScreenshotName(screenshotName);
        /**
         * =======RefundPolicyTitle========
         */
        driver.get(baseURL + "refund-policy");
        String actualPageTitle = driver.getTitle();
        String h1 = driver.findElement(By.tagName("h1")).getText();
        actualPageTitle = actualPageTitle.replace(" – ", " ");
        driver.get(baseURL + "terms");
        String termsText = driver.findElement(By.tagName("html")).getText();
        String siteName = termsText.substring(termsText.indexOf("(") + 2, termsText.indexOf(")") - 1);
        driver.get(baseURL + "refund-policy");
        if ((h1 + " " + siteName).toUpperCase().trim().contentEquals(actualPageTitle.toUpperCase().trim())) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualPageTitle);
            refundPolicy.setPageTitle(actualPageTitle);
            refundPolicy.setPageTitleStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualPageTitle);
            refundPolicy.setPageTitle(actualPageTitle);
            refundPolicy.setPageTitleStatus("Failed");
        }
        /**
         * ========RefundPolicyEmail==========
         */
        driver.get(baseURL + "refund-policy");
        String actualEmail = driver.findElement(By
                .cssSelector("#post-19 > div > article > div > p:nth-child(2) > a"))
                .getText();
        String text = driver.findElement(By
                .cssSelector("html"))
                .getText();
        String WORD = actualEmail;

        int a = 0;
        Pattern pattern = Pattern.compile(WORD);
        Matcher matcher = pattern.matcher(text);//указываем свой текст
        while (matcher.find()) {
            a++;
        }
        if (actualEmail.contains(TestSettings.getExpectedEmail()) && a == 1) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail + " " + a + " Times");
            refundPolicy.setEmail(actualEmail);
            refundPolicy.setEmailStatus("Passed");
            refundPolicy.setEmailTimes(a);
            refundPolicy.setEmailTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail + " " + a + " Times");
            refundPolicy.setEmail(actualEmail);
            refundPolicy.setEmailStatus("Failed");
            refundPolicy.setEmailTimes(a);
            refundPolicy.setEmailTimesStatus("Failed");
        }
        /**
         * ========RefundPolicyAddress==========
         */

        driver.get(baseURL + "refund-policy");
        String actualAddress = text;

        WORD =  expectedCountry;

        int i = 0;
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(actualAddress);//указываем свой текст
        while (matcher.find()) {
            i++;
        }

        if (i == 1) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + WORD + " " + i + " Times");
            refundPolicy.setAddress(WORD);
            refundPolicy.setAddressStatus("Passed");
            refundPolicy.setAddressTimes(i);
            refundPolicy.setAddressTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + WORD + " " + i + " Times");
            refundPolicy.setAddress(WORD);
            refundPolicy.setAddressStatus("Failed");
            refundPolicy.setAddressTimes(i);
            refundPolicy.setAddressTimesStatus("Failed");
        }
        /**
         * ========RefundPolicyCompany==========
         */
        driver.get(baseURL + "refund-policy");
        WORD = expectedCompany;
        int count = 0;
        i = 0;
        pattern = Pattern.compile(WORD.toUpperCase());
        matcher = pattern.matcher(text);//указываем свой текст
        while (matcher.find()) {
            i++;
        }
        count += i;
        i = 0;
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(text);//указываем свой текст
        while (matcher.find()) {
            i++;
        }
        count += i;
        if (count == 2) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + expectedCompany + " " + count + " times");
            refundPolicy.setCompany(expectedCompany);
            refundPolicy.setCompanyStatus("Passed");
            refundPolicy.setCompanyTimes(count);
            refundPolicy.setCompanyTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + expectedCompany + " " + count + " times");
            refundPolicy.setCompany("Wrong Company");
            refundPolicy.setCompanyStatus("Failed");
            refundPolicy.setCompanyTimes(count);
            refundPolicy.setCompanyTimesStatus("Failed");
        }
        count = 0;
        i = 0;
        /**
         * ==========RefundPolicyTFN===========
         */
        WORD = "\\+" + expectedTFN.substring(1, 3) + "\\(" + expectedTFN.substring(4, 7) + "\\)" + expectedTFN.substring(8);

        a = 0;
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(text);//указываем свой текст
        while (matcher.find()) {
            a++;
        }
        if (a == 2) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + expectedTFN);
            refundPolicy.setTfn(expectedTFN);
            refundPolicy.setTfnStatus("Passed");
            refundPolicy.setTfnTimes(a);
            refundPolicy.setTfnTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + expectedTFN);
            refundPolicy.setTfn(expectedTFN);
            refundPolicy.setTfnStatus("Failed");
            refundPolicy.setTfnTimes(a);
            refundPolicy.setTfnTimesStatus("Failed");
        }

        refundPolicy.setDescriptor("N/A");
        refundPolicy.setDescriptorStatus("Passed");
        status += 1;

        if (status == 6) {
            refundPolicy.setStatus("Passed!");
        } else {
            refundPolicy.setStatus("Failed");
        }
        status = 0;
        getPages().add(refundPolicy);
    }
    @Test
    public void Terms() throws IOException, InterruptedException {

        Pages terms = new Pages();
        int status = 0;
        System.out.println("=======Terms========");
        terms.setPageName("Terms");
        terms.setDateOfTest(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                .format(Calendar.getInstance().getTime()));
        terms.setUrl(baseURL + "terms");
        driver.get(baseURL + "terms");
        /**
         * =======TermsScreenshot========
         */
        Thread.sleep(2000);

        String screenshotName = terms.getPageName() + " " + new SimpleDateFormat("yyyy-MM-dd HH_mm_ss")
                .format(Calendar.getInstance().getTime()) + ".png";

        Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);
        ImageIO.write(fpScreenshot.getImage(), "PNG",
                new File(folder.toString() + "/" + screenshotName));
        terms.setScreenshotName(screenshotName);
        /**
         * =======TermsTitle========
         */
        String actualPageTitle = driver.getTitle();
        String h1 = driver.findElement(By.tagName("h1")).getText();
        actualPageTitle = actualPageTitle.replace(" – ", " ");
        String text = driver.findElement(By.tagName("html")).getText();
        String siteName = text.substring(text.indexOf("(") + 2, text.indexOf(")") - 1);

        if ((h1 + " " + siteName).toUpperCase().trim().contentEquals(actualPageTitle.toUpperCase().trim())) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualPageTitle);
            terms.setPageTitle(actualPageTitle);
            terms.setPageTitleStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualPageTitle);
            terms.setPageTitle(actualPageTitle);
            terms.setPageTitleStatus("Failed");
        }

        /**
         * ========TermsEmail==========
         */
        driver.get(baseURL + "terms");
        String actualEmail = driver.findElement(By
                .cssSelector("#post-303 > div > article > div > p:nth-child(18) > a"))
                .getText();
        String termsText = driver.findElement(By
                .cssSelector("html"))
                .getText();
        String WORD = actualEmail;

        int a = 0;
        Pattern pattern = Pattern.compile(WORD);
        Matcher matcher = pattern.matcher(termsText);//указываем свой текст
        while (matcher.find()) {
            a++;
        }
        if (actualEmail.contains(TestSettings.getExpectedEmail()) && a == 1) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail + " " + a + " Times");
            terms.setEmail(actualEmail);
            terms.setEmailStatus("Passed");
            terms.setEmailTimes(a);
            terms.setEmailTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail + " " + a + " Times");
            terms.setEmail(actualEmail);
            terms.setEmailStatus("Failed");
            terms.setEmailTimes(a);
            terms.setEmailTimesStatus("Failed");
        }
        /**
         * ========TermsAddress==========
         */
        driver.get(baseURL + "terms");
        String actualAddress = driver.findElement(By
                .cssSelector("#colophon > div > div > div.footer-col-right > div"))
                .getText();
        String[] CitySateCountry = actualAddress.substring(actualAddress.lastIndexOf(10) + 1)
                .replaceAll("[0-9]", "").replaceAll("  ", ", ").split(", ");
        String actualCity = CitySateCountry[0];
        String actualState = CitySateCountry[1];
        String actualCountry = CitySateCountry[2];
        String actualZip = actualAddress.substring(actualAddress.lastIndexOf(10))
                .replaceAll("[^0-9]", "");
        listOfUSStateAbbreviations.put("AL", "Alabama");
        listOfUSStateAbbreviations.put("AK", "Alaska");
        listOfUSStateAbbreviations.put("AZ", "Arizona");
        listOfUSStateAbbreviations.put("AR", "Arkansas");
        listOfUSStateAbbreviations.put("CA", "California");
        listOfUSStateAbbreviations.put("CO", "Colorado");
        listOfUSStateAbbreviations.put("CT", "Connecticut");
        listOfUSStateAbbreviations.put("DE", "Delaware");
        listOfUSStateAbbreviations.put("DC", "District of Columbia");
        listOfUSStateAbbreviations.put("FL", "Florida");
        listOfUSStateAbbreviations.put("GA", "Georgia");
        listOfUSStateAbbreviations.put("HI", "Hawaii");
        listOfUSStateAbbreviations.put("ID", "Idaho");
        listOfUSStateAbbreviations.put("IL", "Illinois");
        listOfUSStateAbbreviations.put("IN", "Indiana");
        listOfUSStateAbbreviations.put("IA", "Iowa");
        listOfUSStateAbbreviations.put("KS", "Kansas");
        listOfUSStateAbbreviations.put("KY", "Kentucky");
        listOfUSStateAbbreviations.put("LA", "Louisiana");
        listOfUSStateAbbreviations.put("ME", "Maine");
        listOfUSStateAbbreviations.put("MD", "Maryland");
        listOfUSStateAbbreviations.put("MA", "Massachusetts");
        listOfUSStateAbbreviations.put("MI", "Michigan");
        listOfUSStateAbbreviations.put("MN", "Minnesota");
        listOfUSStateAbbreviations.put("MS", "Mississippi");
        listOfUSStateAbbreviations.put("MO", "Missouri");
        listOfUSStateAbbreviations.put("MT", "Montana");
        listOfUSStateAbbreviations.put("NE", "Nebraska");
        listOfUSStateAbbreviations.put("NV", "Nevada");
        listOfUSStateAbbreviations.put("NH", "New Hampshire");
        listOfUSStateAbbreviations.put("NJ", "New Jersey");
        listOfUSStateAbbreviations.put("NM", "New Mexico");
        listOfUSStateAbbreviations.put("NY", "New York");
        listOfUSStateAbbreviations.put("NC", "North Carolina");
        listOfUSStateAbbreviations.put("ND", "North Dakota");
        listOfUSStateAbbreviations.put("OH", "Ohio");
        listOfUSStateAbbreviations.put("OK", "Oklahoma");
        listOfUSStateAbbreviations.put("OR", "Oregon");
        listOfUSStateAbbreviations.put("PA", "Pennsylvania");
        listOfUSStateAbbreviations.put("RI", "Rhode Island");
        listOfUSStateAbbreviations.put("SC", "South Carolina");
        listOfUSStateAbbreviations.put("SD", "South Dakota");
        listOfUSStateAbbreviations.put("TN", "Tennessee");
        listOfUSStateAbbreviations.put("TX", "Texas");
        listOfUSStateAbbreviations.put("UT", "Utah");
        listOfUSStateAbbreviations.put("VT", "Vermont");
        listOfUSStateAbbreviations.put("VA", "Virginia");
        listOfUSStateAbbreviations.put("WA", "Washington");
        listOfUSStateAbbreviations.put("WV", "West Virginia");
        listOfUSStateAbbreviations.put("WI", "Wisconsin");
        listOfUSStateAbbreviations.put("WY", "Wyoming");
        listOfUSStateAbbreviations.put("AS", "American Samoa");
        listOfUSStateAbbreviations.put("GU", "Guam");
        listOfUSStateAbbreviations.put("MP", "Northern Mariana Islands");
        listOfUSStateAbbreviations.put("PR", "Puerto Rico");
        listOfUSStateAbbreviations.put("VI", "U.S. Virgin Islands");
        listOfUSStateAbbreviations.put("UM", "U.S. Minor Outlying Islands");
        listOfUSStateAbbreviations.put("FM", "Micronesia");
        listOfUSStateAbbreviations.put("MH", "Marshall Islands");
        listOfUSStateAbbreviations.put("PW", "Palau");

        String actualFullAddress = actualCity + ", " + listOfUSStateAbbreviations.get(actualState) + ", " + actualCountry;

        driver.get(baseURL + "terms");
        actualAddress = driver.findElement(By
                .cssSelector("#post-303 > div > article > div > p:nth-child(28)"))
                .getText();

        WORD = "the " + expectedCountry;

        int i = 0;
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(actualAddress);//указываем свой текст
        while (matcher.find()) {
            i++;
        }

        if (actualFullAddress.contains(expectedCity + ", " + listOfUSStateAbbreviations.get(expectedState) +
                ", " + expectedCountry) && (i == 1)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + expectedCity + ", " +
                    listOfUSStateAbbreviations.get(expectedState) + ", " + expectedCountry + "    " + WORD + " " + i + " time");
            terms.setAddress(actualFullAddress + " " + WORD);
            terms.setAddressStatus("Passed");
            terms.setAddressTimes(i);
            terms.setAddressTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + expectedCity +
                    ", " + listOfUSStateAbbreviations.get(expectedState) + ", " + expectedCountry + "    " + WORD + " " + i + " time");
            terms.setAddress(actualFullAddress + " " + WORD);
            terms.setAddressStatus("Failed");
            terms.setAddressTimes(i);
            terms.setAddressTimesStatus("Failed");
        }
        /**
         * ========TermsCompany==========
         */
        driver.get(baseURL + "terms");
        WORD = expectedCompany;
        int count = 0;
        i = 0;
        pattern = Pattern.compile(WORD.toUpperCase());
        matcher = pattern.matcher(termsText);//указываем свой текст
        while (matcher.find()) {
            i++;
        }
        count += i;
        i = 0;
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(termsText);//указываем свой текст
        while (matcher.find()) {
            i++;
        }
        count += i;
        if (count == 29) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + expectedCompany + " " + count + " times");
            terms.setCompany(expectedCompany);
            terms.setCompanyStatus("Passed");
            terms.setCompanyTimes(count);
            terms.setCompanyTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + expectedCompany + " " + count + " times");
            terms.setCompany("Wrong Company");
            terms.setCompanyStatus("Failed");
            terms.setCompanyTimes(count);
            terms.setCompanyTimesStatus("Failed");
        }
        count = 0;
        i = 0;
        /**
         * ==========TermsTFN===========
         */
        driver.get(baseURL + "terms");
        String actualTFN = driver.findElement(By.cssSelector("#post-303 > div > article > div > p:nth-child(18)")).getText();
        actualTFN = actualTFN.substring(actualTFN.length() - 18, actualTFN.length() - 1);

        WORD = "\\+" + actualTFN.substring(1, 3) + "\\(" + actualTFN.substring(4, 7) + "\\)" + actualTFN.substring(8);

        a = 0;
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(termsText);//указываем свой текст
        while (matcher.find()) {
            a++;
        }
        if (actualTFN.contentEquals(expectedTFN) && a == 2) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
            terms.setTfn(actualTFN);
            terms.setTfnStatus("Passed");
            terms.setTfnTimes(a);
            terms.setTfnTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualTFN);
            terms.setTfn(actualTFN);
            terms.setTfnStatus("Failed");
            terms.setTfnTimes(a);
            terms.setTfnTimesStatus("Failed");
        }

        terms.setDescriptor("N/A");
        terms.setDescriptorStatus("Passed");
        status += 1;

        if (status == 6) {
            terms.setStatus("Passed!");
        } else {
            terms.setStatus("Failed");
        }
        status = 0;
        getPages().add(terms);
    }
    @Test
    public void PrivacyPolicy() throws IOException, InterruptedException {

        Pages privacyPolicy = new Pages();
        int status = 0;
        System.out.println("=======PrivacyPolicy========");
        privacyPolicy.setPageName("Privacy Policy");
        privacyPolicy.setDateOfTest(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                .format(Calendar.getInstance().getTime()));
        privacyPolicy.setUrl(baseURL + "privacy-policy");
        driver.get(baseURL + "privacy-policy");
        /**
         * =======PrivacyPolicyScreenshot========
         */
        Thread.sleep(2000);

        String screenshotName = privacyPolicy.getPageName() + " " + new SimpleDateFormat("yyyy-MM-dd HH_mm_ss")
                .format(Calendar.getInstance().getTime()) + ".png";

        Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);
        ImageIO.write(fpScreenshot.getImage(), "PNG",
                new File(folder.toString() + "/" + screenshotName));
        privacyPolicy.setScreenshotName(screenshotName);
        /**
         * =======PrivacyPolicyTitle========
         */
        driver.get(baseURL + "terms");
        String text = driver.findElement(By.tagName("html")).getText();
        String siteName = text.substring(text.indexOf("(") + 2, text.indexOf(")") - 1);
        driver.get(baseURL + "privacy-policy");
        String actualPageTitle = driver.getTitle();
        String h1 = driver.findElement(By.tagName("h1")).getText();
        actualPageTitle = actualPageTitle.replace(" – ", " ");

        if ((h1 + " " + siteName).toUpperCase().trim().contentEquals(actualPageTitle.toUpperCase().trim())) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualPageTitle);
            privacyPolicy.setPageTitle(actualPageTitle);
            privacyPolicy.setPageTitleStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualPageTitle);
            privacyPolicy.setPageTitle(actualPageTitle);
            privacyPolicy.setPageTitleStatus("Failed");
        }
        /**
         * ========PrivacyPolicyEmail==========
         */
        driver.get(baseURL + "privacy-policy");
        String actualEmail = driver.findElement(By
                .cssSelector("#post-21 > div > article > div > p:nth-child(9) > a"))
                .getText();
        String termsText = driver.findElement(By
                .cssSelector("html"))
                .getText();
        String WORD = actualEmail;

        int a = 0;
        Pattern pattern = Pattern.compile(WORD);
        Matcher matcher = pattern.matcher(termsText);//указываем свой текст
        while (matcher.find()) {
            a++;
        }
        if (actualEmail.contains(TestSettings.getExpectedEmail()) && a == 5) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail + " " + a + " Times");
            privacyPolicy.setEmail(actualEmail);
            privacyPolicy.setEmailStatus("Passed");
            privacyPolicy.setEmailTimes(a);
            privacyPolicy.setEmailTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail + " " + a + " Times");
            privacyPolicy.setEmail(actualEmail);
            privacyPolicy.setEmailStatus("Failed");
            privacyPolicy.setEmailTimes(a);
            privacyPolicy.setEmailTimesStatus("Failed");
        }
        /**
         * ========PrivacyPolicyAddress==========
         */

        driver.get(baseURL + "privacy-policy");
        String actualAddress = driver.findElement(By.cssSelector("html")).getText();

        WORD = "the " + expectedCountry;

        int i = 0;
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(actualAddress);//указываем свой текст
        while (matcher.find()) {
            i++;
        }

        if (i == 6) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + WORD + " " + i + " Times");
            privacyPolicy.setAddress(WORD);
            privacyPolicy.setAddressStatus("Passed");
            privacyPolicy.setAddressTimes(i);
            privacyPolicy.setAddressTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + WORD + " " + i + " Times");
            privacyPolicy.setAddress(WORD);
            privacyPolicy.setAddressStatus("Failed");
            privacyPolicy.setAddressTimes(i);
            privacyPolicy.setAddressTimesStatus("Failed");
        }
        /**
         * ========PrivacyPolicyCompany==========
         */
        driver.get(baseURL + "privacy-policy");
        WORD = expectedCompany;
        int count = 0;
        i = 0;
        pattern = Pattern.compile(WORD.toUpperCase());
        matcher = pattern.matcher(termsText);//указываем свой текст
        while (matcher.find()) {
            i++;
        }
        count += i;
        i = 0;
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(termsText);//указываем свой текст
        while (matcher.find()) {
            i++;
        }
        count += i;
        if (count == 5) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + expectedCompany + " " + count + " times");
            privacyPolicy.setCompany(expectedCompany);
            privacyPolicy.setCompanyStatus("Passed");
            privacyPolicy.setCompanyTimes(count);
            privacyPolicy.setCompanyTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + expectedCompany + " " + count + " times");
            privacyPolicy.setCompany("Wrong Company");
            privacyPolicy.setCompanyStatus("Failed");
            privacyPolicy.setCompanyTimes(count);
            privacyPolicy.setCompanyTimesStatus("Failed");
        }
        count = 0;
        i = 0;
        /**
         * ==========PrivacyPolicyTFN===========
         */
        WORD = "\\+" + expectedTFN.substring(1, 3) + "\\(" + expectedTFN.substring(4, 7) + "\\)" + expectedTFN.substring(8);

        a = 0;
        pattern = Pattern.compile(WORD);
        matcher = pattern.matcher(termsText);//указываем свой текст
        while (matcher.find()) {
            a++;
        }
        if (a == 1) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + expectedTFN);
            privacyPolicy.setTfn(expectedTFN);
            privacyPolicy.setTfnStatus("Passed");
            privacyPolicy.setTfnTimes(a);
            privacyPolicy.setTfnTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + expectedTFN);
            privacyPolicy.setTfn(expectedTFN);
            privacyPolicy.setTfnStatus("Failed");
            privacyPolicy.setTfnTimes(a);
            privacyPolicy.setTfnTimesStatus("Failed");
        }

        privacyPolicy.setDescriptor("N/A");
        privacyPolicy.setDescriptorStatus("Passed");
        status += 1;

        if (status == 6) {
            privacyPolicy.setStatus("Passed!");
        } else {
            privacyPolicy.setStatus("Failed");
        }
        status = 0;
        getPages().add(privacyPolicy);
    }
    @Test
    public void MembersArea() throws IOException, InterruptedException {
        Pages membersArea = new Pages();
        int status = 0;

        System.out.println("=======MembersArea========");
        driver.get(baseURL + "members-login");
        WebElement login = driver.findElement(By.id("user_login"));
        login.sendKeys("demo");
        WebElement password = driver.findElement(By.id("user_pass"));
        password.sendKeys("demo");
        WebElement logButton = driver.findElement(By.id("wp-submit"));
        logButton.click();
        membersArea.setPageName("Members Area");
        membersArea.setDateOfTest(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                .format(Calendar.getInstance().getTime()));
        membersArea.setUrl(baseURL + "members-area");
        driver.get(baseURL + "members-area");
        /**
         * =======MembersAreaScreenshot========
         */
        Thread.sleep(2000);

        String screenshotName = membersArea.getPageName() + " " + new SimpleDateFormat("yyyy-MM-dd HH_mm_ss")
                .format(Calendar.getInstance().getTime()) + ".png";

        Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);
        ImageIO.write(fpScreenshot.getImage(), "PNG",
                new File(folder.toString() + "/" + screenshotName));
        membersArea.setScreenshotName(screenshotName);
        /**
         * =======MembersAreaTitle========
         */
        String actualPageTitle = driver.getTitle();
        String h1 = driver.findElement(By.tagName("h1")).getText();
        actualPageTitle = actualPageTitle.replace(" – ", " ");
        driver.get(baseURL + "terms");
        String termsText = driver.findElement(By.tagName("html")).getText();
        String siteName = termsText.substring(termsText.indexOf("(") + 2, termsText.indexOf(")") - 1);
        driver.get(baseURL + "members-area");
        if ((h1 + " " + siteName).toUpperCase().trim().contentEquals(actualPageTitle.toUpperCase().trim())) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualPageTitle);
            membersArea.setPageTitle(actualPageTitle);
            membersArea.setPageTitleStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualPageTitle);
            membersArea.setPageTitle(actualPageTitle);
            membersArea.setPageTitleStatus("Failed");
        }
        /**
         * =======MembersAreaEmail========
         */
        String actualEmail = driver.findElement(By
                .cssSelector("#post-15 > div > div:nth-child(2) > a"))
                .getText();
        String text = driver.findElement(By
                .cssSelector("html"))
                .getText();
        String WORD = actualEmail;

        int a = 0;
        Pattern pattern = Pattern.compile(WORD);
        Matcher matcher = pattern.matcher(text);//указываем свой текст
        while (matcher.find()) {
            a++;
        }
        if (actualEmail.contains(TestSettings.getExpectedEmail()) && a == 1) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail + " " + a + " Times");
            membersArea.setEmail(actualEmail);
            membersArea.setEmailStatus("Passed");
            membersArea.setEmailTimes(a);
            membersArea.setEmailTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail + " " + a + " Times");
            membersArea.setEmail(actualEmail);
            membersArea.setEmailStatus("Failed");
            membersArea.setEmailTimes(a);
            membersArea.setEmailTimesStatus("Failed");
        }
        membersArea.setCompany("N/A");
        membersArea.setCompanyStatus("Passed");
        membersArea.setCompanyTimes(0);
        membersArea.setCompanyTimesStatus("Passed");
        membersArea.setAddress("N/A");
        membersArea.setAddressStatus("Passed");
        membersArea.setAddressTimes(0);
        membersArea.setAddressTimesStatus("Passed");
        membersArea.setTfn("N/A");
        membersArea.setTfnStatus("Passed");
        membersArea.setTfnTimes(0);
        membersArea.setTfnTimesStatus("Passed");
        membersArea.setDescriptor("N/A");
        membersArea.setDescriptorStatus("Passed");
        status += 1;

        if (status == 3) {
            membersArea.setStatus("Passed!");
        } else {
            membersArea.setStatus("Failed");
        }
        status = 0;
        getPages().add(membersArea);
    }
    @Test
    public void CheckoutPages() throws IOException, InterruptedException {
        int p = 0;

        System.out.println((char) 13 + "=======CheckoutPages========");
        driver.get(baseURL + "our-products");

        tempListOfLinks = driver.findElements(By.cssSelector(".product-list"));
        for (WebElement we : tempListOfLinks) {
            String str = we.findElement(By.cssSelector("div.buynow > p:nth-child(2) > a")).getAttribute("href");
            tempListOfPurchase.add(str);
        }
        for (String str : tempListOfPurchase) {
            int status = 0;
            driver.get(str);
            String actualPageTitle = driver.getTitle();
            String h1 = driver.findElement(By.cssSelector("div.cartProductName > h4")).getText()
                    .replace("Instant Access: ","");
            //actualPageTitle = actualPageTitle.substring(actualPageTitle.indexOf(" :: ")+1);
            Pages checkoutPage = new Pages();
            checkoutPage.setPageName("Checkout Page");
            checkoutPage.setDateOfTest(new SimpleDateFormat("yyyy-MM-dd HH_mm_ss")
                    .format(Calendar.getInstance().getTime()));
            Thread.sleep(2000);
            String screenshotName = checkoutPage.getPageName() + " " + new SimpleDateFormat("yyyy-MM-dd HH_mm_ss")
                    .format(Calendar.getInstance().getTime()) + ".png";
            Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                    .takeScreenshot(driver);
            ImageIO.write(fpScreenshot.getImage(), "PNG",
                    new File(folder.toString() + "/" + screenshotName));
            checkoutPage.setScreenshotName(screenshotName);
            /**
             * =======CheckoutPagesTitle========
             */
            //driver.get(baseURL + "members-area");
            if (("Secure Checkout" + " :: " + h1).toUpperCase().trim().contentEquals(actualPageTitle.toUpperCase().trim())) {
                System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualPageTitle);
                checkoutPage.setPageTitle(actualPageTitle);
                checkoutPage.setPageTitleStatus("Passed");
                status += 1;
            } else {
                System.out.println(driver.getCurrentUrl() + " Test Failed " + actualPageTitle);
                checkoutPage.setPageTitle(actualPageTitle);
                checkoutPage.setPageTitleStatus("Failed");
            }
            checkoutPage.setUrl(driver.getCurrentUrl());

            /**
             * =======MembersAreaTitle========
             */

            driver.get(str);
            String actualDescriptor = driver.findElement(By.cssSelector("body > div.containerWrap > div > div:nth-child(5) > div > div > p:nth-child(1)")).getText();
            //System.out.println(actualDescriptor);
            String[] add = actualDescriptor.split("\n");
            actualDescriptor = add[1].substring(add[1].indexOf("\"") + 1, add[1].length() - 1);
            if (actualDescriptor.contentEquals(expectedDescriptor)) {
                System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualDescriptor);
                checkoutPage.setDescriptor(actualDescriptor);
                checkoutPage.setDescriptorStatus("Passed");
                status += 1;
            } else {
                System.out.println(driver.getCurrentUrl() + " Test Failed " + actualDescriptor);
                checkoutPage.setDescriptor(actualDescriptor);
                checkoutPage.setDescriptorStatus("Failed");
            }
            String actualAddress = driver.findElement(By.cssSelector("body > div.containerWrap > div > div:nth-child(5) > div > div > p:nth-child(5)")).getText();
            add = actualAddress.split("\n");
            String actualFullAddress = add[2].substring(add[2].indexOf("|") + 2);
            if (actualFullAddress.contentEquals(expectedFullAddress)) {
                System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualFullAddress);
                checkoutPage.setAddress(actualFullAddress);
                checkoutPage.setAddressStatus("Passed");
                checkoutPage.setAddressTimes(1);
                checkoutPage.setAddressTimesStatus("Passed");
                status += 1;
            } else {
                System.out.println(driver.getCurrentUrl() + " Test Failed " + actualFullAddress);
                checkoutPage.setAddress(actualFullAddress);
                checkoutPage.setAddressStatus("Failed");
                checkoutPage.setAddressTimes(1);
                checkoutPage.setAddressTimesStatus("Failed");
            }
            String actualCompany = driver.findElement(By.cssSelector("body > div.containerWrap > div > " +
                    "div:nth-child(5) > div > div > p:nth-child(5)")).getText();
            add = actualCompany.split("\n");
            actualCompany = add[2].substring(0, add[2].indexOf("|") - 1);
            int i = 0;
//            actualCompany = driver.findElement(By
//                    .cssSelector("html"))
//                    .getText();
            String text = driver.findElement(By.cssSelector("html")).getText();
            final String WORD = expectedCompany;
            Pattern pattern = Pattern.compile(WORD);
            Matcher matcher = pattern.matcher(text);//указываем свой текст
            while (matcher.find()) {
                i++;
            }
            if (actualCompany.contentEquals(expectedCompany) && i == 2) {
                System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualCompany);
                checkoutPage.setCompany(actualCompany);
                checkoutPage.setCompanyStatus("Passed");
                checkoutPage.setCompanyTimes(i);
                checkoutPage.setCompanyTimesStatus("Passed");
                status += 1;
            } else {
                System.out.println(driver.getCurrentUrl() + " Test Failed " + actualCompany);
                checkoutPage.setCompany(actualCompany);
                checkoutPage.setCompanyStatus("Failed");
                checkoutPage.setCompanyTimes(i);
                checkoutPage.setCompanyTimesStatus("Failed");
            }
            String actualTFN = driver.findElement(By.cssSelector(" body > div.containerWrap > div > div:nth-child(5) > div > div > p:nth-child(5)")).getText();
            actualTFN = actualTFN.substring(actualTFN.length() - 37, actualTFN.length() - 20);
            if (actualTFN.contentEquals(expectedTFN)) {
                System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
                checkoutPage.setTfn(actualCompany);
                checkoutPage.setTfnStatus("Passed");
                checkoutPage.setTfnTimes(1);
                checkoutPage.setTfnTimesStatus("Passed");
                status += 1;
            } else {
                System.out.println(driver.getCurrentUrl() + " Test Failed " + actualTFN);
                checkoutPage.setTfn(actualCompany);
                checkoutPage.setTfnStatus("Failed");
                checkoutPage.setTfnTimes(1);
                checkoutPage.setTfnTimesStatus("Failed");
            }
            checkoutPage.setEmail("N/A");
            checkoutPage.setEmailStatus("Passed");
            checkoutPage.setEmailTimes(0);
            checkoutPage.setEmailTimesStatus("Passed");
            if (status == 5) {
                checkoutPage.setStatus("Passed!");
            } else {
                checkoutPage.setStatus("Failed");
            }
            status = 0;
            getPages().add(checkoutPage);
        }
        int c = 0;
    }
    @Test
    public void FacebookPage() throws IOException, InterruptedException {
        Pages facebookPage = new Pages();
        int status = 0;
        System.out.println("=======FacebookPage========");
        facebookPage.setPageName("Facebook Page");
        facebookPage.setDateOfTest(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                .format(Calendar.getInstance().getTime()));
        /**
         * ========FacebookPageEmail==========
         */
        driver.get(baseURL);
        driver.get(driver.findElement(By.cssSelector("p.social > a")).getAttribute("href"));

        List<WebElement> emaillinks = driver.findElements(By.cssSelector("._2yau"));
        driver.get(emaillinks.get(4).getAttribute("href"));
        /**
         * =======FacebookPageScreenshot========
         */
        Thread.sleep(2000);

        String screenshotName = facebookPage.getPageName() + " " + new SimpleDateFormat("yyyy-MM-dd HH_mm_ss")
                .format(Calendar.getInstance().getTime()) + ".png";

        Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);
        ImageIO.write(fpScreenshot.getImage(), "PNG",
                new File(folder.toString() + "/" + screenshotName));
        facebookPage.setScreenshotName(screenshotName);
        facebookPage.setUrl(driver.getCurrentUrl());

        List<WebElement> emaillinks2 = driver.findElements(By.cssSelector("._50f4"));
        String actualEmail = emaillinks2.get(1).getText();
        if (actualEmail.contentEquals(getExpectedEmail())) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
            facebookPage.setEmail(actualEmail);
            facebookPage.setEmailStatus("Passed");
            facebookPage.setEmailTimes(1);
            facebookPage.setEmailTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualEmail);
            facebookPage.setEmail(actualEmail);
            facebookPage.setEmailStatus("Failed");
            facebookPage.setEmailTimes(1);
            facebookPage.setEmailTimesStatus("Failed");
        }
        /**
         * ========FacebookPageCompany==========
         */
        driver.get(baseURL);
        driver.get(driver.findElement(By.cssSelector("p.social > a")).getAttribute("href"));
        List<WebElement> links = driver.findElements(By.cssSelector("._2yau"));
        driver.get(links.get(4).getAttribute("href"));
        WebElement links2 = driver.findElement(By.cssSelector("._3-8w"));
        String actualCompany = links2.getText();
        String[] add = actualCompany.split("\n");
        actualCompany = add[0];
        if (actualCompany.contentEquals(expectedCompany)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualCompany);
            facebookPage.setCompany(actualCompany);
            facebookPage.setCompanyStatus("Passed");
            facebookPage.setCompanyTimes(1);
            facebookPage.setCompanyTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualCompany);
            facebookPage.setCompany(actualCompany);
            facebookPage.setCompanyStatus("Failed");
            facebookPage.setCompanyTimes(1);
            facebookPage.setCompanyTimesStatus("Failed");
        }
        /**
         * ========FacebookPageAddress==========
         */
        String actualFullAddress = add[1] + " " + add[2] + " " + add[3];
        if (actualFullAddress.contentEquals(expectedFullAddress)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualFullAddress);
            facebookPage.setAddress(actualCompany);
            facebookPage.setAddressStatus("Passed");
            facebookPage.setAddressTimes(1);
            facebookPage.setAddressTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualFullAddress);
            facebookPage.setAddress(actualCompany);
            facebookPage.setAddressStatus("Failed");
            facebookPage.setAddressTimes(1);
            facebookPage.setAddressTimesStatus("Failed");
        }
        /**
         * ========FacebookPageTFN==========
         */
        driver.get(baseURL);
        driver.get(driver.findElement(By.cssSelector("p.social > a")).getAttribute("href"));
        String actualTFN = driver.findElement(By.cssSelector("._1xnd > div:nth-child(1)")).getText();
        actualTFN = actualTFN.substring(actualTFN.indexOf("+"), actualTFN.indexOf("+") + 15);
        actualTFN = actualTFN.substring(0, 3).concat("(").concat(actualTFN.substring(3, 6).concat(") ").concat(actualTFN.substring(7)));
        if (actualTFN.contentEquals(expectedTFN)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
            facebookPage.setTfn(actualCompany);
            facebookPage.setTfnStatus("Passed");
            facebookPage.setTfnTimes(1);
            facebookPage.setTfnTimesStatus("Passed");
            status += 1;
        } else {
            System.out.println(driver.getCurrentUrl() + " Test Failed " + actualTFN);
            facebookPage.setTfn(actualCompany);
            facebookPage.setTfnStatus("Failed");
            facebookPage.setTfnTimes(1);
            facebookPage.setTfnTimesStatus("Failed");
        }
        facebookPage.setPageTitle("N/A");
        facebookPage.setPageTitleStatus("Passed");
        facebookPage.setDescriptor("N/A");
        facebookPage.setDescriptorStatus("Passed");
        status += 1;

        if (status == 5) {
            facebookPage.setStatus("Passed!");
        } else {
            facebookPage.setStatus("Failed");
        }
        status = 0;
        getPages().add(facebookPage);
    }
    @Test
    public void TestOrders() throws IOException, InterruptedException{
        tempListOfLinks = driver.findElements(By.cssSelector(".product-list"));
        for (WebElement we : tempListOfLinks) {
            String str = we.findElement(By.cssSelector("div.buynow > p:nth-child(2) > a")).getAttribute("href");
            tempListOfPurchase.add(str);
        }
        for (String str : tempListOfPurchase) {
            int status = 0;
            driver.get(str);
            String actualPageTitle = driver.getTitle();
            String h1 = driver.findElement(By.cssSelector("div.cartProductName > h4")).getText()
                    .replace("Instant Access: ", "");
            //actualPageTitle = actualPageTitle.substring(actualPageTitle.indexOf(" :: ")+1);
            Pages checkoutPage = new Pages();
            checkoutPage.setPageName("Checkout Page");
            checkoutPage.setDateOfTest(new SimpleDateFormat("yyyy-MM-dd HH_mm_ss")
                    .format(Calendar.getInstance().getTime()));
            //Thread.sleep(2000);
            WebElement zip = driver.findElement(By.cssSelector("div.form-group:nth-child(4) > div:nth-child(2) >" +
                    " input:nth-child(1)"));
            zip.sendKeys("111");
            WebElement cardHolder = driver.findElement(By.cssSelector("div.form-group:nth-child(5) > " +
                    "div:nth-child(2) > input:nth-child(1)"));
            cardHolder.sendKeys("MIGUEL PIDARAS SMITH");
            WebElement phone = driver.findElement(By.cssSelector("div.col-md-5:nth-child(2) > input:nth-child(1)"));
            phone.sendKeys("1234567890");
            WebElement email = driver.findElement(By.cssSelector("#email"));
            phone.sendKeys("QAS-Denys@protonmail.com");
            WebElement card = driver.findElement(By.cssSelector("div.form-group:nth-child(9) > div:nth-child(2) >" +
                    " input:nth-child(1)"));
            card.sendKeys("4111111111111111");
            WebElement csv = driver.findElement(By.cssSelector("div.col-md-3:nth-child(2) > input:nth-child(1)"));
            csv.sendKeys("123");
            Select month = new Select(driver.findElement(By.cssSelector("#creditcards_month")));
            month.selectByIndex(10);
            WebElement submitButton = driver.findElement(By.cssSelector("#submitButton"));
            submitButton.click();
        }
    }
}

