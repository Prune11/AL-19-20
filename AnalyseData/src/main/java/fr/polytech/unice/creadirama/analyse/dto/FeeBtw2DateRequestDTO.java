package fr.polytech.unice.creadirama.analyse.dto;

import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import javax.validation.constraints.PastOrPresent;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class FeeBtw2DateRequestDTO {

    //@PastOrPresent
    private String from;

    //@PastOrPresent
    private String to;

    private int accountId;

    public DateTime getDateTimeFrom() {
        return DateTime.parse(from, DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
    }

    public DateTime getDateTimeTo() {
        return DateTime.parse(to, DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
    }

}
