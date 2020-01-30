package fr.polytech.unice.credirama.transaction.entities.dto;

import lombok.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionsBtw2DatesRequest {

    private GregorianCalendar dateFrom;

    private GregorianCalendar dateTo;

}
