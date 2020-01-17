package fr.polytech.unice.credirama.transaction.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class MEAAddTransactionRequest {

    private long transactionId;

    private int accountFrom;

    private int accountTo;

    private double amount;

    private double feeAmount;

    public MEAAddTransactionRequest() {

    }

    public MEAAddTransactionRequest(long transactionId, int accountFrom, int accountTo, double amount, double feeAmount) {
        this.transactionId = transactionId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.feeAmount = feeAmount;
    }
}
