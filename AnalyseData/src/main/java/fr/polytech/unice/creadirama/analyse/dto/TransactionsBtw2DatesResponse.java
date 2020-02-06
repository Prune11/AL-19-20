package fr.polytech.unice.creadirama.analyse.dto;

import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import lombok.*;
import org.joda.time.DateTime;

import java.util.Map;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionsBtw2DatesResponse {

    private Map<DateTime, ArrayList<Transaction>> transactionPerDay;

}
