package fr.polytech.unice.creadirama.analyse.controller;

import fr.polytech.unice.creadirama.analyse.component.AnalyseData;
import fr.polytech.unice.creadirama.analyse.dto.*;
import fr.polytech.unice.creadirama.analyse.entity.FeeBtw2Day;
import fr.polytech.unice.creadirama.analyse.entity.Transaction;
import fr.polytech.unice.creadirama.analyse.service.TransactionService;
import gherkin.deps.com.google.gson.Gson;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path = "/analyse", produces = "application/json")
public class AnalyseDataController {

    @Autowired
    private AnalyseData analyseData;

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/fees/day", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public FeeResponseDTO sumFeePerDat(/*@Valid @RequestBody*/ FeeRequestDTO request) {
        List<Transaction> transactions = transactionService.getTransactionsFor1Day(request.getDateTime(), request.getAccountId());
        double sum = analyseData.sumFeesPerDay(transactions);
        double avg = analyseData.avgFeePerDay(transactions);
        return new FeeResponseDTO(request.getDateTime(), request.getAccountId(), sum, avg, transactions.size());
    }

    @PostMapping(value = "/fees/btw/day", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public FeeBtw2DateResponseDTO sumFeeBtw2Date(/*@Valid @RequestBody*/ FeeBtw2DateRequestDTO request) {
        Map<String, List<Transaction>> response = transactionService.getTransactionBtw2Day(request.getDateTimeFrom(), request.getDateTimeTo(), request.getAccountId());
        Map<DateTime, List<Transaction>> transactionPerDay = new TransactionsBtw2DatesResponse(response).toDateTime();
        FeeBtw2Day feeBtw2Day = new FeeBtw2Day();
        analyseData.avgBetweenTwoDate(transactionPerDay, feeBtw2Day);
        analyseData.sumBetweenTwoDate(transactionPerDay, feeBtw2Day);
        analyseData.minBetweenTwoDate(transactionPerDay, feeBtw2Day);
        analyseData.maxBetweenTwoDate(transactionPerDay, feeBtw2Day);
        analyseData.nbTransactionPerDay(transactionPerDay, feeBtw2Day);
        return new FeeBtw2DateResponseDTO(request.getDateTimeFrom(),
                request.getDateTimeTo(),
                request.getAccountId(),
                feeBtw2Day.getSumFeeBtwDay(),
                feeBtw2Day.getAvgFeeBtw(),
                feeBtw2Day.getTotalSum(),
                feeBtw2Day.getTotalAvg(),
                feeBtw2Day.getNbTransactionPerDay(),
                feeBtw2Day.getTotalNbTransaction());
    }

    @PostMapping(value = "/simulation", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map<String, SimulationDTO> feesWithOtherContracts(/*@Valid @RequestBody*/ FeeBtw2DateRequestDTO request) {
        Map<String, List<Transaction>> response = transactionService.getTransactionBtw2Day(request.getDateTimeFrom(), request.getDateTimeTo(), request.getAccountId());
        Map<DateTime, List<Transaction>> transactions = new TransactionsBtw2DatesResponse(response).toDateTime();
        return analyseData.simulationWithAnotherContract(transactions);
    }

    @GetMapping("/test")
    public void test() {
        DateTime dateTime = DateTime.now();
        System.out.println(transactionService.getTransactionBtw2Day(dateTime, dateTime, 1));
    }

}
