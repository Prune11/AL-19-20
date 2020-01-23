package fr.polytech.unice.credirama.transaction.service;

import fr.polytech.unice.credirama.transaction.entities.MEAAddTransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class EnterpriseAccountsService {

    private static final String MEA_URL = "http://localhost:8081";

    @Autowired
    private RestTemplate restTemplate;

    public Double addTransactionToAccount(long idTransaction, int accountFrom, int accountTo, double amount) {
        MEAAddTransactionRequest request = new MEAAddTransactionRequest(idTransaction, accountFrom, accountTo, amount);
        //System.out.println("\n" + request.toString() + "\n");
        return this.restTemplate.postForObject(MEA_URL + "/transaction/add", request, Double.class);
    }
}
