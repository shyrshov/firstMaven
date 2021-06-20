package com.customertimes.cucumber.stepdefs;

import com.customertimes.cucumber.page.ProductListPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class AddToCartStepdefs {

    private ProductListPage productListPage = new ProductListPage(getWebDriver());
    SoftAssert softAssert = new SoftAssert();

    @And("User add product {string} to cart")
    public void userAddProductToCart(String productName) {
        productListPage.productAddToBasket(productName);
    }

    @And("User open Shopping Cart")
    public void userOpenShoppingCart() {
        productListPage.clickShoppingCart();
    }

    @Then("Shopping cart contains product {string} with quantity {string}")
    public void shoppingCartContainsProductWithQuantity(String productName, int productQuantity) {
        String ProductInTheBasketActualText = productListPage.getProductInBasketActualText(productName);

        softAssert.assertEquals(ProductInTheBasketActualText, productName, "Products in the basket are not equals");

        String quantityOfAddedProducts = productListPage.getQuantityOfAddedProducts();

        softAssert.assertEquals(quantityOfAddedProducts, productQuantity,"Quantity of products added to basket not equals");

        softAssert.assertAll();
    }
}
