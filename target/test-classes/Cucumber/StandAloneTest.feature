
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommers Page 

  @Regression
  Scenario Outline: Positive Test of StandAloneTest
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name 								 | 		password 		|productName|
      | halukfaruk@gmail.com |    Haluk123 		|ZARA COAT 3|
      
      
      

       
       