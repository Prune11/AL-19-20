package fr.unice.polytech.credirama.bank.cli.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Transaction {

    private long id;

    private int fromId;

    private int toId;

    private double amount;

    private double feeAmount;

    private TransactionType transactionType;

}
