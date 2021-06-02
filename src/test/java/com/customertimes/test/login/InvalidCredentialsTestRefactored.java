package com.customertimes.test.login;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.framework.pages.LoginPage;
import com.customertimes.model.Customer;
import com.customertimes.test.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class InvalidCredentialsTestRefactored extends BaseTest{

    Customer customer;
    LoginPage loginPage;
    WebDriverWait wait;
    WebDriver driver = WebdriverRunner.getWebDriver(); //я так и не понял почему,
    // но пока не обьявил и не ициализировал переменную у меня ничего не заработало((
    // 2 часа ушло пока догнал
    // Уточнить каким образом это нужно правильно сделать и исправить тест,
    // кажется в Раннер нужно исправить, не пойму как, что-то из Thread убрать


    @BeforeClass
    public void setup() {
        driver.get("http://beeb0b73705f.sn.mynetname.net:3000/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        wait = new WebDriverWait(driver, 5);
        customer = Customer.newBuilder().withName("andrii@gmail.com").withPassword("123456789").build();
        loginPage = new LoginPage(driver);

    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebdriver();
    }

    @Test(dataProvider = "credentials")
    public void userFillIncorrectCredentialsToLoginForm(String email, String password) {

        loginPage.navigateToLoginPage();

        loginPage.enterEmail(email);

        loginPage.enterPassword(password);

        loginPage.clickOnLoginButton();

        String invalidCredentialsErrorActualText = loginPage.getInvalidCredentialsErrorActualText(customer.getInvalidCredentialsErrorText());

        Assert.assertEquals(invalidCredentialsErrorActualText, customer.getInvalidCredentialsErrorText(), "Invalid email or password error message doesn't match");
        System.out.println(email + "," + password); //для теста значений
    }
    @DataProvider
    public Object[][] credentials() {
        return new Object[][]{
                {
                        customer.getEmail(),
                        customer.getIncorrectUserPassword()
                },
                {
                        customer.getIncorrectUserEmail(),
                        customer.getPassword()
                },
                {
                        customer.getIncorrectUserEmail(),
                        customer.getIncorrectUserPassword()
                }
        };
    }

}
