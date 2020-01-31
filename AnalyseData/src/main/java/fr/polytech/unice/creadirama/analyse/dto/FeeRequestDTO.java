package fr.polytech.unice.creadirama.analyse.dto;

import lombok.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.util.Calendar;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class FeeRequestDTO {

    private int accountId;

    @PastOrPresent
    private Calendar date;

}
