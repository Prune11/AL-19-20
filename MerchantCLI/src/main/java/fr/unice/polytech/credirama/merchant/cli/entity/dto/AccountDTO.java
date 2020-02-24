package fr.unice.polytech.credirama.merchant.cli.entity.dto;

import fr.unice.polytech.credirama.merchant.cli.entity.dto.analyse.contract.Contract;
import lombok.*;

import java.util.List;

@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private int id;

    private int ownerId;

    private Contract contract;

    private List<Integer> transactions;

    private double balance;
}
