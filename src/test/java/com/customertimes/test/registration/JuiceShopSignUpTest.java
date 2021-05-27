package com.customertimes.test.registration;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class JuiceShopSignUpTest extends BaseTest {

    String userEmail;
    String userPassword;
    String answerSecurityQuestion;
    String registrationSuccessText;

    @BeforeClass
    public void setup() {
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        userEmail = RandomStringUtils.random(10, true, false) + "@gmail.com";
        userPassword = RandomStringUtils.random(10, true, true);
        answerSecurityQuestion = RandomStringUtils.random(10, true, false);
        registrationSuccessText = "Registration completed successfully. You can now log in.";
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebdriver();
    }

    @Test
    public void userCanSignUpToJuiceShop() throws InterruptedException {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        getWebDriver().findElement(By.cssSelector("[href='#/register']")).click();

        getWebDriver().findElement(By.id("emailControl")).clear();
        getWebDriver().findElement(By.id("emailControl")).sendKeys(userEmail);

        getWebDriver().findElement(By.id("passwordControl")).clear();
        getWebDriver().findElement(By.id("passwordControl")).sendKeys(userPassword);

        getWebDriver().findElement(By.id("repeatPasswordControl")).clear();
        getWebDriver().findElement(By.id("repeatPasswordControl")).sendKeys(userPassword);

        getWebDriver().findElement(By.cssSelector("[name='securityQuestion']")).click();
        getWebDriver().findElement(By.cssSelector("div [role=listbox] mat-option")).click();

        getWebDriver().findElement(By.id("securityAnswerControl")).clear();
        getWebDriver().findElement(By.id("securityAnswerControl")).sendKeys(answerSecurityQuestion);

        Thread.sleep(3000);

        getWebDriver().findElement(By.id("registerButton")).click();

        Thread.sleep(2000);

//      Way #1

        String registrationActualText = getWebDriver().findElement(By.cssSelector("simple-snack-bar span")).getAttribute("textContent");

        Assert.assertEquals(registrationActualText, registrationSuccessText, "Registration wasn't successful");

//      Way #2

//        getWebDriver().findElement(By.id("email")).clear();
//        getWebDriver().findElement(By.id("email")).sendKeys(userEmail);
//
//        getWebDriver().findElement(By.id("password")).clear();
//        getWebDriver().findElement(By.id("password")).sendKeys(userPassword);
//
//        getWebDriver().findElement(By.id("loginButton")).click();
//
//        getWebDriver().findElement(By.id("navbarAccount")).click();
//        Thread.sleep(1000);
//
//        String actualNameText = getWebDriver().findElement(By.cssSelector("button[aria-label='Go to user profile'] span")).getText();
//
//        Assert.assertEquals(actualNameText, userEmail, "User name does not match");




    }


}
