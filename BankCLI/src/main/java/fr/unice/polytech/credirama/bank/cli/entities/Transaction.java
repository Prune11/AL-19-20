package fr.unice.polytech.credirama.bank.cli.entities;

import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Transaction {

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
