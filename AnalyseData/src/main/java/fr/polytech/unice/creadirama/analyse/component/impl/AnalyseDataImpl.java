package fr.polytech.unice.creadirama.analyse.component.impl;

import fr.polytech.unice.creadirama.analyse.component.AnalyseData;
import fr.polytech.unice.creadirama.analyse.dto.SimulationDTO;
import fr.polytech.unice.creadirama.analyse.entity.FeeBtw2Day;
import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import fr.polytech.unice.creadirama.analyse.entity.contract.Contract;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AnalyseDataImpl implements AnalyseData {

    @Override
    public double sumFeesPerDay(List<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getFeeAmount).sum();
    }

    @Override
    public double avgFeePerDay(List<Transaction> transactions) {
        return sumFeesPerDay(transactions) / transactions.size();
    }

    @Override
    public Transaction minTransactionFee(List<Transaction> transactions) {
        return transactions.stream().min(Comparator.comparingDouble(Transaction::getFeeAmount)).orElse(new Transaction());
    }

    @Override
    public Transaction maxTransactionFee(List<Transaction> transactions) {
        return transactions.stream().max(Comparator.comparingDouble(Transaction::getFeeAmount)).orElse(new Transaction());
    }

    @Override
    public FeeBtw2Day sumBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day) {
        Map<DateTime, Double> sumBtw2Date = new HashMap<>();
        for (DateTime dateTime : transactionPerDay.keySet())
            sumBtw2Date.put(dateTime, sumFeesPerDay(transactionPerDay.get(dateTime)));
        feeBtw2Day.setSumFeeBtwDay(sumBtw2Date);
        double totalSum = 0.0;
        for (List<Transaction> transactions : transactionPerDay.values())
            totalSum += transactions.stream().mapToDouble(Transaction::getFeeAmount).sum();
        feeBtw2Day.setTotalSum(totalSum);
        return feeBtw2Day;
    }

    @Override
    public FeeBtw2Day avgBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day) {
        Map<DateTime, Double> avgBtw2Date = new HashMap<>();
        for (DateTime dateTime : transactionPerDay.keySet())
            avgBtw2Date.put(dateTime, avgFeePerDay(transactionPerDay.get(dateTime)));
        feeBtw2Day.setAvgFeeBtw(avgBtw2Date);
        double totalAvg = 0.0;
        for (List<Transaction> transactions : transactionPerDay.values())
            totalAvg += transactions.stream().mapToDouble(Transaction::getFeeAmount).sum() / transactions.size();
        feeBtw2Day.setTotalAvg(totalAvg);
        return feeBtw2Day;
    }

    @Override
    public FeeBtw2Day minBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day) {
        Map<DateTime, Transaction> minBtw2Date = new HashMap<>();
        for (DateTime dateTime : transactionPerDay.keySet())
            minBtw2Date.put(dateTime, minTransactionFee(transactionPerDay.get(dateTime)));
        feeBtw2Day.setMinTransactionBtw(minBtw2Date);
        List<Transaction> transactionsList = new ArrayList<>();
        for (List<Transaction> transactions : transactionPerDay.values())
            transactionsList.add(minTransactionFee(transactions));
        feeBtw2Day.setGlobalMinTransaction(minTransactionFee(transactionsList));
        return feeBtw2Day;
    }

    @Override
    public FeeBtw2Day maxBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day) {
        Map<DateTime, Transaction> maxBtw2Date = new HashMap<>();
        for (DateTime dateTime : transactionPerDay.keySet())
            maxBtw2Date.put(dateTime, maxTransactionFee(transactionPerDay.get(dateTime)));
        feeBtw2Day.setMaxTransactionFeeBtw(maxBtw2Date);
        List<Transaction> transactionsList = new ArrayList<>();
        for (List<Transaction> transactions : transactionPerDay.values())
            transactionsList.add(maxTransactionFee(transactions));
        feeBtw2Day.setGlobalMaxTransaction(maxTransactionFee(transactionsList));
        return feeBtw2Day;
    }

    @Override
    public Map<String, SimulationDTO>  simulationWithAnotherContract(Map<DateTime, List<Transaction>> transactionPerDay) {
        Map<String, SimulationDTO>  simulatedMap = new HashMap<>();
        FeeBtw2Day feeBtw2DayWOOD = new FeeBtw2Day();
        FeeBtw2Day feeBtw2DaySTONE = new FeeBtw2Day();
        FeeBtw2Day feeBtw2DayIRON = new FeeBtw2Day();
        FeeBtw2Day feeBtw2DayDIAMOND = new FeeBtw2Day();
        Contract wood = Contract.WOOD;
        Contract stone = Contract.STONE;
        Contract iron = Contract.IRON;
        Contract diamond = Contract.DIAMOND;


        /*Wood making*/
        runSimulation(transactionPerDay, simulatedMap, feeBtw2DayWOOD, wood);

        /*Stone making*/
        runSimulation(transactionPerDay, simulatedMap, feeBtw2DaySTONE, stone);

        /*Iron making*/
        runSimulation(transactionPerDay, simulatedMap, feeBtw2DayIRON, iron);

        /*Diamond making*/
        runSimulation(transactionPerDay, simulatedMap, feeBtw2DayDIAMOND, diamond);

        return simulatedMap;
    }

    private void runSimulation(Map<DateTime, List<Transaction>> transactionPerDay, Map<String, SimulationDTO> simulatedMap, FeeBtw2Day feeBtw2DayType, Contract contract) {
        for (DateTime dateTime : transactionPerDay.keySet()) {
            List<Transaction> transactionList = transactionPerDay.get(dateTime);
            for (Transaction t : transactionList) {
                t.setFeeAmount(contract.getFee(t, transactionList));
            }
        }
        feeBtw2DayType.setTotalNbTransaction(transactionPerDay.size());
        avgBetweenTwoDate(transactionPerDay, feeBtw2DayType);
        sumBetweenTwoDate(transactionPerDay, feeBtw2DayType);
        minBetweenTwoDate(transactionPerDay, feeBtw2DayType);
        maxBetweenTwoDate(transactionPerDay, feeBtw2DayType);
        simulatedMap.put(contract.name(), new SimulationDTO(feeBtw2DayType));
    }
}
