package fr.polytech.unice.credirama.transaction.entities.dto;

import fr.polytech.unice.credirama.transaction.entities.Transaction;
import lombok.*;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionsBtw2DatesResponse {

    private Map<GregorianCalendar, List<Transaction>> transactionPerDay;

}
