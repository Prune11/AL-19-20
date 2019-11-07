package fr.polytech.unice.credirama.mea.entities.dto;

import fr.polytech.unice.credirama.mea.entities.Client;
import fr.polytech.unice.credirama.mea.entities.Transaction;
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

