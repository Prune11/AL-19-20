package fr.unice.polytech.credirama.bank.cli.command;

import fr.unice.polytech.credirama.bank.cli.entities.Client;
import fr.unice.polytech.credirama.bank.cli.entities.Contract;
import fr.unice.polytech.credirama.bank.cli.service.AccountAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AdministrationCommands {

    @Autowired
    private AccountAdministrationService accountAdministrationService;

    @ShellMethod("Create a account for a client")
    public String createAccount(@ShellOption({"-cl"}) int clientId, @ShellOption({"-c"}) String contract) {
        Contract c = Contract.valueOf(contract);
        accountAdministrationService.createAccount(clientId, c);
        return "Account successfully created for client " + clientId + " with contract " + contract;
    }

    @ShellMethod("Create a client")
    public String createClient(@ShellOption({"-n", "-name"}) String clientName) {
        Client client = accountAdministrationService.createClient(clientName);
        return client.toString();
    }

    @ShellMethod("Get an overview of an account")
    public String getAccount(@ShellOption({"-cl"}) int clientId) {
        return accountAdministrationService.getAccount(clientId).toString();
    }

    @ShellMethod("Get all account")
    public String getAccounts() {
        return accountAdministrationService.getAccounts().toString();
    }

    @ShellMethod("Update contract type")
    public String updateContract(@ShellOption({"-a"}) int accountId, @ShellOption({"-c"}) String contract) {
        Contract c = Contract.valueOf(contract);
        return accountAdministrationService.updateContract(accountId, c).toString();
    }
}
