package fr.polytech.unice.credirama.mea.component;

import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.Contract;
import fr.polytech.unice.credirama.mea.entities.Transaction;

import java.util.List;

public interface ManageEnterpriseAccount {

    Contract getContractById(Integer id);

    double getBalanceById(Integer id);

    List<Transaction> getOperationsById(Integer id);

    void test(Integer idFrom, Integer idTo, Double amount);

    List<Account> getAllAccounts();

    Account getTest();

    Account getAccountById(Integer id);

    Account createAccount(String owner, Contract contract);

    Account updateContract(Integer id, Contract contract);

    Account updateClient(Integer id, String newOwner);

    String deleteAccount(Integer id);

    void deleteAll();

}
