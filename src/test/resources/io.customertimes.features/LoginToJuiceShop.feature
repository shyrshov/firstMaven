Feature: Login

  Scenario: Login to Juice Shop
    Given User goes to Login Page
    When User enters email "andrii@gmail.com" and password "123456789"
    And User clicks on login button
    Then User "andrii@gmail.com" should be logged to application