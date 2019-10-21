package fr.polytech.unice.credirama.mea.controller;

import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.Contract;
import fr.polytech.unice.credirama.mea.entities.Transaction;
import fr.polytech.unice.credirama.mea.repo.AccountRepo;
import fr.polytech.unice.credirama.mea.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/access", produces = "application/json")
public class AccountAccessController {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    TransactionRepo transactionRepo;

    @GetMapping("/contract/{id}")
    public Contract getContract(@PathVariable(name = "id") Integer id) {
        Account account = accountRepo.findById(id).get();
        return account.getContract();
    }

    @GetMapping("/balance/{id}")
    public double getBalance(@PathVariable(name = "id") Integer id) {
        Account account = accountRepo.findById(id).get();
        return account.getBalance();
    }

    @GetMapping("/operations/{id}")
    public List<Transaction> getOperations(@PathVariable(name = "id") Integer id) {
        //account = user
        Account account = accountRepo.findById(id).get();
        //Recover all transactions
        Iterator<Transaction> transactionIterator = transactionRepo.findAll().iterator();
        List<Transaction> transactions = new ArrayList<>();
        //Looking for transactions where from or to is user
        while(transactionIterator.hasNext()){
            if (transactionIterator.next().getFrom() == account ||
                    transactionIterator.next().getTo() == account) {
                //Add to the return List, only if user is from or to
                transactions.add(transactionIterator.next());
            }
        }
        return transactions;


    }
}
