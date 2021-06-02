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

public class VerifyProductInformation extends BaseTest {

    String productTitle;
    String productDescription;
    String productPrice;
    WebDriverWait wait;
    SoftAssert softAssert;


    @BeforeClass
    public void setup() {
        softAssert = new SoftAssert();
        wait = new WebDriverWait(getWebDriver(), 5);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/");
        wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']"))));
        getWebDriver().findElement(By.cssSelector("button[aria-label='Close Welcome Banner']")).click();
        productTitle = "Banana Juice (1000ml)";
        productDescription = "Monkeys love it the most.";
        productPrice = "1.99¤";
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void userCanOpenProductInformation() throws InterruptedException {

        getWebDriver().findElement(By.xpath("//div[contains(text(), '" + productTitle + "')]")).click();

        String actualTitle = getWebDriver().findElement(By.cssSelector("div h1")).getText();
        softAssert.assertEquals(actualTitle, productTitle, "Title not equals");

        String actualDescription = getWebDriver().findElement(By.xpath("//div/h1//following-sibling::div[1]")).getText();
        softAssert.assertEquals(actualDescription, productDescription, "Description not equals");

        String actualPrice = getWebDriver().findElement(By.xpath("//div/p[@class='item-price']")).getText();
        softAssert.assertEquals(actualPrice,productPrice, "Price not equals");

        softAssert.assertAll();

    }



}
