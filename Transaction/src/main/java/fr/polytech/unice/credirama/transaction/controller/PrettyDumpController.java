package fr.polytech.unice.credirama.transaction.controller;

import fr.polytech.unice.credirama.transaction.component.TransactionComponent;
import fr.polytech.unice.credirama.transaction.entities.Transaction;
import fr.polytech.unice.credirama.transaction.repo.TransactionRepo;
import gherkin.deps.com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/prettyDump", produces = "application/json")
public class PrettyDumpController {

    @Autowired
    TransactionRepo repo;

    @Qualifier("transactionComponent")
    @Autowired
    private TransactionComponent component;

    @GetMapping("")
    public String getTransaction() {
        Gson gson = new Gson();
        List<Transaction> transactionList = component.getAllTransactions();
        return gson.toJson(transactionList);

    }
}
