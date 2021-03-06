package fr.polytech.unice.creadirama.analyse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@ToString
@EqualsAndHashCode
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
        this.amount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.feeAmount = new BigDecimal(feeAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.transactionType = transactionType;
        this.timeStamp = DateTime.now().toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
    }

    public Transaction(int accountFrom, int accountTo, double amount, TransactionType transactionType) {
        this.fromId = accountFrom;
        this.toId = accountTo;
        this.amount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.feeAmount = 0.0;
        this.transactionType = transactionType;
        this.timeStamp = DateTime.now().toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
    }

    //Only use for testing
    public Transaction(int accountFrom, int accountTo, double amount, double feeAmount, TransactionType transactionType, DateTime date) {
        this.fromId = accountFrom;
        this.toId = accountTo;
        this.amount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.feeAmount = new BigDecimal(feeAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.transactionType = transactionType;
        this.timeStamp = date.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
    }

    public DateTime toDateTime() {
        return DateTime.parse(this.timeStamp, DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
    }
}
