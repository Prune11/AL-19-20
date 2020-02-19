package fr.polytech.unice.credirama.mea.entities.contract;

import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * * STONE : 5% de frais si en dessous de 5 transactions/jour sinon 10%
 */
public class LimitFees extends ContractAspect {
    private double lowerPercentage;
    private double upperPercentage;
    private int transactionLimit;

    @Autowired
    AccountService accountService;

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
    public double calculateFees(double transactionAmount, Account account) {
        int dailyTransaction = accountService.getNumberOfTransactionToday(account.getId());
        if (dailyTransaction < transactionLimit) {
            return new BigDecimal(transactionAmount * lowerPercentage / 100).setScale(2, RoundingMode.HALF_UP).doubleValue();
        } else {
            return new BigDecimal(transactionAmount * upperPercentage / 100).setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
    }
}
