package fr.polytech.unice.credirama.mea.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Account {
    @Id
    private long id;
    private String owner;
    private Contract contract;
    private double balance;

    public Account(String owner, Contract contract, double balance) {
        this.owner = owner;
        this.contract = contract;
        this.balance = balance;
    }
}
