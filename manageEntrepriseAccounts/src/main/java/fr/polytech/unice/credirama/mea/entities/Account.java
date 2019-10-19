package fr.polytech.unice.credirama.mea.entities;

import lombok.*;

import javax.persistence.*;

@ToString
@EqualsAndHashCode
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String owner;

    @Enumerated(EnumType.STRING)
    private Contract contract;

    private double balance;

    public Account(){

    }

    public Account(String owner, Contract contract, double balance) {
        this.owner = owner;
        this.contract = contract;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
