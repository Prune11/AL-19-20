package fr.unice.polytech.credirama.dto;

import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SimulationDTO {

    private Map<String, SimulationPerDay> dailyResult;

    private double totalSum;

    private double totalAvg;

    private Transaction globalMinTransaction;

    private Transaction globalMaxTransaction;

    private int totalNbTransaction;

}
