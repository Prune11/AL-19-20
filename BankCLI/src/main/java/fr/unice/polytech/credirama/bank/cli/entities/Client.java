package fr.unice.polytech.credirama.bank.cli.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Client {

    private int id;

    private String name;

    private List<Account> accountList;

}
