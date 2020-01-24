package fr.polytech.unice.credirama.prettydump.component.controller;

import fr.polytech.unice.credirama.prettydump.component.PrettyDump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/prettyDump", produces = "application/json")
public class PrettyDumpController {

    @Autowired
    private PrettyDump prettyDump;

    @GetMapping("")
    public String getPrettyDump() {
        return this.prettyDump.getPrettyDump();
    }

}
