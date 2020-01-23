package fr.polytech.unice.creadirama.mea;

import fr.polytech.unice.credirama.mea.controller.AccountAccessController;
import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.Contract;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import springfox.documentation.spring.web.json.Json;

import static junit.framework.TestCase.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class TestStepDefinition {

    @Autowired
    private MockMvc mockMvc;

    private ResultActions lastQuery;

    @Autowired
    private JsonElement jsonElement;

    @Given("{string} creates a new account with contract Wood")
    public void createsANewAccountWithContractWood(String owner) throws Exception {

        JsonObject requestClient = new JsonObject();
        requestClient.addProperty("owner", owner);
        jsonElement.getAsJsonObject().add("owner", requestClient);
        try {
            this.lastQuery = (ResultActions) this.mockMvc.perform(post("/clients")
                    .content(jsonElement.toString())
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andReturn();
            assertTrue( true);
        }
        catch(Error e) {
            System.out.println(e);
            throw e;
        }


    }

    @When("^(.+) verifies in the application his/her account$")
    public void validating_recuperation(String owner) {
        assertTrue(true);
    }

    @Then("^We receive the new account owned by (.+)$")
    public void validating_persistence(String owner) {
        assertTrue(true);
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

