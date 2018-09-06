package com.gmail.freexchange;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebsiteTests extends TestSettings {

    //List<WebElement> tmpListOfLinks = new ArrayList<WebElement>();
    //List<String> listOfLinks = new ArrayList<String>();
    @Test
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
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
            System.out.println(actualTitle);
            System.out.println(expectedTitle);
        }
        driver.findElement(By.linkText("LOGOUT")).click();
    }
    @Test
    public void siteTitles() {
        System.out.println("=======siteTitles========");
        listOfLinks.add(baseURL);
        listOfLinks.add(baseURL+"terms");
        listOfLinks.add(baseURL+"refund-policy");
        listOfLinks.add(baseURL+"privacy-policy");
        listOfLinks.add(baseURL+"our-products");
        listOfLinks.add(baseURL+"contact-us");
        listOfLinks.add(baseURL+"members-login");
        listOfLinks.add(baseURL+"members-area");
        driver.get(baseURL);
        listOfLinks.add(driver.findElement(By.cssSelector(".social>a")).getAttribute("href"));

        for (String b: listOfLinks) {
            System.out.print(b + " ");
            if (!b.contains("facebook")) {
            driver.get(b);
            String pageTitle = driver.getTitle();
            String h1 = driver.findElement(By.tagName("h1")).getText();
                pageTitle = pageTitle.replace(" – ", " ");
                pageTitle = pageTitle.replace(" - ", " ");
                Assert.assertEquals((h1 + " Account Deduction").toUpperCase().trim(), pageTitle.toUpperCase().trim());
                System.out.println("Passed! "+ pageTitle + " - " + (h1 + " Account Deduction"));
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
                System.out.println("Passed! "+ pageTitle + " - " + (h1 + " Account Deduction"));
                }
            }
        }
    }
    @Test
    public void siteTFN() {
        System.out.println((char)13 + "=======siteTFN========");
        driver.get(baseURL);
        String actualTFN = driver.findElement(By
                .cssSelector("#colophon > div > div > div.footer-col-left > div:nth-child(4)"))
                .getText()
                .substring(9, 26);
        if (actualTFN.contentEquals(expectedTFN)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualTFN);
        }
        driver.get(baseURL+"terms");
        actualTFN = driver.findElement(By.cssSelector("#post-303 > div > article > div > p:nth-child(18)")).getText();
        actualTFN = actualTFN.substring(actualTFN.length()-18,actualTFN.length()-1);
        if (actualTFN.contentEquals(expectedTFN)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualTFN);
        }
        driver.get(baseURL+"refund-policy");
        actualTFN = driver.findElement(By.cssSelector("#post-19 > div > article > div > p:nth-child(2)")).getText();
        actualTFN = actualTFN.substring(actualTFN.length()-18,actualTFN.length()-1);
        if (actualTFN.contentEquals(expectedTFN)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualTFN);
        }
        driver.get(baseURL+"contact-us");
        actualTFN = driver.findElement(By.cssSelector("#post-11 > div > div > p:nth-child(2)")).getText();
        actualTFN = actualTFN.substring(actualTFN.length()-17,actualTFN.length());
        if (actualTFN.contentEquals(expectedTFN)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualTFN);
        }
        driver.get(baseURL+"our-products");
        tempListOfLinks = driver.findElements(By.cssSelector(".product-list"));
        for (WebElement we: tempListOfLinks) {
            String str = we.findElement(By.cssSelector("div.buynow > p:nth-child(2) > a")).getAttribute("href");
            tempListOfPurchase.add(str);
        }
        for (String str: tempListOfPurchase) {
           driver.get(str);
            actualTFN = driver.findElement(By.cssSelector(" body > div.containerWrap > div > div:nth-child(5) > div > div > p:nth-child(5)")).getText();
            actualTFN = actualTFN.substring(actualTFN.length()-37,actualTFN.length()-20);
            if (actualTFN.contentEquals(expectedTFN)) {
                System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
            } else {
                System.out.println( driver.getCurrentUrl() + " Test Failed " + actualTFN);
            }
        }
        driver.get(baseURL);
        driver.get(driver.findElement(By.cssSelector("p.social > a")).getAttribute("href"));
        actualTFN = driver.findElement(By.cssSelector("div._4bl9 > div")).getText();
        System.out.println(actualTFN);
        driver.get(baseURL);
        driver.get(driver.findElement(By.cssSelector("p.social > a")).getAttribute("href"));
        actualTFN = driver.findElement(By.cssSelector("._1xnd > div:nth-child(1)")).getText();
        actualTFN = actualTFN.substring(actualTFN.indexOf("+"),actualTFN.indexOf("+")+15);
        actualTFN = actualTFN.substring(0, 3).concat("(").concat(actualTFN.substring(3,6).concat(") ").concat(actualTFN.substring(7)));
        if (actualTFN.contentEquals(expectedTFN)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualTFN);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualTFN);
        }
        driver.get(driver.findElement(By.cssSelector("._2yau")).getAttribute("href"));
    }
    @Test
    public void siteEmail() {
        System.out.println((char)13 + "=======siteEmail========");
        driver.get(baseURL+"terms");
        String actualEmail = driver.findElement(By
                .cssSelector("#post-303 > div > article > div > p:nth-child(18) > a"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        driver.get(baseURL+"refund-policy");
        actualEmail = driver.findElement(By
                .cssSelector("#post-19 > div > article > div > p:nth-child(2) > a"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        driver.get(baseURL+"privacy-policy");
        actualEmail = driver.findElement(By
                .cssSelector("#post-21 > div > article > div > p:nth-child(9) > a"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        actualEmail = driver.findElement(By
                .cssSelector("#post-21 > div > article > div > p:nth-child(46) > a:nth-child(3)"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        actualEmail = driver.findElement(By
                .cssSelector("#post-21 > div > article > div > p:nth-child(48) > a:nth-child(3)"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        actualEmail = driver.findElement(By
                .cssSelector("#post-21 > div > article > div > p:nth-child(53) > a"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        actualEmail = driver.findElement(By
                .cssSelector("#post-21 > div > article > div > p:nth-child(57) > a"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        driver.get(baseURL+"contact-us");
        actualEmail = driver.findElement(By
                .cssSelector("#post-11 > div > div > p:nth-child(2) > a"))
                .getText();
        if (actualEmail.contains(expectedEmail)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualEmail);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        driver.get(baseURL+"members-login");
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
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualEmail);
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
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualEmail);
        }
        driver.get(driver.findElement(By.cssSelector("._2yau")).getAttribute("href"));
    }
}
