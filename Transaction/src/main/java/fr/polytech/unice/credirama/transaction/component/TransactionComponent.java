package fr.polytech.unice.credirama.transaction.component;

import fr.polytech.unice.credirama.transaction.entities.Transaction;
import fr.polytech.unice.credirama.transaction.entities.TransactionType;
import fr.polytech.unice.credirama.transaction.entities.dto.TransactionsBtw2DatesResponse;
import org.joda.time.DateTime;

import java.util.List;

public interface TransactionComponent {

    List<Transaction> getOperationsById(List<Integer> ids);

    Transaction addTransaction(Integer idFrom, Integer idTo, Double amount, TransactionType transactionType);

    double getTransactionsAndFees(List<Integer> ids);

    List<Transaction> getAllTransactions();

    List<Transaction> getAllTransactionsByUserId(int id);

    List<Transaction> getAllTransactionsByUserIdFrom(int userFrom);

    List<Transaction> getAllTransactionsByUserIdTo(int userTo);

    Double getTotalFees(int id);

    TransactionsBtw2DatesResponse getAllReceivedTransactionsByUserIdBetweenToDates(Integer id, DateTime dateFrom, DateTime dateTo);
}
