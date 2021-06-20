package com.customertimes.cucumber.stepdefs;

import com.customertimes.cucumber.page.ProductListPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class PurchaseProcessStepdefs {

    private ProductListPage productListPage = new ProductListPage(getWebDriver());
    SoftAssert softAssert = new SoftAssert();

    @Given("User goes to Main Page")
    public void userGoesToMainPage() {
        productListPage.openPage();
    }

    @When("User click on product {string}")
    public void userClickOnProduct(String productName) {
        productListPage.clickProduct(productName);
    }

    @Then("Product info should contain Title {string} , Description {string}, Price {string}")
    public void productInfoShouldContainTitleDescriptionPrice(String productName, String productDescription, String productPrice) {

        String actualTitle = productListPage.getProductTitle();
        softAssert.assertEquals(actualTitle, productName, "Title not equals");

        String actualDescription = productListPage.getProductDescription();
        softAssert.assertEquals(actualDescription, productDescription, "Description not equals");

        String actualPrice = productListPage.getProductPrice();
        softAssert.assertEquals(actualPrice, productPrice, "Price not equals");

        softAssert.assertAll();
    }
}
