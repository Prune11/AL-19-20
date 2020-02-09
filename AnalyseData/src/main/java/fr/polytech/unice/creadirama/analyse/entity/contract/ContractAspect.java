package fr.polytech.unice.creadirama.analyse.entity.contract;

import fr.polytech.unice.creadirama.analyse.entity.Transaction;

import java.util.List;

public abstract class ContractAspect {

    public abstract double calculateFees(double transactionAmount);

    public double calculateFees(Transaction transactionAmount, List<Transaction> account) {
        return 0;
    }

}
