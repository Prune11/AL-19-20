package fr.polytech.unice.creadirama.analyse.dto;

import lombok.*;
import org.joda.time.DateTime;

import javax.validation.constraints.PastOrPresent;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class FeeRequestDTO {

    private int accountId;

    @PastOrPresent
    private DateTime date;

}
