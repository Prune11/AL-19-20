package fr.polytech.unice.creadirama.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.unice.credirama.transaction.entities.*;

import fr.polytech.unice.credirama.transaction.entities.dto.TransactionRequest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static junit.framework.TestCase.*;

public class TestStepDefinition extends AbstractStep {

    private Transaction transaction;

    @When("Client {int} pays {double} to merchant {int} with contract {string} by {string}")
    public void clientPaysToMerchant(int clientID, double amount, int merchantID,
                                     String contractName, String transactionTypeName) throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest(amount,
                TransactionType.valueOf(transactionTypeName),
                clientID,
                merchantID,
                Contract.valueOf(contractName).getFee(),
                Contract.valueOf(contractName));
        post("/access/operations",
                new ObjectMapper().writeValueAsString(transactionRequest));
        this.transaction = new ObjectMapper().readValue(
                getLastPostResponse().getContentAsString(),
                Transaction.class);

        assertEquals(this.transaction.getAmount(), amount);
    }

    @Then("Merchant {int} has fees")
    public void merchantHasFees(int merchantID) throws Exception {
        get("/access/fees/" + merchantID);
        double totalFees = new ObjectMapper().readValue(
            getLastGetResponse().getContentAsString(),
            double.class);

        assertEquals(this.transaction.getFeeAmount(), totalFees);
    }
}
