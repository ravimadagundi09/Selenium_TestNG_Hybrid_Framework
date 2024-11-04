
Feature: Purchase the Order from Ecommerce Website

#prerequrisites(like beforeMethod)
Background:
Given I landed on Ecommerce Page

@Regression
Scenario Outline: Positive Test of Submitting the order

  Given Logged in with username <name> and password <password>
  When I add product <productName> to cart
  And Checkout <productName> and submit the order
  Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
  
  Examples:
  | name                     | password |productName |
  |ravishopping09@gmail.com  |Smile@123 |ZARA COAT 3 |
  


  