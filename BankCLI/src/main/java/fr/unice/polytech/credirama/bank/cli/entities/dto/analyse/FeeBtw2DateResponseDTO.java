package fr.unice.polytech.credirama.bank.cli.entities.dto.analyse;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
                                  Map<DateTime, Integer> nbTransactionPerDay, int totalNbTransaction) {
        this.from = from.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
        this.to = to.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
        this.accountId = accountId;
        this.sum = new HashMap<>();
        for (DateTime datetime : sum.keySet()) {
            this.sum.put(datetime.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")), new BigDecimal(sum.get(datetime)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        }
        this.avg = new HashMap<>();
        for (DateTime datetime : avg.keySet()) {
            this.avg.put(datetime.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")), new BigDecimal(avg.get(datetime)).setScale(2, RoundingMode.HALF_UP).doubleValue());
        }
        this.totalSum = new BigDecimal(totalSum).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.totalAvg = new BigDecimal(totalAvg).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.nbTransactionPerDay = new HashMap<>();
        for (DateTime datetime : nbTransactionPerDay.keySet()) {
            this.nbTransactionPerDay.put(datetime.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")), nbTransactionPerDay.get(datetime));
        }
        this.totalNbTransaction = totalNbTransaction;
    }

}
