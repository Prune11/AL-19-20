package fr.unice.polytech.credirama.merchant.cli.entity;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Transaction {

    private long id;

    private double amount;

    private int fromId;

    private int toId;

    private double feeAmount;

    private TransactionType transactionType;

    private Date date;

}
