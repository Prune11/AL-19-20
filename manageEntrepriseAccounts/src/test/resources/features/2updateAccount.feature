Feature: Update account 
  
  Scenario: Updating owner and contract from account 1
    Given Account with id 1
    When We change the owner to "Paul"
    Then We set a new "DIAMOND" contract