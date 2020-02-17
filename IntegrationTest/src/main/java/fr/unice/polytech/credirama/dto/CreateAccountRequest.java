package fr.unice.polytech.credirama.dto;

import fr.unice.polytech.credirama.dto.contract.Contract;
import lombok.*;

@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountRequest {

    private Contract contract;

    private int owner;

}
