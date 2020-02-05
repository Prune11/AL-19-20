package fr.polytech.unice.creadirama.analyse.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.validation.constraints.PastOrPresent;
import java.util.Calendar;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class FeeBtw2DateRequestDTO {

    @PastOrPresent
    private DateTime from;

    @PastOrPresent
    private DateTime to;

    private int accountId;

}
