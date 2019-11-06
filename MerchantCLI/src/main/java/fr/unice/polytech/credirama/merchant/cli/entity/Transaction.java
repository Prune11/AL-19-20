package fr.unice.polytech.credirama.merchant.cli.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Transaction {

    private long id;

    private int accountFrom;

    private int accountTo;

    private double amount;

    private double feeAmount;

    private TransactionType transactionType;

}
