package fr.unice.polytech.credirama.bank.cli.service;

import fr.unice.polytech.credirama.bank.cli.entities.Account;
import fr.unice.polytech.credirama.bank.cli.entities.Client;
import fr.unice.polytech.credirama.bank.cli.entities.Contract;
import fr.unice.polytech.credirama.bank.cli.entities.dto.CreateAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AccountAdministrationService {

    private static final String CREDIRAMA_URL = "http://localhost:8081";

    @Autowired
    private RestTemplate restTemplate;

    public Account createAccount(int clientId, Contract contract) {
        CreateAccountRequest request = new CreateAccountRequest(clientId, contract);
        return this.restTemplate.postForObject(CREDIRAMA_URL + "/accounts/create/", request, Account.class);
    }

    public Account getAccount(int clientId) {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/accounts/" + clientId, Account.class);
    }

    public List<Account> getAccounts() {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/accounts", List.class);
    }

    public Client createClient(String clientName) {
        return this.restTemplate.postForObject(CREDIRAMA_URL + "/clients/create", clientName, Client.class);
    }

    public Account updateContract(int accountId, Contract contract) {
        return this.restTemplate.postForObject(CREDIRAMA_URL + "accounts/" + accountId + "/contract", contract, Account.class);
    }
}
