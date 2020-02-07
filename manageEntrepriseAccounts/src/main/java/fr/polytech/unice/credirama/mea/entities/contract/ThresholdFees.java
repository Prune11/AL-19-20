package fr.polytech.unice.credirama.mea.entities.contract;

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
            return transactionAmount * upperPercentage / 100;
        } else {
            return transactionAmount * lowerPercentage / 100;
        }
    }
}
