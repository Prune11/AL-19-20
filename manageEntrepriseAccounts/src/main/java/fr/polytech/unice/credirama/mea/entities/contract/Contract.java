package fr.polytech.unice.credirama.mea.entities.contract;

public enum Contract {
    WOOD(new SimpleFees(20), 50),
    STONE(new SimpleFees(15),100),
    IRON(new SimpleFees(5),120),
    DIAMOND(new SimpleFees(1),500);

    private ContractAspect aspect;
    private double pricePerMonth;


    Contract(ContractAspect aspect, double pricePerMonth) {
        this.aspect = aspect;
        this.pricePerMonth = pricePerMonth;
    }

    public double getFee(double transactionAmount) {
        return this.aspect.calculateFees(transactionAmount);
    }

    @Override
    public String toString() {
        return "Contract{" +
                "aspect=" + aspect +
                '}';
    }
}
