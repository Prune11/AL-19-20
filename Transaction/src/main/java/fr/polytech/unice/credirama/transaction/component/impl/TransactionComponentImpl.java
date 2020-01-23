package fr.polytech.unice.credirama.transaction.component.impl;

import fr.polytech.unice.credirama.transaction.component.TransactionComponent;
import fr.polytech.unice.credirama.transaction.entities.Contract;
import fr.polytech.unice.credirama.transaction.entities.Transaction;
import fr.polytech.unice.credirama.transaction.entities.TransactionType;
import fr.polytech.unice.credirama.transaction.repo.TransactionRepo;
import fr.polytech.unice.credirama.transaction.service.EnterpriseAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public Transaction addTransaction(Integer idFrom, Integer idTo, Double amount, TransactionType transactionType){//, Contract contract) {
        Transaction transactionWithID;
        if (idFrom == 0) {
            //Transaction transaction = new Transaction(0, idTo, amount, 0, transactionType);
            Transaction transaction = new Transaction(0, idTo, amount, transactionType);
            transactionWithID = transactionRepo.save(transaction);
            //TODO save in other database <3
            /*Account accountTo = accountRepo.findById(idTo).get();
            accountTo.addTransaction(transactionWithID);
            accountRepo.save(accountTo);*/
            enterpriseAccountsService.addTransactionToAccount(transactionWithID.getId(), 0, idTo, amount);//, 0.0);
        } else {
            //Récupérer le contrat de l'account from
            //double amountFee = amount * contract.getFee() / 100;
            //Transaction transaction = new Transaction(idFrom, idTo, amount, amountFee, transactionType);
            Transaction transaction = new Transaction(idFrom, idTo, amount, transactionType);
            transactionWithID = transactionRepo.save(transaction);
            //TODO save in other db
            /*Account accountFrom = accountRepo.findById(idFrom).get();
            Account accountTo = accountRepo.findById(idTo).get();
            accountFrom.addTransaction(transactionWithID);
            accountRepo.save(accountFrom);
            accountTo.addTransaction(transactionWithID);
            accountRepo.save(accountTo);*/
            double feeAmount = enterpriseAccountsService.addTransactionToAccount(transactionWithID.getId(), idFrom, idTo, amount).doubleValue();
            System.out.println("\n" + feeAmount + "\n");
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

}
