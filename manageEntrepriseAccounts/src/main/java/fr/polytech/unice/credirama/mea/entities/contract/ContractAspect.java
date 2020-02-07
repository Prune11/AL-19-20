package fr.polytech.unice.credirama.mea.entities.contract;

import fr.polytech.unice.credirama.mea.entities.Account;

public abstract class ContractAspect {

    public abstract double calculateFees(double transactionAmount);

    public double calculateFees(double transactionAmount, Account account) {
        return 0;
    }

}
