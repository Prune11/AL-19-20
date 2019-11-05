package fr.unice.polytech.credirama.marchant.cli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AccessAccountService {

    private static final String ACCESS_ACCOUNT_URL = "";

    @Autowired
    private RestTemplate restTemplate;

    public boolean withdrawMoney(String accountKey, double ammount) {
        return true;
    }

    public Double showBalance(String accountKey) {
        return this.restTemplate.getForObject(ACCESS_ACCOUNT_URL + "/balance/" + accountKey, Double.class);
    }

    public List getOperations(String accountKey) {
        return this.restTemplate.getForObject(ACCESS_ACCOUNT_URL + "/operations/" + accountKey, List.class);
    }

}
