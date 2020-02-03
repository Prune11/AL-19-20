package fr.polytech.unice.credirama.transaction.entities.dto;

import fr.polytech.unice.credirama.transaction.entities.CrediramaDate;
import fr.polytech.unice.credirama.transaction.entities.Transaction;
import lombok.*;

import java.util.ArrayList;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionsBtw2DatesResponse {

    private Map<CrediramaDate, ArrayList<Transaction>> transactionPerDay;

}
