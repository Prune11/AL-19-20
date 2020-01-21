package fr.polytech.unice.creadirama.analyse.component.impl;

import fr.polytech.unice.creadirama.analyse.component.AnalyseData;
import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnalyseDataImpl implements AnalyseData {
    @Override
    public double getAverageFromTransaction(List<Transaction> transactions) {
        return 0;
    }
}
