package fr.polytech.unice.creadirama.analyse.entity.contract;

import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Locale;

/**
 * * STONE : 5% de frais si en dessous de 5 transactions/jour sinon 10%
 */
public class LimitFees extends ContractAspect {
    private double lowerPercentage;
    private double upperPercentage;
    private int transactionLimit;


    public LimitFees(double lowerPercentage, double upperPercentage, int transactionLimit) {
        super();
        this.lowerPercentage = lowerPercentage;
        this.upperPercentage = upperPercentage;
        this.transactionLimit = transactionLimit;
    }

    @Override
    public double calculateFees(double transactionAmount) {
        return 0;
    }

    @Override
    public double calculateFees(Transaction currentTransaction, List<Transaction> transactionList) {
        int dailyTransaction = 0;
        DateTimeFormatter format = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss").withLocale(Locale.FRANCE);
        DateTime currentTime = format.parseDateTime(currentTransaction.getTimeStamp());

        for (Transaction t : transactionList) {
            DateTime time = format.parseDateTime(t.getTimeStamp());
            if (time.getDayOfYear() == currentTime.getDayOfYear()) {
                if (time.getMillis() < currentTime.getMillis()) {
                    dailyTransaction++;
                }

            }

        }

        if (dailyTransaction < transactionLimit) {
            return currentTransaction.getAmount() * lowerPercentage / 100;
        } else {
            return currentTransaction.getAmount() * upperPercentage / 100;
        }
    }
}
