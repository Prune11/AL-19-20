package fr.polytech.unice.creadirama.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.unice.credirama.transaction.entities.Transaction;
import fr.polytech.unice.credirama.transaction.entities.TransactionType;
import fr.polytech.unice.credirama.transaction.entities.dto.TransactionRequest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;

import static junit.framework.TestCase.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpResponse.response;

public class TestStepDefinition extends AbstractStep {

    private Transaction transaction;

    private ClientAndServer clientServer;
    private MockServerClient mockServer;
    private HttpRequest request;

    @When("Client {int} pays {double} to merchant {int} with contract {string} by {string}")
    public void clientPaysToMerchant(int clientID, double amount, int merchantID,
                                     String contractName, String transactionTypeName) throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest(
                clientID,
                merchantID,
                amount,
                TransactionType.valueOf(transactionTypeName)
        );
        this.clientServer = startClientAndServer(8081);
        mockServer = new MockServerClient("127.0.0.1", 8081);
        request = new HttpRequest();
        request.withMethod("POST").withPath("/transaction/add");
        mockServer.when(request).respond(response().withStatusCode(200));

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
        Double totalFees = new ObjectMapper().readValue(
            getLastGetResponse().getContentAsString(),
            Double.class);

        assertEquals(this.transaction.getFeeAmount(), totalFees);
        mockServer.stop();
    }
}
