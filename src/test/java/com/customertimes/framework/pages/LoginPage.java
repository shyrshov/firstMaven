package com.customertimes.framework.pages;

import com.customertimes.model.Customer;
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

    @Override
    public void openPage() {
        driver.get("http://beeb0b73705f.sn.mynetname.net:3000/");
    }

    public void clickOnAccountButton() {
        driver.findElement(By.id("navbarAccount")).click();
    }


    public void navigateToLoginPage() {
        clickOnAccountButton();
        driver.findElement(By.id("navbarLoginButton")).click();
    }


    public void enterEmail(String email) {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void clickOnLoginButton() {
        driver.findElement(By.id("loginButton")).click();
    }

    public String getActualNameText(String currentEmail) {
        wait.until(ExpectedConditions.textToBe(By.cssSelector("button[aria-label='Go to user profile'] span"), currentEmail));
        String actualNameText = driver.findElement(By.cssSelector("button[aria-label='Go to user profile'] span")).getText();
        return actualNameText;
    }

    public String getInvalidCredentialsErrorActualText(String invalidCredentialsErrorText) {
        wait.until(ExpectedConditions.textToBe(By.cssSelector("div [class='error ng-star-inserted']"), invalidCredentialsErrorText));
        String invalidCredentialsErrorActualText = driver.findElement(By.cssSelector("div [class='error ng-star-inserted']")).getText();
        return invalidCredentialsErrorActualText;
    }

    public void clickNotYetACustomerButton() {
        driver.findElement(By.cssSelector("[href='#/register']")).click();
    }
}
