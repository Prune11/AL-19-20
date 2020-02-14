package fr.polytech.unice.credirama.mea.component.impl;

import fr.polytech.unice.credirama.mea.component.ManageEnterpriseAccount;
import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.Client;
import fr.polytech.unice.credirama.mea.entities.contract.Contract;
import fr.polytech.unice.credirama.mea.entities.dto.AccountDTO;
import fr.polytech.unice.credirama.mea.entities.dto.MEAAddTransactionRequest;
import fr.polytech.unice.credirama.mea.repo.AccountRepo;
import fr.polytech.unice.credirama.mea.repo.ClientRepo;
import gherkin.deps.com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ManageEnterpriseAccountImpl implements ManageEnterpriseAccount {

    @Autowired
    private AccountRepo accountRepo;


    @Autowired
    private ClientRepo clientRepo;

    public Contract getContractById(Integer id) {
        Account account = accountRepo.findById(id).get();
        return account.getContract();
    }

    public double getBalanceById(Integer id) {
        Account account = accountRepo.findById(id).get();
        return account.getBalance();
    }

    public List<Account> getAllAccounts() {
        Iterator<Account> accountIterator = accountRepo.findAll().iterator();
        List<Account> accounts = new ArrayList<>();
        while (accountIterator.hasNext()) {
            accounts.add(accountIterator.next().resultToSend());
        }
        return accounts;
    }

    public Account getTest() {
        Client client = new Client("Michel");
        Client clientWithId = clientRepo.save(client);
        Account account = new Account(clientWithId, Contract.DIAMOND, 2.2);
        accountRepo.save(account);
        account.updateForSave();
        accountRepo.save(account);
        clientRepo.save(clientWithId);
        return account.resultToSend();
    }

    public Account getAccountById(Integer id) {
        return accountRepo.findById(id).get().resultToSend();
    }

    public Account createAccount(Integer clientId, Contract contract) {
        Client client = clientRepo.findById(clientId).get();
        Account account = new Account(client, contract, 10.0);
        accountRepo.save(account);
        account.updateForSave();
        accountRepo.save(account);
        clientRepo.save(client);
        return account.resultToSend();
    }

    public Account updateContract(Integer id, Contract contract) {
        Account account = accountRepo.findById(id).get();
        account.setContract(contract);
        accountRepo.save(account);
        return account.resultToSend();
    }

    public Account updateClient(Integer id, Integer newOwner) {
        Client client = clientRepo.findById(newOwner).get();
        Account account = accountRepo.findById(id).get();
        account.setOwner(client);
        accountRepo.save(account);
        return account.resultToSend();
    }

    public String deleteAccount(Integer id) {
        accountRepo.deleteById(id);
        return "Account " + id + " has been deleted.";
    }

    public void deleteAllAccounts() {
        accountRepo.deleteAll();
    }

    public List<Client> getAllClients() {
        Iterator<Client> clientIterator = clientRepo.findAll().iterator();
        List<Client> clients = new ArrayList<>();
        while (clientIterator.hasNext()) {
            clients.add(clientIterator.next().resultToSend());
        }
        return clients;
    }

    public Client getClientById(Integer id) {
        return clientRepo.findById(id).get().resultToSend();
    }


    public Client createClient(String owner) {
        Client client = new Client(owner);
        client = this.clientRepo.save(client);
        return client.resultToSend();
    }

    public String deleteClient(Integer id) {
        clientRepo.deleteById(id);
        return "Client " + id + " has been deleted.";
    }


    public String deleteAllClients() {
        this.clientRepo.deleteAll();
        return "All clients have been deleted.";
    }

    public Double addTransaction(MEAAddTransactionRequest transactionRequest) {
        try {
            Account accountTo = accountRepo.findById(transactionRequest.getAccountTo()).get();
            Double feeAmount = accountTo.addTransaction(transactionRequest);
            accountRepo.save(accountTo);
            if (transactionRequest.getAccountFrom() != 0) {
                Account accountFrom = accountRepo.findById(transactionRequest.getAccountFrom()).get();
                accountFrom.addTransaction(transactionRequest);
                accountRepo.save(accountFrom);
            }
            return feeAmount;
        } catch (Exception e) {
            return -1.0;
        }
    }


    @Override
    public String getPrettyDump() {
        List<Client> clients = getAllClients();
        List<AccountDTO> accounts = new ArrayList<>();
        for (Client client : clients) {
            for (Account account : client.getAccountList()) {
                account.setOwner(client.cloneForPrettyDump());
                accounts.add(new AccountDTO(account));
            }
        }
        String response = "";
        Gson gson = new Gson();
        response += gson.toJson(clients);
        response += gson.toJson(accounts);
        return response;
    }
}
