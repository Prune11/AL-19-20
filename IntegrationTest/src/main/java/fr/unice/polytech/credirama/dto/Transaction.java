package fr.unice.polytech.credirama.dto;

import lombok.*;

@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private long id;

    private int fromId;

    private int toId;

    private double amount;

    private TransactionType transactionType;

    private double feeAmount;

    private String timeStamp;

}
