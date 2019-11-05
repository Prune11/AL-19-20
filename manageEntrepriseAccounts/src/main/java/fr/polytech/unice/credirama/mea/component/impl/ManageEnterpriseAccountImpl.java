package fr.polytech.unice.credirama.mea.component.impl;

import fr.polytech.unice.credirama.mea.component.ManageEnterpriseAccount;
import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.Contract;
import fr.polytech.unice.credirama.mea.entities.Transaction;
import fr.polytech.unice.credirama.mea.repo.AccountRepo;
import fr.polytech.unice.credirama.mea.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ManageEnterpriseAccountImpl implements ManageEnterpriseAccount {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    TransactionRepo transactionRepo;

    public Contract getContractById(Integer id) {
        Account account = accountRepo.findById(id).get();
        return account.getContract();
    }

    public double getBalanceById(Integer id) {
        Account account = accountRepo.findById(id).get();
        return account.getBalance();
    }

    public List<Transaction> getOperationsById(Integer id) {
        Account account = accountRepo.findById(id).get();
        return account.getTransactions();
    }

    public void test(Integer idFrom, Integer idTo, Double amount) {
        Account accountFrom = accountRepo.findById(idFrom).get();
        Account accountTo = accountRepo.findById(idTo).get();
        Transaction transaction = new Transaction(accountFrom.getId(), accountTo.getId(), amount);
        Transaction transactionWithID = transactionRepo.save(transaction);
        accountFrom.addTransaction(transactionWithID);
        accountRepo.save(accountFrom);
        accountTo.addTransaction(transactionWithID);
        accountRepo.save(accountTo);
    }

    public List<Account> getAllAccounts(){
        Iterator<Account> accountIterator = accountRepo.findAll().iterator();
        List<Account> accounts = new ArrayList<>();
        while(accountIterator.hasNext()){
            accounts.add(accountIterator.next());
        }
        return accounts;
    }

    public Account getTest(){
        Account account = new Account("michel", Contract.DIAMOND, 2.2);
        accountRepo.save(account);
        return account;
    }

    public Account getAccountById(Integer id){
        return accountRepo.findById(id).get();
    }

    public Account createAccount(String owner, Contract contract){
        Account account = new Account(owner, contract, 0.0);
        accountRepo.save(account);
        return account;
    }

    public Account updateContract(Integer id, Contract contract){
        Account account = accountRepo.findById(id).get();
        account.setContract(contract);
        accountRepo.save(account);
        return account;
    }

    public Account updateClient(Integer id, String newOwner){
        Account account = accountRepo.findById(id).get();
        account.setOwner(newOwner);
        accountRepo.save(account);
        return account;
    }

    public String deleteAccount(Integer id){
        accountRepo.deleteById(id);
        return "Account " + id + " has been deleted.";
    }

    public void deleteAll(){
        accountRepo.deleteAll();
    }
}
