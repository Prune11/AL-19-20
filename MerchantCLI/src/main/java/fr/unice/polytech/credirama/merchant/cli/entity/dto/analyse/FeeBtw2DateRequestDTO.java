package fr.unice.polytech.credirama.merchant.cli.entity.dto.analyse;

import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

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

}
