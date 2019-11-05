package fr.unice.polytech.credirama.marchant.cli.command;

import fr.unice.polytech.credirama.marchant.cli.entity.Transaction;
import fr.unice.polytech.credirama.marchant.cli.service.AccessAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class AccountCommands {

    @Autowired
    private AccessAccountService accessAccountService;

    @ShellMethod(value = "Withdraw money from account", key = "withdraw")
    public String withdrawMoney(@ShellOption({"-k", "-key", "--account-key"}) String accountKey,
                                @ShellOption({"-a", "-amount"}) double amount) {
        return amount + "dkk withdraw successfully from account " + accountKey;
    }

    @ShellMethod(value = "Show the list of operations", key = "operations")
    public String showOperations(@ShellOption({"-k", "-key", "--account-key"}) String accountKey) {
        List<Transaction> operations = accessAccountService.getOperations(accountKey);
        return "The account " + accountKey + " has " + operations.toString() + " of operations";
    }

    @ShellMethod(value = "Show the balance of the account", key = "balance")
    public String showBalance(@ShellOption({"-k", "-key", "--account-key"}) String accountKey) {
        double balance = accessAccountService.showBalance(accountKey);
        return "The account " + accountKey + " has a balance of " + balance + "dkk";
    }

}
