package fr.unice.polytech.credirama.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SimulationPerDay {

    private double sum;

    private double avg;

    private int nbTransaction;

}
