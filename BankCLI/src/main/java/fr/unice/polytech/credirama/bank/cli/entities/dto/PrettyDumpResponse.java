package fr.unice.polytech.credirama.bank.cli.entities.dto;

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

    private List<ClientDTO> clients;

    private List<AccountDTO> accounts;

    private List<Transaction> transactions;

    private String timestamp;

}
