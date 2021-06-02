package com.customertimes.test.purchase;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.framework.pages.LoginPage;
import com.customertimes.framework.pages.ProductListPage;
import com.customertimes.model.Customer;
import com.customertimes.model.Product;
import com.customertimes.test.BaseTest;
import com.customertimes.testData.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class VerifyProductInformation extends BaseTest {

    LoginPage loginPage;
    ProductListPage productListPage;
    WebDriverWait wait;
    SoftAssert softAssert;
    TestData testData;
    Product product;


    @BeforeClass
    public void setupDataToJuiceShop() {
        loginPage = new LoginPage(driver);
        productListPage = new ProductListPage(driver);
        testData = new TestData();
        softAssert = new SoftAssert();
        wait = new WebDriverWait(getWebDriver(), 5);
        product = product.newBuilder().withTitle("Banana Juice (1000ml)").withDescription("Monkeys love it the most.").withPrice("1.99¤").build();
        driver.get("http://beeb0b73705f.sn.mynetname.net:3000/");
        wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']"))));
        driver.findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();

    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void userCanOpenProductInformation() throws InterruptedException {

        productListPage.clickProduct(product.getTitle());

        String actualTitle = productListPage.getProductTitle();
        softAssert.assertEquals(actualTitle, product.getTitle(), "Title not equals");

        String actualDescription = productListPage.getProductDescription();
        softAssert.assertEquals(actualDescription, product.getDescription(), "Description not equals");

        String actualPrice = productListPage.getProductPrice();
        softAssert.assertEquals(actualPrice, product.getPrice(), "Price not equals");

        softAssert.assertAll();

    }




}
