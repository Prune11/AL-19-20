package fr.polytech.unice.credirama.mea.controller;

import fr.polytech.unice.credirama.mea.component.PrettyDump;
import fr.polytech.unice.credirama.mea.entities.dto.PrettyDumpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/prettyDump", produces = "application/json")
public class PrettyDumpController {

    @Autowired
    private PrettyDump prettyDump;

    @GetMapping
    public PrettyDumpResponse getPrettyDump() {
        return this.prettyDump.getPrettyDump();
    }

}
