package fr.polytech.unice.creadirama.analyse.service;

import com.google.common.collect.Lists;
import fr.polytech.unice.creadirama.analyse.dto.TransactionsBtw2DatesRequest;
import fr.polytech.unice.creadirama.analyse.dto.TransactionsBtw2DatesResponse;
import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class TransactionService {

    private static final String TRANSACTION_URL = "http://localhost:8084";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;


    public List<Transaction> getTransactionsFor1Day(DateTime date, int idFrom) {
        String transactionUrl = env.getProperty("TRANSACTION");
        if (transactionUrl == null || transactionUrl.equals("")) transactionUrl = TRANSACTION_URL;
        TransactionsBtw2DatesRequest request = new TransactionsBtw2DatesRequest(date, date);
        TransactionsBtw2DatesResponse result = this.restTemplate.postForObject(transactionUrl + "/access/operations/" + idFrom + "/dates", request.toSend(), TransactionsBtw2DatesResponse.class);
        assert result != null;
        DateTime dateTime = new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), 0, 0);
        String timeStamp = dateTime.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
        return result.getTransactionPerDay().get(timeStamp);
    }

    public Map<String, List<Transaction>> getTransactionBtw2Day(DateTime from, DateTime to, int idFrom) {
        String transactionUrl = env.getProperty("TRANSACTION");
        if (transactionUrl == null || transactionUrl.equals("")) transactionUrl = TRANSACTION_URL;
        TransactionsBtw2DatesRequest request = new TransactionsBtw2DatesRequest(from, to);
        TransactionsBtw2DatesResponse result = this.restTemplate.postForObject(transactionUrl + "/access/operations/" + idFrom + "/dates" , request.toSend(), TransactionsBtw2DatesResponse.class);
        assert result != null;
        return result.getTransactionPerDay();
    }

}
