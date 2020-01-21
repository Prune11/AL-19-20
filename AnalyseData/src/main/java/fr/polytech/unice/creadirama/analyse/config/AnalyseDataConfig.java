package fr.polytech.unice.creadirama.analyse.config;

import fr.polytech.unice.creadirama.analyse.component.AnalyseData;
import fr.polytech.unice.creadirama.analyse.component.impl.AnalyseDataImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnalyseDataConfig {

    @Bean
    public AnalyseData analyseData() {
        return new AnalyseDataImpl();
    }

}
