package fr.polytech.unice.creadirama.analyse.entity;

import lombok.*;
import org.joda.time.DateTime;

import java.util.Map;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FeeBtw2Day {

    private Map<DateTime, Double> sumFeeBtwDay;

    private Map<DateTime, Double> avgFeeBtw;

}
