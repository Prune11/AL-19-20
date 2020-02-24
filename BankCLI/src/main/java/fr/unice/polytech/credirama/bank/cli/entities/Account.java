package fr.unice.polytech.credirama.bank.cli.entities;

import fr.unice.polytech.credirama.bank.cli.entities.dto.analyse.contract.Contract;
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

    private List<Long> transactions;

    private double balance;

}
