package fr.polytech.unice.creadirama.analyse.dto;

import lombok.*;

import javax.validation.constraints.PastOrPresent;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionsBtw2DatesRequest {

    private Calendar dateFrom;

    private Calendar dateTo;

}
