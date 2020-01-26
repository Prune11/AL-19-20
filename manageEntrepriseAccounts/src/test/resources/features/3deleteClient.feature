Feature: Delete client

  Scenario: Roger quits the bank
    Given "Roger" with id 1 wants to quit
    Then We eliminate his profile
