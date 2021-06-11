package com.customertimes.test.registration;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.framework.pages.LoginPage;
import com.customertimes.framework.pages.RegistrationPage;
import com.customertimes.model.Customer;
import com.customertimes.test.BaseTest;
import com.customertimes.testData.TestData;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class JuiceShopSignUpTest extends BaseTest {

    LoginPage loginPage;
    WebDriverWait wait;
    TestData testData;
    RegistrationPage registrationPage;
    Customer customer;
    SoftAssert softAssert;


    @BeforeClass
    public void setupDataToJuiceShop() {
        driver.get("http://beeb0b73705f.sn.mynetname.net:3000/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        softAssert = new SoftAssert();
        wait = new WebDriverWait(driver, 5);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        testData = new TestData();
        customer = Customer.newBuilder().withName(testData.getIncorrectUserEmail()).withPassword(testData.getIncorrectUserPassword()).build();
    }

    @Test
    public void userCanSignUpToJuiceShop() {

        loginPage.navigateToLoginPage();

        loginPage.clickNotYetACustomerButton();

        registrationPage.enterEmail(customer.getEmail());

        registrationPage.enterPassword(customer.getPassword());

        registrationPage.enterRepeatPassword(customer.getPassword());

        registrationPage.chooseSecurityQuestion();

        registrationPage.enterAnswerSecurityQuestion(testData.getAnswerSecurityQuestion());

        registrationPage.waitToLanguagePopUpDisappear();

        registrationPage.clickRegisterButton();

        registrationPage.waitUntilRegistrationSuccessPopUpAppear();

        String registrationSuccessActualText = registrationPage.getRegistrationSuccessActualText();

        softAssert.assertEquals(registrationSuccessActualText, testData.getRegistrationSuccessText(), "Registration wasn't successful");

        loginPage.navigateToLoginPage();

        loginPage.enterEmail(customer.getEmail());

        loginPage.enterPassword(customer.getPassword());

        loginPage.clickOnLoginButton();

        loginPage.clickOnAccountButton();

        String actualNameText = loginPage.getActualNameText(customer.getEmail());

        softAssert.assertEquals(actualNameText, customer.getEmail(), "User name does not match");

        softAssert.assertAll();

    }




}
