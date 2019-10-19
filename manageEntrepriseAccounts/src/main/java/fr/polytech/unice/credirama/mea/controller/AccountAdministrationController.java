package fr.polytech.unice.credirama.mea.controller;

import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.Contract;
import fr.polytech.unice.credirama.mea.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/accounts", produces = "application/json")
public class AccountAdministrationController {

    @Autowired
    AccountRepo accountRepo;

    @GetMapping("")
    public List<Account> getAllAccounts(){
        Iterator<Account> accountIterator = accountRepo.findAll().iterator();
        List<Account> accounts = new ArrayList<>();
        while(accountIterator.hasNext()){
            accounts.add(accountIterator.next());
        }
        return accounts;
    }

    @GetMapping("test")
    public Account getTest(){
        Account account = new Account("michel", Contract.DIAMOND, 2.2);
        accountRepo.save(account);
        return account;
    }

    @GetMapping("/account/{id}")
    public Account getAccountById(@PathVariable(name = "id") Integer id){
        return accountRepo.findById(id).get();
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody String owner, @RequestBody Contract contract){
        Account account = new Account(owner, contract, 0.0);
        accountRepo.save(account);
        return account;
    }

    @PostMapping("/update/{id}/contract")
    public Account updateContract(@PathVariable(name = "id") Integer id, @RequestBody Contract contract){
        Account account = accountRepo.findById(id).get();
        account.setContract(contract);
        accountRepo.save(account);
        return account;
    }

    @PostMapping("/update/{id}/owner")
    public Account updateClient(@PathVariable(name = "id") Integer id, @RequestBody String newOwner){
        Account account = accountRepo.findById(id).get();
        account.setOwner(newOwner);
        accountRepo.save(account);
        return account;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable(name = "id") Integer id){
        accountRepo.deleteById(id);
        return "Account " + id + " has been deleted.";
    }

    @DeleteMapping("/delete")
    public void deleteAll(){
        accountRepo.deleteAll();
    }



}

