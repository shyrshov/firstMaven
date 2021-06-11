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

public class ProductAddToCart extends BaseTest {

    LoginPage loginPage;
    ProductListPage productListPage;
    WebDriverWait wait;
    SoftAssert softAssert;
    TestData testData;
    Product product;
    Customer customer;
    String productPlacedIntoBasketText;

    @BeforeClass
    public void setupDataToJuiceShop() {
        loginPage = new LoginPage(driver);
        productListPage = new ProductListPage(driver);
        testData = new TestData();
        softAssert = new SoftAssert();
        wait = new WebDriverWait(getWebDriver(), 5);
        product = product.newBuilder().withTitle("Banana Juice (1000ml)").withDescription("Monkeys love it the most.").withPrice("1.99¤").build();
        customer = Customer.newBuilder().withName("andrii@gmail.com").withPassword("123456789").build();
        driver.get("http://beeb0b73705f.sn.mynetname.net:3000/");
        wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']"))));
        driver.findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        productPlacedIntoBasketText = "Placed " + product.getTitle() + " into basket.";
    }

    @AfterClass
    public void tearDownClass() {
        getWebDriver().findElement(By.cssSelector("[data-icon='trash-alt']")).click();
    }

    @Test
    public void userCanAddProductToCart() {

        loginPage.navigateToLoginPage();

        loginPage.enterEmail(customer.getEmail());

        loginPage.enterPassword(customer.getPassword());

        loginPage.clickOnLoginButton();

        productListPage.productAddToBasket(product.getTitle());

        String productPlacedIntoBasketActualText = productListPage.getProductPlacedIntoBasketActualText();

        softAssert.assertEquals(productPlacedIntoBasketActualText, productPlacedIntoBasketText, "Products added to the cart are not equals");

        productListPage.clickShoppingCart();

        String ProductInTheBasketActualText = productListPage.getProductInBasketActualText(product.getTitle());

        softAssert.assertEquals(ProductInTheBasketActualText, product.getTitle(), "Products in the basket are not equals");

        String quantityOfAddedProducts = productListPage.getQuantityOfAddedProducts();

        softAssert.assertEquals("1", quantityOfAddedProducts, "Quantity of products added to basket not equals");

        softAssert.assertAll();
    }




}

