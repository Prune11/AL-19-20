package fr.polytech.unice.credirama.mea.controller;

import fr.polytech.unice.credirama.mea.component.ManageEnterpriseAccount;
import fr.polytech.unice.credirama.mea.entities.contract.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/access", produces = "application/json")
public class AccountAccessController {

    @Qualifier("manageEnterpriseAccount")
    @Autowired
    private ManageEnterpriseAccount manageEnterpriseAccount;

    @GetMapping("/contract/{id}")
    public Contract getContract(@PathVariable(name = "id") Integer id) {
        return this.manageEnterpriseAccount.getContractById(id);
    }

    @GetMapping("/balance/{id}")
    public double getBalance(@PathVariable(name = "id") Integer id) {
        return this.manageEnterpriseAccount.getBalanceById(id);
    }


}
