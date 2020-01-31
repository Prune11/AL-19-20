package fr.polytech.unice.creadirama.analyse.dto;

import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import lombok.*;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionsBtw2DatesResponse {

    private Map<Calendar, List<Transaction>> transactionPerDay;

}
