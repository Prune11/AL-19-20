package fr.unice.polytech.credirama.merchant.cli.entity.dto.analyse;

import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

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
