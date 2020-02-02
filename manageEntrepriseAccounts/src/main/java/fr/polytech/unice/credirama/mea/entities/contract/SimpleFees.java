package fr.polytech.unice.credirama.mea.entities.contract;

public class SimpleFees extends ContractAspect {
    private int percentage;

    public SimpleFees(int percentage) {
        super();
        this.percentage = percentage;
    }

    @Override
    public double calculateFees(double transactionAmount) {
        return transactionAmount * percentage / 100;
    }
}
