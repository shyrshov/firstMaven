package com.customertimes.test;

import com.customertimes.framework.driver.WebdriverRunner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void setupBaseTest() {
        System.out.println("This is before suite in BaseTest");
    }

    @BeforeClass
    public void beforeClassBaseTest() {
        driver = WebdriverRunner.getWebDriver();
        System.out.println("This is before class in the BaseTest");
    }

    @AfterClass
    public void afterClassBaseTest() {
        WebdriverRunner.closeWebDriver();
        System.out.println("This is after class in the BaseTest");
    }

    @AfterSuite
    public void tearDownBaseTest() {
        WebdriverRunner.closeWebDriver();
        System.out.println("This is after suite");
    }
}
