package fr.unice.polytech.credirama.merchant.cli.entity;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Account {

    private int id;

    private Client owner;

    private Contract contract;

    private List<Transaction> transactions;

    private double balance;

}
