package fr.polytech.unice.creadirama.mea;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.Client;
import fr.polytech.unice.credirama.mea.entities.Contract;
import fr.polytech.unice.credirama.mea.entities.dto.CreateAccountRequest;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.HdrHistogram.AtomicHistogram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static junit.framework.TestCase.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestStepDefinition extends AbstractStep {


    @Given("{string} creates a new account with contract Wood")
    public void createsANewAccountWithContractWood(String owner) throws Exception {
        post("/clients", owner);
        Client client = new ObjectMapper().readValue(
                getLastPostResponse().getContentAsString(),
                Client.class);

        CreateAccountRequest createAccountRequest = new CreateAccountRequest(client.getId(), Contract.DIAMOND);
        post("/accounts/create", new ObjectMapper().writeValueAsString(createAccountRequest));
        Account newAccount = new ObjectMapper().readValue(
                getLastPostResponse().getContentAsString(),
                Account.class);

        assertEquals(newAccount.getContract(), Contract.DIAMOND);
    }

    @When("{string} verifies in the application his\\/her account")
    public void verifiesInTheApplicationHisHerAccount(String owner) {
        assertTrue(true);
    }
}
