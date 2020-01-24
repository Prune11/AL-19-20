package fr.polytech.unice.creadirama.mea;

import gherkin.deps.com.google.gson.JsonElement;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", plugin = {"pretty", "json:target/cucumber-report.json"})
public class CucumberTest {

}
