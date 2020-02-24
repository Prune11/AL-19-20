package fr.unice.polytech.credirama.merchant.cli.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.credirama.merchant.cli.entity.dto.analyse.FeeBtw2DateRequestDTO;
import fr.unice.polytech.credirama.merchant.cli.entity.dto.analyse.SimulationDTO;
import fr.unice.polytech.credirama.merchant.cli.entity.dto.analyse.SimulationResponseDTO;
import fr.unice.polytech.credirama.merchant.cli.service.CrediramaService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@ShellComponent
public class AnalyseDataCommands {

    @Autowired
    private CrediramaService crediramaService;

    @ShellMethod(value = "Get stats for transaction between two date", key = "stats")
    public String statsForTransaction(@ShellOption(value = {"-a", "--accountId"}, help = "The account Id") int accountKey,
                             @ShellOption(value = {"-from"}, help = "The date from which you want stats (format : MM/dd/yyyy HH:mm:ss)") String fromDate,
                             @ShellOption(value = {"-to"}, help = "The date to which you want stats (format : MM/dd/yyyy HH:mm:ss)") String toDate) throws JsonProcessingException {
        FeeBtw2DateRequestDTO requestDTO = new FeeBtw2DateRequestDTO();
        requestDTO.setAccountId(accountKey);
        requestDTO.setFrom(DateTime.parse(fromDate, DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")).toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")));
        requestDTO.setTo(DateTime.parse(toDate, DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")).toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")));
        SimulationDTO dump = crediramaService.getFeeBtw2Date(requestDTO);

        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(dump);
    }

    @ShellMethod(value = "Get simulation for all contract between two date", key = "simulation")
    public String simulation(@ShellOption(value = {"-a", "--accountId"}, help = "The account Id") int accountKey,
                             @ShellOption(value = {"-from"}, help = "The date from which you want stats (format : MM/dd/yyyy HH:mm:ss)") String fromDate,
                             @ShellOption(value = {"-to"}, help = "The date to which you want stats (format : MM/dd/yyyy HH:mm:ss)") String toDate) throws JsonProcessingException {
        FeeBtw2DateRequestDTO requestDTO = new FeeBtw2DateRequestDTO();
        requestDTO.setAccountId(accountKey);
        requestDTO.setFrom(DateTime.parse(fromDate, DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")).toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")));
        requestDTO.setTo(DateTime.parse(toDate, DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")).toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")));
        SimulationResponseDTO dump = crediramaService.getSimulation(requestDTO);

        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(dump);
    }
}
