package com.customertimes.test.login;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.framework.pages.LoginPage;
import com.customertimes.model.Customer;
import com.customertimes.test.BaseTest;
import com.customertimes.testData.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class InvalidCredentialsTestRefactored extends BaseTest{

    Customer customer;
    LoginPage loginPage;
    WebDriverWait wait;
    TestData testData;


    @BeforeClass
    public void setupDataToJuiceShop() {
        loginPage = new LoginPage(driver);
        testData = new TestData();
        loginPage.openPage();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        wait = new WebDriverWait(driver, 5);
        customer = Customer.newBuilder().withName("andrii@gmail.com").withPassword("123456789").build();

    }


    @Test(dataProvider = "credentials")
    public void userFillIncorrectCredentialsToLoginForm(String email, String password) {

        loginPage.navigateToLoginPage();

        loginPage.enterEmail(email);

        loginPage.enterPassword(password);

        loginPage.clickOnLoginButton();

        String invalidCredentialsErrorActualText = loginPage.getInvalidCredentialsErrorActualText(testData.getInvalidCredentialsErrorText());

        Assert.assertEquals(invalidCredentialsErrorActualText, testData.getInvalidCredentialsErrorText(), "Invalid email or password error message doesn't match");
        System.out.println(email + "," + password); //для теста значений
    }
    @DataProvider
    public Object[][] credentials() {
        return new Object[][]{
                {
                        customer.getEmail(),
                        testData.getIncorrectUserPassword()
                },
                {
                        testData.getIncorrectUserEmail(),
                        customer.getPassword()
                },
                {
                        testData.getIncorrectUserEmail(),
                        testData.getIncorrectUserPassword()
                }
        };
    }

}
