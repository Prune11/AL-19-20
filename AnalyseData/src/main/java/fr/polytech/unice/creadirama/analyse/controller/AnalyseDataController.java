package fr.polytech.unice.creadirama.analyse.controller;

import fr.polytech.unice.creadirama.analyse.component.AnalyseData;
import fr.polytech.unice.creadirama.analyse.dto.FeeBtw2DateRequestDTO;
import fr.polytech.unice.creadirama.analyse.dto.FeeBtw2DateResponseDTO;
import fr.polytech.unice.creadirama.analyse.dto.FeeRequestDTO;
import fr.polytech.unice.creadirama.analyse.dto.FeeResponseDTO;
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
        ArrayList<Transaction> transactions = transactionService.getTransactionsFor1Day(request.getDate());
        double sum = analyseData.sumFeesPerDay(transactions);
        double avg = analyseData.avgFeePerDay(transactions);
        FeeResponseDTO response = new FeeResponseDTO(request.getDate(), request.getAccountId(), sum, avg, transactions.size());
        return response;
    }

    @PostMapping("/fees/btw/day")
    public FeeBtw2DateResponseDTO sumFeeBtw2Date(@Valid @RequestBody FeeBtw2DateRequestDTO request) {
        Map<DateTime, ArrayList<Transaction>> transactionPerDay = transactionService.getTransactionBtw2Day(request.getFrom(), request.getTo());
        FeeBtw2Day feeBtw2Day = analyseData.sumBetweenTwoDate(transactionPerDay);
        FeeBtw2DateResponseDTO response = new FeeBtw2DateResponseDTO(request.getFrom(),
                request.getTo(),
                request.getAccountId(),
                feeBtw2Day.getSumFeeBtwDay(),
                feeBtw2Day.getAvgFeeBtw(),
                0,
                0,
                new HashMap<>(),
                0);
        return response;
    }
}
