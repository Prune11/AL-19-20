package fr.unice.polytech.credirama.bank.cli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class BankCLI {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BankCLI.class, args);
    }
}
