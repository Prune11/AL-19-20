package fr.unice.polytech.credirama.bank.cli.command;

import fr.unice.polytech.credirama.bank.cli.service.CrediramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class PrettyDumpCommands {

    @Autowired
    private CrediramaService crediramaService;

    @ShellMethod(value = "Get a pretty dump of the whole system", key = "dump")
    public String prettyDump(@ShellOption(value = {"-p", "--pretty-print"}, help = "Pretty print Json") boolean beautify,
                             @ShellOption(value = {"-s", "--save"}, help = "Save pretty dump in a json file") boolean save) {
        String dump = crediramaService.prettyDump();
        if (beautify) {

        }
        return dump;
    }

}
