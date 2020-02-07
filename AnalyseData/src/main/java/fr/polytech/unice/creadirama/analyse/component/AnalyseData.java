package fr.polytech.unice.creadirama.analyse.component;

import fr.polytech.unice.creadirama.analyse.entity.FeeBtw2Day;
import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;

public interface AnalyseData {

    double sumFeesPerDay(List<Transaction> transactions);

    double avgFeePerDay(List<Transaction> transactions);

    Transaction minTransactionFee(List<Transaction> transactions);

    Transaction maxTransactionFee(List<Transaction> transactions);

    FeeBtw2Day sumBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day);

    FeeBtw2Day avgBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day);

    FeeBtw2Day minBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day);

    FeeBtw2Day maxBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day);
}
