package fr.unice.polytech.credirama.bank.cli.entities.dto;

import fr.unice.polytech.credirama.bank.cli.entities.TransactionType;
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

    private int fees;

    public TransactionRequest(double amount, TransactionType type, int fromId, int toId) {
        this.amount = amount;
        this.type = type;
        this.fromId = fromId;
        this.toId = toId;
        this.fees = 0;
    }
}
