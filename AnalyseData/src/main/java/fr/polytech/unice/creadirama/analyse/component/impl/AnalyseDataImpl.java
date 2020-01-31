package fr.polytech.unice.creadirama.analyse.component.impl;

import fr.polytech.unice.creadirama.analyse.component.AnalyseData;
import fr.polytech.unice.creadirama.analyse.entity.FeeBtw2Day;
import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AnalyseDataImpl implements AnalyseData {

    @Override
    public double sumFeesPerDay(List<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getFeeAmount).sum();
    }

    @Override
    public double avgFeePerDay(List<Transaction> transactions) {
        return sumFeesPerDay(transactions) / transactions.size();
    }

    @Override
    public Transaction minTransactionFee(List<Transaction> transactions) {
        return transactions.stream().min(Comparator.comparingDouble(Transaction::getFeeAmount)).get();
    }

    @Override
    public Transaction maxTransactionFee(List<Transaction> transactions) {
        return transactions.stream().max(Comparator.comparingDouble(Transaction::getFeeAmount)).get();
    }

    @Override
    public FeeBtw2Day sumBetweenTwoDate(Map<Calendar, List<Transaction>> transactionPerDay) {
        Map<Calendar, Double> feePerDay = new HashMap<>();

        return new FeeBtw2Day();
    }
}
