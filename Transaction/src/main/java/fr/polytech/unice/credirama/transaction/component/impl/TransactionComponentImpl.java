package fr.polytech.unice.credirama.transaction.component.impl;

import fr.polytech.unice.credirama.transaction.component.TransactionComponent;
import fr.polytech.unice.credirama.transaction.entities.Transaction;
import fr.polytech.unice.credirama.transaction.entities.TransactionType;
import fr.polytech.unice.credirama.transaction.entities.dto.TransactionsBtw2DatesResponse;
import fr.polytech.unice.credirama.transaction.repo.TransactionRepo;
import fr.polytech.unice.credirama.transaction.service.EnterpriseAccountsService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Component
public class TransactionComponentImpl implements TransactionComponent {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private EnterpriseAccountsService enterpriseAccountsService;

    /**
     * Get all transactions by transaction ID
     *
     * @param ids
     * @return
     */
    @Override
    public List<Transaction> getOperationsById(List<Integer> ids) {
        List<Transaction> transactions = new ArrayList<>();
        Transaction t;
        for (Integer i : ids) {
            t = transactionRepo.findById(i).get();
            transactions.add(t);
            t = null;
        }
        return transactions;
    }

    @Override
    public Transaction addTransaction(Integer idFrom, Integer idTo, Double amount, TransactionType transactionType){
        Transaction transactionWithID;
        amount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        if (idFrom == 0) {
            Transaction transaction = new Transaction(0, idTo, amount, transactionType);
            transactionWithID = transactionRepo.save(transaction);
            enterpriseAccountsService.addTransactionToAccount(transactionWithID.getId(), 0, idTo, amount);
        } else {
            Transaction transaction = new Transaction(idFrom, idTo, amount, transactionType);
            transactionWithID = transactionRepo.save(transaction);
            double feeAmount = BigDecimal.valueOf(enterpriseAccountsService.addTransactionToAccount(transactionWithID.getId(), idFrom, idTo, amount)).setScale(2, RoundingMode.HALF_UP).doubleValue();
            transactionWithID.setFeeAmount(feeAmount);
            transactionRepo.save(transactionWithID);
        }
        return transactionWithID;
    }

    @Override
    public double getTransactionsAndFees(List<Integer> ids) {
        double total = 0;
        for (Integer i : ids) {
            Transaction t;
            t = transactionRepo.findById(i).get();
            total += t.getFeeAmount();
        }
        return new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        Iterator<Transaction> transactionIterator = transactionRepo.findAll().iterator();
        List<Transaction> transactions = new ArrayList<>();
        while (transactionIterator.hasNext()) {
            transactions.add(transactionIterator.next());
        }
        return transactions;
    }

    @Override
    public List<Transaction> getAllTransactionsByUserId(int userId) {
        return this.transactionRepo.getTransactionsByFromIdOrToId(userId, userId);
    }

    @Override
    public List<Transaction> getAllTransactionsByUserIdFrom(int idFrom) {
        return this.transactionRepo.getTransactionsByFromId(idFrom);
    }

    @Override
    public List<Transaction> getAllTransactionsByUserIdTo(int idTo) {
        return this.transactionRepo.getTransactionsByToId(idTo);
    }

    @Override
    public Double getTotalFees(int id) {
        Iterator<Transaction> transactionIterator = transactionRepo.findAll().iterator();
        double total = 0;
        while (transactionIterator.hasNext()) {
            Transaction t = transactionIterator.next();
            if (t.getToId() == id) {
                total += t.getFeeAmount();
            }
        }
        return new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    //TODO check if tomorrow for dateTo
    @Override
    public TransactionsBtw2DatesResponse getAllReceivedTransactionsByUserIdBetweenToDates(Integer id, DateTime dateFrom, DateTime dateTo) {
        dateTo = new DateTime(dateTo.getYear(), dateTo.getMonthOfYear(), dateTo.getDayOfMonth(), 0, 0).plusDays(1).minusMillis(1);
        List<Transaction> userTransactions = this.transactionRepo.getTransactionsByToId(id);
        Map<String, List<Transaction>> result = new HashMap<>();
        DateTime date = new DateTime(dateFrom.getYear(), dateFrom.getMonthOfYear(), dateFrom.getDayOfMonth(), 0, 0);
        dateFrom = date;
        while(!date.isAfter(dateTo)) {
            result.put(date.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")), new ArrayList<>());
            date = date.plusDays(1);
        }
        for(Transaction transaction : userTransactions) {
            DateTime transactionDate = transaction.toDateTime();
            if (transactionDate.isAfter(dateFrom) && transactionDate.isBefore(dateTo)){
                for (String timeStamp : result.keySet()) {
                    DateTime dateTime = DateTime.parse(timeStamp, DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
                    if(dateTime.getYear() == transactionDate.getYear()
                            && (dateTime.getDayOfYear() == transactionDate.getDayOfYear())){
                                List<Transaction> transactions = result.get(timeStamp);
                                transactions.add(transaction);
                                result.put(timeStamp, transactions);
                    }
                }
            }
        }
        return new TransactionsBtw2DatesResponse(result);
    }
}
