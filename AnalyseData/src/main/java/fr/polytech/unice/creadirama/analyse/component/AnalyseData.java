package fr.polytech.unice.creadirama.analyse.component;

import fr.polytech.unice.creadirama.analyse.entity.Transaction;

import java.util.List;

public interface AnalyseData {

    double getAverageFromTransaction(List<Transaction> transactions);

}
