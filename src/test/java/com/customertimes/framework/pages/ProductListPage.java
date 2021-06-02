package com.customertimes.framework.pages;

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

    @Override
    public void openPage() {
    }

    public void clickProduct(String product) {
        driver.findElement(By.xpath("//div[contains(text(), '" + product + "')]")).click();
    }

    public String getProductTitle() {
        return driver.findElement(By.cssSelector("div h1")).getText();
    }

    public String getProductDescription() {
        return driver.findElement(By.xpath("//div/h1//following-sibling::div[1]")).getText();
    }

    public String getProductPrice() {
        String actualPrice = driver.findElement(By.xpath("//div/p[@class='item-price']")).getText();
        return actualPrice;
    }

    public void productAddToBasket(String product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), '" + product + "')]")));
        driver.findElement(By.xpath("//div[contains(text(), '" + product + "')]//following::button[1]")).click();
    }

    public String getProductPlacedIntoBasketActualText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Placed')]")));
        String productPlacedIntoBasketActualText = getWebDriver().findElement(By.xpath("//span[contains(text(), 'Placed')]")).getText();
        return productPlacedIntoBasketActualText;
    }

    public void clickShoppingCart() {
        driver.findElement(By.cssSelector("button[aria-label='Show the shopping cart']")).click();
    }

    public String getProductInBasketActualText(String productTitle) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-table/mat-row/mat-cell[contains(text(), '" + productTitle + "')]")));
        String ProductInTheBasketActualText = getWebDriver().findElement(By.xpath("//mat-table/mat-row/mat-cell[contains(text(), '" + productTitle + "')]")).getAttribute("innerText");
        return ProductInTheBasketActualText;
    }

    public String getQuantityOfAddedProducts() {
        String quantityOfAddedProducts = driver.findElement(By.xpath("//mat-cell/button/following-sibling::span[1]")).getAttribute("innerText");
        return quantityOfAddedProducts;
    }

    public void waitForProductListLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div mat-grid-list")));
    }

    public void closeCookieAlert() {
        getWebDriver().findElement(By.cssSelector("[aria-label='dismiss cookie message']")).click();
    }

    public void moveToNextPage() {
        WebElement element = driver.findElement(By.cssSelector("button[aria-label='Next page']"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.cssSelector("button[aria-label='Next page']")).click();
    }

    public void AddToCartOutOFStockProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("div[class*=ribbon]"))));
        driver.findElement(By.cssSelector("div[class*=ribbon] +div +div button")).click();
    }

    public String getProductOutOfStockActualText() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='cdk-overlay-pane'] snack-bar-container[class*=errorBar] simple-snack-bar span")));
        String buyProductOutOfStockActualText = driver.findElement(By.cssSelector("div[class='cdk-overlay-pane'] snack-bar-container[class*=errorBar] simple-snack-bar span")).getAttribute("textContent");
        return buyProductOutOfStockActualText;
    }

    public boolean isCheckoutButtonEnabled() {
        boolean checkoutButton = driver.findElement(By.id("checkoutButton")).isEnabled();
        return checkoutButton;
    }

}
