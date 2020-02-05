package fr.polytech.unice.creadirama.analyse.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Map;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class FeeBtw2DateResponseDTO {

    private DateTime from;

    private DateTime to;

    private int accountId;

    private Map<DateTime, Double> sum;

    private Map<DateTime, Double> avg;

    private double totalSum;

    private double totalAvg;

    private Map<DateTime, Integer> nbTransactionPerDay;

    private int totalNbTransaction;

}
