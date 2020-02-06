package fr.polytech.unice.credirama.transaction.component.impl;

import fr.polytech.unice.credirama.transaction.component.TransactionComponent;
import fr.polytech.unice.credirama.transaction.entities.CrediramaDate;
import fr.polytech.unice.credirama.transaction.entities.Transaction;
import fr.polytech.unice.credirama.transaction.entities.TransactionType;
import fr.polytech.unice.credirama.transaction.entities.dto.TransactionsBtw2DatesResponse;
import fr.polytech.unice.credirama.transaction.repo.TransactionRepo;
import fr.polytech.unice.credirama.transaction.service.EnterpriseAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
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
        if (idFrom == 0) {
            Transaction transaction = new Transaction(0, idTo, amount, transactionType);
            transactionWithID = transactionRepo.save(transaction);
            enterpriseAccountsService.addTransactionToAccount(transactionWithID.getId(), 0, idTo, amount);
        } else {
            Transaction transaction = new Transaction(idFrom, idTo, amount, transactionType);
            transactionWithID = transactionRepo.save(transaction);
            double feeAmount = enterpriseAccountsService.addTransactionToAccount(transactionWithID.getId(), idFrom, idTo, amount);
            transactionWithID.setFeeAmount(feeAmount);
            transactionRepo.save(transactionWithID);
        }


        //TODO c'est un peu sale ca
        transactionWithID.getDate().setTransaction(null); //to avoid stackOverFlow


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
        return total;
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
    public Double getTotalFees(int id) {
        Iterator<Transaction> transactionIterator = transactionRepo.findAll().iterator();
        double total = 0;
        while (transactionIterator.hasNext()) {
            Transaction t = transactionIterator.next();
            if (t.getToId() == id) {
                total += t.getFeeAmount();
            }
        }
        return total;
    }

    //TODO check if tomorrow for dateTo
    @Override
    public TransactionsBtw2DatesResponse getAllReceivedTransactionsByUserIdBetweenToDates(Integer id, GregorianCalendar dateFrom, GregorianCalendar dateTo) {
        List<Transaction> userTransactions = this.transactionRepo.getTransactionsByToId(id);
        Map<CrediramaDate, ArrayList<Transaction>> result = new HashMap<>();
        GregorianCalendar calendar = new GregorianCalendar(dateFrom.get(Calendar.YEAR), dateFrom.get(Calendar.MONTH), dateFrom.get(Calendar.DAY_OF_MONTH));
        while(!calendar.after(dateTo)) {
            result.put(new CrediramaDate(calendar), new ArrayList<>());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar = new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }
        for(Transaction transaction : userTransactions) {
            GregorianCalendar date = transaction.getDate().toGregorian();
            if (date.after(dateFrom) && date.before(dateTo)){
                for (CrediramaDate crediramaDate : result.keySet()) {
                    GregorianCalendar g = crediramaDate.toGregorian();
                    if(g.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)
                            && (g.get(Calendar.MONTH) == date.get(Calendar.MONTH)
                            && (g.get(Calendar.YEAR) == date.get(Calendar.YEAR)))){
                                ArrayList<Transaction> transactions = result.get(crediramaDate);
                                transactions.add(transaction);
                                result.put(crediramaDate, transactions);
                    }
                }
            }
        }
        return new TransactionsBtw2DatesResponse(result);
    }
}
