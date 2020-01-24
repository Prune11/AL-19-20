package fr.unice.polytech.credirama.bank.cli.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.credirama.bank.cli.entities.dto.PrettyDumpResponse;
import fr.unice.polytech.credirama.bank.cli.service.CrediramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@ShellComponent
public class PrettyDumpCommands {

    @Autowired
    private CrediramaService crediramaService;
    
    @ShellMethod(value = "Get a pretty dump of the whole system", key = "dump")
    public String prettyDump(@ShellOption(value = {"-p", "--pretty-print"}, help = "Pretty print Json") boolean beautify,
                             @ShellOption(value = {"-s", "--save"}, help = "Save pretty dump in a json file") boolean save) throws JsonProcessingException {
        String dump = crediramaService.prettyDump();
        String result = new ObjectMapper().writeValueAsString(dump);
        if (beautify) {
            result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(dump);
        }
        if (save) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(Calendar.getInstance().getTime().toString() + ".json", true))) {
                writer.print(result);
                return "The pretty dump json file is created : \n" + result;
            } catch (IOException e) {
                // ... handle IO exception
                e.printStackTrace();
            }
        }
        return result;
    }

}
