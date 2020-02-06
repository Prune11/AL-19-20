package fr.polytech.unice.creadirama.analyse.dto;

import fr.polytech.unice.creadirama.analyse.entity.Transaction;
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

    private Map<DateTime, List<Transaction>> transactionPerDay;

}
