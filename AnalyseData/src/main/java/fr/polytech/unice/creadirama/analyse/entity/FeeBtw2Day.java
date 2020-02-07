package fr.polytech.unice.creadirama.analyse.entity;

import lombok.*;
import org.joda.time.DateTime;

import java.util.Map;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FeeBtw2Day {

    private Map<DateTime, Double> sumFeeBtwDay;

    private Map<DateTime, Double> avgFeeBtw;

    private Map<DateTime, Transaction> minTransactionBtw;

    private Map<DateTime, Transaction> maxTransactionFeeBtw;

    private double totalSum;

    private double totalAvg;

    private Transaction globalMinTransaction;

    private Transaction globalMaxTransaction;

    private Map<DateTime, Integer> nbTransactionPerDay;

    private int totalNbTransaction;

}
