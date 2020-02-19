package fr.polytech.unice.credirama.transaction.controller;

import fr.polytech.unice.credirama.transaction.component.TransactionComponent;
import fr.polytech.unice.credirama.transaction.entities.Transaction;
import fr.polytech.unice.credirama.transaction.entities.dto.TransactionRequest;
import fr.polytech.unice.credirama.transaction.entities.dto.TransactionsBtw2DatesRequest;
import fr.polytech.unice.credirama.transaction.entities.dto.TransactionsBtw2DatesResponse;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/access", produces = "application/json")
public class TransactionController {

    @Autowired
    private TransactionComponent transactionComponent;

    //TODO
    @GetMapping("/operations")
    public List<Transaction> getAllOperations() {
        return this.transactionComponent.getAllTransactions();
    }

    @GetMapping("/operations/{id}")
    public List<Transaction> getAllOperations(@PathVariable(name = "id") int id) {
        return this.transactionComponent.getAllTransactionsByUserId(id);
    }

    @GetMapping("/operations/from/{id}")
    public List<Transaction> getAllOperationsFrom(@PathVariable(name = "id") int userIdFrom) {
        return this.transactionComponent.getAllTransactionsByUserIdFrom(userIdFrom);
    }

    @GetMapping("/operations/to/{id}")
    public List<Transaction> getAllOperationsTo(@PathVariable(name = "id") int userIdTo) {
        return this.transactionComponent.getAllTransactionsByUserIdTo(userIdTo);
    }

    @PostMapping("/operations")
    public Transaction makeTransaction(@RequestBody TransactionRequest request) {
        Transaction t = this.transactionComponent.addTransaction(request.getFromId(), request.getToId(), request.getAmount(), request.getType());
        return t;
    }

    @PostMapping(value = "/operations/flutter", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Transaction makeTransactionFromFlutter(TransactionRequest request) {
        System.out.println(request.toString());
        Transaction t = this.transactionComponent.addTransaction(request.getFromId(), request.getToId(), request.getAmount(), request.getType());
        return t;
    }

    @GetMapping("/fees/{id}")
    public Double getTotalFees(@PathVariable(name = "id") int id) {
        return new BigDecimal(this.transactionComponent.getTotalFees(id)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @GetMapping("/operations/{id}/today")
    public int getNumberOfTransactionsToday(@PathVariable(name = "id") int userId) {
        DateTime today = DateTime.now();
        return this.transactionComponent.getAllReceivedTransactionsByUserIdBetweenToDates(userId, today, today).getTransactionPerDay().get(today).size();
    }

    @PostMapping("/operations/{id}/dates")
    public TransactionsBtw2DatesResponse getTransactionsBetweenTwoDates(@PathVariable(name = "id") String userId, @RequestBody TransactionsBtw2DatesRequest transactionsBtw2DatesRequest) {
        DateTime dateFrom = DateTime.parse(transactionsBtw2DatesRequest.getDateFrom(), DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
        DateTime dateTo = DateTime.parse(transactionsBtw2DatesRequest.getDateTo(), DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
        return this.transactionComponent.getAllReceivedTransactionsByUserIdBetweenToDates(Integer.parseInt(userId), dateFrom, dateTo);
    }

    @PostMapping(value = "/operations/{id}/dates/flutter", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public TransactionsBtw2DatesResponse getTransactionsBetweenTwoDatesFlutter(@PathVariable(name = "id") String userId, TransactionsBtw2DatesRequest transactionsBtw2DatesRequest) {
        DateTime dateFrom = DateTime.parse(transactionsBtw2DatesRequest.getDateFrom(), DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
        DateTime dateTo = DateTime.parse(transactionsBtw2DatesRequest.getDateTo(), DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
        return this.transactionComponent.getAllReceivedTransactionsByUserIdBetweenToDates(Integer.parseInt(userId), dateFrom, dateTo);
    }

}
