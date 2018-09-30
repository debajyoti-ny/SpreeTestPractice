Feature: Spree login functionality

  @debug
  Scenario: 1. User with valid login credentials
    Given Not a validated user
    When User browse to the site
    Then Spree Homepage should display
    When User clicks on the login link
    Then Spree Login page should display
    When User enter user email as "shiftqa1@gmail.com"
    And User enter user password as "shiftedtech"
    And User clicks on login button
    Then Spree Homepage should display
    And Login success message should display

    @debug
  Scenario Outline: 2.Valid user with valid password
    Given Not a validated user
    When User browse to the site
    Then Spree Homepage should display
    When User clicks on the login link
    Then Spree Login page should display
    When User enter user email as "<EmailAddress>"
    And User enter user password as "<Password>"
    And User clicks on login button
    Then Spree Homepage should display
    And Login success message should display
    Examples:
      |EmailAddress             |Password    |
      |debajyoti1990@gmail.com  |deb0119     |
      |shiftqa1@gmail.com       |shiftedtech |

   @debug
  Scenario: 3: Product list validation
    Given  Not a validated search box
    When User browse to the site
    Then Spree Homepage should display
    When User type the product name "Ruby" in the search box
    And Clicks the search button
    Then following products should display
      |Ruby on Rails Tote xx| Ruby on Rails Bag| Ruby on Rails Baseball Jersey| Ruby on Rails Jr. Spaghetti|Ruby on Rails Ringer T-Shirt| Ruby Baseball Jersey| Ruby on Rails Mug| Ruby on Rails Stein|

