package fr.polytech.unice.creadirama.analyse.component;

import fr.polytech.unice.creadirama.analyse.entity.FeeBtw2Day;
import fr.polytech.unice.creadirama.analyse.entity.Transaction;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface AnalyseData {

    double sumFeesPerDay(List<Transaction> transactions);

    double avgFeePerDay(List<Transaction> transactions);

    Transaction minTransactionFee(List<Transaction> transactions);

    Transaction maxTransactionFee(List<Transaction> transactions);

    FeeBtw2Day sumBetweenTwoDate(Map<Calendar, List<Transaction>> transactionPerDay);
}
