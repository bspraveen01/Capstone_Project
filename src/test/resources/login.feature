Feature: User Login

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter login email "john.doe@test.com" and password "Test@123"
    And I submit the login form
    Then I should be logged in successfully
