package fr.polytech.unice.credirama.transaction.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;

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

    private Calendar date;


    public Transaction() {

    }

    public Transaction(int accountFrom, int accountTo, double amount, double feeAmount, TransactionType transactionType) {
        this.fromId = accountFrom;
        this.toId = accountTo;
        this.amount = amount;
        this.feeAmount = feeAmount;
        this.transactionType = transactionType;
        this.date = Calendar.getInstance();
        this.date.setTime(new Date());
    }

    public Transaction(int accountFrom, int accountTo, double amount, TransactionType transactionType) {
        this.fromId = accountFrom;
        this.toId = accountTo;
        this.amount = amount;
        this.feeAmount = 0.0;
        this.transactionType = transactionType;
        this.date = Calendar.getInstance();
        this.date.setTime(new Date());
    }
}
