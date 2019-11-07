package fr.unice.polytech.credirama.bank.cli.entities.dto;

import fr.unice.polytech.credirama.bank.cli.entities.Account;
import fr.unice.polytech.credirama.bank.cli.entities.Client;
import fr.unice.polytech.credirama.bank.cli.entities.Transaction;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PrettyDumpResponse {

    private List<Client> clients;

    private List<Account> accounts;

    private List<Transaction> transactions;

}
