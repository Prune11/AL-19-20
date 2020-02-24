package fr.unice.polytech.credirama.bank.cli.entities.dto.analyse;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class FeeBtw2DateRequestDTO {

    //@PastOrPresent
    private String from;

    //@PastOrPresent
    private String to;

    private int accountId;

}
