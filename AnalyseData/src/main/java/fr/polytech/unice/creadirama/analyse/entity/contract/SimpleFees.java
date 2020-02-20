package fr.polytech.unice.creadirama.analyse.entity.contract;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleFees extends ContractAspect {
    private int percentage;

    public SimpleFees(int percentage) {
        super();
        this.percentage = percentage;
    }

    @Override
    public double calculateFees(double transactionAmount) {
        return new BigDecimal(transactionAmount * percentage / 100).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
