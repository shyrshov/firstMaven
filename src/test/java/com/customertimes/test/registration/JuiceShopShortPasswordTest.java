package com.customertimes.test.registration;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.framework.pages.LoginPage;
import com.customertimes.framework.pages.RegistrationPage;
import com.customertimes.model.Customer;
import com.customertimes.test.BaseTest;
import com.customertimes.testData.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

@Epic("Sign in/Sign up")
@Story("Registration")
public class JuiceShopShortPasswordTest extends BaseTest{

    LoginPage loginPage;
    WebDriverWait wait;
    TestData testData;
    RegistrationPage registrationPage;


    @BeforeClass
    public void setupDataToJuiceShop() {
        wait = new WebDriverWait(driver, 5);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        testData = new TestData();
        registrationPage.openPage();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
    }

    @Test
    @Description("Registration with invalid short password")
    public void userFillShortPasswordToSignUpForm() {

        loginPage.navigateToLoginPage();

        loginPage.clickNotYetACustomerButton();

        registrationPage.enterPassword(testData.getInvalidUserPassword());

        registrationPage.clickUserRegistrationTitle();

        String passwordLengthErrorActualText = registrationPage.getPasswordLengthErrorActualText();

        Assert.assertEquals(passwordLengthErrorActualText, testData.getPasswordLengthErrorText(), "Password length validation error text doesn't match");
    }




}
