package com.customertimes.test.purchase;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class ProductAddToCart extends BaseTest {

    String userEmail;
    String userPassword;
    WebDriverWait wait;
    SoftAssert softAssert;
    String productTitle;
    String productPlacedIntoBasketText;

    @BeforeClass
    public void setup() {
        wait = new WebDriverWait(getWebDriver(), 5);
        softAssert = new SoftAssert();
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']"))));
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        userEmail = "andrii@gmail.com";
        userPassword = "123456789";
        productTitle = "Banana Juice (1000ml)";
        productPlacedIntoBasketText = "Placed " + productTitle + " into basket.";
    }

    @AfterClass
    public void tearDown() {

        getWebDriver().findElement(By.cssSelector("[data-icon='trash-alt']")).click();
        WebdriverRunner.closeWebdriver();
    }

    @Test
    public void userCanAddProductToCart() throws InterruptedException {

        getWebDriver().findElement(By.id("navbarAccount")).click();
        getWebDriver().findElement(By.id("navbarLoginButton")).click();

        getWebDriver().findElement(By.id("email")).clear();
        getWebDriver().findElement(By.id("email")).sendKeys(userEmail);

        getWebDriver().findElement(By.id("password")).clear();
        getWebDriver().findElement(By.id("password")).sendKeys(userPassword);

        getWebDriver().findElement(By.id("loginButton")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), '" + productTitle + "')]")));
        getWebDriver().findElement(By.xpath("//div[contains(text(), '" + productTitle + "')]//following::button[1]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Placed')]")));
        String productPlacedIntoBasketActualText = getWebDriver().findElement(By.xpath("//span[contains(text(), 'Placed')]")).getText();
        softAssert.assertEquals(productPlacedIntoBasketActualText, productPlacedIntoBasketText, "Products added to the cart are not equals");

        getWebDriver().findElement(By.cssSelector("button[aria-label='Show the shopping cart']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-table/mat-row/mat-cell[contains(text(), '" + productTitle + "')]")));
        String ProductInTheBasketActualText = getWebDriver().findElement(By.xpath("//mat-table/mat-row/mat-cell[contains(text(), '" + productTitle + "')]")).getAttribute("innerText");
        softAssert.assertEquals(ProductInTheBasketActualText, productTitle, "Products in the basket are not equals");

        String quantityOfAddedProducts = getWebDriver().findElement(By.xpath("//mat-cell/button/following-sibling::span[1]")).getAttribute("innerText");
        softAssert.assertEquals("1", quantityOfAddedProducts, "Quantity of products added to basket not equals");

        softAssert.assertAll();
    }
}

