package com.customertimes.cucumber.stepdefs;

import com.customertimes.cucumber.page.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class LoginStepdefs {

    private LoginPage loginPage = new LoginPage(getWebDriver());
    @Given("User goes to Login Page")
    public void navigateToLoginPage() {
        loginPage.openPage();
    }

    @When("User enters email {string} and password {string}")
    public void enterUserEmailAndPassword(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @And("User clicks on login button")
    public void userClicksOnLoginButton() {
        loginPage.clickOnLoginButton();
    }

    @Then("User {string} should be logged to application")
    public void verifyUserIsLoggedToApplication(String expectedUser) {
        loginPage.clickOnAccountButton();
        String actualUser = loginPage.getActualNameText(expectedUser);

        Assert.assertEquals(actualUser, expectedUser, "User is not logged to Juice Shop");

    }
}
