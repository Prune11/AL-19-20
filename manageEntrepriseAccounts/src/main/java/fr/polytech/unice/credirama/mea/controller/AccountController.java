package fr.polytech.unice.credirama.mea.controller;

import fr.polytech.unice.credirama.mea.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(path = "/accounts", produces = "application/json")
public class AccountController {

    @Autowired
    AccountRepo accountRepo;

    @GetMapping("/hello")
    public String hello() {
        return "World";
    }

}

