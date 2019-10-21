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

    private Account from;
    private Account to;
    private double amount;
    /**
     * TODO: Add Date?
     */


    public Transaction() {

    }

    public Transaction(Account from, Account to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public Account getFrom() {
        return this.from;
    }

    public Account getTo() {
        return this.to;
    }

    public double getAmount() {
        return this.amount;
    }
}
