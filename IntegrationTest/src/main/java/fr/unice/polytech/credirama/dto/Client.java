package fr.unice.polytech.credirama.dto;

import lombok.*;

import java.util.List;

@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private int id;

    private String name;

    private List<Account> accountList;

}
