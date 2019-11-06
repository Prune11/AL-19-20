package fr.unice.polytech.credirama.bank.cli.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Example CommandLineRunner that shows how overall shell behavior can be customized. In
 * this particular example, any program (process) arguments are assumed to be shell
 * commands that need to be executed (and the shell then quits).
 */
@Order(InteractiveShellApplicationRunner.PRECEDENCE - 2)
public class BankCommandLineRunner implements CommandLineRunner {

    private Shell shell;

    private final ConfigurableEnvironment environment;

    public BankCommandLineRunner(Shell shell, ConfigurableEnvironment environment) {
        this.shell = shell;
        this.environment = environment;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> commandsToRun = new ArrayList<>();
        for (String arg : args) {
            if (!arg.startsWith("@")) {
                commandsToRun.add(arg);
            }
        }
        if (!commandsToRun.isEmpty()) {
            InteractiveShellApplicationRunner.disable(environment);
            shell.run(new StringInputProvider(commandsToRun));
        }
    }
}