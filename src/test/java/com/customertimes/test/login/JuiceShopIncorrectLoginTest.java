package com.customertimes.test.login;

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

public class JuiceShopIncorrectLoginTest extends BaseTest{

    String userEmail;
    String userPassword;
    String invalidCredentialsErrorText;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        wait = new WebDriverWait(getWebDriver(), 5);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']"))));
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        userEmail = RandomStringUtils.random(7, true, true) + "@gmail.com";
        userPassword = "123456789";
        invalidCredentialsErrorText = "Invalid email or password.";
    }

    @Test
    public void userFillIncorrectEmailToLoginForm() throws InterruptedException {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        getWebDriver().findElement(By.id("email")).clear();
        getWebDriver().findElement(By.id("email")).sendKeys(userEmail);

        getWebDriver().findElement(By.id("password")).clear();
        getWebDriver().findElement(By.id("password")).sendKeys(userPassword);

        getWebDriver().findElement(By.cssSelector("button#loginButton")).click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("div [class='error ng-star-inserted']"), invalidCredentialsErrorText));
        String invalidCredentialsErrorActualText = getWebDriver().findElement(By.cssSelector("div [class='error ng-star-inserted']")).getText();

        Assert.assertEquals(invalidCredentialsErrorText, invalidCredentialsErrorActualText, "Invalid email or password error message doesn't match");
    }

}
