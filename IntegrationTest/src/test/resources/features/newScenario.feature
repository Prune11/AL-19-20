Feature: New Scenario

  Scenario: Merchant make transaction and analyse his transactions data
    Given "Michel" is a client
    Given "Michel" has a account with a "WOOD" contract
    Given "Roger" is a client
    Given "Roger" has a account with a "WOOD" contract
    When "Michel" is receiving a "TRANSFER" transaction of 100.50 dkk in his account
    When "Michel" is making a "TRANSFER" transaction of 10.50 dkk from his account to "Roger" account
    When "Michel" is making a "TRANSFER" transaction of 10.50 dkk from his account to "Roger" account
    When "Michel" is making a "TRANSFER" transaction of 10.50 dkk from his account to "Roger" account
    When "Michel" is making a "TRANSFER" transaction of 10.50 dkk from his account to "Roger" account
    When "Michel" is making a "TRANSFER" transaction of 10.50 dkk from his account to "Roger" account
    When "Michel" is making a "TRANSFER" transaction of 10.50 dkk from his account to "Roger" account
    Then "Roger" has 3.18 transaction fees on his account
    Then "Roger" run a simulation for a all contracts
    Then "Roger" see the "DIAMOND" is better suited for him
    Then "Roger" ask the bank to change the contract of his account from "WOOD" to "IRON"
