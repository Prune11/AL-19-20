package fr.polytech.unice.credirama.transaction.entities.dto;

import fr.polytech.unice.credirama.transaction.entities.Transaction;
import lombok.*;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionsBtw2DatesResponse {

    private Map<String, List<Transaction>> transactionPerDay;

}
