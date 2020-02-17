package fr.unice.polytech.credirama.acceptation;

import fr.unice.polytech.credirama.dto.*;
import fr.unice.polytech.credirama.dto.contract.Contract;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PocScenarioStepDef extends AbstractStep {

    private static final String TRANSACTION_URL = "http://localhost:8084";
    private static final String PRETTY_DUMP_URL = "http://localhost:8085";
    private static final String MEA_URL = "http://localhost:8081";
    private static final String ANALYSE_DATA_URL = "http://localhost:8088";

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private Map<String, Client> clients = new HashMap<>();

    @Given("^\"([^\"]*)\" is a client$")
    public void isAClient(String client) {
        Client clientO = restTemplate.postForObject(MEA_URL + "/clients", client, Client.class);
        clients.put(client, clientO);
    }

    @Given("^\"([^\"]*)\" has a account with a \"([^\"]*)\" contract$")
    public void hasAAccountWithAContract(String client, String contract) {
        Account account = restTemplate.postForObject(MEA_URL + "/accounts/create", new CreateAccountRequest(Contract.valueOf(contract), clients.get(client).getId()), Account.class);
        clients.get(client).getAccountList().add(account);
    }

    @When("^\"([^\"]*)\" is making a \"([^\"]*)\" transaction of (\\d+) dkk from his account to \"([^\"]*)\" account$")
    public void isMakingATransactionOfDkkFromHisAccountToAccount(String client, String transactionType, int amount, String receiver) {
        AddTransactionRequest request = new AddTransactionRequest(clients.get(client).getAccountList().get(0).getId(), clients.get(receiver).getAccountList().get(0).getId(), amount, TransactionType.valueOf(transactionType));
        Transaction something = restTemplate.postForObject(TRANSACTION_URL + "/access/operations", request, Transaction.class);
    }

    @When("^\"([^\"]*)\" is receiving a \"([^\"]*)\" transaction of (\\d+) dkk in his account$")
    public void isReceivingATransactionOfDkkInHisAccount(String client, String transactionType, int amount) {
        AddTransactionRequest request = new AddTransactionRequest(0, clients.get(client).getAccountList().get(0).getId(), amount, TransactionType.valueOf(transactionType));
        Transaction something = restTemplate.postForObject(TRANSACTION_URL + "/access/operations", request, Transaction.class);
    }

    @Then("^\"([^\"]*)\" has (\\d+) transaction fees on his account$")
    public void hasTransactionFeesOnHisAccount(String client, int feeAmount) {
        double fee = restTemplate.getForObject(TRANSACTION_URL + "/access/fees/" + clients.get(client).getAccountList().get(0).getId(), Double.class);
        assertEquals(feeAmount, fee, 0.5);
    }

    @Then("^\"([^\"]*)\" ask the bank to change the contract of his account from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void askTheBankToChangeTheContractOfHisAccountFromTo(String client, String oldContract, String newContract) {
        Account account = restTemplate.postForObject(MEA_URL + "/accounts/update/" + clients.get(client).getAccountList().get(0).getId() + "/contract", Contract.valueOf(newContract), Account.class);
        assertNotEquals(Contract.valueOf(oldContract), account.getContract());
        assertEquals(Contract.valueOf(newContract), account.getContract());
    }

    @Then("^the bank is dumping the current state of the system$")
    public void theBankIsDumpingTheCurrentStateOfTheSystem() {
        String prettyDump = restTemplate.getForObject(PRETTY_DUMP_URL + "/prettyDump", String.class);
        System.out.println(prettyDump);
        assertTrue(true);
    }

}
