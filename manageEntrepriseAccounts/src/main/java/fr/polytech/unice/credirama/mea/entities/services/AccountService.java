package fr.polytech.unice.credirama.mea.entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AccountService {

    @Autowired
    RestTemplate restTemplate;
    private static final String TRANSACTION_URL = "http://localhost:8084";

    public int getNumberOfTransactionToday(int userId) {
        return this.restTemplate.getForObject(TRANSACTION_URL + "/access/operations/" + userId + "/today", int.class);
    }
}
