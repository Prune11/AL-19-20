package fr.polytech.unice.creadirama.analyse.dto;

import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import javax.validation.constraints.PastOrPresent;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class FeeRequestDTO {

    private int accountId;

    //@PastOrPresent
    private String date;

    public DateTime getDateTime() {
        return DateTime.parse(date, DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
    }

}
