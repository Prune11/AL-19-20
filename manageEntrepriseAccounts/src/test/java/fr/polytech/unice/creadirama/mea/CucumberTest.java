package fr.polytech.unice.creadirama.mea;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


@RunWith(Cucumber.class)
@AutoConfigureMockMvc
@CucumberOptions(features = "src/test/resources/features", plugin = {"pretty", "json:target/cucumber-report.json"})
public class CucumberTest {
}
