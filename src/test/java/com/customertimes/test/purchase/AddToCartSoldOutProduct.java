package com.customertimes.test.purchase;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.framework.pages.LoginPage;
import com.customertimes.framework.pages.ProductListPage;
import com.customertimes.model.Customer;
import com.customertimes.test.BaseTest;
import com.customertimes.testData.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;


public class AddToCartSoldOutProduct extends BaseTest {

    LoginPage loginPage;
    ProductListPage productListPage;
    WebDriverWait wait;
    SoftAssert softAssert;
    TestData testData;
    Customer customer;
    JavascriptExecutor js;

    @BeforeClass
    public void setupDataToJuiceShop() {
        loginPage = new LoginPage(driver);
        productListPage = new ProductListPage(driver);
        testData = new TestData();
        softAssert = new SoftAssert();
        customer = Customer.newBuilder().withName("andrii@gmail.com").withPassword("123456789").build();
        js = (JavascriptExecutor) getWebDriver();
        wait = new WebDriverWait(getWebDriver(), 5);
        driver.get("http://beeb0b73705f.sn.mynetname.net:3000/");
        wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']"))));
        driver.findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();

    }

    @Test
    public void userCantAddSoldOutProductToCart() {

        loginPage.navigateToLoginPage();

        loginPage.enterEmail(customer.getEmail());

        loginPage.enterPassword(customer.getPassword());

        loginPage.clickOnLoginButton();

        productListPage.waitForProductListLoad();

        productListPage.closeCookieAlert();

        productListPage.moveToNextPage();

        productListPage.AddToCartOutOFStockProduct();

        String buyProductOutOfStockActualText = productListPage.getProductOutOfStockActualText();

        softAssert.assertEquals(buyProductOutOfStockActualText, testData.getBuyProductOutOfStockText(), "Product out of stock message is incorrect");

        productListPage.clickShoppingCart();

        boolean checkoutButton = productListPage.isCheckoutButtonEnabled();

        softAssert.assertEquals(false, checkoutButton, "Checkout button condition is not valid");

        softAssert.assertAll();

    }

}

