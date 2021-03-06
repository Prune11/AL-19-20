package fr.unice.polytech.credirama.bank.cli.service;

import fr.unice.polytech.credirama.bank.cli.entities.*;
import fr.unice.polytech.credirama.bank.cli.entities.dto.CreateAccountRequest;
import fr.unice.polytech.credirama.bank.cli.entities.dto.TransactionRequest;
import fr.unice.polytech.credirama.bank.cli.entities.dto.analyse.FeeBtw2DateRequestDTO;
import fr.unice.polytech.credirama.bank.cli.entities.dto.analyse.SimulationDTO;
import fr.unice.polytech.credirama.bank.cli.entities.dto.analyse.SimulationResponseDTO;
import fr.unice.polytech.credirama.bank.cli.entities.dto.analyse.contract.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CrediramaService {

    private static final String CREDIRAMA_URL = "http://localhost:8081";
    private static final String TRANSACTION_URL = "http://localhost:8084";
    private static final String PRETTY_DUMP_URL = "http://localhost:8085";
    private static final String ANALYSE_URL = "http://localhost:8088";

    @Autowired
    private RestTemplate restTemplate;

    /************ CREATE *************/

    public Account createAccount(int clientId, Contract contract) {
        CreateAccountRequest request = new CreateAccountRequest(clientId, contract);
        return this.restTemplate.postForObject(CREDIRAMA_URL + "/accounts/create", request, Account.class);
    }

    public Client createClient(String clientName) {
        return this.restTemplate.postForObject(CREDIRAMA_URL + "/clients", clientName, Client.class);
    }

    public Transaction makeTransaction(int originAccountKey, int destinationAccountKey, double amount, TransactionType transactionType) {
        TransactionRequest request = new TransactionRequest(amount, transactionType, originAccountKey, destinationAccountKey);
        return this.restTemplate.postForObject(TRANSACTION_URL + "/access/operations", request, Transaction.class);
    }

    /************ GET *************/

    public List getAccounts() {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/accounts", List.class);
    }

    public Account getAccount(int clientId) {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/accounts/account/" + clientId, Account.class);
    }

    public List getAllClient() {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/clients", List.class);
    }

    public Client getClient(int clientId) {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/clients/" + clientId, Client.class);
    }

    public Double showBalance(int accountKey) {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/access/balance/" + accountKey, Double.class);
    }

    public List getTransaction(int accountKey) {
        return this.restTemplate.getForObject(TRANSACTION_URL + "/access/operations/" + accountKey, List.class);
    }

    public Contract getContract(int accountKey) {
        return this.restTemplate.getForObject(CREDIRAMA_URL + "/access/contract/" + accountKey, Contract.class);
    }

    public Double getTotalFees(int accountId) {
        return this.restTemplate.getForObject(TRANSACTION_URL + "/access/fees/" + accountId, Double.class);
    }

    public SimulationDTO getFeeBtw2Date(FeeBtw2DateRequestDTO requestDTO) {
        return this.restTemplate.postForObject(ANALYSE_URL + "/analyse/fees/btw/day/json", requestDTO, SimulationDTO.class);
    }

    public SimulationResponseDTO getSimulation(FeeBtw2DateRequestDTO requestDTO) {
        return this.restTemplate.postForObject(ANALYSE_URL + "/analyse/simulation/json", requestDTO, SimulationResponseDTO.class);
    }

    /************ DELETE *************/

    public void deleteAccount(int accountId) {
        this.restTemplate.delete(CREDIRAMA_URL + "/accounts/delete/" + accountId);
    }

    public void deleteAllAccount() {
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

    public Account updateOwner(int accountId, int clientId) {
        return this.restTemplate.postForObject(CREDIRAMA_URL + "/accounts/update/" + accountId + "/owner", clientId, Account.class);
    }

    /************ PRETTY DUMP *************/


    public String prettyDump() {
        return this.restTemplate.getForObject(PRETTY_DUMP_URL + "/prettyDump", String.class);
    }
}
