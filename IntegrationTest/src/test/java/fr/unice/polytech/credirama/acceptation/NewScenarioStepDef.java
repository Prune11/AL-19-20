package fr.unice.polytech.credirama.acceptation;

import fr.unice.polytech.credirama.dto.Client;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.HashMap;
import java.util.Map;

public class NewScenarioStepDef extends AbstractStep {

    private static final String TRANSACTION_URL = "http://localhost:8084";
    private static final String PRETTY_DUMP_URL = "http://localhost:8085";
    private static final String MEA_URL = "http://localhost:8081";
    private static final String ANALYSE_DATA_URL = "http://localhost:8088";

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private Map<String, Client> clients = new HashMap<>();

}
