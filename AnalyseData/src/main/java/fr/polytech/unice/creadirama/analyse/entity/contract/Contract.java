package fr.polytech.unice.creadirama.analyse.entity.contract;

import fr.polytech.unice.creadirama.analyse.entity.Transaction;

import java.util.List;

public enum Contract {
    WOOD(new ThresholdFees(10, 5, 1), 50),
    STONE(new LimitFees(5, 10, 5), 100),
    IRON(new SimpleFees(5), 120),
    DIAMOND(new SimpleFees(1), 500);

    private ContractAspect aspect;
    private double pricePerMonth;


    Contract(ContractAspect aspect, double pricePerMonth) {
        this.aspect = aspect;
        this.pricePerMonth = pricePerMonth;
    }

    public double getFee(Transaction transaction, List<Transaction> transactionList) {
        if (this.aspect instanceof LimitFees) {
            return this.aspect.calculateFees(transaction, transactionList);
        }
        return this.aspect.calculateFees(transaction.getAmount());
    }

    public double getPricePerMonth() {
        return pricePerMonth;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "aspect=" + aspect +
                '}';
    }
}
