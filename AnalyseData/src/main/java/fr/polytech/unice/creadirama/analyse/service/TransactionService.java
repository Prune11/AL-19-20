package fr.polytech.unice.creadirama.analyse.service;

import com.google.common.collect.Lists;
import fr.polytech.unice.creadirama.analyse.dto.TransactionsBtw2DatesRequest;
import fr.polytech.unice.creadirama.analyse.dto.TransactionsBtw2DatesResponse;
import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class TransactionService {

    private static final String TRANSACTION_URL = "http://localhost:8085";

    @Autowired
    private RestTemplate restTemplate;


    public List<Transaction> getTransactionsFor1Day(DateTime date, int idFrom) {
        TransactionsBtw2DatesRequest request = new TransactionsBtw2DatesRequest(date, date);
        return Lists.newArrayList(this.restTemplate.postForEntity(TRANSACTION_URL + "/access/operations/" + idFrom + "/dates", request, Transaction[].class).getBody());
    }

    public Map<DateTime, List<Transaction>> getTransactionBtw2Day(DateTime from, DateTime to, int idFrom) {
        TransactionsBtw2DatesRequest request = new TransactionsBtw2DatesRequest(from, to);
        TransactionsBtw2DatesResponse response = this.restTemplate.postForEntity(TRANSACTION_URL + "/access/operations/" + idFrom + "/dates", request, TransactionsBtw2DatesResponse.class).getBody();
        return response.getTransactionPerDay();
    }

}
