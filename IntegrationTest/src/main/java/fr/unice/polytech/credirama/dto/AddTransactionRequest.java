package fr.unice.polytech.credirama.dto;

import lombok.*;

@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTransactionRequest {

    private int fromId;

    private int toId;

    private double amount;

    private TransactionType type;

}
