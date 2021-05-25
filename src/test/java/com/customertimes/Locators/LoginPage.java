package com.customertimes.Locators;

public class LoginPage {
    private String LoginPageHeaderCss = "mat-card h1";
    private String LoginPageHeaderXpath = "//h1[text()='Login']";
    private String EmailCss = "[name=email]";
    private String EmailXpath = "//*[@name='email']";
    private String PasswordCss = "[name=password]";
    private String PasswordXpath = "//*[@name='password']";
    private String DisplayPasswordCss = "[aria-label='Button to display the password']";
    private String DisplayPasswordXpath = "//*[@aria-label='Button to display the password']";
    private String LogInButtonCss = "button[type=submit]";
    private String LogInButtonXpath = "//button[@type='submit']";
    private String LogInIconCss = "#loginButton mat-icon";
    private String LogInIconXpath = "//*[@id='loginButton']";
    private String RememberMeCheckboxCss = "span.mat-checkbox-inner-container";
    private String RememberMeCheckboxXpath = "//span[@class='mat-checkbox-inner-container']";
    private String RememberLabelCss = "span.mat-checkbox-label";
    private String RememberMeLabelXpath = "//span[@class='mat-checkbox-label']";
    private String RegisterCss = "[href='#/register']";
    private String RegisterXpath = "//*[@href='#/register']";


}
