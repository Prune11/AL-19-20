package fr.polytech.unice.creadirama.mea;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static junit.framework.TestCase.assertTrue;

public class TestStepDefinition {

    @Given("^A new Test$")
    public void anotherTest() {
        assertTrue(true);
    }

    @When("^Test is executed$")
    public void test() {
        assertTrue(true);
    }

    @Then("^Test is test$")
    public void lastTest() {
        assertTrue(true);
    }
}
