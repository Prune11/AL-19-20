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
        return account.getTransactions();
    }

    @GetMapping("/operations/from/{idFrom}/to/{idTo}/amount/{amount}")
    public void test(@PathVariable(name = "idFrom") Integer idFrom, @PathVariable(name = "idTo") Integer idTo, @PathVariable(name = "amount") Double amount) {
        Account accountFrom = accountRepo.findById(idFrom).get();
        Account accountTo = accountRepo.findById(idTo).get();
        Transaction transaction = new Transaction(accountFrom.getId(), accountTo.getId(), amount);
        Transaction transactionWithID = transactionRepo.save(transaction);
        accountFrom.addTransaction(transactionWithID);
        accountRepo.save(accountFrom);
        accountTo.addTransaction(transactionWithID);
        accountRepo.save(accountTo);
    }

}
