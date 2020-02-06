package fr.polytech.unice.credirama.transaction.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

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

    private String timeStamp;


    public Transaction() {

    }

    public Transaction(int accountFrom, int accountTo, double amount, double feeAmount, TransactionType transactionType) {
        this.fromId = accountFrom;
        this.toId = accountTo;
        this.amount = amount;
        this.feeAmount = feeAmount;
        this.transactionType = transactionType;
        this.timeStamp = DateTime.now().toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
    }

    public Transaction(int accountFrom, int accountTo, double amount, TransactionType transactionType) {
        this.fromId = accountFrom;
        this.toId = accountTo;
        this.amount = amount;
        this.feeAmount = 0.0;
        this.transactionType = transactionType;
        this.timeStamp = DateTime.now().toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
    }

    //Only use for testing
    public Transaction(int accountFrom, int accountTo, double amount, double feeAmount, TransactionType transactionType, DateTime date) {
        this.fromId = accountFrom;
        this.toId = accountTo;
        this.amount = amount;
        this.feeAmount = feeAmount;
        this.transactionType = transactionType;
        this.timeStamp = date.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
    }

    public DateTime toDateTime() {
        return DateTime.parse(this.timeStamp, DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
    }

    public void registerDateTime(DateTime dateTime){
        this.timeStamp = dateTime.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
    }
}
