package fr.polytech.unice.credirama.mea.component;

import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.Client;
import fr.polytech.unice.credirama.mea.entities.Contract;
import fr.polytech.unice.credirama.mea.entities.Transaction;
import fr.polytech.unice.credirama.mea.entities.TransactionType;
import fr.polytech.unice.credirama.mea.entities.dto.PrettyDumpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ManageEnterpriseAccount {

    Contract getContractById(Integer id);

    double getBalanceById(Integer id);

    List<Transaction> getOperationsById(Integer id);

    void addTransaction(Integer idFrom, Integer idTo, Double amount, TransactionType transactionType);

    List<Account> getAllAccounts();

    Account getTest();

    Account getAccountById(Integer id);

    Account createAccount(Integer id, Contract contract);

    Account updateContract(Integer id, Contract contract);

    Account updateClient(Integer id, String newOwner);

    String deleteAccount(Integer id);

    void deleteAllAccounts();

    List<Client> getAllClients();

    Client getClientById(Integer id);

    Client createClient(String owner);

    String deleteClient(Integer id);

    String deleteAllClients();

    Map<Transaction, Double> getTransactionsAndFees(Integer id);

}
