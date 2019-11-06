package fr.polytech.unice.credirama.mea.entities.dto;

import fr.polytech.unice.credirama.mea.entities.Contract;

import java.util.Objects;

public class CreateAccountRequest {

    private int owner;

    private Contract contract;

    public CreateAccountRequest(int owner, Contract contract) {
        this.owner = owner;
        this.contract = contract;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
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
        return Objects.equals(owner, that.owner) &&
                contract == that.contract;
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, contract);
    }

    @Override
    public String toString() {
        return "CreateAccountRequest{" +
                "owner='" + owner + '\'' +
                ", contract=" + contract +
                '}';
    }
}
