package com.customertimes.test.registration;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class JuiceShopShortPasswordTest extends BaseTest{

    String userPassword;
    String passwordLengthErrorText;
    WebDriverWait wait;


    @BeforeClass
    public void setup() {
        wait = new WebDriverWait(getWebDriver(), 5);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']"))));
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        userPassword = RandomStringUtils.random(3, true, true);
        passwordLengthErrorText = "Password must be 5-20 characters long.";

    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void userFillShortPasswordToSignUpForm() {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        getWebDriver().findElement(By.cssSelector("[href='#/register']")).click();

        getWebDriver().findElement(By.id("passwordControl")).clear();
        getWebDriver().findElement(By.id("passwordControl")).sendKeys(userPassword);

        getWebDriver().findElement(By.cssSelector("h1")).click();

        String passwordLengthErrorActualText = getWebDriver().findElement(By.cssSelector("mat-error[role='alert']")).getText();

        Assert.assertEquals(passwordLengthErrorActualText, passwordLengthErrorText, "Password length validation error text doesn't match");
    }


}
