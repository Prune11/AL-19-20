package fr.polytech.unice.credirama.transaction.entities.dto;

import lombok.*;
import org.joda.time.DateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionsBtw2DatesRequest {

    private DateTime dateFrom;

    private DateTime dateTo;

}
