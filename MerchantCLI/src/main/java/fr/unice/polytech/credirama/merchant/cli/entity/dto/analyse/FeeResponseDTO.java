package fr.unice.polytech.credirama.merchant.cli.entity.dto.analyse;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class FeeResponseDTO {

    private String date;

    private int accountId;

    private double sum;

    private double avg;

    private int nbTransaction;

    public FeeResponseDTO(DateTime date, int accountId, double sum, double avg, int nbTransaction){
        this.date = date.toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss"));
        this.accountId = accountId;
        this.sum = new BigDecimal(sum).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.avg = new BigDecimal(avg).setScale(2,RoundingMode.HALF_UP).doubleValue();
        this.nbTransaction = nbTransaction;
    }

}
