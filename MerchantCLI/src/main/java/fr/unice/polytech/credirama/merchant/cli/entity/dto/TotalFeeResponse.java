package fr.unice.polytech.credirama.merchant.cli.entity.dto;

import fr.unice.polytech.credirama.merchant.cli.entity.Transaction;
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
