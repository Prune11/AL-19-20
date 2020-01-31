package fr.polytech.unice.creadirama.analyse.dto;

import lombok.*;

import java.util.Calendar;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class FeeResponseDTO {

    private Calendar date;

    private int accountId;

    private double sum;

    private double avg;

    private int nbTransaction;

}
