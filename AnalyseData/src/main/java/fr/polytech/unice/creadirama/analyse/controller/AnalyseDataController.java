package fr.polytech.unice.creadirama.analyse.controller;

import fr.polytech.unice.creadirama.analyse.component.AnalyseData;
import fr.polytech.unice.creadirama.analyse.dto.*;
import fr.polytech.unice.creadirama.analyse.entity.FeeBtw2Day;
import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import fr.polytech.unice.creadirama.analyse.service.TransactionService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/analyse")
public class AnalyseDataController {

    @Autowired
    private AnalyseData analyseData;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/fees/day")
    public FeeResponseDTO sumFeePerDat(@Valid @RequestBody FeeRequestDTO request) {
        List<Transaction> transactions = transactionService.getTransactionsFor1Day(request.getDate(), request.getAccountId());
        double sum = analyseData.sumFeesPerDay(transactions);
        double avg = analyseData.avgFeePerDay(transactions);
        FeeResponseDTO response = new FeeResponseDTO(request.getDate(), request.getAccountId(), sum, avg, transactions.size());
        return response;
    }

    @PostMapping("/fees/btw/day")
    public FeeBtw2DateResponseDTO sumFeeBtw2Date(@Valid @RequestBody FeeBtw2DateRequestDTO request) {
        Map<String, List<Transaction>> response = transactionService.getTransactionBtw2Day(request.getFrom(), request.getTo(), request.getAccountId());
        Map<DateTime, List<Transaction>> transactionPerDay = new TransactionsBtw2DatesResponse(response).toDateTime();
        FeeBtw2Day feeBtw2Day = new FeeBtw2Day();
        analyseData.avgBetweenTwoDate(transactionPerDay, feeBtw2Day);
        analyseData.sumBetweenTwoDate(transactionPerDay, feeBtw2Day);
        analyseData.minBetweenTwoDate(transactionPerDay, feeBtw2Day);
        analyseData.maxBetweenTwoDate(transactionPerDay, feeBtw2Day);
        FeeBtw2DateResponseDTO result = new FeeBtw2DateResponseDTO(request.getFrom(),
                request.getTo(),
                request.getAccountId(),
                feeBtw2Day.getSumFeeBtwDay(),
                feeBtw2Day.getAvgFeeBtw(),
                feeBtw2Day.getTotalSum(),
                feeBtw2Day.getTotalAvg(),
                feeBtw2Day.getNbTransactionPerDay(),
                feeBtw2Day.getTotalNbTransaction());
        return result;
    }

    @GetMapping("/simulation")
    public List<FeeBtw2Day> feesWithOtherContracts(@Valid @RequestBody FeeBtw2DateRequestDTO request) {
        Map<String, List<Transaction>> response = transactionService.getTransactionBtw2Day(request.getFrom(), request.getTo(), request.getAccountId());
        Map<DateTime, List<Transaction>> transactions = new TransactionsBtw2DatesResponse(response).toDateTime();
        return analyseData.simulationWithAnotherContract(transactions);
    }

    @GetMapping("/test")
    public void test() {
        DateTime dateTime = DateTime.now();
        System.out.println(transactionService.getTransactionBtw2Day(dateTime, dateTime, 1));
    }

}
