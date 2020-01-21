package fr.polytech.unice.creadirama.analyse.controller;

import fr.polytech.unice.creadirama.analyse.component.AnalyseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/analyse")
public class AnalyseDataController {

    @Autowired
    private AnalyseData analyseData;

    @GetMapping("/hello")
    public String helloWorld() {
        double res = analyseData.getAverageFromTransaction(new ArrayList<>());
        return "World!" + res;
    }
}
