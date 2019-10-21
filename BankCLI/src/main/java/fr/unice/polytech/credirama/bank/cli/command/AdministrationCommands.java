package fr.unice.polytech.credirama.bank.cli.command;

import fr.unice.polytech.credirama.bank.cli.entities.Client;
import fr.unice.polytech.credirama.bank.cli.service.AdministrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AdministrationCommands {

//    @Autowired
//    private AdministrateService administrateService;

    @ShellMethod("Create a account for a company")
    public String createAccount(@ShellOption String companyName) {
//        Client client =  administrateService.createAccount(companyName);
        return "Account successfully created for client " + companyName;
    }

}
