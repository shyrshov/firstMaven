package com.customertimes.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class RegistrationPage extends AbstractPage {

    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIME_OUT);
    }

    @Step
    @Override
    public void openPage() {
        driver.get("http://beeb0b73705f.sn.mynetname.net:3000/");
    }

    @Step
    public void enterEmail(String email) {
        driver.findElement(By.id("emailControl")).clear();
        driver.findElement(By.id("emailControl")).sendKeys(email);
    }

    @Step
    public void enterPassword(String password) {
        driver.findElement(By.id("passwordControl")).clear();
        driver.findElement(By.id("passwordControl")).sendKeys(password);
    }

    @Step
    public void enterRepeatPassword(String repeatPassword) {
        driver.findElement(By.id("repeatPasswordControl")).clear();
        driver.findElement(By.id("repeatPasswordControl")).sendKeys(repeatPassword);
    }

    @Step
    public void chooseSecurityQuestion() {
        driver.findElement(By.cssSelector("[name='securityQuestion']")).click();
        driver.findElement(By.cssSelector("div [role=listbox] mat-option")).click();
    }

    @Step
    public void enterAnswerSecurityQuestion(String answerSecurityQuestion) {
        driver.findElement(By.id("securityAnswerControl")).clear();
        driver.findElement(By.id("securityAnswerControl")).sendKeys(answerSecurityQuestion);
    }

    @Step
    public void clickUserRegistrationTitle() {
        driver.findElement(By.cssSelector("h1")).click();
    }

    @Step
    public String getEmailNotValidActualText() {
        String emailNotValidActualText = driver.findElement(By.cssSelector("mat-error[role='alert']")).getText();
        return emailNotValidActualText;
    }

    @Step
    public String getPasswordLengthErrorActualText() {
        String passwordLengthErrorActualText = driver.findElement(By.cssSelector("mat-error[role='alert']")).getText();
        return passwordLengthErrorActualText;
    }

    @Step
    public String getPasswordsDoNotMatchActualText() {
        String passwordsDoNotMatchActualText = driver.findElement(By.cssSelector("mat-error[role='alert']")).getText();
        return passwordsDoNotMatchActualText;
    }

    @Step
    public void waitToLanguagePopUpDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("simple-snack-bar div button .mat-button-wrapper")));
    }

    @Step
    public void clickRegisterButton() {
        driver.findElement(By.id("registerButton")).click();
    }

    @Step
    public void waitUntilRegistrationSuccessPopUpAppear() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("simple-snack-bar span")));
    }

    @Step
    public String getRegistrationSuccessActualText() {
        String registrationSuccessActualText = driver.findElement(By.cssSelector("simple-snack-bar span")).getAttribute("innerText");
        return registrationSuccessActualText;
    }

}
