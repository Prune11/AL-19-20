Feature: Make a transaction

  Scenario: Transaction between two clients
    When Client 2 pays 5.00 to merchant 1 with contract "WOOD" by "CREDIT_CARD"
    Then Merchant 1 has fees

