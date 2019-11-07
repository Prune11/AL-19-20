package fr.polytech.unice.credirama.mea.component.impl;

import fr.polytech.unice.credirama.mea.PrettyDumpWriter;
import fr.polytech.unice.credirama.mea.component.PrettyDump;
import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.Client;
import fr.polytech.unice.credirama.mea.entities.Transaction;
import fr.polytech.unice.credirama.mea.entities.dto.PrettyDumpResponse;
import fr.polytech.unice.credirama.mea.repo.AccountRepo;
import fr.polytech.unice.credirama.mea.repo.ClientRepo;
import fr.polytech.unice.credirama.mea.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Component
public class PrettyDumpImpl implements PrettyDump {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Override
    public PrettyDumpResponse getPrettyDump() {
        PrettyDumpResponse response = new PrettyDumpResponse();
        List<Client> clients = getAllClients();
//        List<Account> accounts = new ArrayList<>();
//        for (Client client : clients) {
//            for (Account account : client.getAccountList()) {
//                account.setOwner(client.cloneForPrettyDump());
//                accounts.add(account);
//            }
//        }
        List<Account> accounts = getAllAccounts();
        List<Transaction> transactions = getAllTransactions();
        response.setAccounts(accounts);
        response.setClients(clients);
        response.setTransactions(transactions);

        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DATE);
        int hour = parseTime(date);
        int minute = date.get(Calendar.MINUTE);

        String timestamp = year + "-" + month + "-" + day + "-" + hour + "-" + minute;

        response.setTimestamp(timestamp);

        PrettyDumpWriter.writePrettyDump(clients, accounts, transactions);

        return response;
    }

    private List<Account> getAllAccounts() {
        Iterator<Account> accountIterator = accountRepo.findAll().iterator();
        List<Account> accounts = new ArrayList<>();
        while (accountIterator.hasNext()) {
            accounts.add(accountIterator.next().resultToSend());
        }
        return accounts;
    }

    private List<Transaction> getAllTransactions() {
        Iterator<Transaction> transactionIterator = transactionRepo.findAll().iterator();
        List<Transaction> transactions = new ArrayList<>();
        while (transactionIterator.hasNext()) {
            transactions.add(transactionIterator.next());
        }
        return transactions;
    }

    private List<Client> getAllClients() {
        Iterator<Client> clientIterator = clientRepo.findAll().iterator();
        List<Client> clients = new ArrayList<>();
        while (clientIterator.hasNext()) {
            clients.add(clientIterator.next().resultToSend());
        }
        return clients;
    }

    private int parseTime(Calendar date) {
        if (date.get(Calendar.AM_PM) == 1) {
            //PM
            return date.get(Calendar.HOUR) + 12;
        } else {
            return date.get(Calendar.HOUR);
        }
    }
}
