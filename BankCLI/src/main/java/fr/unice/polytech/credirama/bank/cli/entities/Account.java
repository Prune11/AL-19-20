package fr.unice.polytech.credirama.bank.cli.entities;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private int id;

    private Client owner;

    private Contract contract;

    private List<Transaction> transactions;

    private double balance;

    public Account(){

    }

    public Account(Client owner, Contract contract, double balance) {
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

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
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

}
