package fr.polytech.unice.creadirama.analyse.component.impl;

import fr.polytech.unice.creadirama.analyse.component.AnalyseData;
import fr.polytech.unice.creadirama.analyse.entity.FeeBtw2Day;
import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import org.joda.time.DateTime;
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
    public FeeBtw2Day sumBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day) {
        Map<DateTime, Double> sumBtw2Date = new HashMap<>();
        for (DateTime dateTime : transactionPerDay.keySet())
            sumBtw2Date.put(dateTime, sumFeesPerDay(transactionPerDay.get(dateTime)));
        feeBtw2Day.setSumFeeBtwDay(sumBtw2Date);
        double totalSum = 0.0;
        for (List<Transaction> transactions : transactionPerDay.values())
            totalSum += transactions.stream().mapToDouble(Transaction::getFeeAmount).sum();
        feeBtw2Day.setTotalSum(totalSum);
        return feeBtw2Day;
    }

    @Override
    public FeeBtw2Day avgBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day) {
        Map<DateTime, Double> avgBtw2Date = new HashMap<>();
        for (DateTime dateTime : transactionPerDay.keySet())
            avgBtw2Date.put(dateTime, avgFeePerDay(transactionPerDay.get(dateTime)));
        feeBtw2Day.setAvgFeeBtw(avgBtw2Date);
        double totalAvg = 0.0;
        for (List<Transaction> transactions : transactionPerDay.values())
            totalAvg += transactions.stream().mapToDouble(Transaction::getFeeAmount).sum() / transactions.size();
        feeBtw2Day.setTotalAvg(totalAvg);
        return feeBtw2Day;
    }

    @Override
    public FeeBtw2Day minBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day) {
        Map<DateTime, Transaction> minBtw2Date = new HashMap<>();
        for (DateTime dateTime : transactionPerDay.keySet())
            minBtw2Date.put(dateTime, minTransactionFee(transactionPerDay.get(dateTime)));
        feeBtw2Day.setMinTransactionBtw(minBtw2Date);
        List<Transaction> transactionsList = new ArrayList<>();
        for (List<Transaction> transactions : transactionPerDay.values())
            transactionsList.add(minTransactionFee(transactions));
        feeBtw2Day.setGlobalMinTransaction(minTransactionFee(transactionsList));
        return feeBtw2Day;
    }

    @Override
    public FeeBtw2Day maxBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day) {
        Map<DateTime, Transaction> maxBtw2Date = new HashMap<>();
        for (DateTime dateTime : transactionPerDay.keySet())
            maxBtw2Date.put(dateTime, maxTransactionFee(transactionPerDay.get(dateTime)));
        feeBtw2Day.setMaxTransactionFeeBtw(maxBtw2Date);
        List<Transaction> transactionsList = new ArrayList<>();
        for (List<Transaction> transactions : transactionPerDay.values())
            transactionsList.add(maxTransactionFee(transactions));
        feeBtw2Day.setGlobalMinTransaction(maxTransactionFee(transactionsList));
        return feeBtw2Day;
    }
}
