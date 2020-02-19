package fr.polytech.unice.creadirama.analyse.dto;

import fr.polytech.unice.creadirama.analyse.entity.FeeBtw2Day;
import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.HashMap;
import java.util.List;
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
        this.totalAvg = fees.getTotalAvg();
        this.totalSum = fees.getTotalSum();
    }

}
