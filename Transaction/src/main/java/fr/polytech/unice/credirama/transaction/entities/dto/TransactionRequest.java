package fr.polytech.unice.credirama.transaction.entities.dto;

import fr.polytech.unice.credirama.transaction.entities.Contract;
import fr.polytech.unice.credirama.transaction.entities.TransactionType;
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

    private double fees;

    private Contract contract;

}
