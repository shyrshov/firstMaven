package com.customertimes.test.login;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class JuiceShopLoginTest extends BaseTest {

    String userEmail;

    @BeforeClass
    public void setup() throws InterruptedException {
        getWebDriver().get(BASE_URL);
        Thread.sleep(2000);
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        userEmail = "andrii@gmail.com";
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebdriver();
    }

    @Test
    public void userCanLoginToJuiceShop() throws InterruptedException {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        getWebDriver().findElement(By.id("email")).clear();
        getWebDriver().findElement(By.id("email")).sendKeys(userEmail);

        getWebDriver().findElement(By.id("password")).clear();
        getWebDriver().findElement(By.id("password")).sendKeys("123456789");

        getWebDriver().findElement(By.id("loginButton")).click();

        getWebDriver().findElement(By.id("navbarAccount")).click();
        Thread.sleep(1000);

        String actualNameText = getWebDriver().findElement(By.cssSelector("button[aria-label='Go to user profile'] span")).getText();

        Assert.assertEquals(actualNameText, userEmail, "User name does not match");





    }

}
