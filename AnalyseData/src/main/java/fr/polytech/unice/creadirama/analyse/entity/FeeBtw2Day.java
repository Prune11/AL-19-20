package fr.polytech.unice.creadirama.analyse.entity;

import lombok.*;

import java.util.Calendar;
import java.util.Map;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FeeBtw2Day {

    private Map<Calendar, Double> sumFeeBtwDay;

    private Map<Calendar, Double> avgFeeBtw;

}
