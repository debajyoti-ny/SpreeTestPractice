Feature: Spree login functionality

  Background:
    Given Not a validated user
    When User browse to the site
    Then Spree Homepage should display
    When User clicks on the login link
    Then Spree Login page should display

  Scenario: 1. User with valid login credentials
    When User enter user email as "shiftqa1@gmail.com"
    And User enter user password as "shiftedtech"
    And User clicks on login button
    Then Spree Home page should display
    And Login success message should display

  @debug
  Scenario: 2. User with invalid login credentials
    When User enter user email as "shiftqa1@gmail.com"
    And User enter user password as "shiftedtechXX"
    And User clicks on login button
    Then Login error message should display

  Scenario Outline: 3.Valid user with valid password
    When User enter user email as "<EmailAddress>"
    And User enter user password as "<Password>"
    And User clicks on login button
    Then Spree Home page should display
    And Login success message should display
    Examples:
      |EmailAddress             |Password    |
      |debajyoti1990@gmail.com  |deb0119     |
      |shiftqa1@gmail.com       |shiftedtech |