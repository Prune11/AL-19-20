package fr.unice.polytech.credirama.dto;

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
