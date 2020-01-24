package fr.polytech.unice.credirama.transaction.component;

import fr.polytech.unice.credirama.transaction.entities.Transaction;
import fr.polytech.unice.credirama.transaction.entities.TransactionType;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface TransactionComponent {

    List<Transaction> getOperationsById(List<Integer> ids);

    Transaction addTransaction(Integer idFrom, Integer idTo, Double amount, TransactionType transactionType);

    double getTransactionsAndFees(List<Integer> ids);

    List<Transaction> getAllTransactions();

    Double getTotalFees(int id);

    List<Transaction> getAllReceivedTransactionsByUserIdBetweenToDates(Integer id, Calendar dateFrom, Calendar dateTo);
}
