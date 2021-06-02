package com.customertimes.test.login;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.framework.pages.LoginPage;
import com.customertimes.model.Customer;
import com.customertimes.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
//    WebDriver driver = WebdriverRunner.getWebDriver(); //я так и не понял почему,
    // но пока не обьявил и не ициализировал переменную у меня ничего не заработало((
    // 2 часа ушло пока догнал
    // Уточнить каким образом это нужно правильно сделать и исправить тест


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
    public void userCanLoginToJuiceShop() {

        loginPage.navigateToLoginPage();

        loginPage.enterEmail(customer.getEmail());

        loginPage.enterPassword(customer.getPassword());

        loginPage.clickOnLoginButton();

        loginPage.clickOnAccountButton();

        String actualNameText = loginPage.getActualNameText(customer.getEmail());

        Assert.assertEquals(actualNameText, customer.getEmail(), "User name does not match");

    }


}
