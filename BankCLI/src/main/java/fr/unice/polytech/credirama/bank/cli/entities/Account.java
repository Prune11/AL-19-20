package fr.unice.polytech.credirama.bank.cli.entities;

import lombok.*;

import java.util.ArrayList;
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
