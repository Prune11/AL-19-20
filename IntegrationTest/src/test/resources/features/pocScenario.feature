Feature: PoC Scenario

  Scenario: Transaction between two clients
    Given "Michel" is a client
    Given "Michel" has a account with a "WOOD" contract
    Given "Roger" is a client
    Given "Roger" has a account with a "WOOD" contract
    When "Roger" is making a "TRANSFER" transaction of 5 dkk from his account 2 to "Michel" account 1
    When "Roger" is receiving a "TRANSFER" transaction of 5 dkk in his account 2
    Then "Roger" has 50 transaction fees on his account 2
    Then "Roger" ask the bank to change the contract of his account 2 from "WOOD" to "STONE"
    When "Roger" is making a "TRANSFER" transaction of 5 dkk from his account 2 to "Michel" account 1
    Then "Roger" has 52 transaction fees on his account 2
    Then the bank is dumping the current state of the system