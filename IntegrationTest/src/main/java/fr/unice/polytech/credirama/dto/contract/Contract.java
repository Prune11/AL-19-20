package fr.unice.polytech.credirama.dto.contract;


import fr.unice.polytech.credirama.dto.Account;

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

    public double getFee(double transactionAmount, Account account) {
        if (this.aspect instanceof LimitFees) {
            return this.aspect.calculateFees(transactionAmount, account);
        }
        return this.aspect.calculateFees(transactionAmount);
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
