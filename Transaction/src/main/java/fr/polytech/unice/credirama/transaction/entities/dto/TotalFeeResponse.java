package fr.polytech.unice.credirama.transaction.entities.dto;

import fr.polytech.unice.credirama.transaction.entities.Transaction;
import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TotalFeeResponse {

    private Map<Transaction, Double> fees;

}
