package fr.polytech.unice.credirama.mea.entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@EqualsAndHashCode
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Account> accountList;

    public Client(){

    }

    public Client(String name){
        this.name = name;
        this.accountList = new ArrayList<>();
    }

    public Client cloneForPrettyDump(){
        Client client = new Client();
        client.setId(this.id);
        client.setName(this.name);
        client.setAccountList(null);
        return client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public void addAccount(Account account){
        this.accountList.add(account);
    }

    public void removeAccount(Account account){
        this.accountList.remove(account);
    }

    public Client resultToSend(){
        for (Account account : accountList){
            account.setOwner(null);
        }
        return this;
    }
}
