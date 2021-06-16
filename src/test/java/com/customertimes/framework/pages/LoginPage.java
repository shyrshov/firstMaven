package com.customertimes.framework.pages;

import com.customertimes.model.Customer;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class LoginPage extends AbstractPage {

    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIME_OUT);
    }

    @Step
    @Override
    public void openPage() {
        driver.get("http://beeb0b73705f.sn.mynetname.net:3000/");
    }

    @Step
    public void clickOnAccountButton() {
        driver.findElement(By.id("navbarAccount")).click();
    }

    @Step
    public void navigateToLoginPage() {
        clickOnAccountButton();
        driver.findElement(By.id("navbarLoginButton")).click();
    }

    @Step
    public void enterEmail(String email) {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
    }

    @Step
    public void enterPassword(String password) {
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Step
    public void clickOnLoginButton() {
        driver.findElement(By.id("loginButton")).click();
    }

    @Step
    public String getActualNameText(String currentEmail) {
        wait.until(ExpectedConditions.textToBe(By.cssSelector("button[aria-label='Go to user profile'] span"), currentEmail));
        String actualNameText = driver.findElement(By.cssSelector("button[aria-label='Go to user profile'] span")).getText();
        return actualNameText;
    }

    @Step
    public String getInvalidCredentialsErrorActualText(String invalidCredentialsErrorText) {
        wait.until(ExpectedConditions.textToBe(By.cssSelector("div [class='error ng-star-inserted']"), invalidCredentialsErrorText));
        String invalidCredentialsErrorActualText = driver.findElement(By.cssSelector("div [class='error ng-star-inserted']")).getText();
        return invalidCredentialsErrorActualText;
    }

    @Step
    public void clickNotYetACustomerButton() {
        driver.findElement(By.cssSelector("[href='#/register']")).click();
    }
}
