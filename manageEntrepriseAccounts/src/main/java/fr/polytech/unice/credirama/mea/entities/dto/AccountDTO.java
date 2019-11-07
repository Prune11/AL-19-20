package fr.polytech.unice.credirama.mea.entities.dto;

import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.Client;
import fr.polytech.unice.credirama.mea.entities.Contract;
import fr.polytech.unice.credirama.mea.entities.Transaction;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private int id;

    private int ownerId;

    private Contract contract;

    private List<Long> transactions;

    private double balance;

    public AccountDTO(Account account) {
        this.ownerId = account.getOwner().getId();
        this.contract = account.getContract();
        this.balance = account.getBalance();
        this.transactions = new ArrayList<>();
        for(Transaction transaction : account.getTransactions()){
           transactions.add(transaction.getId());
        }
    }
}
