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

    public void addTransactionToAccount(long idTransaction, int accountFrom, int accountTo, double amount, double feeAmount) {
        MEAAddTransactionRequest request = new MEAAddTransactionRequest(idTransaction, accountFrom, accountTo, amount, feeAmount);
        this.restTemplate.postForObject(MEA_URL + "/access/operations", request, MEAAddTransactionRequest.class);
    }
}
