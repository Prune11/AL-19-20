package fr.unice.polytech.credirama.merchant.cli.command;

import fr.unice.polytech.credirama.merchant.cli.entity.dto.analyse.contract.Contract;
import fr.unice.polytech.credirama.merchant.cli.service.CrediramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AccessAccountCommands {

    @Autowired
    private CrediramaService crediramaService;

    @ShellMethod(value = "Show the balance of the account", key = "balance")
    public String showBalance(@ShellOption(value = {"-a", "--accountId"}, help = "The account Id") int accountKey) {
        double balance = crediramaService.showBalance(accountKey);
        return "The account " + accountKey + " has a balance of " + balance + "dkk";
    }

    @ShellMethod("Show the contract for an account")
    public String showContract(@ShellOption(value = {"-a", "--accountId"}, help = "The account Id") int accountKey) {
        Contract contract = crediramaService.getContract(accountKey);
        return "The account " + accountKey + " has the following contract : " + contract.name();
    }




}
