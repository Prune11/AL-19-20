package fr.unice.polytech.credirama.merchant.cli.service;

import fr.unice.polytech.credirama.merchant.cli.entity.Contract;
import fr.unice.polytech.credirama.merchant.cli.entity.Transaction;
import fr.unice.polytech.credirama.merchant.cli.entity.TransactionType;
import fr.unice.polytech.credirama.merchant.cli.entity.dto.PrettyDumpResponse;
import fr.unice.polytech.credirama.merchant.cli.entity.dto.TotalFeeResponse;
import fr.unice.polytech.credirama.merchant.cli.entity.dto.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CrediramaService {

    private static final String MEA_URL = "http://localhost:8081";
    private static final String TRANSACTION_URL = "http://localhost:8084";
    private static final String PRETTY_DUMP_URL = "http://localhost:8085";

    @Autowired
    private RestTemplate restTemplate;

    /************ CREATE *********/

    public Transaction makeTransaction(int originAccountKey, int destinationAccountKey, double amount, TransactionType transactionType) {
        TransactionRequest request = new TransactionRequest(amount, transactionType, originAccountKey, destinationAccountKey);
        return this.restTemplate.postForObject(TRANSACTION_URL + "/access/operations", request, Transaction.class);
    }

    /************ GET *************/

    public Double showBalance(int accountKey) {
        return this.restTemplate.getForObject(MEA_URL + "/access/balance/" + accountKey, Double.class);
    }

    public List getTransaction(int accountKey) {
        return this.restTemplate.getForObject(TRANSACTION_URL + "/access/operations/" + accountKey, List.class);
    }

    public Contract getContract(int accountKey) {
        return this.restTemplate.getForObject(MEA_URL + "/access/contract/" + accountKey, Contract.class);
    }

    public Double getTotalFees(int accountId) {
        return this.restTemplate.getForObject(TRANSACTION_URL + "/access/fees/" + accountId, Double.class);
    }

    /************ PRETTY DUMP *************/

    public String prettyDump() {
        return this.restTemplate.getForObject(PRETTY_DUMP_URL + "/prettyDump", String.class);
    }
}
