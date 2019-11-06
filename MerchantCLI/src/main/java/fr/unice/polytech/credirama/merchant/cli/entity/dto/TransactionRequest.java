package fr.unice.polytech.credirama.merchant.cli.entity.dto;

import fr.unice.polytech.credirama.merchant.cli.entity.TransactionType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionRequest {

    private double amount;

    private TransactionType type;

    private int fromId;

    private int toId;

}
