package fr.polytech.unice.credirama.transaction.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CrediramaDate date;


    public Transaction() {

    }

    public Transaction(int accountFrom, int accountTo, double amount, double feeAmount, TransactionType transactionType) {
        this.fromId = accountFrom;
        this.toId = accountTo;
        this.amount = amount;
        this.feeAmount = feeAmount;
        this.transactionType = transactionType;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        this.date = new CrediramaDate(cal);
        this.date.setTransaction(this);
    }

    public Transaction(int accountFrom, int accountTo, double amount, TransactionType transactionType) {
        this.fromId = accountFrom;
        this.toId = accountTo;
        this.amount = amount;
        this.feeAmount = 0.0;
        this.transactionType = transactionType;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        this.date = new CrediramaDate(cal);
        this.date.setTransaction(this);
    }

    //Only use for testing
    public Transaction(int accountFrom, int accountTo, double amount, double feeAmount, TransactionType transactionType, GregorianCalendar calendar) {
        this.fromId = accountFrom;
        this.toId = accountTo;
        this.amount = amount;
        this.feeAmount = feeAmount;
        this.transactionType = transactionType;
        this.date = new CrediramaDate(calendar);
        this.date.setTransaction(this);
    }
}
