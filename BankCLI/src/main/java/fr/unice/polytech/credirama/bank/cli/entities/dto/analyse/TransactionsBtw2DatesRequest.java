package fr.unice.polytech.credirama.bank.cli.entities.dto.analyse;

import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionsBtw2DatesRequest {

    private DateTime dateFrom;

    private DateTime dateTo;

    public Map<String, String> toSend(){
        Map<String, String> result = new HashMap<>();
        result.put("dateFrom", dateFrom.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")));
        result.put("dateTo", dateTo.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")));
        return result;
    }

}
