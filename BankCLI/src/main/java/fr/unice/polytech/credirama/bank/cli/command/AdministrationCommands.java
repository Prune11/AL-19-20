package fr.unice.polytech.credirama.bank.cli.command;

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
    public String createAccount(@ShellOption(value = {"-cl", "--clientId"}, help = "The client ID") int clientId,
                                @ShellOption(value = {"-c", "--contract"}, help = "The original contract for this account") String contract) {
        Contract c = Contract.valueOf(contract);
        crediramaService.createAccount(clientId, c);
        return "Account successfully created for client " + clientId + " with contract " + contract;
    }

    @ShellMethod(value = "Create a client", key = "create-client")
    public String createClient(@ShellOption(value = {"-n", "--name"}, help = "The name of the new client") String clientName) {
        Client client = crediramaService.createClient(clientName);
        return client.toString();
    }

    @ShellMethod(value = "Get an overview of an account", key = "get-account")
    public String getAccount(@ShellOption(value = {"-cl", "--clientId"}, help = "The client ID") int clientId) {
        return crediramaService.getAccount(clientId).toString();
    }

    @ShellMethod(value = "Get all account", key = "get-accounts")
    public String getAccounts() {
        return crediramaService.getAccounts().toString();
    }

    @ShellMethod(value = "Update contract type", key = "update-contract")
    public String updateContract(@ShellOption(value = {"-a", "--accountId"}, help = "The Id of the account you wish to change contract") int accountId,
                                 @ShellOption(value = {"-c"}, help = "The new contract") String contract) {
        Contract c = Contract.valueOf(contract);
        return crediramaService.updateContract(accountId, c).toString();
    }

    @ShellMethod("Get client by Id")
    public String getClient(@ShellOption({"-cl"}) int clientId) {
        return crediramaService.getClient(clientId).toString();
    }

    @ShellMethod("Get all clients")
    public String getClients() {
        return crediramaService.getAllClient().toString();
    }

    @ShellMethod("Update the owner of the account")
    public String updateOwner(@ShellOption({"-a"}) int accountId, @ShellOption({"-c"}) Client client) {
        return crediramaService.updateOwner(accountId, client).toString();
    }

    public String deleteAccount(@ShellOption({"-a"}) int accountId) {
        crediramaService.deleteAccount(accountId);
        return "Done";
    }

    public String deleteAccounts() {
        crediramaService.deletetAllAccount();
        return "Done";
    }

    public String deleteClient(@ShellOption({"-cl"}) int clientId) {
        crediramaService.deleteClient(clientId);
        return "Done";
    }

    public String deleteClients() {
        crediramaService.deleteAllClient();
        return "Done";
    }
}
