package com.customertimes.test.login;

import com.customertimes.test.BaseTest;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;

import java.util.Random;


public class MyFirstTest extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        System.out.println("This is before class");
    }

    @AfterClass
    public void afterClass () {
        System.out.println("This is after class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("This is before method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("This is after method");
    }

    @Test
    public void checkSiteTitle()
    {

        driver.get("https://github.com");
//        try {
//            Thread.sleep(1_000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        String expectedTitle = "GitHub: Where the world builds software · GitHub";
        String actualTitle = driver.getTitle();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle, expectedTitle, "title is not expected");

        Random rand = new Random();
        int random = rand.nextInt(10);
//        to check generated value
//        System.out.println("Generated integer = " + random);
        softAssert.assertTrue(0 <= random && random <= 10, "generated value is not in correct range");

        softAssert.assertAll();
    }
}
