package fr.polytech.unice.credirama.mea.entities.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class AddTransactionRequest {

    private long transactionId;

    private int accountFrom;

    private int accountTo;

    private double amount;

    private double feeAMount;

    public AddTransactionRequest() {

    }

    public AddTransactionRequest(long transactionId, int accountFrom, int accountTo, double amount, double feeAmount) {
        this.transactionId = transactionId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.feeAMount = feeAmount;
    }
}
