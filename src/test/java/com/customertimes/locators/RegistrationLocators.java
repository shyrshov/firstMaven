package com.customertimes.locators;

public class RegistrationLocators {
    private String UserRegistrationHeaderCss = "div h1";
    private String UserRegistrationHeaderXpath = "//div/h1";
    private String EmailCss = "input#emailControl";
    private String EmailXpath = "//input[@id='emailControl']";
    private String PasswordCss =  "input#passwordControl";
    private String PasswordXpath = "//input[@id='passwordControl']";
    private String PasswordPromptIconCss =  "#mat-hint-0 svg";
    private String PasswordPromptIconXpath = "//*[@id='mat-hint-0']/*[@data-icon='exclamation-circle']";
    private String PasswordPromptCss =  "#mat-hint-0 em";
    private String PasswordPromptXpath = "//*[text()='Password must be 5-20 characters long.']";
    private String PasswordCounterCss =  "#mat-hint-1";
    private String PasswordCounterXpath = "//*[@id='mat-hint-1']";
    private String RepeatPasswordCss =  "input#repeatPasswordControl";
    private String RepeatPasswordXpath = "//input[@id='repeatPasswordControl']";
    private String RepeatPasswordCounterCss =  "#mat-hint-2";
    private String RepeatPasswordCounterXpath = "//*[@id='mat-hint-2']";
    private String SlideBarCss =  "#mat-slide-toggle-1-input";
    private String SlideBarXpath = "//*[@id='#mat-slide-toggle-1-input']";
    private String SlideBarTextCss =  "span.mat-slide-toggle-content";
    private String SlideBarTextXpath = "//span[@class='mat-slide-toggle-content']";
    private String ProgressBarTextCss =  "mat-progress-bar div div:first-of-type";
    private String ProgressBarTextXpath = "//mat-progress-bar/div/div[1]";
    private String SecurityQuestionCss =  "[name=securityQuestion]";
    private String SecurityQuestionXpath = "//*[@name='securityQuestion']";
    private String ArrowDownCss =  "[class*=mat-select-arrow-wrapper]";
    private String ArrowDownXpath = "//*[contains(@class, 'mat-select-arrow-wrapper')]";
    private String AnswerCss =  "input#securityAnswerControl";
    private String AnswerXpath = "//input[@id='securityAnswerControl']";
    private String RegisterButtonCss =  "button#registerButton";
    private String RegisterButtonXpath = "//button[@id='registerButton']";
    private String RegisterIconCss =  "button#registerButton .material-icons";
    private String RegisterIconXpath = "//button[@id='registerButton']//i[@class='material-icons']";
    private String RegisterTextCss =  "button#registerButton span.mat-button-wrapper";
    private String RegisterTextXpath = "//button[@id='registerButton']/span[@class='mat-button-wrapper']";
    private String LoginCss =  ".primary-link";
    private String LoginXpath = "//*[@class='primary-link']";


}
