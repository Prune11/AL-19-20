package fr.polytech.unice.credirama.mea.entities.dto;

import fr.polytech.unice.credirama.mea.entities.Account;
import fr.polytech.unice.credirama.mea.entities.contract.Contract;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

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
        this.id = account.getId();
        this.ownerId = account.getOwner().getId();
        this.contract = account.getContract();
        this.balance = new BigDecimal(account.getBalance()).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.transactions = new ArrayList<>();
        for(Long transaction : account.getTransactions()){
            transactions.add(transaction);
        }
    }
}
