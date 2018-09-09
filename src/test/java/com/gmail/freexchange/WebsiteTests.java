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
    @Test
    public void siteAddress() {
        System.out.println((char)13 + "=======siteAddress========");
        driver.get(baseURL);
        String actualAddress = driver.findElement(By
                .cssSelector("#colophon > div > div > div.footer-col-right > div"))
                .getText();
        String[] CitySateCountry = actualAddress.substring(actualAddress.lastIndexOf(10)+1)
                .replaceAll("[0-9]","").replaceAll("  ", ", ").split(", ");
        String actualCity = CitySateCountry[0];
        String actualState = CitySateCountry[1];
        String actualCountry = CitySateCountry[2];
        String actualZip = actualAddress.substring(actualAddress.lastIndexOf(10))
                .replaceAll("[^0-9]","");
        actualAddress = actualAddress.substring(actualAddress.indexOf("| ")+2, actualAddress.length());
        String Address = actualAddress.substring(0, actualAddress.lastIndexOf(10));
        String actualFullAddress = Address+" "+actualCity+", "+actualState+" "+actualZip+" "+actualCountry;
        System.out.println(actualFullAddress);
        System.out.println(expectedFullAddress);
        if (actualFullAddress.contains(expectedFullAddress)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + actualFullAddress);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + actualFullAddress);
        }

        driver.get(baseURL+"terms");
        actualAddress = driver.findElement(By
                .cssSelector("#post-303 > div > article > div > p:nth-child(35)"))
                .getText();
        listOfUSStateAbbreviations.put("AL","Alabama");
        listOfUSStateAbbreviations.put("AK","Alaska");
        listOfUSStateAbbreviations.put("AZ","Arizona");
        listOfUSStateAbbreviations.put("AR","Arkansas");
        listOfUSStateAbbreviations.put("CA","California");
        listOfUSStateAbbreviations.put("CO","Colorado");
        listOfUSStateAbbreviations.put("CT","Connecticut");
        listOfUSStateAbbreviations.put("DE","Delaware");
        listOfUSStateAbbreviations.put("DC","District of Columbia");
        listOfUSStateAbbreviations.put("FL","Florida");
        listOfUSStateAbbreviations.put("GA","Georgia");
        listOfUSStateAbbreviations.put("HI","Hawaii");
        listOfUSStateAbbreviations.put("ID","Idaho");
        listOfUSStateAbbreviations.put("IL","Illinois");
        listOfUSStateAbbreviations.put("IN","Indiana");
        listOfUSStateAbbreviations.put("IA","Iowa");
        listOfUSStateAbbreviations.put("KS","Kansas");
        listOfUSStateAbbreviations.put("KY","Kentucky");
        listOfUSStateAbbreviations.put("LA","Louisiana");
        listOfUSStateAbbreviations.put("ME","Maine");
        listOfUSStateAbbreviations.put("MD","Maryland");
        listOfUSStateAbbreviations.put("MA","Massachusetts");
        listOfUSStateAbbreviations.put("MI","Michigan");
        listOfUSStateAbbreviations.put("MN","Minnesota");
        listOfUSStateAbbreviations.put("MS","Mississippi");
        listOfUSStateAbbreviations.put("MO","Missouri");
        listOfUSStateAbbreviations.put("MT","Montana");
        listOfUSStateAbbreviations.put("NE","Nebraska");
        listOfUSStateAbbreviations.put("NV","Nevada");
        listOfUSStateAbbreviations.put("NH","New Hampshire");
        listOfUSStateAbbreviations.put("NJ","New Jersey");
        listOfUSStateAbbreviations.put("NM","New Mexico");
        listOfUSStateAbbreviations.put("NY","New York");
        listOfUSStateAbbreviations.put("NC","North Carolina");
        listOfUSStateAbbreviations.put("ND","North Dakota");
        listOfUSStateAbbreviations.put("OH","Ohio");
        listOfUSStateAbbreviations.put("OK","Oklahoma");
        listOfUSStateAbbreviations.put("OR","Oregon");
        listOfUSStateAbbreviations.put("PA","Pennsylvania");
        listOfUSStateAbbreviations.put("RI","Rhode Island");
        listOfUSStateAbbreviations.put("SC","South Carolina");
        listOfUSStateAbbreviations.put("SD","South Dakota");
        listOfUSStateAbbreviations.put("TN","Tennessee");
        listOfUSStateAbbreviations.put("TX","Texas");
        listOfUSStateAbbreviations.put("UT","Utah");
        listOfUSStateAbbreviations.put("VT","Vermont");
        listOfUSStateAbbreviations.put("VA","Virginia");
        listOfUSStateAbbreviations.put("WA","Washington");
        listOfUSStateAbbreviations.put("WV","West Virginia");
        listOfUSStateAbbreviations.put("WI","Wisconsin");
        listOfUSStateAbbreviations.put("WY","Wyoming");
        listOfUSStateAbbreviations.put("AS","American Samoa");
        listOfUSStateAbbreviations.put("GU","Guam");
        listOfUSStateAbbreviations.put("MP","Northern Mariana Islands");
        listOfUSStateAbbreviations.put("PR","Puerto Rico");
        listOfUSStateAbbreviations.put("VI","U.S. Virgin Islands");
        listOfUSStateAbbreviations.put("UM","U.S. Minor Outlying Islands");
        listOfUSStateAbbreviations.put("FM","Micronesia");
        listOfUSStateAbbreviations.put("MH","Marshall Islands");
        listOfUSStateAbbreviations.put("PW","Palau");
        actualFullAddress = actualCity+", "+listOfUSStateAbbreviations.get(actualState)+", "+actualCountry;
        if (actualFullAddress.contains(expectedCity+", "+listOfUSStateAbbreviations.get(expectedState)+
                ", "+expectedCountry)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + expectedCity+", "+
                    listOfUSStateAbbreviations.get(expectedState)+", "+expectedCountry);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + expectedCity+
                    ", "+listOfUSStateAbbreviations.get(expectedState)+", "+expectedCountry);
        }

        driver.get(baseURL+"terms");
        actualAddress = driver.findElement(By
                .cssSelector("#post-303 > div > article > div > p:nth-child(28)"))
                .getText();
        if (("the"+actualAddress).contains(" the "+expectedCountry)) {
            System.out.println(driver.getCurrentUrl() + " Test Passed! " + " the " + expectedCountry);
        } else {
            System.out.println( driver.getCurrentUrl() + " Test Failed " + " the " + expectedCountry);
        }
    }
}
