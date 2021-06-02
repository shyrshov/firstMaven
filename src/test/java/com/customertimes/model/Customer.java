package com.customertimes.model;

import org.apache.commons.lang3.RandomStringUtils;

public class Customer {

    private String email;
    private String password;
    private String invalidCredentialsErrorText = "Invalid email or password.";
    private String incorrectUserEmail = RandomStringUtils.random(7, true, true) + "@gmail.com";
    private String incorrectUserPassword = RandomStringUtils.random(10, true, true);

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getInvalidCredentialsErrorText() { return invalidCredentialsErrorText; }

    public String getIncorrectUserEmail() { return incorrectUserEmail; }

    public String getIncorrectUserPassword() { return incorrectUserPassword; }

    public static Builder newBuilder() { return new Builder(); }

    public Customer() {
    }

    public Customer(final Builder builder) {
        email = builder.email;
        password = builder.password;
    }

    public static final class Builder {
        private String email;
        private String password;

    private Builder() {
    }

    public Builder withName(final String val) {
        email = val;
        return this;
    }

    public Builder withPassword(final String val) {
        password = val;
        return this;
    }

    public Customer build() { return new Customer(this); }
}

}
