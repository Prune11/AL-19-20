package fr.unice.polytech.credirama.marchant.cli.entity;


public class Transaction {

    private long id;

    private int accountFrom;

    private int accountTo;

    private double amount;
    /**
     * TODO: Add Date?
     */


    public Transaction() {

    }

    public Transaction(int accountFrom, int accountTo, double amount){
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
