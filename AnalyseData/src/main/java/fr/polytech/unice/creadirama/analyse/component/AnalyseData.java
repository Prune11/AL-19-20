package fr.polytech.unice.creadirama.analyse.component;

import fr.polytech.unice.creadirama.analyse.entity.CrediramaDate;
import fr.polytech.unice.creadirama.analyse.entity.FeeBtw2Day;
import fr.polytech.unice.creadirama.analyse.entity.Transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface AnalyseData {

    double sumFeesPerDay(ArrayList<Transaction> transactions);

    double avgFeePerDay(ArrayList<Transaction> transactions);

    Transaction minTransactionFee(ArrayList<Transaction> transactions);

    Transaction maxTransactionFee(ArrayList<Transaction> transactions);

    FeeBtw2Day sumBetweenTwoDate(Map<CrediramaDate, ArrayList<Transaction>> transactionPerDay);
}
