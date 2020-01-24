package fr.polytech.unice.credirama.transaction.entities.dto;

import lombok.*;

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
