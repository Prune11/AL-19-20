package fr.polytech.unice.credirama.transaction.controller;

import fr.polytech.unice.credirama.transaction.component.TransactionComponent;
import fr.polytech.unice.credirama.transaction.entities.Transaction;
import fr.polytech.unice.credirama.transaction.entities.dto.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/access", produces = "application/json")
public class TransactionController {
    @Autowired
    private TransactionComponent transactionComponent;


    @GetMapping("/operations/{id}")
    public List<Transaction> getOperations(@PathVariable(name = "id") int id) {
        List<Integer> ids = new ArrayList<>();
        return this.transactionComponent.getOperationsById(ids);
    }

    @PostMapping("/operations")
    public Transaction makeTransaction(@RequestBody TransactionRequest request) {
        Transaction t = this.transactionComponent.addTransaction(request.getFromId(), request.getToId(), request.getAmount(), request.getType(), request.getContract());
        System.out.println("\n JE SUIS ICI <3 \n");
        return t;
    }

}
