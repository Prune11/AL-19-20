package fr.polytech.unice.creadirama.analyse.dto;

import lombok.*;
import org.joda.time.DateTime;

import javax.validation.constraints.PastOrPresent;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionsBtw2DatesRequest {

    private DateTime dateFrom;

    private DateTime dateTo;

}
