package fr.polytech.unice.credirama.mea.entities;

import lombok.*;

import javax.persistence.*;

@ToString
@EqualsAndHashCode
@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int fromId;

    private int toId;

    private double amount;

    private TransactionType transactionType;

    private double feeAmount;

    /**
     * TODO: Add Date?
     */


    public Transaction() {

    }

    public Transaction(int accountFrom, int accountTo, double amount, double feeAmount, TransactionType transactionType) {
        this.fromId = accountFrom;
        this.toId = accountTo;
        this.amount = amount;
        this.feeAmount = feeAmount;
        this.transactionType = transactionType;
    }
}
