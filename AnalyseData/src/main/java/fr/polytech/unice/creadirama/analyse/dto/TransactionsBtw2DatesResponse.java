package fr.polytech.unice.creadirama.analyse.dto;

import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionsBtw2DatesResponse {

    private Map<String, List<Transaction>> transactionPerDay;

    public Map<DateTime, List<Transaction>> toDateTime(){
        Map<DateTime, List<Transaction>> result = new HashMap<>();
        for (String timeStamp: this.getTransactionPerDay().keySet()) {
            result.put(DateTime.parse(timeStamp, DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")), this.transactionPerDay.get(timeStamp));
        }
        return result;
    }

}
