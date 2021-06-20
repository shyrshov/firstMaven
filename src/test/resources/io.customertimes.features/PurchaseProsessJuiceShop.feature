Feature: PurchaseProcess

  Scenario: Verify Product Information
    Given User goes to Main Page
    When User click on product "Banana Juice (1000ml)"
    Then Product info should contain Title "Banana Juice (1000ml)" , Description "Monkeys love it the most.", Price "1.99¤"

  Scenario: Add product to cart
    Given User goes to Login Page
    When User enters email "andrii@gmail.com" and password "123456789"
    And User clicks on login button
    And User add product "Banana Juice (1000ml)" to cart
    And User open Shopping Cart
    Then Shopping cart contains product "Banana Juice (1000ml)" with quantity "1"