package fr.polytech.unice.credirama.mea.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String owner;

    @Enumerated(EnumType.STRING)
    private Contract contract;

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Transaction> transactions;

    private double balance;

    public Account(){

    }

    public Account(String owner, Contract contract, double balance) {
        this.owner = owner;
        this.contract = contract;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> addTransaction(Transaction transaction){
        this.transactions.add(transaction);
        if(transaction.getAccountTo() == this.id){
            this.balance += transaction.getAmount();
        } else {
            this.balance -= transaction.getAmount();
        }
        return this.transactions;
    }
}
