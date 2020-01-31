package fr.polytech.unice.creadirama.analyse.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.PastOrPresent;
import java.util.Calendar;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class FeeBtw2DateRequestDTO {

    @PastOrPresent
    private Calendar from;

    @PastOrPresent
    private Calendar to;

    private int accountId;

}
