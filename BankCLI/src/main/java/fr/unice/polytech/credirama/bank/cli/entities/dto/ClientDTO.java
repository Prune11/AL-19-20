package fr.unice.polytech.credirama.bank.cli.entities.dto;

import lombok.*;

import java.util.List;

@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private int id;

    private String name;

    private List<Integer> accountIds;
}
