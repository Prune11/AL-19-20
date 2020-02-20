package fr.unice.polytech.credirama.acceptation;

import fr.unice.polytech.credirama.dto.*;
import fr.unice.polytech.credirama.dto.contract.Contract;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ScenarioStepDef extends AbstractStep {

    private static final String TRANSACTION_URL = "http://localhost:8084";
    private static final String PRETTY_DUMP_URL = "http://localhost:8085";
    private static final String MEA_URL = "http://localhost:8081";
    private static final String ANALYSE_DATA_URL = "http://localhost:8088";

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private Map<String, Client> clients = new HashMap<>();
    
    private SimulationResponseDTO response = new SimulationResponseDTO();

    private Scanner sc = new Scanner(System.in);

    @Given("{string} is a client")
    public void isAClient(String client) {
//        sc.nextLine();
        Client clientO = restTemplate.postForObject(MEA_URL + "/clients", client, Client.class);
        clients.put(client, clientO);
    }

    @Given("{string} has a account with a {string} contract")
    public void hasAAccountWithAContract(String client, String contract) {
//        sc.nextLine();
        Account account = restTemplate.postForObject(MEA_URL + "/accounts/create", new CreateAccountRequest(Contract.valueOf(contract), clients.get(client).getId()), Account.class);
        clients.get(client).getAccountList().add(account);
    }

    @When("{string} is making a {string} transaction of {double} dkk from his account to {string} account")
    public void isMakingATransactionOfDkkFromHisAccountToAccount(String client, String transactionType, double amount, String receiver) {
//        sc.nextLine();
        AddTransactionRequest request = new AddTransactionRequest(clients.get(client).getAccountList().get(0).getId(), clients.get(receiver).getAccountList().get(0).getId(), amount, TransactionType.valueOf(transactionType));
        Transaction something = restTemplate.postForObject(TRANSACTION_URL + "/access/operations", request, Transaction.class);
    }

    @When("{string} is receiving a {string} transaction of {double} dkk in his account")
    public void isReceivingATransactionOfDkkInHisAccount(String client, String transactionType, double amount) {
//        sc.nextLine();
        AddTransactionRequest request = new AddTransactionRequest(0, clients.get(client).getAccountList().get(0).getId(), amount, TransactionType.valueOf(transactionType));
        Transaction something = restTemplate.postForObject(TRANSACTION_URL + "/access/operations", request, Transaction.class);
    }

    @Then("{string} has {double} transaction fees on his account")
    public void hasTransactionFeesOnHisAccount(String client, double feeAmount) {
//        sc.nextLine();
        double fee = restTemplate.getForObject(TRANSACTION_URL + "/access/fees/" + clients.get(client).getAccountList().get(0).getId(), Double.class);
        assertEquals(feeAmount, fee, 0.005);
    }

    @Then("{string} ask the bank to change the contract of his account from {string} to {string}")
    public void askTheBankToChangeTheContractOfHisAccountFromTo(String client, String oldContract, String newContract) {
//        sc.nextLine();
        Account account = restTemplate.postForObject(MEA_URL + "/accounts/update/" + clients.get(client).getAccountList().get(0).getId() + "/contract", Contract.valueOf(newContract), Account.class);
        assertNotEquals(Contract.valueOf(oldContract), account.getContract());
        assertEquals(Contract.valueOf(newContract), account.getContract());
    }

    @Then("the bank is dumping the current state of the system")
    public void theBankIsDumpingTheCurrentStateOfTheSystem() {
//        sc.nextLine();
        String prettyDump = restTemplate.getForObject(PRETTY_DUMP_URL + "/prettyDump", String.class);
        System.out.println(prettyDump);
        assertTrue(true);
    }

    @Then("{string} run a simulation for a all contracts")
    public void runASimulationForAAllContracts(String client) {
//        sc.nextLine();
        FeeBtw2DateRequest request = new FeeBtw2DateRequest(DateTime.now().toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")), DateTime.now().toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")), clients.get(client).getAccountList().get(0).getId());
        response = restTemplate.postForObject(ANALYSE_DATA_URL + "/analyse/simulation/json", request, SimulationResponseDTO.class);
        assertNotNull(response);
    }

    @Then("{string} see the {string} is better suited for him")
    public void seeTheIsBetterSuitedForHim(String client, String contract) {
//        sc.nextLine();
        System.out.println(response.toString());
        BestContract bestContract = new BestContract();

        response.getResponse().forEach((s, simulationDTO) -> {
            if (bestContract.getBestFee() == 0.0) {
                bestContract.setBestContract(s);
                bestContract.setBestFee(simulationDTO.getTotalSum());
            }
            if (simulationDTO.getTotalSum() < bestContract.getBestFee()) {
                bestContract.setBestFee(simulationDTO.getTotalSum());
                bestContract.setBestContract(s);
            }
        });

        assertEquals(contract, bestContract.getBestContract());
    }


}
