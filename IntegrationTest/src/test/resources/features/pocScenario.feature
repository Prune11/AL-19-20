Feature: PoC Scenario

  Scenario: Transaction between two clients
    Given "Michel" is a client
    Given "Michel" has a account with a "WOOD" contract
    Given "Roger" is a client
    Given "Roger" has a account with a "WOOD" contract
    When "Roger" is making a "TRANSFER" transaction of 5 dkk from his account to "Michel" account
    When "Roger" is receiving a "TRANSFER" transaction of 5 dkk in his account
    Then "Roger" has 0 transaction fees on his account
    Then "Roger" ask the bank to change the contract of his account from "WOOD" to "STONE"
    When "Roger" is making a "TRANSFER" transaction of 5 dkk from his account to "Michel" account
    Then "Roger" has 0 transaction fees on his account
    Then the bank is dumping the current state of the system