package fr.polytech.unice.credirama.mea.entities.dto;

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

    public MEAAddTransactionRequest() {

    }

    public MEAAddTransactionRequest(long transactionId, int accountFrom, int accountTo, double amount){
        this.transactionId = transactionId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }
}
