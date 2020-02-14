package fr.polytech.unice.creadirama.analyse.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class FeeBtw2DateResponseDTO {

    private String from;

    private String to;

    private int accountId;

    private Map<String, Double> sum;

    private Map<String, Double> avg;

    private double totalSum;

    private double totalAvg;

    private Map<String, Integer> nbTransactionPerDay;

    private int totalNbTransaction;

    public FeeBtw2DateResponseDTO(DateTime from, DateTime to, int accountId, Map<DateTime, Double> sum,
                                  Map<DateTime, Double> avg, double totalSum, double totalAvg,
                                  Map<DateTime, Integer> nbTransactionPerDay, int totalNbTransaction){
        this.from = from.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
        this.to = to.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
        this.accountId = accountId;
        this.sum = new HashMap<>();
        for (DateTime datetime: sum.keySet()) {
            this.sum.put(datetime.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")), sum.get(datetime));
        }
        this.avg = new HashMap<>();
        for (DateTime datetime: avg.keySet()) {
            this.avg.put(datetime.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")), avg.get(datetime));
        }
        this.totalSum = totalSum;
        this.totalAvg = totalAvg;
        this.nbTransactionPerDay = new HashMap<>();
        for (DateTime datetime: nbTransactionPerDay.keySet()) {
            this.nbTransactionPerDay.put(datetime.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")), nbTransactionPerDay.get(datetime));
        }
        this.totalNbTransaction = totalNbTransaction;
    }

}
