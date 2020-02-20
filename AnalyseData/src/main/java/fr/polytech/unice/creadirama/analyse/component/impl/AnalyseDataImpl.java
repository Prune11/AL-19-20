package fr.polytech.unice.creadirama.analyse.component.impl;

import fr.polytech.unice.creadirama.analyse.component.AnalyseData;
import fr.polytech.unice.creadirama.analyse.dto.*;
import fr.polytech.unice.creadirama.analyse.entity.FeeBtw2Day;
import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import fr.polytech.unice.creadirama.analyse.entity.contract.Contract;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.*;

@Component
public class AnalyseDataImpl implements AnalyseData {

    @Override
    public double sumFeesPerDay(List<Transaction> transactions) {
        if (transactions.isEmpty()) return 0;
        return new BigDecimal(transactions.stream().filter(Objects::nonNull).mapToDouble(Transaction::getFeeAmount).sum()).setScale(2,RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public double avgFeePerDay(List<Transaction> transactions) {
        if (transactions.isEmpty()) return 0;
        return new BigDecimal(sumFeesPerDay(transactions) / transactions.size()).setScale(2,RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public Transaction minTransactionFee(List<Transaction> transactions) {
        if (transactions.isEmpty()) return null;
        return transactions.stream().filter(Objects::nonNull).min(Comparator.comparingDouble(Transaction::getFeeAmount)).orElse(null);
    }

    @Override
    public Transaction maxTransactionFee(List<Transaction> transactions) {
        if (transactions.isEmpty()) return null;
        return transactions.stream().filter(Objects::nonNull).max(Comparator.comparingDouble(Transaction::getFeeAmount)).orElse(null);
    }

    @Override
    public FeeBtw2Day sumBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day) {
        Map<DateTime, Double> sumBtw2Date = new HashMap<>();
        for (DateTime dateTime : transactionPerDay.keySet())
            sumBtw2Date.put(dateTime, sumFeesPerDay(transactionPerDay.get(dateTime)));
        feeBtw2Day.setSumFeeBtwDay(sumBtw2Date);
        double totalSum = 0.0;
        for (List<Transaction> transactions : transactionPerDay.values())
            totalSum += new BigDecimal(transactions.stream().mapToDouble(Transaction::getFeeAmount).sum()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        feeBtw2Day.setTotalSum(totalSum);
        return feeBtw2Day;
    }

    @Override
    public FeeBtw2Day avgBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day) {
        Map<DateTime, Double> avgBtw2Date = new HashMap<>();
        for (DateTime dateTime : transactionPerDay.keySet())
            avgBtw2Date.put(dateTime, avgFeePerDay(transactionPerDay.get(dateTime)));
        feeBtw2Day.setAvgFeeBtw(avgBtw2Date);
        if(feeBtw2Day.getTotalNbTransaction() != 0) {
            double totalAvg = new BigDecimal(feeBtw2Day.getTotalSum() / feeBtw2Day.getTotalNbTransaction()).setScale(2,RoundingMode.HALF_UP).doubleValue();
            feeBtw2Day.setTotalAvg(totalAvg);
        } else {
            feeBtw2Day.setTotalAvg(0);
        }
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
    public FeeBtw2Day nbTransactionBetweenTwoDate(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2Day feeBtw2Day) {
        Map<DateTime, Integer> res = new HashMap<>();
        transactionPerDay.keySet().stream().forEach((dateTime -> res.put(dateTime, transactionPerDay.get(dateTime).size())));
        //transactionPerDay.forEach((dateTime, transactions) -> res.put(dateTime, transactions.size()));
        feeBtw2Day.setNbTransactionPerDay(res);
        int totalNbTransaction = 0;
        for (List<Transaction> list : transactionPerDay.values()) {
            totalNbTransaction += list.size();
        }
        feeBtw2Day.setTotalNbTransaction(totalNbTransaction);
        return feeBtw2Day;
    }

    @Override
    public FeeResponseDTO sumFeePerDate(List<Transaction> transactions, FeeRequestDTO request) {
        double sum = new BigDecimal(sumFeesPerDay(transactions)).setScale(2,RoundingMode.HALF_UP).doubleValue();
        double avg = new BigDecimal(avgFeePerDay(transactions)).setScale(2,RoundingMode.HALF_UP).doubleValue();
        return new FeeResponseDTO(request.getDateTime(), request.getAccountId(), sum, avg, transactions.size());
    }

    @Override
    public SimulationDTO sumFeeBtw2Date(Map<DateTime, List<Transaction>> transactionPerDay, FeeBtw2DateRequestDTO request) {
        FeeBtw2Day feeBtw2Day = new FeeBtw2Day();
        nbTransactionBetweenTwoDate(transactionPerDay, feeBtw2Day);
        sumBetweenTwoDate(transactionPerDay, feeBtw2Day);
        avgBetweenTwoDate(transactionPerDay, feeBtw2Day);
        maxBetweenTwoDate(transactionPerDay, feeBtw2Day);
        minBetweenTwoDate(transactionPerDay, feeBtw2Day);
        return new SimulationDTO(feeBtw2Day);
                /*FeeBtw2DateResponseDTO(request.getDateTimeFrom(),
                request.getDateTimeTo(),
                request.getAccountId(),
                feeBtw2Day.getSumFeeBtwDay(),
                feeBtw2Day.getAvgFeeBtw(),
                feeBtw2Day.getTotalSum(),
                feeBtw2Day.getTotalAvg(),
                feeBtw2Day.getNbTransactionPerDay(),
                feeBtw2Day.getTotalNbTransaction());*/
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
        nbTransactionBetweenTwoDate(transactionPerDay, feeBtw2DayType);
        sumBetweenTwoDate(transactionPerDay, feeBtw2DayType);
        avgBetweenTwoDate(transactionPerDay, feeBtw2DayType);
        minBetweenTwoDate(transactionPerDay, feeBtw2DayType);
        maxBetweenTwoDate(transactionPerDay, feeBtw2DayType);
        simulatedMap.put(contract.name(), new SimulationDTO(feeBtw2DayType));
    }
}
