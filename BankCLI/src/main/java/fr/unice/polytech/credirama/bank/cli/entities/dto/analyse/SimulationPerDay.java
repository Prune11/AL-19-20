package fr.unice.polytech.credirama.bank.cli.entities.dto.analyse;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SimulationPerDay {

    private double sum;

    private double avg;

    /*private Transaction minTransaction;

    private Transaction maxTransaction;*/

    private int nbTransaction;

}
