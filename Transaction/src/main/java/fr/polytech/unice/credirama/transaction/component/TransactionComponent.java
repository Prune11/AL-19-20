package fr.polytech.unice.credirama.transaction.component;

import fr.polytech.unice.credirama.transaction.entities.Contract;
import fr.polytech.unice.credirama.transaction.entities.Transaction;
import fr.polytech.unice.credirama.transaction.entities.TransactionType;

import java.util.List;

public interface TransactionComponent {

    List<Transaction> getOperationsById(List<Integer> ids);

    Transaction addTransaction(Integer idFrom, Integer idTo, Double amount, TransactionType transactionType, Contract contract);

    double getTransactionsAndFees(List<Integer> ids);

}