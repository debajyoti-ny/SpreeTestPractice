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

  Scenario: 2. User with invalid login credentials
    When User enter user email as "shiftqa1@gmail.com"
    And User enter user password as "shiftedtechXX"
    And User clicks on login button
    Then Login error message should display

  Scenario: 3. User with invalid login credentials
    When User login with invalid email and password
    |shiftqa01@gmail.com      |shiftedtechXX|
    |debajyoti1990@gmail.com  |deb0119XX|
    Then Login error message should display

  Scenario Outline: 4.Valid user with valid password
    When User login with invalid email and password
      |<EmailAddress>|<Password>|
    Then Login error message should display
    Examples:
      |EmailAddress             |Password     |
      |shiftqa01@gmail.com      |shiftedtechXX|
      |debajyoti1990@gmail.com  |deb0119XX    |

  @debug
  Scenario: 5. Valid user with valid password
    When User login with email and password
      |Key      |Value             |
      |EMAIL    |shiftqa1@gmail.com|
      |PASSWORD |shiftedtech       |
    Then Spree Home page should display
    And Login success message should display

  @debug
  Scenario: 6. Valid username with missing password
    When User login with email and password
      |Key      |Value             |
      |PASSWORD |shiftedtech       |
    Then Login error message should display

  @debug
  Scenario: 7. Missing username with valid password
    When User login with email and password
      |Key      |Value             |
      |EMAIL    |shiftqa1@gmail.com|
    Then Login error message should display


  @debug
  Scenario: 8. Missing username with missing password
    When User login with email and password
      |Key      |Value             |
    Then Login error message should display

