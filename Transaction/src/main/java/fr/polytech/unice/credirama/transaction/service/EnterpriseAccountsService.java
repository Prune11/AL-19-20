package fr.polytech.unice.credirama.transaction.service;

import fr.polytech.unice.credirama.transaction.entities.MEAAddTransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
public class EnterpriseAccountsService {

    private static final String MEA_URL = "http://localhost:8081";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    public Double addTransactionToAccount(long idTransaction, int accountFrom, int accountTo, double amount) {
        String meaURL = env.getProperty("MEA");
        System.out.println(meaURL);
        if (meaURL == null || meaURL.equals("")) meaURL = MEA_URL;
        MEAAddTransactionRequest request = new MEAAddTransactionRequest(idTransaction, accountFrom, accountTo, new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue());
        return this.restTemplate.postForObject(meaURL + "/transaction/add", request, Double.class);
    }
}
