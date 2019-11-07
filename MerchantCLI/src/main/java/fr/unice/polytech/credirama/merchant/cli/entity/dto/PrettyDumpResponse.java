package fr.unice.polytech.credirama.merchant.cli.entity.dto;

import fr.unice.polytech.credirama.merchant.cli.entity.Transaction;
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
