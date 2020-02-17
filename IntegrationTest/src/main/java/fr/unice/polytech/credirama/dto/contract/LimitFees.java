package fr.unice.polytech.credirama.dto.contract;

import fr.unice.polytech.credirama.dto.Account;
import lombok.*;

/**
 * * STONE : 5% de frais si en dessous de 5 transactions/jour sinon 10%
 */
@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitFees extends ContractAspect {

    private double lowerPercentage;

    private double upperPercentage;

    private int transactionLimit;

    @Override
    public double calculateFees(double transactionAmount) {
        return 0;
    }
}
