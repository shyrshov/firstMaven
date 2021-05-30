package com.customertimes.test.login;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class JuiceShopLoginTest extends BaseTest {

    String userEmail;
    WebDriverWait wait;

    @BeforeClass
    public void setup() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 5);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']"))));
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        userEmail = "andrii@gmail.com";
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebdriver();
    }

    @Test
    public void userCanLoginToJuiceShop() {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        getWebDriver().findElement(By.id("email")).clear();
        getWebDriver().findElement(By.id("email")).sendKeys(userEmail);

        getWebDriver().findElement(By.id("password")).clear();
        getWebDriver().findElement(By.id("password")).sendKeys("123456789");

        getWebDriver().findElement(By.id("loginButton")).click();

        getWebDriver().findElement(By.id("navbarAccount")).click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("button[aria-label='Go to user profile'] span"), userEmail));

        String actualNameText = getWebDriver().findElement(By.cssSelector("button[aria-label='Go to user profile'] span")).getText();

        Assert.assertEquals(actualNameText, userEmail, "User name does not match");





    }

}
