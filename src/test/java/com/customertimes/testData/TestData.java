package com.customertimes.testData;

import org.apache.commons.lang3.RandomStringUtils;

public class TestData {

    private String invalidCredentialsErrorText = "Invalid email or password.";
    private String incorrectUserEmail = RandomStringUtils.random(7, true, true) + "@gmail.com";
    private String incorrectUserPassword = RandomStringUtils.random(10, true, true);

    public String getInvalidCredentialsErrorText() { return invalidCredentialsErrorText; }

    public String getIncorrectUserEmail() { return incorrectUserEmail; }

    public String getIncorrectUserPassword() { return incorrectUserPassword; }

}
