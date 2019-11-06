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
public class CrediramaService {

    private static final String CREDIRAMA_URL = "http://localhost:8081";
//    private static final String CREDIRAMA_URL = "https://credirama.free.beeceptor.com";

    @Autowired
    private RestTemplate restTemplate;

    /************ CREATE *************/

    public Account createAccount(int clientId, Contract contract) {
        CreateAccountRequest request = new CreateAccountRequest(clientId, contract);
        return this.restTemplate.postForObject(CREDIRAMA_URL + "/accounts/create/", request, Account.class);
    }

    public Client createClient(String clientName) {
        return this.restTemplate.postForObject(CREDIRAMA_URL + "/clients/", clientName, Client.class);
    }

    /************ GET *************/

    public List getAccounts() {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/accounts", List.class);
    }

    public Account getAccount(int clientId) {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/accounts/account/" + clientId, Account.class);
    }

    public List getAllClient() {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/clients/", List.class);
    }

    public Client getClient(int clientId) {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/clients/" + clientId, Client.class);
    }

    public Double showBalance(int accountKey) {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/access/balance/" + accountKey, Double.class);
    }

    public List getTransaction(int accountKey) {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/access/operations/" + accountKey, List.class);
    }

    public Contract getContract(int accountKey) {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/access/contract/" + accountKey, Contract.class);
    }

    public boolean makeTransaction(int originAccountKey, int destinationAccountKey, double amount) {
        this.restTemplate.getForObject(CREDIRAMA_URL + "/access/operations/from/" + originAccountKey + "/to/" + destinationAccountKey + "/amount/" + amount, Boolean.class);
        return true;
    }

    /************ DELETE *************/

    public void deleteAccount(int accountId) {
        this.restTemplate.delete(CREDIRAMA_URL + "/accounts/delete/" + accountId);
    }

    public void deletetAllAccount() {
        this.restTemplate.delete(CREDIRAMA_URL + "/accounts/delete");
    }

    public void deleteClient(int clientId) {
        this.restTemplate.delete(CREDIRAMA_URL + "/clients/" + clientId);
    }

    public void deleteAllClient() {
        this.restTemplate.delete(CREDIRAMA_URL + "/clients");
    }

    /************ UPDATE *************/


    public Account updateContract(int accountId, Contract contract) {
        return this.restTemplate.postForObject(CREDIRAMA_URL + "/accounts/update/" + accountId + "/contract", contract, Account.class);
    }

    public Account updateOwner(int accountId, Client client) {
        return this.restTemplate.postForObject(CREDIRAMA_URL + "/accounts/update/" + accountId + "/owner", client, Account.class);
    }

    /************ PRETTY DUMP *************/

    public String prettyDump() {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/prettyDump", String.class);
    }
}
