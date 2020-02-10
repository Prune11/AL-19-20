package fr.polytech.unice.credirama.transaction.entities.dto;

import lombok.*;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionsBtw2DatesRequest {

    private String dateFrom;

    private String dateTo;

}
