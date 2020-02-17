package fr.unice.polytech.credirama.dto;

import fr.unice.polytech.credirama.dto.contract.Contract;
import lombok.*;

import java.util.ArrayList;

@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private int id;

    private Client owner;

    private Contract contract;

    private ArrayList<Long> transactionIDs;

    private double balance;
}
