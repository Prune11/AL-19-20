package fr.polytech.unice.creadirama.analyse.dto;

import lombok.*;
import org.joda.time.DateTime;

import java.util.Calendar;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class FeeResponseDTO {

    private DateTime date;

    private int accountId;

    private double sum;

    private double avg;

    private int nbTransaction;

}
