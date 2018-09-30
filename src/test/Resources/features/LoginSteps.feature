Feature: Spree Login Functionality
  Scenario: User with valid login credentials
    Given Not a validated user
    When User browse to the site
    Then Spree Homepage should display
    When User clicks on the login link
    Then Spree Login page should display
    When User enter user email as "shiftqa1@gmail.com"
    And User enter user password as "shiftedtech"
    And User clicks on login button
    Then Spree Home page should display
    And Login success message should display