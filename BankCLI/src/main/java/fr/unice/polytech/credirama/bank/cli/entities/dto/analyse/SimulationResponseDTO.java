package fr.unice.polytech.credirama.bank.cli.entities.dto.analyse;

import lombok.*;

import java.util.Map;

@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulationResponseDTO {

    private Map<String, SimulationDTO> response;

}
