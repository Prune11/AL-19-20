package fr.unice.polytech.credirama.acceptation;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class PocScenarioStepDef extends AbstractStep {

    private static final String TRANSACTION_URL = "http://localhost:8081";
    private static final String PRETTY_DUMP_URL = "http://localhost:8081";
    private static final String MEA_URL = "http://localhost:8081";
    private static final String ANALYSE_DAA_URL = "http://localhost:8081";

    @Given("^\"([^\"]*)\" is a client$")
    public void isAClient(String client) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @Given("^\"([^\"]*)\" has a account with a \"([^\"]*)\" contract$")
    public void hasAAccountWithAContract(String client, String contract) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @When("^\"([^\"]*)\" is making a \"([^\"]*)\" transaction of (\\d+) dkk from his account (\\d+) to \"([^\"]*)\" account (\\d+)$")
    public void isMakingATransactionOfDkkFromHisAcountToAccount(String client, String transactionType,
                                                                int amount, int idFrom, String receiver, int idTo) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @When("^\"([^\"]*)\" is receiving a \"([^\"]*)\" transaction of (\\d+) dkk in his account (\\d+)$")
    public void isReceivingATransactionOfDkkInHisAccount(String client, String transactionType, int amount, int idTo) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @Then("^\"([^\"]*)\" has (\\d+) transaction fees on his account (\\d+)$")
    public void hasTransactionFeesOnHisAccount(String client, int feeAmount, int idAccount) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @Then("^\"([^\"]*)\" ask the bank to change the contract of his account (\\d+) from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void askTheBankToChangeTheContractOfHisAccountFromTo(String client, int idAccount, String oldContract, String newContract) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @Then("^the bank is dumping the current state of the system$")
    public void theBankIsDumpingTheCurrentStateOfTheSystem() {
        assertTrue(true);
    }

}
