Feature: Create client and account

  Scenario: Roger as new client and account with Wood contract
    Given "Roger" creates a new account with contract "WOOD"
    When "Roger" verifies in the application his account
    Then He checks his balance and his new contract

