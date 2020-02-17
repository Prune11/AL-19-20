package fr.unice.polytech.credirama.dto.contract;

import lombok.*;

@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleFees extends ContractAspect {

    private int percentage;

    @Override
    public double calculateFees(double transactionAmount) {
        return transactionAmount * percentage / 100;
    }
}
