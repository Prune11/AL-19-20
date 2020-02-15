package fr.polytech.unice.creadirama.analyse.dto;

import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Calendar;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class FeeResponseDTO {

    private String date;

    private int accountId;

    private double sum;

    private double avg;

    private int nbTransaction;

    public FeeResponseDTO(DateTime date, int accountId, double sum, double avg, int nbTransaction){
        this.date = date.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
        this.accountId = accountId;
        this.sum = sum;
        this.avg = avg;
        this.nbTransaction = nbTransaction;
    }

}
