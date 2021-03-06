package fr.polytech.unice.credirama.mea.entities.contract;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ThresholdFees extends ContractAspect {
    private double lowerPercentage;
    private double upperPercentage;
    private double threshold;


    public ThresholdFees(double lowerPercentage, double upperPercentage, double threshold) {
        super();
        this.lowerPercentage = lowerPercentage;
        this.upperPercentage = upperPercentage;
        this.threshold = threshold;
    }

    @Override
    public double calculateFees(double transactionAmount) {
        if (transactionAmount > threshold) {
            return new BigDecimal(transactionAmount * upperPercentage / 100).setScale(2, RoundingMode.HALF_UP).doubleValue();
        } else {
            return new BigDecimal(transactionAmount * lowerPercentage / 100).setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
    }
}
