package fr.unice.polytech.credirama.bank.cli.command;

import fr.unice.polytech.credirama.bank.cli.entities.Account;
import fr.unice.polytech.credirama.bank.cli.entities.Client;
import fr.unice.polytech.credirama.bank.cli.entities.Contract;
import fr.unice.polytech.credirama.bank.cli.service.CrediramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AdministrationCommands {

    @Autowired
    private CrediramaService crediramaService;

    @ShellMethod(value = "Create a account for a client", key = "create-account")
    public String createAccount(@ShellOption(value = {"-cl", "--clientId"}, help = "The client Id") int clientId,
                                @ShellOption(value = {"-c", "--contract"}, help = "The original contract for this account, possible values : WOOD, STONE, IRON, DIAMOND") String contract) {
        Contract c = Contract.valueOf(contract);
        crediramaService.createAccount(clientId, c);
        return "Account successfully created for client " + clientId + " with contract " + contract + " : " + c.getFee();
    }

    @ShellMethod(value = "Create a client", key = "create-client")
    public String createClient(@ShellOption(value = {"-n", "--name"}, help = "The name of the new client") String clientName) {
        Client client = crediramaService.createClient(clientName);
        return "The client " + clientName + " has been successfully created";
    }

    @ShellMethod(value = "Get an overview of an account", key = "get-account")
    public String getAccount(@ShellOption(value = {"-cl", "--clientId"}, help = "The client Id") int clientId) {
        return crediramaService.getAccount(clientId).toString();
    }

    @ShellMethod(value = "Get all account", key = "get-accounts")
    public String getAccounts() {
        return crediramaService.getAccounts().toString();
    }

    @ShellMethod(value = "Update contract type", key = "update-contract")
    public String updateContract(@ShellOption(value = {"-a", "--accountId"}, help = "The Id of the account you wish to change contract") int accountId,
                                 @ShellOption(value = {"-c", "--contract"}, help = "The new contract, possible values : WOOD, STONE, IRON, DIAMOND") String contract) {
        Contract before = crediramaService.getContract(accountId);
        Contract c = Contract.valueOf(contract);
        Account a = crediramaService.updateContract(accountId, c);
        return "The account " + accountId + " of " + a.getOwner().getName() + "has change contract from " + before + " to " + c;
    }

    @ShellMethod("Get client by Id")
    public String getClient(@ShellOption(value = {"-cl", "--clientId"}, help = "The client Id") int clientId) {
        return crediramaService.getClient(clientId).toString();
    }

    @ShellMethod("Get all clients")
    public String getClients() {
        return crediramaService.getAllClient().toString();
    }

    @ShellMethod("Update the owner of the account")
    public String updateOwner(@ShellOption(value = {"-a", "--accountId"}, help = "The account Id") int accountId, @ShellOption({"-c"}) Client client) {
        return crediramaService.updateOwner(accountId, client).toString();
    }

    @ShellMethod("Delete an account")
    public String deleteAccount(@ShellOption(value = {"-a", "--accountId"}, help = "The account Id") int accountId) {
        crediramaService.deleteAccount(accountId);
        return "Done";
    }

    @ShellMethod("Delete all accounts")
    public String deleteAccounts() {
        crediramaService.deletetAllAccount();
        return "Done";
    }

    @ShellMethod("Delete a client")
    public String deleteClient(@ShellOption(value = {"-cl", "--clientId"}, help = "The client Id") int clientId) {
        crediramaService.deleteClient(clientId);
        return "Done";
    }

    @ShellMethod("Delete all clients")
    public String deleteClients() {
        crediramaService.deleteAllClient();
        return "Done";
    }
}
