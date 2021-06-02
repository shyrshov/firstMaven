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

public class JuiceShopIncorrectEmailTest extends BaseTest {

    String userEmail;
    String emailNotValid;
    WebDriverWait wait;


    @BeforeClass
    public void setup() {
        wait = new WebDriverWait(getWebDriver(), 5);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']"))));
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        userEmail = RandomStringUtils.random(10, true, false);
        emailNotValid = "Email address is not valid.";

    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void userFillIncorrectEmailToSignUpForm() {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        getWebDriver().findElement(By.cssSelector("[href='#/register']")).click();

        getWebDriver().findElement(By.id("emailControl")).clear();
        getWebDriver().findElement(By.id("emailControl")).sendKeys(userEmail);

        getWebDriver().findElement(By.cssSelector("h1")).click();

        String emailNotValidActualText = getWebDriver().findElement(By.cssSelector("mat-error[role='alert']")).getText();

        Assert.assertEquals(emailNotValidActualText, emailNotValid, "Email field validation error doesn't match");
    }

}
