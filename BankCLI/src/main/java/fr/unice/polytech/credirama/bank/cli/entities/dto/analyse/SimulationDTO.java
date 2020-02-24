package fr.unice.polytech.credirama.bank.cli.entities.dto.analyse;

import fr.unice.polytech.credirama.bank.cli.entities.Transaction;
import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
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

    public SimulationDTO(FeeBtw2Day fees){
        this.dailyResult = new HashMap<>();
        for (DateTime dateTime: fees.getAvgFeeBtw().keySet()){
            String date = dateTime.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")).split(" ")[0];
            this.dailyResult.put(date, new SimulationPerDay(fees.getSumFeeBtwDay().get(dateTime),
                                                            fees.getAvgFeeBtw().get(dateTime),
                                                            /*fees.getMinTransactionBtw().get(dateTime),
                                                            fees.getMaxTransactionFeeBtw().get(dateTime),*/
                                                            fees.getNbTransactionPerDay().get(dateTime)));
        }
        this.globalMaxTransaction = fees.getGlobalMaxTransaction();
        this.globalMinTransaction = fees.getGlobalMinTransaction();
        this.totalNbTransaction = fees.getTotalNbTransaction();
        this.totalAvg = new BigDecimal(fees.getTotalAvg()).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.totalSum = new BigDecimal(fees.getTotalSum()).setScale(2,RoundingMode.HALF_UP).doubleValue();
    }

}
