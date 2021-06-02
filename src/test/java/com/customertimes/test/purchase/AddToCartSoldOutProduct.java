package com.customertimes.test.purchase;

import com.customertimes.framework.driver.WebdriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;


public class AddToCartSoldOutProduct {

    String userEmail;
    String userPassword;
    WebDriverWait wait;
    SoftAssert softAssert;
    String productOutOfStockText;
    JavascriptExecutor js = (JavascriptExecutor) getWebDriver();

    @BeforeClass
    public void setup() {
        wait = new WebDriverWait(getWebDriver(), 5);
        softAssert = new SoftAssert();
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']"))));
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        userEmail = "andrii@gmail.com";
        userPassword = "123456789";
        productOutOfStockText = "We are out of stock! Sorry for the inconvenience.";
    }

    @AfterClass
    public void tearDown() { WebdriverRunner.closeWebDriver(); }

    @Test
    public void userCantAddSoldOutProductToCart() {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        getWebDriver().findElement(By.id("email")).clear();
        getWebDriver().findElement(By.id("email")).sendKeys(userEmail);

        getWebDriver().findElement(By.id("password")).clear();
        getWebDriver().findElement(By.id("password")).sendKeys(userPassword);

        getWebDriver().findElement(By.id("loginButton")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div mat-grid-list")));

        getWebDriver().findElement(By.cssSelector("[aria-label='dismiss cookie message']")).click();

        WebElement element = getWebDriver().findElement(By.cssSelector("button[aria-label='Next page']"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        getWebDriver().findElement(By.cssSelector("button[aria-label='Next page']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("div[class*=ribbon]"))));
        getWebDriver().findElement(By.cssSelector("div[class*=ribbon] +div +div button")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='cdk-overlay-pane'] snack-bar-container[class*=errorBar] simple-snack-bar span")));
        String productOutOfStockActualText = getWebDriver().findElement(By.cssSelector("div[class='cdk-overlay-pane'] snack-bar-container[class*=errorBar] simple-snack-bar span")).getAttribute("textContent");
        softAssert.assertEquals(productOutOfStockActualText, productOutOfStockText, "Product out of stock message is incorrect");

        getWebDriver().findElement(By.cssSelector("button[aria-label='Show the shopping cart']")).click();

        boolean checkoutButton = getWebDriver().findElement(By.id("checkoutButton")).isEnabled();
        softAssert.assertEquals(false, checkoutButton, "Checkout button condition is not valid");

        softAssert.assertAll();

    }



}

