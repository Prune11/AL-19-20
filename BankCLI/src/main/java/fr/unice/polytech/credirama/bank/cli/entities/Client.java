package fr.unice.polytech.credirama.bank.cli.entities;

import java.util.List;
import java.util.Objects;

public class Client {

    private String name;

    private List<Account> accounts;

    private int clientId;

    public Client(String name, List<Account> accounts, int clientId) {
        this.name = name;
        this.accounts = accounts;
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return clientId == client.clientId &&
                Objects.equals(name, client.name) &&
                Objects.equals(accounts, client.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, accounts, clientId);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", accounts=" + accounts +
                ", clientId=" + clientId +
                '}';
    }


}
