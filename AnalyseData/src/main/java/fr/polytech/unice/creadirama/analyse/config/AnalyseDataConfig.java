package fr.polytech.unice.creadirama.analyse.config;

import com.google.common.base.Predicates;
import fr.polytech.unice.creadirama.analyse.component.AnalyseData;
import fr.polytech.unice.creadirama.analyse.component.impl.AnalyseDataImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
/*import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;*/

@Configuration
public class AnalyseDataConfig {

    @Bean
    public AnalyseData analyseData() {
        return new AnalyseDataImpl();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }*/

}
