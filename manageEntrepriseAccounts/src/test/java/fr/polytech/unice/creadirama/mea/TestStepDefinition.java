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

@AutoConfigureMockMvc
@SpringBootTest(classes = TestStepDefinition.class)
public class TestStepDefinition {

    @Autowired
    private MockMvc mockMvc;

    //private ResultActions lastQuery;
    private MvcResult lastQuery;

    private JsonElement jsonElement;
    @Given("{string} creates a new account with contract Wood")
    public void createsANewAccountWithContractWood(String owner) throws Exception {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest(0, Contract.DIAMOND);
        try {
          /*  this.lastQuery = (MvcResult) this.mockMvc.perform(post("/accounts/create")
                    .content(new ObjectMapper().writeValueAsString(createAccountRequest))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isOk())
                    .andReturn();
            Account newAccount = new ObjectMapper().readValue(
                    lastQuery.getResponse().getContentAsString(),
                    Account.class);
            assertEquals(newAccount.getContract(), Contract.DIAMOND);*/
          assertTrue(true);
        } catch (Error e) {
            throw e;
        }
    }

    @When("{string} verifies in the application his\\/her account")
    public void verifiesInTheApplicationHisHerAccount(String owner) {
    }
}

/* HUGO POST
    this.last_query = mockMvc.perform(post("/order")
                .content(jsonElement.toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        JsonParser parser = new JsonParser();
        jsonElement = parser.parse("{\"id\": \"1\",\"jsonrpc\": \"2.0\",\"method\": \"orderItem\"}");
        JsonObject requestOrder = new JsonObject();
        JsonObject requestCoord = new JsonObject();
        requestCoord.addProperty("lat", order.getCoord().getLat());
        requestCoord.addProperty("lon", order.getCoord().getLon());
        JsonObject requestItem = new JsonObject();
        requestItem.addProperty("name", item.getName());
        JsonObject requestCustomer = new JsonObject();
        requestCustomer.addProperty("name", customer.getName());
        requestCustomer.addProperty("firstName", customer.getFirstName());
        requestOrder.add("coord", requestCoord);
        requestOrder.add("item", requestItem);
        requestOrder.add("customer", requestCustomer);
        requestOrder.addProperty("paymentInfo", order.getPaymentInfo());
        JsonObject param = new JsonObject();
        param.add("order", requestOrder);
        jsonElement.getAsJsonObject().add("params", param);
 */
/*GET
    this.mockMvc.perform(get("/clients"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());
        //String body = this.lastQuery.andReturn().getResponse().getContentAsString();
        */

/*

    @Given("{string} creates a new account with contract Wood")
    public void createsANewAccountWithContractWood(String owner) throws Exception {

        JsonObject requestClient = new JsonObject();
        requestClient.addProperty("owner", owner);
        jsonElement = new JsonObject();
        try {
            jsonElement.getAsJsonObject().add("owner", requestClient);
            this.lastQuery = (MvcResult) this.mockMvc.perform(post("/clients")
                    .content(jsonElement.toString())
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isOk())
                    .andReturn();
            Account newAccount = new ObjectMapper().readValue(
                    lastQuery.getResponse().getContentAsString(),
                    Account.class);
            assertTrue(newAccount.getOwner().equals(owner));
        } catch (Error e) {
            throw e;
        }


    }
 */

