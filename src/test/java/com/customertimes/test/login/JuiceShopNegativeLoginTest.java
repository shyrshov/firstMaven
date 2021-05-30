package com.customertimes.test.login;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class JuiceShopNegativeLoginTest extends BaseTest {

    private static final String INVALID_CREDS_ERROR_TEXT = "Invalid email or password.";

    @BeforeClass
    public void setup() {
        getWebDriver().get(BASE_URL);
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebdriver();
    }


    @Test(dataProvider = "credentials")
    public void userCannotLoginWithInvalidCredentials(String email, String password) throws InterruptedException {
        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        getWebDriver().findElement(By.id("email")).clear();
        getWebDriver().findElement(By.id("email")).sendKeys(email);

        getWebDriver().findElement(By.id("password")).clear();
        getWebDriver().findElement(By.id("password")).sendKeys(password);

        getWebDriver().findElement(By.cssSelector("button#loginButton")).click();
        Thread.sleep(1000);

        String invalidCredentialsErrorActualText = getWebDriver().findElement(By.cssSelector("div [class='error ng-star-inserted']")).getText();

        Assert.assertEquals(INVALID_CREDS_ERROR_TEXT, invalidCredentialsErrorActualText, "Invalid email or password error message doesn't match");
    }


    @DataProvider
    public Object[][] credentials() {
        return new Object[][]{
                {
                        RandomStringUtils.random(7, true, true) + "@gmail.com",
                        "123456789"
                },
                {
                        RandomStringUtils.random(10, true, false) + "@gmail.com",
                        RandomStringUtils.random(10, true, false)
                },
                {
                        "andrii@gmail.com",
                        RandomStringUtils.random(10, true, true)
                }
        };
    }
}
