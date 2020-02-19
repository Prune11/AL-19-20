package fr.polytech.unice.credirama.mea.entities.contract;

import fr.polytech.unice.credirama.mea.entities.Account;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum Contract {
    WOOD(new ThresholdFees(10, 5, 6), 50),
    STONE(new LimitFees(5, 10, 5), 100),
    IRON(new SimpleFees(5), 120),
    DIAMOND(new SimpleFees(1), 500);

    private ContractAspect aspect;
    private double pricePerMonth;


    Contract(ContractAspect aspect, double pricePerMonth) {
        this.aspect = aspect;
        this.pricePerMonth = new BigDecimal(pricePerMonth).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double getFee(double transactionAmount, Account account) {
        if (this.aspect instanceof LimitFees) {
            return this.aspect.calculateFees(transactionAmount, account);
        }
        return new BigDecimal(this.aspect.calculateFees(transactionAmount)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double getPricePerMonth() {
        return new BigDecimal(this.pricePerMonth).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public String toString() {
        return "Contract{" +
                "aspect=" + aspect +
                '}';
    }
}
