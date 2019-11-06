package fr.polytech.unice.credirama.mea.controller;

import fr.polytech.unice.credirama.mea.component.ManageEnterpriseAccount;
import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.Contract;
import fr.polytech.unice.credirama.mea.entities.dto.CreateAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/accounts", produces = "application/json")
public class AccountAdministrationController {

    @Qualifier("manageEnterpriseAccount")
    @Autowired
    private ManageEnterpriseAccount manageEnterpriseAccount;

    @GetMapping("")
    public List<Account> getAllAccounts() {
        return this.manageEnterpriseAccount.getAllAccounts();
    }

    @GetMapping("test")
    public Account getTest() {
        return this.manageEnterpriseAccount.getTest();
    }

    @GetMapping("/account/{id}")
    public Account getAccountById(@PathVariable(name = "id") Integer id) {
        return this.manageEnterpriseAccount.getAccountById(id);
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody CreateAccountRequest request) {
        return this.manageEnterpriseAccount.createAccount(request.getOwner(), request.getContract());
    }

    @PostMapping("/update/{id}/contract")
    public Account updateContract(@PathVariable(name = "id") Integer id, @RequestBody Contract contract) {
        return this.manageEnterpriseAccount.updateContract(id, contract);
    }

    @PostMapping("/update/{id}/owner")
    public Account updateClient(@PathVariable(name = "id") Integer id, @RequestBody String newOwner){
        return this.manageEnterpriseAccount.updateClient(id, newOwner);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable(name = "id") Integer id){
        return this.manageEnterpriseAccount.deleteAccount(id);
    }

    @DeleteMapping("/delete")
    public void deleteAllAccounts(){
        this.manageEnterpriseAccount.deleteAllAccounts();
    }



}

