package fr.unice.polytech.credirama.dto;

import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeBtw2DateRequest {

    private String from;

    private String to;

    private int accountId;

    public DateTime getDateTimeFrom() {
        return DateTime.parse(from, DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
    }

    public DateTime getDateTimeTo() {
        return DateTime.parse(to, DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
    }

}
