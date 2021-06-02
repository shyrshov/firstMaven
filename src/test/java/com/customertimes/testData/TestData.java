package com.customertimes.testData;

import org.apache.commons.lang3.RandomStringUtils;

public class TestData {

    private String invalidCredentialsErrorText = "Invalid email or password.";
    private String emailNotValidErrorText = "Email address is not valid.";
    private String passwordLengthErrorText = "Password must be 5-20 characters long.";
    private String passwordsDoNotMatchErrorText = "Passwords do not match";
    private String registrationSuccessText = "Registration completed successfully. You can now log in.";
    private String incorrectUserEmail = RandomStringUtils.random(7, true, true) + "@gmail.com";
    private String incorrectUserPassword = RandomStringUtils.random(10, true, true);
    private String invalidUserEmail = RandomStringUtils.random(10, true, true);
    private String invalidUserPassword = RandomStringUtils.random(3, true, true);
    private String answerSecurityQuestion = RandomStringUtils.random(10, true, false);



    public String getInvalidCredentialsErrorText() { return invalidCredentialsErrorText; }

    public String getEmailNotValidErrorText() { return emailNotValidErrorText; }

    public String getPasswordLengthErrorText() { return passwordLengthErrorText; }

    public String getPasswordsDoNotMatchErrorText() { return passwordsDoNotMatchErrorText; }

    public String getRegistrationSuccessText() { return registrationSuccessText; }

    public String getIncorrectUserEmail() { return incorrectUserEmail; }

    public String getIncorrectUserPassword() { return incorrectUserPassword; }

    public String getInvalidUserEmail() {return invalidUserEmail; }

    public String getInvalidUserPassword() {return invalidUserPassword; }

    public String getAnswerSecurityQuestion() { return answerSecurityQuestion; }



}
