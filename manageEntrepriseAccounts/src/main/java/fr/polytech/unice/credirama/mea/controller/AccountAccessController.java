package fr.polytech.unice.credirama.mea.controller;

import fr.polytech.unice.credirama.mea.component.ManageEnterpriseAccount;
import fr.polytech.unice.credirama.mea.entities.Contract;
import fr.polytech.unice.credirama.mea.entities.Transaction;
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

    @GetMapping("/operations/from/{idFrom}/to/{idTo}/amount/{amount}")
    public void test(@PathVariable(name = "idFrom") Integer idFrom, @PathVariable(name = "idTo") Integer idTo, @PathVariable(name = "amount") Double amount) {
        this.manageEnterpriseAccount.test(idFrom, idTo, amount);
    }

}
