package com.customertimes.test.login;

import com.customertimes.framework.config.TestConfig;
import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.framework.pages.LoginPage;
import com.customertimes.model.Customer;
import com.customertimes.test.BaseTest;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

public class JuiceShopLoginTest extends BaseTest {

    WebDriverWait wait;
    Customer customer;
    LoginPage loginPage;


    @BeforeClass
    public void setupDataToJuiceShop() {
        driver.get("http://beeb0b73705f.sn.mynetname.net:3000/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        wait = new WebDriverWait(driver, 5);
        customer = Customer.newBuilder().withName("andrii@gmail.com").withPassword("123456789").build();
        loginPage = new LoginPage(driver);

    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void userCanLoginToJuiceShop() throws InterruptedException {

        loginPage.navigateToLoginPage();

        loginPage.enterEmail(customer.getEmail());

        loginPage.enterPassword(customer.getPassword());

        Thread.sleep(5000);
        loginPage.clickOnLoginButton();

        loginPage.clickOnAccountButton();

        String actualNameText = loginPage.getActualNameText(customer.getEmail());

        Assert.assertEquals(actualNameText, customer.getEmail(), "User name does not match");

    }


}
