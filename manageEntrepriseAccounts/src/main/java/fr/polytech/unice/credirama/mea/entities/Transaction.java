package fr.polytech.unice.credirama.mea.entities;

import lombok.*;

import javax.persistence.*;

@ToString
@EqualsAndHashCode
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int accountFrom;

    private int accountTo;

    private double amount;

    private TransactionType transactionType;

    private double feeAmount;

    /**
     * TODO: Add Date?
     */


    public Transaction() {

    }

    public Transaction(int accountFrom, int accountTo, double amount, double feeAmount, TransactionType transactionType) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.feeAmount = feeAmount;
        this.transactionType = transactionType;
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

    public TransactionType getType() {
        return this.transactionType;
    }

    public Double getFees() {
        return this.feeAmount;
    }
}
