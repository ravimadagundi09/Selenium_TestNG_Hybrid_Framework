Feature: Error Validation


@ErrorValidation
Scenario Outline: Login with invalid Creadientials
  
  Given I landed on Ecommerce Page
  When Logged in with username <name> and password <password>
  Then "Incorrect email or password." error message is displayed
  
  Examples:
  | name                     | password |
  |ravishopping09@gmail.com  |Smile@3 |
  