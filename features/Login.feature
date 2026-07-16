Feature: Login with Valid Credentials

  @sanity @regression
  Scenario: Successful Login with Valid Credentials
    Given the user navigates to login page
    When user enters email as "madhu111@test.com" and password as "Test123"
    And the user clicks on the Login button
    Then the user should be redirected to the MyAccount Page
    
 # @regression
 # Scenario Outline: Login Data Driven
   # Given the user navigates to login page
    #When user enters email as "<email>" and password as "<password>"
    #And the user clicks on the Login button
    #Then the user should be redirected to the MyAccount Page

    #Examples: 
     # | email                	    | password |
      #| madhu111@test.com 	        | Test123  |
      #| pavanoltraining@gmail.com   | test@123 |
  