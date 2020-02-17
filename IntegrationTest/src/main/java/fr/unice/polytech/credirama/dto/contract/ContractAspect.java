package fr.unice.polytech.credirama.dto.contract;

import fr.unice.polytech.credirama.dto.Account;

public abstract class ContractAspect {

    public abstract double calculateFees(double transactionAmount);

    public double calculateFees(double transactionAmount, Account account) {
        return 0;
    }

}
