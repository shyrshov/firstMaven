package com.customertimes.test;

import com.customertimes.framework.driver.WebdriverRunner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected WebDriver driver;
    protected final static String BASE_URL = "http://beeb0b73705f.sn.mynetname.net:3000/";

    @BeforeSuite
    public void setup() throws InterruptedException {
        driver = WebdriverRunner.getWebDriver();
        System.out.println("This is before suite");
    }

    @AfterSuite
    public void tearDown() {
        WebdriverRunner.closeWebdriver();
        System.out.println("This is after suite");
    }

    @BeforeClass
    public void beforeClassBaseTest() {
        System.out.println("This is before class in the BaseTest");
    }
}
