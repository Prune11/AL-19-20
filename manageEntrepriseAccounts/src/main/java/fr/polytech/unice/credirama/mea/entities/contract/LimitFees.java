package fr.polytech.unice.credirama.mea.entities.contract;

import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

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
            return transactionAmount * lowerPercentage / 100;
        } else {
            return transactionAmount * upperPercentage / 100;
        }
    }
}
