package fr.unice.polytech.credirama.merchant.cli.entity;

public enum Contract {
    WOOD(10),
    STONE(5),
    IRON(2),
    DIAMOND(1);

    private int fee;

    Contract(int fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "fee=" + fee +
                '}';
    }

    public int getFee() {
        return this.fee;
    }
}
