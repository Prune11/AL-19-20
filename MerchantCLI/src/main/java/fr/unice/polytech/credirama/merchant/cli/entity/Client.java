package fr.unice.polytech.credirama.merchant.cli.entity;

import lombok.*;

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
