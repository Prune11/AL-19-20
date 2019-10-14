package fr.polytech.unice.credirama.mea;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(path = "/test", produces = "application/json")
public class TestController {

    @GetMapping
    public String hello(@PathVariable(name = "hello") String hello) {
        return "World";
    }

}

