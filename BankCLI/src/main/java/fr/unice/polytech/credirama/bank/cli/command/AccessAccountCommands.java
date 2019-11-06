package fr.unice.polytech.credirama.bank.cli.command;

import fr.unice.polytech.credirama.bank.cli.service.CrediramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class AccessAccountCommands {

    @Autowired
    private CrediramaService crediramaService;

    @ShellMethod(value = "Show the list of operations", key = "operations")
    public String showOperations(@ShellOption(value = {"-k", "-key", "--account-key"}, help = "Account Id") int accountKey) {
        List transaction = crediramaService.getTransaction(accountKey);
        return "The account " + accountKey + " has " + transaction.toString() + " of operations";
    }

    @ShellMethod(value = "Show the balance of the account", key = "balance")
    public String showBalance(@ShellOption(value = {"-k", "-key", "--account-key"}, help = "Account Id") int accountKey) {
        double balance = crediramaService.showBalance(accountKey);
        return "The account " + accountKey + " has a balance of " + balance + "dkk";
    }

    @ShellMethod("Show the contract for an account")
    public String showContract(@ShellOption({"-k", "-key", "--account-key"}) int accountKey) {
        return "The account " + accountKey + " has the following contract : " + crediramaService.getContract(accountKey);
    }

    @ShellMethod("Make a transaction from an account ot another account with specifice amount of money")
    public String makeTransaction(int originAccountKey, int destinationAccountKey, double amount, String type) {
        crediramaService.makeTransaction(originAccountKey, destinationAccountKey, amount, type);
        return "A trasaction from account " + originAccountKey + " to account " + destinationAccountKey + " with " + amount + "dkk has been made";
    }

}
