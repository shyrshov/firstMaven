package com.customertimes.test.login;

import com.customertimes.framework.config.TestConfig;
import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.framework.pages.LoginPage;
import com.customertimes.model.Customer;
import com.customertimes.test.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

@Epic("Sign in/Sign up")
@Story("Login")
public class JuiceShopLoginTest extends BaseTest {

    WebDriverWait wait;
    Customer customer;
    LoginPage loginPage;


    @BeforeClass
    public void setupDataToJuiceShop() {
        loginPage = new LoginPage(driver);
        loginPage.openPage();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        wait = new WebDriverWait(driver, 5);
        customer = Customer.newBuilder().withName("andrii@gmail.com").withPassword("123456789").build();


    }

    @Test
    @Description("Login with valid credentials")
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
