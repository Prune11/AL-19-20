package fr.polytech.unice.credirama.mea.entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString
@EqualsAndHashCode
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany
    private List<Account> accountList;

    private Contract contract;
}
