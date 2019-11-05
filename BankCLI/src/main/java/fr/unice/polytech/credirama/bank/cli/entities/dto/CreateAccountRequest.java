package fr.unice.polytech.credirama.bank.cli.entities.dto;

import fr.unice.polytech.credirama.bank.cli.entities.Contract;

import java.util.Objects;

public class CreateAccountRequest {

    private int clientId;

    private Contract contract;

    public CreateAccountRequest(int owner, Contract contract) {
        this.clientId = owner;
        this.contract = contract;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAccountRequest that = (CreateAccountRequest) o;
        return Objects.equals(clientId, that.clientId) &&
                contract == that.contract;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, contract);
    }

    @Override
    public String toString() {
        return "CreateAccountRequest{" +
                "owner='" + clientId + '\'' +
                ", contract=" + contract +
                '}';
    }
}
