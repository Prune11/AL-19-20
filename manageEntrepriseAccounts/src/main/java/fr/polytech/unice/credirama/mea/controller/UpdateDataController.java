package fr.polytech.unice.credirama.mea.controller;

import fr.polytech.unice.credirama.mea.component.ManageEnterpriseAccount;
import fr.polytech.unice.credirama.mea.entities.dto.MEAAddTransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.math.RoundingMode;
import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/transaction", produces = "application/json")
public class UpdateDataController {

    @Qualifier("manageEnterpriseAccount")
    @Autowired
    private ManageEnterpriseAccount manageEnterpriseAccount;


    @PostMapping("/add")
    public Double addTransaction(@RequestBody MEAAddTransactionRequest transactionRequest) {
        return new BigDecimal(this.manageEnterpriseAccount.addTransaction(transactionRequest)).setScale(2, RoundingMode.HALF_UP).doubleValue();

    }

}
