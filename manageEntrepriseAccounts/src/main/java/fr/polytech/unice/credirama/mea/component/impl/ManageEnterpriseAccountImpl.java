package fr.polytech.unice.credirama.mea.component.impl;

import fr.polytech.unice.credirama.mea.component.ManageEnterpriseAccount;
import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.Client;
import fr.polytech.unice.credirama.mea.entities.Contract;
import fr.polytech.unice.credirama.mea.entities.Transaction;
import fr.polytech.unice.credirama.mea.repo.AccountRepo;
import fr.polytech.unice.credirama.mea.repo.ClientRepo;
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

    @Autowired
    ClientRepo clientRepo;

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
            accounts.add(accountIterator.next().resultToSend());
        }
        return accounts;
    }

    public Account getTest(){
        Client client = new Client("Michel");
        Client clientWithId = clientRepo.save(client);
        Account account = new Account(clientWithId, Contract.DIAMOND, 2.2);
        accountRepo.save(account);
        account.updateForSave();
        accountRepo.save(account);
        clientRepo.save(clientWithId);
        return account.resultToSend();
    }

    public Account getAccountById(Integer id){
        return accountRepo.findById(id).get().resultToSend();
    }

    public Account createAccount(Integer clientId, Contract contract){
        Client client = clientRepo.findById(clientId).get();
        Account account = new Account(client, contract, 0.0);
        accountRepo.save(account);
        return account.resultToSend();
    }

    public Account updateContract(Integer id, Contract contract){
        Account account = accountRepo.findById(id).get();
        account.setContract(contract);
        accountRepo.save(account);
        return account.resultToSend();
    }

    public Account updateClient(Integer id, String newOwner){
        Client client = clientRepo.findById(id).get();
        Account account = accountRepo.findById(id).get();
        account.setOwner(client);
        accountRepo.save(account);
        return account.resultToSend();
    }

    public String deleteAccount(Integer id){
        accountRepo.deleteById(id);
        return "Account " + id + " has been deleted.";
    }

    public void deleteAllAccounts(){
        accountRepo.deleteAll();
    }

    public List<Client> getAllClients(){
        Iterator<Client> clientIterator = clientRepo.findAll().iterator();
        List<Client> clients = new ArrayList<>();
        while(clientIterator.hasNext()){
            clients.add(clientIterator.next().resultToSend());
        }
        return clients;
    }

    public Client getClientById(Integer id){
        return clientRepo.findById(id).get().resultToSend();
    }


    public Client createClient(String owner){
        Client client = new Client(owner);
        this.clientRepo.save(client);
        return client.resultToSend();
    }

    public String deleteClient(Integer id){
        clientRepo.deleteById(id);
        return "Client " + id + " has been deleted.";
    }


    public String deleteAllClients(){
        this.clientRepo.deleteAll();
        return "All clients have been deleted.";
    }
}
