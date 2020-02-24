package fr.unice.polytech.credirama.merchant.cli.entity.dto.analyse.contract;


import fr.unice.polytech.credirama.merchant.cli.entity.Transaction;

import java.util.List;

public abstract class ContractAspect {

    public abstract double calculateFees(double transactionAmount);

    public double calculateFees(Transaction transactionAmount, List<Transaction> account) {
        return 0;
    }

}
