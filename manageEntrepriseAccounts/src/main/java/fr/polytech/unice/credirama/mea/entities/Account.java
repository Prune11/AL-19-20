package fr.polytech.unice.credirama.mea.entities;

import fr.polytech.unice.credirama.mea.entities.dto.AddTransactionRequest;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@EqualsAndHashCode
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client owner;

    @Enumerated(EnumType.STRING)
    private Contract contract;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Long> transactionIDs;

    private double balance;

    public Account() {

    }

    public Account(Client owner, Contract contract, double balance) {
        this.owner = owner;
        this.contract = contract;
        this.balance = balance;
        this.transactionIDs = new ArrayList<>();
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

    public List<Long> getTransactions() {
        return transactionIDs;
    }

    public Account updateForSave() {
        this.owner.addAccount(this);
        return this;
    }

    public Account resultToSend() {
        this.owner.setAccountList(new ArrayList<>());
        return this;
    }

    public List<Long> addTransaction(AddTransactionRequest transactionRequest) {
        this.transactionIDs.add(transactionRequest.getTransactionId());
        if (transactionRequest.getAccountTo() == this.id) {
            this.balance += transactionRequest.getAmount();
        } else {
            this.balance -= transactionRequest.getAmount();
            this.balance -= transactionRequest.getFeeAMount();
        }
        return this.transactionIDs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Double.compare(account.balance, balance) == 0 &&
                Objects.equals(owner, account.owner) &&
                contract == account.contract &&
                Objects.equals(transactionIDs, account.transactionIDs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, contract, transactionIDs, balance);
    }
}
