package com.customertimes.test.registration;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class JuiceShopPasswordsDoNotMatchTest extends BaseTest{


    String userPassword;
    String userRepeatPassword;
    String passwordsDoNotMatchErrorText;


    @BeforeClass
    public void setup() {
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        userPassword = RandomStringUtils.random(10, true, true);
        userRepeatPassword = RandomStringUtils.random(9, true, true);
        passwordsDoNotMatchErrorText = "Passwords do not match";

    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebdriver();
    }

    @Test
    public void userFillDifferentPasswordsToSignUpForm() {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        getWebDriver().findElement(By.cssSelector("[href='#/register']")).click();

        getWebDriver().findElement(By.id("passwordControl")).clear();
        getWebDriver().findElement(By.id("passwordControl")).sendKeys(userPassword);

        getWebDriver().findElement(By.id("repeatPasswordControl")).clear();
        getWebDriver().findElement(By.id("repeatPasswordControl")).sendKeys(userRepeatPassword);

        getWebDriver().findElement(By.cssSelector("h1")).click();

        String passwordsDoNotMatchActualText = getWebDriver().findElement(By.cssSelector("mat-error[role='alert']")).getText();

        Assert.assertEquals(passwordsDoNotMatchActualText, passwordsDoNotMatchErrorText, "Passwords do not match error text is incorrect");
    }

}
