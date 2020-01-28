package fr.polytech.unice.creadirama.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.Client;
import fr.polytech.unice.credirama.mea.entities.Contract;
import fr.polytech.unice.credirama.mea.entities.dto.CreateAccountRequest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static junit.framework.TestCase.*;

public class TestStepDefinition extends AbstractStep {

    private Client client;
    private Account newAccount;

    @Given("{string} creates a new account with contract {string}")
    public void createsANewAccountWithContract(String owner, String contract) throws Exception{
        post("/clients", owner);
        this.client = new ObjectMapper().readValue(
                getLastPostResponse().getContentAsString(),
                Client.class);
        Contract newContract = Contract.valueOf(contract);

        CreateAccountRequest createAccountRequest = new CreateAccountRequest(this.client.getId(), newContract);
        post("/accounts/create", new ObjectMapper().writeValueAsString(createAccountRequest));
        this.newAccount = new ObjectMapper().readValue(
                getLastPostResponse().getContentAsString(),
                Account.class);

        assertEquals(this.newAccount.getContract(), newContract);
    }

    @When("{string} verifies in the application his/her account")
    public void verifiesInTheApplicationHisHerAccount(String owner) throws  Exception{
        get("/clients/" + this.client.getId());
        Client returnedClient = new ObjectMapper().readValue(
                getLastGetResponse().getContentAsString(),
                Client.class
        );

        if (returnedClient.getId() == this.client.getId()) {
            //We check we have the same client so that the assertion is not a coincidence
            assertEquals(owner, returnedClient.getName());
        }
    }

    @Then("He/She checks his/her balance and his/her new contract")
    public void heChecksHisBalance() throws Exception {
        get("/access/balance/" + this.newAccount.getId());
        double currentBalance = new ObjectMapper().readValue(
                getLastGetResponse().getContentAsString(),
                double.class);
        get("/access/contract/" + this.newAccount.getId());
        Contract currentContract = new ObjectMapper().readValue(
                getLastGetResponse().getContentAsString(),
                Contract.class
        );
        if (currentContract.equals(this.newAccount.getContract())) {
            assertEquals(this.newAccount.getBalance(), currentBalance);
        }
    }

    @Given("Account with id {int}")
    public void latestCreateAccount(int accountID) throws Exception {
        get("/accounts/account/" + accountID);
        Account returnedAccount = new ObjectMapper().readValue(
                getLastGetResponse().getContentAsString(),
                Account.class
        );
        assertEquals(accountID, returnedAccount.getId());
        this.newAccount = returnedAccount;
        this.client = this.newAccount.getOwner();
    }

    @When("We change the owner to {string}")
    public void weChangeTheOwnerTo(String newOwnerName) throws Exception {
        post("/clients", newOwnerName);
        Client newClient = new ObjectMapper().readValue(
                getLastPostResponse().getContentAsString(),
                Client.class);

        post("/accounts/update/" + this.newAccount.getId() + "/owner",
                new ObjectMapper().writeValueAsString(newClient.getId()));
        this.newAccount = new ObjectMapper().readValue(
                getLastPostResponse().getContentAsString(),
                Account.class);

        assertEquals(newOwnerName, this.newAccount.getOwner().getName());
    }

    @Then("We set a new {string} contract")
    public void weSetANewContract(String newContractName) throws Exception {
        Contract newContract = Contract.valueOf(newContractName);
        post("/accounts/update/" + this.newAccount.getId() + "/contract" ,
                new ObjectMapper().writeValueAsString(newContract));
        this.newAccount = new ObjectMapper().readValue(
                getLastPostResponse().getContentAsString(),
                Account.class);
        assertEquals(newContract, this.newAccount.getContract());
    }

    @Given("{string} with id {int} wants to quit")
    public void accountWithIdWantsToQuit(String owner, int idClient) throws Exception {
        get("/clients/" + idClient);
        Client returnedClient = new ObjectMapper().readValue(
                getLastGetResponse().getContentAsString(),
                Client.class
        );
        assertEquals(owner, returnedClient.getName());
        this.client = returnedClient;
    }

    @Then("We eliminate his profile")
    public void weEliminateHisProfile() throws Exception {
        delete("/clients/" + this.client.getId());
        try {
            get("/clients/" + this.client.getId());
        }
        catch (Exception e) {
            assertNotNull(e);
            post("/clients", this.client.getName());
            this.client = new ObjectMapper().readValue(
                    getLastPostResponse().getContentAsString(),
                    Client.class);
        }
    }

    @Given("{string} with id {int} wants to eliminate his account with id {int}")
    public void withIdWantsToEliminateHisAccountWithId(String owner, int idClient, int idAccount) throws Exception {
        get("/accounts/account/" + idAccount);
        Account returnedAccount = new ObjectMapper().readValue(
                getLastGetResponse().getContentAsString(),
                Account.class
        );
        if (owner.equals(returnedAccount.getOwner().getName())) {
            assertEquals(idClient, returnedAccount.getOwner().getId());
            this.newAccount = returnedAccount;
            this.client = newAccount.getOwner();
        }
    }

    @Then("We eliminate his account")
    public void weEliminateHisAccount() throws Exception{
        delete("/accounts/delete/" + this.newAccount.getId());
        try {
            get("/accounts/" + this.newAccount.getId());
        }
        catch (Exception e) {
            assertNotNull(e);
            CreateAccountRequest createAccountRequest = new CreateAccountRequest(this.client.getId(), Contract.valueOf("WOOD"));
            post("/accounts/create", new ObjectMapper().writeValueAsString(createAccountRequest));
            this.newAccount = new ObjectMapper().readValue(
                    getLastPostResponse().getContentAsString(),
                    Account.class);
        }
    }
}
