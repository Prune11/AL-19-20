package fr.unice.polytech.credirama.bank.cli.entities;

import lombok.*;

import java.util.Date;

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

    private Date date;

}
