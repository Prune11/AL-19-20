package fr.unice.polytech.credirama.merchant.cli.command;

import fr.unice.polytech.credirama.merchant.cli.entity.Transaction;
import fr.unice.polytech.credirama.merchant.cli.entity.TransactionType;
import fr.unice.polytech.credirama.merchant.cli.service.CrediramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class TransactionCommands {

    @Autowired
    private CrediramaService crediramaService;

    @ShellMethod(value = "Show the list of operations", key = "operations")
    public String showOperations(@ShellOption(value = {"-a", "--accountId"}, help = "The account Id") int accountKey) {
        List transaction = crediramaService.getTransaction(accountKey);
        return "The account " + accountKey + " has " + transaction.toString() + " of operations";
    }

    @ShellMethod(value = "Make a transaction from an account ot another account with specific amount of money", key = "transaction")
    public String makeTransaction(@ShellOption(value = {"-f", "--from"}, help = "The origin account of the transaction") int originAccountKey,
                                  @ShellOption(value = {"-t", "--to"}, help = "The destination account of the transaction") int destinationAccountKey,
                                  @ShellOption(value = {"-a", "--amount"}, help = "The amount for this transaction") double amount,
                                  @ShellOption(value = {"-T", "--type"}, help = "The type for this transaction, possible values : CREDIT_CARD, DEBIT_CARD, TRANSFER") String type) {
        TransactionType transactionType = TransactionType.valueOf(type);
        Transaction transaction = crediramaService.makeTransaction(originAccountKey, destinationAccountKey, amount, transactionType);
        return "A " + type + "  transaction from account " + originAccountKey + " to account " + destinationAccountKey + " with " + amount + " dkk " + "and fees " + transaction.getFeeAmount() + " has been made : " + transaction.toString();
    }

    @ShellMethod(value = "Get all the fee for all transaction", key = "fees")
    public double getTotalFees(@ShellOption(value = {"-a", "--accountId"}, help = "The account Id") int accountId) {
        return this.crediramaService.getTotalFees(accountId);
    }
}
