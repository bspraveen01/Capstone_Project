Feature: User Registration

  Scenario: Successful user registration
    Given I am on the registration page
    When I enter first name "John" and last name "Doe"
    And I enter registration email "john.doe@test.com" and password "Test@123"
    And I submit the registration form
    Then I should see a confirmation message "Your registration completed"
