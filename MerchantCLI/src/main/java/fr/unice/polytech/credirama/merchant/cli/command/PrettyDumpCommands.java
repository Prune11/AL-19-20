package fr.unice.polytech.credirama.merchant.cli.command;

import fr.unice.polytech.credirama.merchant.cli.service.CrediramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class PrettyDumpCommands {

    @Autowired
    private CrediramaService crediramaService;

    @ShellMethod(value = "Get a pretty dump of the whole system", key = "dump")
    public String prettyDump(@ShellOption(value = {"-b"}, help = "Beautify Json") boolean beautify) {
        return crediramaService.prettyDump();
    }

}
