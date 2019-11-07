package fr.polytech.unice.credirama.mea.controller;

import fr.polytech.unice.credirama.mea.component.ManageEnterpriseAccount;
import fr.polytech.unice.credirama.mea.entities.Contract;
import fr.polytech.unice.credirama.mea.entities.Transaction;
import fr.polytech.unice.credirama.mea.entities.TransactionType;
import fr.polytech.unice.credirama.mea.entities.dto.TotalFeeResponse;
import fr.polytech.unice.credirama.mea.entities.dto.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/access", produces = "application/json")
public class AccountAccessController {

    @Qualifier("manageEnterpriseAccount")
    @Autowired
    private ManageEnterpriseAccount manageEnterpriseAccount;

    @GetMapping("/contract/{id}")
    public Contract getContract(@PathVariable(name = "id") Integer id) {
        return this.manageEnterpriseAccount.getContractById(id);
    }

    @GetMapping("/balance/{id}")
    public double getBalance(@PathVariable(name = "id") Integer id) {
        return this.manageEnterpriseAccount.getBalanceById(id);
    }

    @GetMapping("/operations/{id}")
    public List<Transaction> getOperations(@PathVariable(name = "id") Integer id) {
        return this.manageEnterpriseAccount.getOperationsById(id);
    }

    @PostMapping("/operations")
    public Transaction makeTransaction(@RequestBody TransactionRequest request) {
        return this.manageEnterpriseAccount.addTransaction(request.getFromId(), request.getToId(), request.getAmount(), request.getType());
    }

    @GetMapping("/fees/{id}")
    public double getTotalOfFees(@PathVariable(name = "id") Integer accountId) {
        return this.manageEnterpriseAccount.getTransactionsAndFees(accountId);
    }

}
