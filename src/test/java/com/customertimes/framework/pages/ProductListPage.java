package com.customertimes.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class ProductListPage extends AbstractPage{

    private WebDriverWait wait;
    JavascriptExecutor js = (JavascriptExecutor) getWebDriver();;

    public ProductListPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIME_OUT);
    }

    @Step
    @Override
    public void openPage() {
        driver.get("http://beeb0b73705f.sn.mynetname.net:3000/");
    }

    @Step
    public void clickProduct(String product) {
        driver.findElement(By.xpath("//div[contains(text(), '" + product + "')]")).click();
    }

    @Step
    public String getProductTitle() {
        return driver.findElement(By.cssSelector("div h1")).getText();
    }

    @Step
    public String getProductDescription() {
        return driver.findElement(By.xpath("//div/h1//following-sibling::div[1]")).getText();
    }

    @Step
    public String getProductPrice() {
        String actualPrice = driver.findElement(By.xpath("//div/p[@class='item-price']")).getText();
        return actualPrice;
    }

    @Step
    public void productAddToBasket(String product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), '" + product + "')]")));
        driver.findElement(By.xpath("//div[contains(text(), '" + product + "')]//following::button[1]")).click();
    }

    @Step
    public String getProductPlacedIntoBasketActualText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Placed')]")));
        String productPlacedIntoBasketActualText = getWebDriver().findElement(By.xpath("//span[contains(text(), 'Placed')]")).getText();
        return productPlacedIntoBasketActualText;
    }

    @Step
    public void clickShoppingCart() {
        driver.findElement(By.cssSelector("button[aria-label='Show the shopping cart']")).click();
    }

    @Step
    public String getProductInBasketActualText(String productTitle) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-table/mat-row/mat-cell[contains(text(), '" + productTitle + "')]")));
        String ProductInTheBasketActualText = getWebDriver().findElement(By.xpath("//mat-table/mat-row/mat-cell[contains(text(), '" + productTitle + "')]")).getAttribute("innerText");
        return ProductInTheBasketActualText;
    }

    @Step
    public String getQuantityOfAddedProducts() {
        String quantityOfAddedProducts = driver.findElement(By.xpath("//mat-cell/button/following-sibling::span[1]")).getAttribute("innerText");
        return quantityOfAddedProducts;
    }

    @Step
    public void waitForProductListLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div mat-grid-list")));
    }

    public void closeCookieAlert() {
        getWebDriver().findElement(By.cssSelector("[aria-label='dismiss cookie message']")).click();
    }

    @Step
    public void moveToNextPage() {
        WebElement element = driver.findElement(By.cssSelector("button[aria-label='Next page']"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.cssSelector("button[aria-label='Next page']")).click();
    }

    @Step
    public void AddToCartOutOFStockProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("div[class*=ribbon]"))));
        driver.findElement(By.cssSelector("div[class*=ribbon] +div +div button")).click();
    }

    @Step
    public String getProductOutOfStockActualText() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='cdk-overlay-pane'] snack-bar-container[class*=errorBar] simple-snack-bar span")));
        String buyProductOutOfStockActualText = driver.findElement(By.cssSelector("div[class='cdk-overlay-pane'] snack-bar-container[class*=errorBar] simple-snack-bar span")).getAttribute("textContent");
        return buyProductOutOfStockActualText;
    }

    @Step
    public boolean isCheckoutButtonEnabled() {
        boolean checkoutButton = driver.findElement(By.id("checkoutButton")).isEnabled();
        return checkoutButton;
    }

}
