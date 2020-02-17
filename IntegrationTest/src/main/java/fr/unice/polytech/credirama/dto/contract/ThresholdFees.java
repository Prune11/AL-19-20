package fr.unice.polytech.credirama.dto.contract;

import lombok.*;

@ToString
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThresholdFees extends ContractAspect {

    private double lowerPercentage;
    private double upperPercentage;
    private double threshold;


    @Override
    public double calculateFees(double transactionAmount) {
        if (transactionAmount > threshold) {
            return transactionAmount * upperPercentage / 100;
        } else {
            return transactionAmount * lowerPercentage / 100;
        }
    }
}
